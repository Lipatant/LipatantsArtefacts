import json
from item.item import Item
from item.item_list import ItemList
from item_modifier.item_modifier_list import ItemModifierList
from save_manager import save_item_category

class ItemCategory:

    def __init__(self, identifier: str, rule: callable = None):
        self.identifier = identifier
        self.item_list = None
        self.item_modifier_list = None
        self.rule = rule

    def get_file_path(self, prefix: str = "", suffix: str = "") -> str:
        return (self.get_path_str() % (prefix + self.identifier.split(":", maxsplit=1)[1] + suffix)) + ".json"

    def get_path(self, suffix: str = "") -> str:
        return self.identifier.split(":", maxsplit=1)[0] + ":" + (self.get_path_str() % (self.identifier.split(":", maxsplit=1)[1] + suffix))

    def get_path_str(self) -> str:
        return "%s"

    def save(self, item_list: ItemList = None, item_modifier_list: ItemModifierList = None) -> bool:
        self.item_list = item_list
        self.item_modifier_list = item_modifier_list
        return save_item_category(self.get_file_path(prefix="all/"), self.to_str_all())

    def to_data_all(self) -> dict | list:
        pools = []
        if not self.rule:
            raise Exception("ItemCategory '%s' has no 'rule" % self.get_path())
        if self.item_list:
            for item in self.item_list.values():
                if self.rule(item):
                    pools.append(
                        {
                            "entries": [
                                {
                                    "type": "minecraft:loot_table",
                                    "value": item.get_path("_base") if item.variants else item.get_path(),
                                }
                            ],
                            "rolls": 1,
                        }
                    )
            pools.sort(key=lambda e: e["entries"][0]["value"])
        else:
            raise Exception("ItemCategory '%s' cannot access the ItemList" % self.get_path())
        return {"pools": pools} if pools else {}

    def to_str_all(self) -> str:
        return json.dumps(self.to_data_all(), indent=4, sort_keys=True)