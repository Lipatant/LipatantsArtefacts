import item_modifier.set_durability
import load_manager
from item import Item, ItemList
from item_modifier.set_durability import ItemModifierSetDurability
from item_modifier.set_enchantable import ItemModifierSetEnchantable
from item_modifier.set_rarity import ItemModifierSetRarity
from item_modifier_base import ItemModifierList
from item_set.item_set import ItemSet
from item_set.item_set_armor import ItemSetArmor

item_list = ItemList()
item_modifier_list = ItemModifierList()
item_template_list = ItemList()

def initialize() -> None:
    initialize_item_modifiers()
    initialize_items()

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

def initialize_item_modifiers_set_enchantable() -> None:
    global item_modifier_list
    data = item_modifier.set_enchantable.generate_data()
    for tier in data:
        if type(data[tier]) is int:
            item_modifier_list.append(
                ItemModifierSetEnchantable(
                    tier,
                    data[tier],
                )
            )
        else:
            item_modifier_list.append(
                ItemModifierSetEnchantable(
                    tier,
                    data[tier][0],
                )
            )
            item_modifier_list.append(
                ItemModifierSetEnchantable(
                    tier + "_armor",
                    data[tier][1],
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
    initialize_item_modifiers_set_enchantable()
    initialize_item_modifiers_set_rarity()

def initialize_items() -> None:
    global item_list, item_template_list
    initialize_items_templates()
    item_data_list = []
    item_sets = load_manager.load_all_item_sets()
    for data in item_sets.get("armor", {}).values():
        item_data_list.extend(ItemSetArmor(data).to_item_data_list())
    for data in item_sets.get("generic", {}).values():
        item_data_list.extend(ItemSet(data).to_item_data_list())
    item_data_list.extend(load_manager.load_all_items().values())
    for data in item_data_list:
        if not data:
            continue
        inherits: str = data["inherits"] if ("inherits" in data) else ""
        if inherits and not (inherits.startswith("minecraft:")):
            inherits = "lipartefacts:items/" + inherits
            if inherits not in item_template_list:
                raise Exception("Item template `%s` hasn't been defined" % inherits)
            item_template = item_template_list[inherits]
            item: Item = item_template.duplicate().load(data)
            item.inherits = item_template.inherits
            item_list.append(item)
        else:
            item_list.append(Item(data))

def initialize_items_templates() -> None:
    global item_template_list
    for data in load_manager.load_all_item_templates().values():
        item_template_list.append(Item(data))

def save() -> None:
    global item_list, item_modifier_list
    item_list.save()
    item_modifier_list.save()

initialize()
save()