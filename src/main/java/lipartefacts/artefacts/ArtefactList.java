package lipartefacts.artefacts;

import net.minecraft.world.item.Item;

public class ArtefactList {

    /**
     * Generates the default properties of an artefact.
     */
    public static Item.Properties createProperties() {
        return new Item.Properties();
    }

    /**
     * Initializes the class.
     */
    public static void initialize() {
        ArtefactCharmList.initialize();
        ArtefactMusicDiscList.initialize();
    }

}
