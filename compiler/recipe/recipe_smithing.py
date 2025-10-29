from recipe.recipe import Recipe

class RecipeSmithing(Recipe):

    def get_path_str(self) -> str:
        return "%s_smithing"