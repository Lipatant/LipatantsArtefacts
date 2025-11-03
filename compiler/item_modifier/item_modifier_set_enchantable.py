from item.item import COMPONENT_ENCHANTABLE
from item.item import FUNCTION, FUNCTION_SET_COMPONENTS
from item_modifier.item_modifier import ItemModifier

def generate_data() -> dict:
    return {
        "chainmail": [12, 12],
        "copper": [13, 8],
        "diamond": [10, 10],
        "golden": [22, 25],
        "iron": [14, 9],
        "leather": [15, 15],
        "netherite": [15, 15],
        "stone": 5,
        "turtle": [9, 9],
        "wooden": 15,
    }

class ItemModifierSetEnchantable(ItemModifier):

    def __init__(self, identifier: str, enchantable: int):
        super().__init__(identifier)
        self.value = enchantable

    def get_path_str(self) -> str:
        return "set_enchantable/%s"

    def modify_item_data(self, data: dict) -> None:
        if "components" not in data:
            data["components"] = {}
        data["components"][COMPONENT_ENCHANTABLE] = {
            "value": self.value,
        }

    def to_data(self) -> dict | list:
        return {
                FUNCTION: FUNCTION_SET_COMPONENTS,
                "components": {
                    COMPONENT_ENCHANTABLE: {
                        "value": self.value,
                    },
                },
            }