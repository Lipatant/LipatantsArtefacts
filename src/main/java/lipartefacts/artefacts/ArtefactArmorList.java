package lipartefacts.artefacts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import lipartefacts.Lipartefacts;
import lipartefacts.artefacts.armors.ArtefactArmorSet;
import lipartefacts.artefacts.armors.ArtefactArmorSetBogged;
import lipartefacts.artefacts.armors.ArtefactArmorSetCat;
import lipartefacts.artefacts.armors.ArtefactArmorSetCopperGolem;
import lipartefacts.artefacts.armors.ArtefactArmorSetFisherman;
import lipartefacts.artefacts.armors.ArtefactArmorSetGoat;
import lipartefacts.artefacts.armors.ArtefactArmorSetIncognito;
import lipartefacts.artefacts.armors.ArtefactArmorSetParched;
import lipartefacts.artefacts.armors.ArtefactArmorSetPiglin;
import lipartefacts.artefacts.armors.ArtefactArmorSetSniffer;
import lipartefacts.artefacts.armors.ArtefactArmorSetStray;
import lipartefacts.artefacts.armors.ArtefactArmorSetType;
import lipartefacts.artefacts.armors.ArtefactArmorSetWarden;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

public class ArtefactArmorList extends ArtefactListModule {

    public final ArtefactArmorSet BOGGED = new ArtefactArmorSetBogged();
    public final ArtefactArmorSet CAT = new ArtefactArmorSetCat();
    public final ArtefactArmorSet COPPER_GOLEM = new ArtefactArmorSetCopperGolem();
    public final ArtefactArmorSet FISHERMAN = new ArtefactArmorSetFisherman();
    public final ArtefactArmorSet GOAT = new ArtefactArmorSetGoat();
    public final ArtefactArmorSet INCOGNITO_PAPER_BAG_ALEX = new ArtefactArmorSetIncognito("incognito_paper_bag_alex");
    public final ArtefactArmorSet INCOGNITO_PAPER_BAG_CREEPER = new ArtefactArmorSetIncognito("incognito_paper_bag_creeper");
    public final ArtefactArmorSet INCOGNITO_PAPER_BAG_MENACING = new ArtefactArmorSetIncognito("incognito_paper_bag_menacing");
    public final ArtefactArmorSet INCOGNITO_PAPER_BAG_MUMBO = new ArtefactArmorSetIncognito("incognito_paper_bag_mumbo");
    public final ArtefactArmorSet INCOGNITO_PAPER_BAG_STEVE = new ArtefactArmorSetIncognito("incognito_paper_bag_steve");
    public final ArtefactArmorSet PARCHED = new ArtefactArmorSetParched();
    public final ArtefactArmorSet PIGLIN = new ArtefactArmorSetPiglin();
    public final ArtefactArmorSet SNIFFER = new ArtefactArmorSetSniffer();
    public final ArtefactArmorSet STRAY = new ArtefactArmorSetStray();
    public final ArtefactArmorSet WARDEN = new ArtefactArmorSetWarden();

    public void buildRecipes(RecipeProvider provider, RecipeOutput output) {
        super.buildRecipes(provider, output);
        getArmorSetList().forEach((a) -> {
            a.buildRecipes(provider, output);
        });
    }

    protected ResourceKey<EquipmentAsset> createEquipmentAssetId(final String name) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, Lipartefacts.id(name));
    }

    protected List<ArtefactArmorSet> getArmorSetList() {
        return List.of(
            COPPER_GOLEM,
            PIGLIN,
            SNIFFER,
            WARDEN,
            BOGGED,
            CAT,
            FISHERMAN,
            GOAT,
            INCOGNITO_PAPER_BAG_ALEX,
            INCOGNITO_PAPER_BAG_CREEPER,
            INCOGNITO_PAPER_BAG_MENACING,
            INCOGNITO_PAPER_BAG_MUMBO,
            INCOGNITO_PAPER_BAG_STEVE,
            PARCHED,
            STRAY
        );
    }

    public Collection<ResourceKey<Item>> getIdList() {
        Collection<ResourceKey<Item>> list = super.getIdList();

        getArmorSetList().forEach((a) -> {
            list.addAll(a.getIdList());
        });
        return list;
    }

    public Collection<ResourceKey<Item>> getIdList(ArtefactArmorSetType type) {
        Collection<ResourceKey<Item>> list = super.getIdList();

        getArmorSetList().forEach((a) -> {
            list.addAll(a.getIdList(type));
        });
        return list;
    }

    public Collection<ItemStack> getItemStackList(HolderLookup.Provider registries) {
        Collection<ItemStack> list = super.getItemStackList(registries);

        getArmorSetList().forEach((a) -> {
            list.addAll(a.getItemStackList(registries));
        });
        return list;
    }

    public Map<TagKey<Item>, Collection<ResourceKey<Item>>> getItemTagList() {
        Map<TagKey<Item>, Collection<ResourceKey<Item>>> list = super.getItemTagList();

        getArmorSetList().forEach((a) -> {
            a.getItemTagList().forEach((t, l) -> {
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

    public static Map<ArmorType, Integer> makeDefense(final int boots, final int legs, final int chest, final int helm, final int body) {
        return ArmorMaterials.makeDefense(boots, legs, chest, helm, body);
    }

    public static Map<ArmorType, Integer> makeDefenseDiamond() {
        return makeDefense(3, 6, 8, 3, 11);
    }

    public static Map<ArmorType, Integer> makeDefenseLeather() {
        return makeDefense(1, 2, 3, 1, 3);
    }

}
