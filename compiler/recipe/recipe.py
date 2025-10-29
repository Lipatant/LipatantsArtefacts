import json
from item.item_list import ItemList
from item_modifier.item_modifier_list import ItemModifierList
from save_manager import save_recipe

class Recipe():

    def __init__(self, identifier: str):
        self.identifier = identifier
        self.item_list = None
        self.item_modifier_list = None

    def get_file_path(self, suffix: str = "") -> str:
        return (self.get_path_str() % (self.identifier.split(":", maxsplit=1)[1] + suffix)) + ".json"

    def get_item(self, path: str, force_map: bool = False) -> dict | str:
        if (not path) or path.startswith("minecraft:"):
            return {"id": path} if force_map else path
        if self.item_list and (path in self.item_list):
            return self.item_list[path].to_data_nbt(self.item_modifier_list) if force_map else self.item_list[path].inherits
        raise Exception("Recipe '%s' cannot access Item '%s'" % (self.get_path(), path))

    def get_path(self, suffix: str = "") -> str:
        return self.identifier.split(":", maxsplit=1)[0] + ":" + (self.get_path_str() % (self.identifier.split(":", maxsplit=1)[1] + suffix))

    def get_path_str(self) -> str:
        return "%s"

    def save(self, item_list: ItemList = None, item_modifier_list: ItemModifierList = None) -> bool:
        self.item_list = item_list
        self.item_modifier_list = item_modifier_list
        return save_recipe(self.get_file_path(), self.to_str())

    def to_data(self) -> dict | list:
        return {}

    def to_str(self) -> str:
        return json.dumps(self.to_data(), indent=4, sort_keys=True)
