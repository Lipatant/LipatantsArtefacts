import item_modifier.set_durability
import item_modifier.set_rarity
from item_modifier.set_durability import ItemModifierDurability
from item_modifier.set_rarity import ItemModifierRarity

def initialize_item_modifiers_set_durability() -> None:
    data = item_modifier.set_durability.generate_data()
    for tier in data:
        for item in data[tier]:
            ItemModifierDurability(tier + "/" + item, data[tier][item]).save()

def initialize_item_modifiers_set_rarity() -> None:
    data = item_modifier.set_rarity.generate_data()
    for tier in data:
        ItemModifierRarity(tier, tier, str(data[tier])).save()

def initialize_item_modifiers() -> None:
    initialize_item_modifiers_set_durability()
    initialize_item_modifiers_set_rarity()

def initialize() -> None:
    initialize_item_modifiers()

initialize()