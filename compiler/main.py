import item_modifier.set_durability
import load_manager
from item_modifier.set_durability import ItemModifierSetDurability
from item_modifier.set_rarity import ItemModifierSetRarity
from item_modifier_base import ItemModifierList

item_modifier_list = ItemModifierList()

def initialize_item_modifiers_set_durability() -> None:
    global item_modifier_list
    data = item_modifier.set_durability.generate_data()
    for tier in data:
        for item in data[tier]:
            item_modifier_list.append(
                ItemModifierSetDurability(
                    tier + "/" + item,
                    data[tier][item],
                )
            )

def initialize_item_modifiers_set_rarity() -> None:
    global item_modifier_list
    for data in load_manager.load_all_rarities().values():
        if not data:
            continue
        item_modifier_list.append(
            ItemModifierSetRarity(
                data["id"],
                data["rarity"],
                data["secondary_color"],
            )
        )

def initialize_item_modifiers() -> None:
    initialize_item_modifiers_set_durability()
    initialize_item_modifiers_set_rarity()

def initialize() -> None:
    initialize_item_modifiers()

def save() -> None:
    global item_modifier_list
    item_modifier_list.save()

initialize()
save()