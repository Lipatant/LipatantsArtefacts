import json
from save_manager import save_item_modifier

class ItemModifier:

    def __init__(self, identifier):
        self.identifier = identifier

    def get_file_path(self) -> str:
        return (self.get_path_str() % self.identifier) + ".json"

    def get_path(self) -> str:
        return "lipartefacts:" + (self.get_path_str() % self.identifier)

    def get_path_str(self) -> str:
        return "%s"

    def modify_item_data(self, data: dict) -> None:
        pass

    def save(self) -> bool:
        return save_item_modifier(self.get_file_path(), self.to_str())

    def to_data(self) -> dict | list:
        return {}

    def to_str(self) -> str:
        return json.dumps(self.to_data(), indent=4)