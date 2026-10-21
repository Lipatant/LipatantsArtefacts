package lipartefacts.artefacts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import lipartefacts.Lipartefacts;
import lipartefacts.artefacts.armors.ArtefactArmorSet;
import lipartefacts.artefacts.armors.ArtefactArmorSetCopperGolem;
import lipartefacts.artefacts.armors.ArtefactArmorSetPiglin;
import lipartefacts.artefacts.armors.ArtefactArmorSetSniffer;
import lipartefacts.artefacts.armors.ArtefactArmorSetType;
import lipartefacts.artefacts.armors.ArtefactArmorSetWarden;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

public class ArtefactArmorList {

    public static final ArtefactArmorSet COPPER_GOLEM = new ArtefactArmorSetCopperGolem();
    public static final ArtefactArmorSet PIGLIN = new ArtefactArmorSetPiglin();
    public static final ArtefactArmorSet SNIFFER = new ArtefactArmorSetSniffer();
    public static final ArtefactArmorSet WARDEN = new ArtefactArmorSetWarden();

    public static void buildRecipes(RecipeProvider provider, RecipeOutput output) {
        getArmorSetList().forEach((a) -> {
            a.buildRecipes(provider, output);
        });
    }

    public static Item.Properties createProperties() {
        return ArtefactList.createProperties();
    }

    protected static ResourceKey<EquipmentAsset> createEquipmentAssetId(final String name) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, Lipartefacts.id(name));
    }

    protected static List<ArtefactArmorSet> getArmorSetList() {
        return List.of(
            COPPER_GOLEM,
            PIGLIN,
            SNIFFER,
            WARDEN
        );
    }

    public static Collection<ResourceKey<Item>> getIdList() {
        ArrayList<ResourceKey<Item>> list = new ArrayList<ResourceKey<Item>>();

        getArmorSetList().forEach((a) -> {
            list.addAll(a.getIdList());
        });
        return list;
    }

    public static Collection<ResourceKey<Item>> getIdList(ArtefactArmorSetType type) {
        ArrayList<ResourceKey<Item>> list = new ArrayList<ResourceKey<Item>>();

        getArmorSetList().forEach((a) -> {
            list.addAll(a.getIdList(type));
        });
        return list;
    }

    public static Collection<Item> getList() {
        ArrayList<Item> list = new ArrayList<Item>();

        getArmorSetList().forEach((a) -> {
            list.addAll(a.getList());
        });
        return list;
    }

    public static Collection<Item> getList(ArtefactArmorSetType type) {
        ArrayList<Item> list = new ArrayList<Item>();

        getArmorSetList().forEach((a) -> {
            list.addAll(a.getList(type));
        });
        return list;
    }

    public static void initialize() {}

    public static Map<ArmorType, Integer> makeDefense(final int boots, final int legs, final int chest, final int helm, final int body) {
        return ArmorMaterials.makeDefense(boots, legs, chest, helm, body);
    }

    public static Map<ArmorType, Integer> makeDefenseDiamond() {
        return makeDefense(3, 6, 8, 3, 11);
    }

}
