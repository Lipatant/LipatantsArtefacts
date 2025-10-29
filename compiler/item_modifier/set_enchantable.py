from item import COMPONENT_ENCHANTABLE
from item import FUNCTION, FUNCTION_SET_COMPONENTS
from item_modifier_base import ItemModifierBase

def generate_data() -> dict:
    return {
        "chainmail": [12, 12],
        "diamond": [13, 8],
        "golden": [22, 25],
        "iron": [14, 9],
        "leather": [15, 15],
        "netherite": [15, 15],
        "stone": 5,
        "turtle": [9, 9],
        "wooden": 15,
    }

class ItemModifierSetEnchantable(ItemModifierBase):

    def __init__(self, identifier: str, enchantable: int):
        super().__init__(identifier)
        self.value = enchantable

    def get_path_str(self) -> str:
        return "set_enchantable/%s"

    def to_data(self) -> dict | list:
        return {
                FUNCTION: FUNCTION_SET_COMPONENTS,
                "components": {
                    COMPONENT_ENCHANTABLE: {
                        "value": self.value,
                    },
                },
            }