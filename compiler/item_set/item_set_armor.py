from copy import deepcopy
from item_set.item_set import ItemSet

def _get_armor(material: str, item_type: str) -> int:
    if item_type == "elytra" or item_type == "tool":
        return 0
    ARMOR_VALUES: dict[str, list[int]] = {
        "chainmail": [2, 5, 4, 1],
        "copper": [2, 5, 3, 1],
        "diamond": [3, 8, 6, 3],
        "golden": [2, 4, 3, 1],
        "iron": [2, 6, 5, 2],
        "leather": [1, 3, 2, 1],
        "netherite": [3, 8, 6, 3],
    }
    ARMOR_TYPES: dict[str, int] = {
        "helmet": 0,
        "chestplate": 1,
        "leggings": 2,
        "boots": 3,
    }
    if item_type in ARMOR_TYPES:
        return ARMOR_VALUES.get(material, [0, 0, 0, 0])[ARMOR_TYPES[item_type]]
    return 0

def _get_armor_toughness(material: str, item_type: str) -> int:
    if item_type == "tool":
        return 0
    match material:
        case "diamond":
            return 2 if (item_type != "elytra") else 0
        case "netherite":
            return 3 if (item_type != "elytra") else 1
    return 0

def _item_data_get_type(data: dict) -> str:
    TYPES: list[str] = [
        "boots",
        "chestplate",
        "elytra",
        "helmet",
        "leggings",
    ]
    inherits: str = data.get("inherits", "")
    for type in TYPES:
        if inherits.endswith(type):
            return type
    return "tool"

def _item_data_has_item_modifier(data: dict, prefix: str) -> bool:
    item_modifiers: list[str] = data.get("item_modifiers", [])
    for item_modifier in item_modifiers:
        if item_modifier.startswith(prefix):
            return True
    return False

class ItemSetArmor(ItemSet):

    def __init__(self, data: dict = {}):
        self.armor_stats = {}
        super().__init__(data)

    def load(self, data: dict) -> ItemSet:
        if "armor_stats" in data:
            for key, value in data["armor_stats"].items():
                self.armor_stats[key] = value
        return super().load(data)

    def to_item_data(self, data: dict) -> dict:
        item_data: dict = deepcopy(self.data) | data
        item_data_type: str = _item_data_get_type(item_data)
        if ("durability" in self.armor_stats) and (not _item_data_has_item_modifier(data, "lipartefacts:set_durability/")):
            if "item_modifiers" not in item_data:
                item_data["item_modifiers"] = []
            item_data["item_modifiers"].append("lipartefacts:set_durability/{material}/{type}".format_map({
                "material": self.armor_stats["durability"],
                "type": item_data_type,
            }))
        if item_data_type != "elytra":
            if "armor" in self.armor_stats:
                armor = _get_armor(self.armor_stats["armor"], item_data_type)
                if armor != 0:
                    if "attributes" not in item_data:
                        item_data["attributes"] = []
                    item_data["attributes"].append(
                        {
                            "amount": armor,
                            "attribute": "minecraft:armor",
                            "operation": "add_value",
                        },
                    )
            if "armor_toughness" in self.armor_stats:
                armor_toughness = _get_armor_toughness(self.armor_stats["armor_toughness"], item_data_type)
                if armor_toughness != 0:
                    if "attributes" not in item_data:
                        item_data["attributes"] = []
                    item_data["attributes"].append(
                        {
                            "amount": armor_toughness,
                            "attribute": "minecraft:armor_toughness",
                            "operation": "add_value",
                        },
                    )
            if ("enchantable" in self.armor_stats) and (not _item_data_has_item_modifier(data, "lipartefacts:set_enchantable/")):
                if "item_modifiers" not in item_data:
                    item_data["item_modifiers"] = []
                item_data["item_modifiers"].append("lipartefacts:set_enchantable/{material}{suffix}".format_map({
                    "material": self.armor_stats["enchantable"],
                    "suffix": "_armor" if (item_data_type != "tool") else "",
                }))
        return item_data