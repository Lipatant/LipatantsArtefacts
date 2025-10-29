import json
from save_manager import save_item_modifier

class ItemModifierBase:

    def __init__(self, identifier):
        self.identifier = identifier

    def get_file_path(self) -> str:
        return (self.get_path_str() % self.identifier) + ".json"

    def get_path(self) -> str:
        return "lipartefacts:" + (self.get_path_str() % self.identifier)

    def get_path_str(self) -> str:
        return "%s"

    def save(self) -> bool:
        return save_item_modifier(self.get_file_path(), self.to_str())

    def to_data(self) -> dict | list:
        return {}

    def to_str(self) -> str:
        return json.dumps(self.to_data(), indent=4)

class ItemModifierList(dict[str, ItemModifierBase]):

    def append(self, item_modifier: ItemModifierBase) -> str:
        if not item_modifier:
            raise Exception("Trying to append a null object to an ItemModifierList")
        path = item_modifier.get_path()
        if path in self:
            raise Exception("Item modifier '%s' has already been defined" % path)
        self[path] = item_modifier
        return path

    def save(self) -> None:
        for item_modifier in self.values():
            item_modifier.save()