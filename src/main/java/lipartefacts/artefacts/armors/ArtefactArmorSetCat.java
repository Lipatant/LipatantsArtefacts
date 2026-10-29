package lipartefacts.artefacts.armors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import lipartefacts.artefacts.ArtefactArmorList;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ArtefactArmorSetCat extends ArtefactArmorSet {

    public void buildRecipes(RecipeProvider provider, RecipeOutput output) {
        super.buildRecipes(provider, output);
        provider.dyedItem(HELMET, "cat_helmet");
    }

    public ArtefactArmorAttribute createArmorAttribute() {
        return super.createArmorAttribute()
            .attributeAddArmor(ArtefactArmorList.makeDefenseLeather())
            .attributeAdd(Attributes.SAFE_FALL_DISTANCE, 1.0F, AttributeModifier.Operation.ADD_VALUE)
            .durability(5)
            .enchantmentValue(15)
            .repairIngredient(Items.LEATHER)
        ;
    }

    public Map<TagKey<Item>, Collection<ResourceKey<Item>>> getItemTagList() {
        Map<TagKey<Item>, Collection<ResourceKey<Item>>> list = super.getItemTagList();
        Collection<ResourceKey<Item>> cauldronCanRemoveDyeIdList = new ArrayList<ResourceKey<Item>>();

        cauldronCanRemoveDyeIdList.addAll(getIdList(ArtefactArmorSetType.ARMOR));
        list.put(ItemTags.CAULDRON_CAN_REMOVE_DYE, cauldronCanRemoveDyeIdList);
        return list;
    }

    public String getName() {
        return "cat";
    }

    public List<ArtefactArmorSetType> getRegisteredList() {
        return List.of(
            ArtefactArmorSetType.HELMET
        );
    }

}
