package lipartefacts.artefacts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ArtefactList {

    public static final ArtefactArmorList ARMOR = new ArtefactArmorList();
    public static final ArtefactCharmList CHARM = new ArtefactCharmList();
    public static final ArtefactEnchantmentList ENCHANTMENT = new ArtefactEnchantmentList();
    public static final ArtefactMusicDiscList MUSIC_DISC = new ArtefactMusicDiscList();

    public static void buildRecipes(RecipeProvider provider, RecipeOutput output) {
        getModuleList().forEach((m) -> {
            m.buildRecipes(provider, output);
        });
    }

    public static Item.Properties createProperties() {
        return new Item.Properties();
    }

    public static Map<TagKey<Item>, Collection<ResourceKey<Item>>> getItemTagList() {
        HashMap<TagKey<Item>, Collection<ResourceKey<Item>>> list = new HashMap<TagKey<Item>, Collection<ResourceKey<Item>>>();

        getModuleList().forEach((m) -> {
            m.getItemTagList().forEach((t, l) -> {
                Collection<ResourceKey<Item>> l2 = list.getOrDefault(t, null);
                if (l2 == null) {
                    l2 = new ArrayList<ResourceKey<Item>>();
                    l2.addAll(l);
                    list.put(t, l2);
                } else {
                    l2.addAll(l);
                }
            });
        });
        return list;
    }

    protected static List<ArtefactListModule> getModuleList() {
        return List.of(
            ARMOR,
            CHARM,
            ENCHANTMENT,
            MUSIC_DISC
        );
    }

    public static Collection<ResourceKey<Item>> getIdList() {
        ArrayList<ResourceKey<Item>> list = new ArrayList<ResourceKey<Item>>();

        getModuleList().forEach((m) -> {
            list.addAll(m.getIdList());
        });
        return list;
    }

    public static Collection<ItemStack> getItemStackList(HolderLookup.Provider registries) {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();

        getModuleList().forEach((m) -> {
            list.addAll(m.getItemStackList(registries));
        });
        return list;
    }

    public static Collection<Item> getList() {
        ArrayList<Item> list = new ArrayList<Item>();

        getModuleList().forEach((m) -> {
            list.addAll(m.getList());
        });
        return list;
    }

    public static void initialize() {}

}
