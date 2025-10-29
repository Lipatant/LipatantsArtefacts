import load_manager
import item_modifier.item_modifier_set_durability
import item_modifier.item_modifier_set_enchantable
from item.item import Item
from item.item_list import ItemList
from item_modifier.item_modifier import ItemModifier
from item_modifier.item_modifier_list import ItemModifierList
from item_modifier.item_modifier_set_durability import ItemModifierSetDurability
from item_modifier.item_modifier_set_enchantable import ItemModifierSetEnchantable
from item_modifier.item_modifier_set_rarity import ItemModifierSetRarity
from item_set.item_set import ItemSet
from item_set.item_set_armor import ItemSetArmor
from recipe.recipe_list import RecipeList
from recipe.recipe_smithing_upgrade import RecipeSmithingUpgrade

item_list = ItemList()
item_modifier_list = ItemModifierList()
item_template_list = ItemList()
recipe_list = RecipeList()

def initialize() -> None:
    initialize_item_modifiers()
    initialize_items()

def initialize_item_modifiers_set_durability() -> None:
    global item_modifier_list
    data = item_modifier.item_modifier_set_durability.generate_data()
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
    data = item_modifier.item_modifier_set_enchantable.generate_data()
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
    global item_list, item_template_list, recipe_list
    initialize_items_templates()
    item_data_list = []
    item_sets = load_manager.load_all_item_sets()
    for data in item_sets.get("armor", {}).values():
        item_data_list.extend(ItemSetArmor(data).to_item_data_list())
    for data in item_sets.get("generic", {}).values():
        item_data_list.extend(ItemSet(data).to_item_data_list())
    item_data_list.extend(load_manager.load_all_items().values())
    for data in item_data_list:
        item: Item
        if not data:
            continue
        inherits: str = data["inherits"] if ("inherits" in data) else ""
        if inherits and not (inherits.startswith("minecraft:")):
            inherits = inherits
            if inherits not in item_template_list:
                raise Exception("Item template `%s` hasn't been defined" % inherits)
            item_template = item_template_list[inherits]
            item = item_template.duplicate().load(data)
            item.inherits = item_template.inherits
        else:
            item = Item(data)
        item_list.append(item)
        for recipe in item.recipe_smithing_upgrades:
            recipe_instance: str = recipe.copy()
            if "result" not in recipe_instance:
                recipe_instance["result"] = item.identifier
            recipe_list.append(
                RecipeSmithingUpgrade(
                    item.identifier,
                    **recipe_instance,
                )
            )

def initialize_items_templates() -> None:
    global item_template_list
    for data in load_manager.load_all_item_templates().values():
        item_template_list.append(Item(data))

def save() -> None:
    global item_list, item_modifier_list, recipe_list
    item_list.save()
    item_modifier_list.save()
    recipe_list.save(item_list, item_modifier_list)

initialize()
save()