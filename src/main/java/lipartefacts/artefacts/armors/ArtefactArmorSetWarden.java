package lipartefacts.artefacts.armors;

import lipartefacts.artefacts.ArtefactArmorList;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;

public class ArtefactArmorSetWarden extends ArtefactArmorSet {

    public ArtefactArmorAttribute createArmorAttribute() {
        return super.createArmorAttribute()
            .attributeAddArmor(ArtefactArmorList.makeDefenseDiamond())
            .attributeAdd(Attributes.ARMOR_TOUGHNESS, 2.0F, AttributeModifier.Operation.ADD_VALUE)
            .attributeAdd(Attributes.MAX_HEALTH, 2.0F, AttributeModifier.Operation.ADD_VALUE)
            .durability(37)
            .enchantmentValue(10)
        ;
    }

    public String getName() {
        return "warden";
    }

    public Rarity getRarity() {
        return Rarity.RARE;
    }

    protected Ingredient getUpgradeSmithingTemplateAddition() {
        return Ingredient.of(Items.ECHO_SHARD);
    }

}
