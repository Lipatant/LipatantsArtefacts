import json
from save_manager import save_loot_table

COMPONENT_CUSTOM_MODEL_DATA = "minecraft:custom_model_data"
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

    attributes: list[dict] = []
    attributes_keep: bool = False
    components: dict = {}
    enchantments: dict = {}
    identifier: str = ""
    inherits: str = "minecraft:stone"
    item_modifiers: list[str] = []
    variants: list[str] = []

    def __init__(self, data: dict = {}):
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
        item.variants = self.variants.copy()
        return item

    def get_file_path(self, suffix: str = "") -> str:
        return (self.get_path_str() % (self.identifier + suffix)) + ".json"

    def get_path(self, suffix: str = "") -> str:
        return "lipartefacts:" + (self.get_path_str() % (self.identifier + suffix))

    def get_path_str(self) -> str:
        return "items/%s"

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
            for enchantment in data["enchantments"]:
                self.enchantments.append(enchantment)
        if "id" in data:
            self.identifier = data["id"]
        if "inherits" in data:
            self.inherits = data["inherits"]
        if "item_modifiers" in data:
            for item_modifier in data["item_modifiers"]:
                self.item_modifiers.append(item_modifier)
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
        components = {
            COMPONENT_ITEM_MODEL: "lipartefacts:" + self.identifier,
            COMPONENT_ITEM_NAME: {
                "italic": False,
                "translate": "item.lipartefacts." + self.identifier,
            },
        }
        for component, value in self.components.items():
            components[component] = value
        functions = [
            {
                "components": components,
                FUNCTION: FUNCTION_SET_COMPONENTS,
            }
        ]
        if self.attributes:
            functions.append(
                {
                    FUNCTION: FUNCTION_SET_ATTRIBUTES,
                    "modifiers": self.attributes,
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
        for item_modifier in self.item_modifiers:
            functions.append(
                {
                    FUNCTION: FUNCTION_REFERENCE,
                    "name": item_modifier,
                }
            )
        return {
            "pools": [
                {
                    "entries": [
                        {
                            "functions": functions,
                            "name": self.inherits,
                            "type": "minecraft:item",
                        }
                    ],
                    "rolls": 1,
                }
            ]
        }

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

    def to_str_loot_table(self) -> str:
        return json.dumps(self.to_data_loot_table(), indent=4, sort_keys=True)

    def to_str_loot_table_variants(self) -> str:
        return json.dumps(self.to_data_loot_table_variants(), indent=4, sort_keys=True)

class ItemList(dict[str, Item]):

    def append(self, item: Item) -> str:
        if not item:
            raise Exception("Trying to append a null object to an ItemList")
        path = item.get_path()
        if path in self:
            raise Exception("Item '%s' has already been defined")
        self[path] = item
        return path

    def save(self) -> None:
        for item in self.values():
            item.save()