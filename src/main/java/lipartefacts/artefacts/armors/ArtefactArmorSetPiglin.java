package lipartefacts.artefacts.armors;

import java.util.Collection;
import java.util.Map;

import lipartefacts.artefacts.ArtefactArmorList;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
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

    public Map<TagKey<Item>, Collection<ResourceKey<Item>>> getItemTagList() {
        Map<TagKey<Item>, Collection<ResourceKey<Item>>> list = super.getItemTagList();

        list.put(ItemTags.PIGLIN_SAFE_ARMOR, getIdList(ArtefactArmorSetType.ARMOR));
        return list;
    }

    public String getName() {
        return "piglin";
    }

    public Rarity getRarity() {
        return Rarity.RARE;
    }

    protected Ingredient getUpgradeSmithingTemplateAddition() {
        return Ingredient.of(Items.GOLD_INGOT);
    }

}
