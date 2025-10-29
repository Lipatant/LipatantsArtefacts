import copy
import json
from item_modifier.item_modifier import ItemModifier
from item_modifier.item_modifier_list import ItemModifierList
from math import floor
from save_manager import save_loot_table

COMPONENT_ATTRIBUTE_MODIFIERS = "minecraft:attribute_modifiers"
COMPONENT_CONSUMABLE = "minecraft:consumable"
COMPONENT_CUSTOM_MODEL_DATA = "minecraft:custom_model_data"
COMPONENT_ENCHANTABLE = "minecraft:enchantable"
COMPONENT_ITEM_MODEL = "minecraft:item_model"
COMPONENT_ITEM_NAME = "minecraft:item_name"
COMPONENT_LORE = "minecraft:lore"
COMPONENT_MAX_DAMAGE = "minecraft:max_damage"
COMPONENT_RARITY = "minecraft:rarity"
FUNCTION = "function"
FUNCTION_REFERENCE = "minecraft:reference"
FUNCTION_SET_ATTRIBUTES = "minecraft:set_attributes"
FUNCTION_SET_COMPONENTS = "minecraft:set_components"
FUNCTION_SET_ENCHANTMENTS = "minecraft:set_enchantments"
FUNCTION_SET_LORE = "minecraft:set_lore"

class Item:
    pass

