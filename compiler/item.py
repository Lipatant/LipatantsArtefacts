import json
from save_manager import save_loot_table

COMPONENT_ITEM_MODEL = "minecraft:item_model"
COMPONENT_ITEM_NAME = "minecraft:item_name"
COMPONENT_LORE = "minecraft:lore"
COMPONENT_MAX_DAMAGE = "minecraft:max_damage"
COMPONENT_RARITY = "minecraft:rarity"
FUNCTION = "function"
FUNCTION_REFERENCE = "minecraft:reference"
FUNCTION_SET_ATTRIBUTES = "minecraft:set_attributes"
FUNCTION_SET_COMPONENTS = "minecraft:set_components"
FUNCTION_SET_LORE = "minecraft:set_lore"

class Item:
    pass

class Item:

    attributes: list[dict] = []
    components: dict = {}
    identifier: str = ""
    inherits: str = "minecraft:stone"
    item_modifiers: list[str] = []

    def __init__(self, data: dict = {}):
        self.load(data)

    def duplicate(self) -> Item:
        item = Item()
        item.attributes = self.attributes.copy()
        item.components = self.components.copy()
        item.identifier = self.identifier
        item.inherits = self.inherits
        item.item_modifiers = self.item_modifiers.copy()
        return item

    def get_file_path(self) -> str:
        return (self.get_path_str() % self.identifier) + ".json"

    def get_path(self) -> str:
        return "lipartefacts:" + (self.get_path_str() % self.identifier)

    def get_path_str(self) -> str:
        return "items/%s"

    def load(self, data: dict) -> Item:
        if "attributes" in data:
            for attribute in data["attributes"]:
                self.attributes.append(attribute)
        if "components" in data:
            for key, value in data["components"].items():
                self.components[key] = value
        if "id" in data:
            self.identifier = data["id"]
        if "inherits" in data:
            self.inherits = data["inherits"]
        if "item_modifiers" in data:
            for item_modifier in data["item_modifiers"]:
                self.item_modifiers.append(item_modifier)
        return self

    def save(self) -> None:
        self.save_loot_table()

    def save_loot_table(self) -> bool:
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
                    "replace": True,
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

    def to_str_loot_table(self) -> str:
        return json.dumps(self.to_data_loot_table(), indent=4)

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