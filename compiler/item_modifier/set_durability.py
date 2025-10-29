from item import COMPONENT_MAX_DAMAGE
from item import FUNCTION, FUNCTION_SET_COMPONENTS
from item_modifier_base import ItemModifierBase

def generate_data() -> dict:
    data = {
        "chainmail": {
            "base": 15,
        },
        "diamond": {
            "base": 33,
            "elytra": 432,
            "tool": 1561
        },
        "golden": {
            "base": 7,
            "tool": 32
        },
        "iron": {
            "base": 15,
            "tool": 250
        },
        "leather": {
            "base": 5,
        },
        "netherite": {
            "base": 37,
            "elytra": 496,
            "tool": 2031
        },
        "stone": {
            "tool": 131
        },
        "turtle": {
            "base": 25,
        },
        "wooden": {
            "tool": 59
        },
    }
    data_generated_multiplicative = {
        "boots": 13,
        "chestplate": 16,
        "helmet": 11,
        "leggings": 15,
    }
    output = {}
    for tier in data:
        output[tier] = {}
        if "base" in data[tier]:
            for item in data_generated_multiplicative:
                if item in data[tier]:
                    continue
                output[tier][item] = data[tier]["base"] * data_generated_multiplicative[item]
        for item in data[tier]:
            if item == "base":
                continue
            output[tier][item] = data[tier][item]
    return output

class ItemModifierSetDurability(ItemModifierBase):

    def __init__(self, identifier: str, max_damage: int):
        super().__init__(identifier)
        self.max_damage = max_damage

    def get_path_str(self) -> str:
        return "set_durability/%s"

    def to_data(self) -> dict | list:
        return {
                FUNCTION: FUNCTION_SET_COMPONENTS,
                "components": {
                    COMPONENT_MAX_DAMAGE: self.max_damage,
                },
            }