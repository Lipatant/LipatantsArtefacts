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
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

public class ArtefactArmorList extends ArtefactListModule {

    public static final ArtefactArmorSet COPPER_GOLEM = new ArtefactArmorSetCopperGolem();
    public static final ArtefactArmorSet PIGLIN = new ArtefactArmorSetPiglin();
    public static final ArtefactArmorSet SNIFFER = new ArtefactArmorSetSniffer();
    public static final ArtefactArmorSet WARDEN = new ArtefactArmorSetWarden();

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
            WARDEN
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

    public Collection<Item> getList() {
        Collection<Item> list = super.getList();

        getArmorSetList().forEach((a) -> {
            list.addAll(a.getList());
        });
        return list;
    }

    public Collection<Item> getList(ArtefactArmorSetType type) {
        Collection<Item> list = super.getList();

        getArmorSetList().forEach((a) -> {
            list.addAll(a.getList(type));
        });
        return list;
    }

    public static Map<ArmorType, Integer> makeDefense(final int boots, final int legs, final int chest, final int helm, final int body) {
        return ArmorMaterials.makeDefense(boots, legs, chest, helm, body);
    }

    public static Map<ArmorType, Integer> makeDefenseDiamond() {
        return makeDefense(3, 6, 8, 3, 11);
    }

}
