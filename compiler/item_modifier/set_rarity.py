from item import COMPONENT_RARITY
from item import FUNCTION, FUNCTION_SET_COMPONENTS, FUNCTION_SET_LORE
from item_modifier_base import ItemModifierBase

def generate_data() -> dict:
    return {
        "common": "gray",
        "epic": "dark_purple",
        "rare": "dark_aqua",
        "uncommon": "#aaaa00",
    }

class ItemModifierRarity(ItemModifierBase):

    rarity = None
    secondary_color = None

    def __init__(self, identifier: str, rarity: str, secondary_color: str):
        super().__init__(identifier)
        self.rarity = rarity
        self.secondary_color = secondary_color

    def get_file_path_str(self) -> str:
        return "set_rarity/%s.json"

    def to_data(self) -> dict | list:
        data_components = {
            COMPONENT_RARITY: self.rarity,
        }
        data_lore = [
            {
                "color": self.secondary_color,
                "italic": False,
                "translate": "item.lipartefacts.generic.%s.desc" % self.identifier,
            }
        ]
        return [
            {
                FUNCTION: FUNCTION_SET_COMPONENTS,
                "components": data_components,
            },
            {
                FUNCTION: FUNCTION_SET_LORE,
                "lore": data_lore,
                "mode": "append",
            },
        ]