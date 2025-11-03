from item.item_list import ItemList
from item_category.item_category import ItemCategory
from item_modifier.item_modifier_list import ItemModifierList

class ItemCategoryList(dict[str, ItemCategory]):

    def append(self, item_category: ItemCategory) -> str:
        if not item_category:
            raise Exception("Trying to append a null object to an ItemCategoryList")
        path = item_category.identifier
        if path in self:
            raise Exception("ItemCategory '%s' has already been defined" % path)
        self[path] = item_category
        return path

    def save(self, item_list: ItemList = None, item_modifier_list: ItemModifierList = None) -> None:
        for item_category in self.values():
            item_category.save(item_list, item_modifier_list)