class Item:

    def __init__(self, data: dict = {}):
        self.attributes = []
        self.attributes_keep = False
        self.components = {}
        self.enchantments = {}
        self.identifier = ""
        self.inherits = "minecraft:stone"
        self.item_modifiers = []
        self.on_consume_effects = []
        self.recipe_smithing_upgrades = []
        self.slot = "any"
        self.variants = []
        self.load(data)

    def duplicate(self) -> Item:
        item = Item()
        item.attributes = self.attributes.copy()
        item.attributes_keep = self.attributes_keep
        item.components = self.components.copy()
        item.enchantments = self.enchantments.copy()
        item.identifier = self.identifier
        item.inherits = self.inherits
        item.item_modifiers = self.item_modifiers.copy()
        item.on_consume_effects = self.on_consume_effects.copy()
        item.recipe_smithing_upgrades = self.recipe_smithing_upgrades.copy()
        item.slot = self.slot
        item.variants = self.variants.copy()
        return item

    def get_file_path(self, suffix: str = "") -> str:
        return (self.get_path_str() % (self.identifier.split(":", maxsplit=1)[1] + suffix)) + ".json"

    def get_path(self, suffix: str = "") -> str:
        return self.identifier.split(":", maxsplit=1)[0] + ":" + (self.get_path_str() % (self.identifier.split(":", maxsplit=1)[1] + suffix))

    def get_path_str(self) -> str:
        return "items/%s"

    def get_translate_path(self) -> str:
        return "item." + self.identifier.split(":", maxsplit=1)[0] + "." + self.identifier.split(":", maxsplit=1)[1]

    def load(self, data: dict) -> Item:
        if "attributes" in data:
            for attribute in data["attributes"]:
                self.attributes.append(attribute)
        if "attributes_keep" in data:
            self.attributes_keep = data["attributes_keep"]
        if "components" in data:
            for key, value in data["components"].items():
                self.components[key] = value
        if "enchantments" in data:
            for key, value in data["enchantments"].items():
                self.enchantments[key] = value
        if "id" in data:
            self.identifier = data["id"]
        if "inherits" in data:
            self.inherits = data["inherits"]
        if "item_modifiers" in data:
            for item_modifier in data["item_modifiers"]:
                self.item_modifiers.append(item_modifier)
        if "on_consume_effects" in data:
            for effect in data["on_consume_effects"]:
                self.on_consume_effects.append(effect)
        if "recipe_smithing_upgrades" in data:
            for recipe in data["recipe_smithing_upgrades"]:
                self.recipe_smithing_upgrades.append(recipe)
        if "slot" in data:
            self.slot = data["slot"]
        if "variants" in data:
            for variant in data["variants"]:
                self.variants.append(variant)
        return self

    def save(self) -> None:
        self.save_loot_table()

    def save_loot_table(self) -> bool:
        if self.variants:
            output = True
            if not save_loot_table(self.get_file_path("_base"), self.to_str_loot_table()):
                output = False
            if not save_loot_table(self.get_file_path(), self.to_str_loot_table_variants()):
                output = False
            return output
        else:
            return save_loot_table(self.get_file_path(), self.to_str_loot_table())

    def to_data_loot_table(self) -> dict:
        return {
            "pools": [
                {
                    "entries": [
                        {
                            "functions": self.to_data_loot_table_functions(),
                            "name": self.inherits,
                            "type": "minecraft:item",
                        }
                    ],
                    "rolls": 1,
                }
            ]
        }

    def to_data_loot_table_functions(self) -> list[dict]:
        components = {
            COMPONENT_ITEM_MODEL: self.identifier,
            COMPONENT_ITEM_NAME: {
                "italic": False,
                "translate": self.get_translate_path(),
            },
        }
        lore = []
        for component, value in self.components.items():
            components[component] = value
            if (component == "minecraft:equippable") and ("slot" not in components[component]):
                components[component]["slot"] = self.slot
        if self.on_consume_effects:
            if COMPONENT_CONSUMABLE in components:
                if "on_consume_effects" not in components[COMPONENT_CONSUMABLE]:
                    components[COMPONENT_CONSUMABLE]["on_consume_effects"] = [
                        {
                            "effects": [],
                            "type": "apply_effects",
                        }
                    ]
                for effect in self.on_consume_effects:
                    components[COMPONENT_CONSUMABLE]["on_consume_effects"][-1]["effects"].append(effect)
                    amplifier = int(effect["amplifier"])
                    duration = int(effect["duration"]) / 20
                    lore_line = [
                        {
                            "color": "blue",
                            "italic": False,
                            "translate": "effect.%s" % effect["id"].replace(":", ".")
                        },
                    ]
                    if amplifier > 0:
                        lore_line.append(
                            {
                                "color": "blue",
                                "italic": False,
                                "text": " ",
                            }
                        )
                        lore_line.append(
                            {
                                "color": "blue",
                                "italic": False,
                                "translate": "potion.potency.%s" % amplifier,
                            }
                        )
                    lore_line.append(
                        {
                            "color": "blue",
                            "italic": False,
                            "text": " (%s%s:%s%s)" % (
                                floor(duration / 60 / 10),
                                floor(duration / 60 % 10),
                                floor(duration % 60 / 10),
                                floor(duration % 60 % 10),
                            )
                        }
                    )
                    lore.append(lore_line)
            else:
                raise Exception("'on_consume_effects' requires the {COMPONENT_CONSUMABLE} component")
        functions = []
        if self.attributes:
            attributes: list[dict] = copy.deepcopy(self.attributes)
            attributes_slot_armor: dict[str, str] = {
                "chest": "minecraft:armor.chestplate",
                "feet": "minecraft:armor.boots",
                "legs": "minecraft:armor.leggings",
                "head": "minecraft:armor.helmets",
            }
            for i in range(len(attributes)):
                if "slot" not in attributes[i]:
                    attributes[i]["slot"] = self.slot
                if "id" not in attributes[i]:
                    if (self.slot in attributes_slot_armor.keys()) and (attributes[i]["slot"] in attributes_slot_armor.keys()):
                        attributes[i]["id"] = attributes_slot_armor[attributes[i]["slot"]]
                    else:
                        attributes[i]["id"] = "minecraft:" + attributes[i]["slot"] + "." + attributes[i]["attribute"][len("minecraft:"):]
            attributes.sort(key=lambda e: e.get("attribute", ""))
            functions.append(
                {
                    FUNCTION: FUNCTION_SET_ATTRIBUTES,
                    "modifiers": attributes,
                    "replace": not self.attributes_keep,
                }
            )
        if self.enchantments:
            functions.append(
                {
                    "enchantments": self.enchantments,
                    FUNCTION: FUNCTION_SET_ENCHANTMENTS,
                }
            )
        if lore:
            functions.append(
                {
                    FUNCTION: FUNCTION_SET_LORE,
                    "lore": lore,
                    "mode": "insert",
                }
            )
        functions.sort(key=lambda e: e.get("function", ""))
        functions.insert(
            0,
            {
                "components": components,
                FUNCTION: FUNCTION_SET_COMPONENTS,
            },
        )
        item_modifiers = self.item_modifiers.copy()
        item_modifiers.sort()
        for item_modifier in item_modifiers:
            functions.append(
                {
                    FUNCTION: FUNCTION_REFERENCE,
                    "name": item_modifier,
                }
            )
        return functions

    def to_data_loot_table_variants(self) -> dict:
        entries = []
        for variant in self.variants:
            entry = {
                "type": "minecraft:loot_table",
                "value": self.get_path("_base"),
            }
            if variant:
                entry["functions"] = [
                    {
                        "function": "minecraft:set_components",
                        "components": {
                            COMPONENT_CUSTOM_MODEL_DATA: {
                                "strings": [
                                    variant
                                ]
                            }
                        },
                    }
                ]
            entries.append(entry)
        return {
            "pools": [
                {
                    "entries": entries,
                    "rolls": 1,
                }
            ]
        }

    def to_data_nbt(self, item_modifier_list: ItemModifierList = None) -> dict:
        components: dict = {}
        functions: list[dict] = self.to_data_loot_table_functions()
        output: dict = {
            "id": self.inherits,
        }
        for function in functions:
            type: str = function.get(FUNCTION)
            if type == FUNCTION_SET_COMPONENTS:
                components: dict[str, any] = function.get("components", {})
                if components:
                    for key, value in components.items():
                        components[key] = value
        for function in functions:
            type: str = function.get(FUNCTION)
            if type == FUNCTION_REFERENCE:
                pass
            elif type == FUNCTION_SET_ATTRIBUTES:
                modifiers: list[dict] = function.get("modifiers", [])
                if modifiers:
                    if COMPONENT_ATTRIBUTE_MODIFIERS not in components:
                        components[COMPONENT_ATTRIBUTE_MODIFIERS] = []
                    for modifier in modifiers:
                        attribute_modifier: dict = modifier.copy()
                        attribute_modifier["type"] = attribute_modifier.get("attribute", "")
                        attribute_modifier.pop("attribute")
                        components[COMPONENT_ATTRIBUTE_MODIFIERS].append(attribute_modifier)
            elif type == FUNCTION_SET_COMPONENTS:
                pass
            else:
                raise Exception("Unknown function '%s' in Item '%s'" % (type, self.get_path()))
        if components:
            output["components"] = components
        for function in functions:
            type: str = function.get(FUNCTION)
            if type == FUNCTION_REFERENCE:
                name: str = function.get("name", "")
                if not name:
                    continue
                if item_modifier_list and (name, item_modifier_list):
                    item_modifier: ItemModifier = item_modifier_list.get(name)
                    if item_modifier:
                        item_modifier.modify_item_data(output)
                        continue
                raise Exception("Item '%s' cannot access ItemModifier '%s'" % (self.get_path(), name))
        return output

    def to_str_loot_table(self) -> str:
        return json.dumps(self.to_data_loot_table(), indent=4, sort_keys=True)

    def to_str_loot_table_variants(self) -> str:
        return json.dumps(self.to_data_loot_table_variants(), indent=4, sort_keys=True)