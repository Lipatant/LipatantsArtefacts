package lipartefacts.artefacts.armors;

import java.util.Set;

import lipartefacts.artefacts.ArtefactArmorList;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.equipment.ArmorType;

public class ArtefactArmorSetCopperGolem extends ArtefactArmorSet {

    public ArtefactArmorAttribute createArmorAttribute() {
        return super.createArmorAttribute()
            .attributeAddArmor(ArtefactArmorList.makeDefenseDiamond())
            .attributeAdd(Attributes.ARMOR_TOUGHNESS, 2.0F, AttributeModifier.Operation.ADD_VALUE)
            .attributeAdd(Attributes.SCALE, -0.05F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
            .durability(37)
            .equipmentAssetIgnored(Set.of(ArmorType.HELMET))
            .enchantmentValue(10)
        ;
    }

    public String getName() {
        return "copper_golem";
    }

    public Rarity getRarity() {
        return Rarity.RARE;
    }

    protected Ingredient getUpgradeSmithingTemplateAddition() {
        return Ingredient.of(Items.COPPER_INGOT);
    }

}
