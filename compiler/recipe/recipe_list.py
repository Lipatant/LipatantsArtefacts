from item.item_list import ItemList
from item_modifier.item_modifier_list import ItemModifierList
from recipe.recipe import Recipe

class RecipeList(dict[str, Recipe]):

    def append(self, recipe: Recipe) -> str:
        if not recipe:
            raise Exception("Trying to append a null object to an RecipeList")
        path = recipe.get_path()
        if path in self:
            raise Exception("Recipe '%s' has already been defined" % path)
        self[path] = recipe
        return path

    def save(self, item_list: ItemList = None, item_modifier_list: ItemModifierList = None) -> None:
        for recipe in self.values():
            recipe.save(item_list, item_modifier_list)