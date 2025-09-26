import json
from save_manager import save_item_modifier

class ItemModifierBase:

    identifier = "component"

    def __init__(self, identifier):
        self.identifier = identifier

    def get_file_path(self) -> str:
        return self.get_file_path_str() % self.identifier

    def get_file_path_str(self) -> str:
        return "%s.json"

    def get_path(self) -> str:
        return "lipartefacts:" + self.get_file_path()

    def save(self) -> bool:
        return save_item_modifier(self.get_file_path(), self.to_str())

    def to_data(self) -> dict | list:
        return {}

    def to_str(self) -> str:
        return json.dumps(self.to_data(), indent=4)

class ItemModifierList(dict[str, ItemModifierBase]):

    def append(self, item_modifier: ItemModifierBase) -> None:
        path = item_modifier.get_path()
        if path in self:
            raise Exception("Item modifier '%s' has already been defined")
        self[path] = item_modifier

    def save(self) -> None:
        for item_modifier in self.values():
            item_modifier.save()