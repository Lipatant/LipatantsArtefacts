package lipartefacts.artefacts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

abstract public class ArtefactListModule {

    public void buildRecipes(RecipeProvider provider, RecipeOutput output) {}

    public Item.Properties createProperties() {
        return ArtefactList.createProperties();
    }

    public Collection<ResourceKey<Item>> getIdList() {
        return new ArrayList<ResourceKey<Item>>();
    }

    public Collection<ItemStack> getItemStackList(HolderLookup.Provider registries) {
        return new ArrayList<ItemStack>();
    }

    public Map<TagKey<Item>, Collection<ResourceKey<Item>>> getItemTagList() {
        return new HashMap<TagKey<Item>, Collection<ResourceKey<Item>>>();
    }

    public Collection<Item> getList() {
        return new ArrayList<Item>();
    }

}
