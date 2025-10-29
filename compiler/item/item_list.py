from item.item import Item

class ItemList(dict[str, Item]):

    def append(self, item: Item) -> str:
        if not item:
            raise Exception("Trying to append a null object to an ItemList")
        path = item.identifier
        if path in self:
            raise Exception("Item '%s' has already been defined" % path)
        self[path] = item
        return path

    def save(self) -> None:
        for item in self.values():
            item.save()