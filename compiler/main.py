import item_modifier.set_durability
import item_modifier.set_rarity

from load_manager import load_template_item_modifier
from save_manager import save_item_modifier

def initialize_item_modifiers_set_durability() -> None:
    data = item_modifier.set_durability.generate_data()
    template = load_template_item_modifier("set_durability.json")
    for tier in data:
        for item in data[tier]:
            save_item_modifier("set_durability/{0}/{1}.json".format(tier, item), template.replace("@1", str(data[tier][item])))

def initialize_item_modifiers_set_rarity() -> None:
    data = item_modifier.set_rarity.generate_data()
    template = load_template_item_modifier("set_rarity.json")
    for tier in data:
        save_item_modifier("set_rarity/{0}.json".format(tier), template.replace("@1", str(tier)).replace("@2", str(data[tier])))

def initialize_item_modifiers() -> None:
    initialize_item_modifiers_set_durability()
    initialize_item_modifiers_set_rarity()

def initialize() -> None:
    initialize_item_modifiers()

initialize()