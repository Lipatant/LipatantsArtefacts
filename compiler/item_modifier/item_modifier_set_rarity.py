from item.item import COMPONENT_LORE, COMPONENT_RARITY
from item.item import FUNCTION, FUNCTION_SET_COMPONENTS, FUNCTION_SET_LORE
from item_modifier.item_modifier import ItemModifier

class ItemModifierSetRarity(ItemModifier):

    def __init__(self, identifier: str, rarity: str, secondary_color: str, display: str = ""):
        super().__init__(identifier)
        self.display = display
        self.rarity = rarity
        self.secondary_color = secondary_color

    def get_path_str(self) -> str:
        return "set_rarity/%s"

    def modify_item_data(self, data: dict) -> None:
        if "components" not in data:
            data["components"] = {}
        if COMPONENT_LORE not in data["components"]:
            data["components"][COMPONENT_LORE] = []
        data["components"][COMPONENT_LORE].append(
            {
                "color": self.secondary_color,
                "italic": False,
                "translate": "item.lipartefacts.generic.%s.desc" % (self.display if self.display else self.identifier),
            }
        )
        data["components"][COMPONENT_RARITY] = self.rarity

    def to_data(self) -> dict | list:
        data_components = {
            COMPONENT_RARITY: self.rarity,
        }
        data_lore = [
            {
                "color": self.secondary_color,
                "italic": False,
                "translate": "item.lipartefacts.generic.%s.desc" % (self.display if self.display else self.identifier),
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