from item_modifier.item_modifier import ItemModifier

class ItemModifierList(dict[str, ItemModifier]):

    def append(self, item_modifier: ItemModifier) -> str:
        if not item_modifier:
            raise Exception("Trying to append a null object to an ItemModifierList")
        path = item_modifier.get_path()
        if path in self:
            raise Exception("Item modifier '%s' has already been defined" % path)
        self[path] = item_modifier
        return path

    def save(self) -> None:
        for item_modifier in self.values():
            item_modifier.save()