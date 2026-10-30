package lipartefacts.artefacts.armors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.mojang.datafixers.util.Pair;

import lipartefacts.artefacts.ArtefactArmorList;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.crafting.Ingredient;

public class ArtefactArmorSetGoat extends ArtefactArmorSet {

    public void buildRecipes(RecipeProvider provider, RecipeOutput output) {
        super.buildRecipes(provider, output);
        provider.dyedItem(HELMET, "goat_helmet");

        List<Pair<Ingredient, Item>> smithingPairList = List.of(
            Pair.of(Ingredient.of(Items.DIAMOND_HELMET), HELMET)
        );

        smithingPairList.forEach((pair) -> {
            if (pair.getSecond() == null) return;
            for (DyeColor color: DyeColor.values()) {
                DataComponentPatch.Builder components = DataComponentPatch.builder();
                if (color != DyeColor.WHITE) components.set(DataComponents.DYED_COLOR, new DyedItemColor(color.getTextureDiffuseColor()));
                ItemStackTemplate result = new ItemStackTemplate(pair.getSecond(), components.build());
                new SmithingTransformRecipeBuilder(
                    Ingredient.of(Items.GOAT_HORN),
                    pair.getFirst(),
                    Ingredient.of(BuiltInRegistries.ITEM.get(Identifier.withDefaultNamespace(color.getName() + "_wool")).orElseThrow().value()),
                    RecipeCategory.COMBAT,
                    result
                )
                    .unlocks(RecipeProvider.getHasName(Items.GOAT_HORN), provider.has(Items.GOAT_HORN))
                    .save(output, RecipeProvider.getItemName(pair.getSecond()) + "_smithing_" + color.getName())
                ;
            }
        });
    }

    public ArtefactArmorAttribute createArmorAttribute() {
        return super.createArmorAttribute()
            .attributeAddArmor(ArtefactArmorList.makeDefenseDiamond())
            .attributeAdd(Attributes.ARMOR_TOUGHNESS, 2.0F, AttributeModifier.Operation.ADD_VALUE)
            .attributeAdd(Attributes.ATTACK_KNOCKBACK, 1.0F, AttributeModifier.Operation.ADD_VALUE)
            .durability(33)
            .enchantmentValue(10)
            .repairIngredient(Items.DIAMOND)
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
        return "goat";
    }

    public Rarity getRarity() {
        return Rarity.UNCOMMON;
    }

    public List<ArtefactArmorSetType> getRegisteredList() {
        return List.of(
            ArtefactArmorSetType.HELMET
        );
    }

}
