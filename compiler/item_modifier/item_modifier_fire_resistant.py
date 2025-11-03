from item.item import COMPONENT_DAMAGE_RESISTANT
from item.item import FUNCTION, FUNCTION_SET_COMPONENTS
from item_modifier.item_modifier import ItemModifier

class ItemModifierFireResistant(ItemModifier):

    def modify_item_data(self, data: dict) -> None:
        if "components" not in data:
            data["components"] = {}
        if COMPONENT_DAMAGE_RESISTANT not in data["components"]:
            data["components"][COMPONENT_DAMAGE_RESISTANT] = {}
        data["components"][COMPONENT_DAMAGE_RESISTANT]["types"] = "#minecraft:is_fire"

    def to_data(self) -> dict | list:
        return {
            "components": {
                COMPONENT_DAMAGE_RESISTANT: {
                    "types": "#minecraft:is_fire",
                }
            },
            FUNCTION: FUNCTION_SET_COMPONENTS,
        }