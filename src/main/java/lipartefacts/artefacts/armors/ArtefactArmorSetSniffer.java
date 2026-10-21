package lipartefacts.artefacts.armors;

import java.util.function.Function;

import lipartefacts.artefacts.ArtefactArmorList;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;

public class ArtefactArmorSetSniffer extends ArtefactArmorSet {

    public void buildRecipes(RecipeProvider provider, RecipeOutput output) {
        super.buildRecipes(provider, output);
        provider.dyedItem(HELMET, "sniffer_helmet");
        provider.dyedItem(LEGGINGS, "sniffer_leggnigs");
    }

    public ArtefactArmorAttribute createArmorAttribute() {
        return super.createArmorAttribute()
            .attributeAddArmor(ArtefactArmorList.makeDefenseDiamond())
            .attributeAdd(Attributes.ARMOR_TOUGHNESS, 2.0F, AttributeModifier.Operation.ADD_VALUE)
            .attributeAdd(Attributes.KNOCKBACK_RESISTANCE, 0.2F, AttributeModifier.Operation.ADD_VALUE)
            .durability(37)
            .enchantmentValue(10)
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
        return "sniffer";
    }

    protected Ingredient getUpgradeSmithingTemplateAddition() {
        return Ingredient.of(Items.LEATHER);
    }

}
