from recipe.recipe_smithing import RecipeSmithing

def _item_data_get_type(identifier: dict) -> str:
    TYPES: list[str] = [
        "boots",
        "chestplate",
        "elytra",
        "helmet",
        "leggings",
    ]
    for type in TYPES:
        if identifier.endswith(type):
            return type
    return identifier

class RecipeSmithingUpgrade(RecipeSmithing):

    def __init__(self, identifier: str, addition: str = "", base: str = "", base_relative: str = "", result: str = "", template: str = ""):
        super().__init__(identifier)
        self.addition = addition
        self.base = base
        self.base_relative = base_relative
        self.result = result
        self.template = template

    def get_base_from_base_relative(self) -> str:
        result: str = self.result
        if not result:
            return self.base
        type: str = _item_data_get_type(result)
        print(type)
        if type == "elytra":
            return "minecraft:elytra"
        if type == result:
            return result
        return self.base_relative + "_" + type

    def to_data(self) -> dict | list:
        output = {
            "type": "minecraft:smithing_transform",
        }
        addition = self.get_item(self.addition)
        base = self.get_item(self.get_base_from_base_relative())
        print(base)
        result = self.get_item(self.result, True)
        template = self.get_item(self.template)
        if addition:
            output["addition"] = addition
        if base:
            output["base"] = base
        else:
            raise Exception("Recipe '%s' is missing a base" % self.get_path())
        if result:
            output["result"] = result
        else:
            raise Exception("Recipe '%s' is missing a result" % self.get_path())
        if template:
            output["template"] = template
        return output