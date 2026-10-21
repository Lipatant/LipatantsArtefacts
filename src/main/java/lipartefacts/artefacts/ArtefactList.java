package lipartefacts.artefacts;

import java.util.ArrayList;
import java.util.Collection;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class ArtefactList {

    /**
     * Generates the default properties of an artefact.
     */
    public static Item.Properties createProperties() {
        return new Item.Properties();
    }

    /**
     * Returns a collection of all artefacts.
     */
    public static Collection<ResourceKey<Item>> getIdList() {
        ArrayList<ResourceKey<Item>> list = new ArrayList<ResourceKey<Item>>();

        list.addAll(ArtefactArmorList.getIdList());
        list.addAll(ArtefactCharmList.getIdList());
        list.addAll(ArtefactMusicDiscList.getIdList());
        return list;
    }

    /**
     * Returns a collection of all artefacts.
     */
    public static Collection<Item> getList() {
        ArrayList<Item> list = new ArrayList<Item>();

        list.addAll(ArtefactArmorList.getList());
        list.addAll(ArtefactCharmList.getList());
        list.addAll(ArtefactMusicDiscList.getList());
        return list;
    }

    /**
     * Initializes the class.
     */
    public static void initialize() {
        ArtefactArmorList.initialize();
        ArtefactCharmList.initialize();
        ArtefactMusicDiscList.initialize();
    }

}
