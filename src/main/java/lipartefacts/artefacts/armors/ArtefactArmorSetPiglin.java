package lipartefacts.artefacts.armors;

import java.util.function.Function;

import lipartefacts.artefacts.ArtefactArmorList;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;

public class ArtefactArmorSetPiglin extends ArtefactArmorSet {

    public ArtefactArmorAttribute createArmorAttribute() {
        return super.createArmorAttribute()
            .attributeAddArmor(ArtefactArmorList.makeDefenseDiamond())
            .attributeAdd(Attributes.ATTACK_DAMAGE, 0.1F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
            .durability(37)
            .enchantmentValue(15)
        ;
    }

	public Function<Item.Properties, Item> getItemFactory(ArtefactArmorSetType type) {
        return (p) -> {
            return super.getItemFactory(type).apply(p
                .rarity(Rarity.RARE)
            );
        };
	}

    public String getName() {
        return "piglin";
    }

    protected Ingredient getUpgradeSmithingTemplateAddition() {
        return Ingredient.of(Items.GOLD_INGOT);
    }

}
