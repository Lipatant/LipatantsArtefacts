from copy import deepcopy
from item import Item, ItemList

class ItemSet:
    pass

class ItemSet:

    def __init__(self, data: dict = {}):
        self.data = {}
        self.identifier = ""
        self.items = {}
        self.load(data)

    def load(self, data: dict) -> ItemSet:
        if "data" in data:
            for key, value in data["data"].items():
                self.data[key] = value
        if "id" in data:
            self.identifier = data["id"]
        if "items" in data:
            for key, value in data["items"].items():
                self.items[key] = value
        return self

    def to_item_data(self, data: dict) -> dict:
        item_data: dict = deepcopy(self.data) | data
        return item_data

    def to_item_data_list(self) -> list[dict]:
        item_data_list: list[dict] = []
        for item_identifier, item_data in self.items.items():
            item_data = self.to_item_data(item_data)
            if "id" not in item_data:
                item_data["id"] = self.identifier + "_" + item_identifier
            item_data_list.append(item_data)
        return item_data_list