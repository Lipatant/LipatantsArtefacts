package lipartefacts;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class ItemIdList {

    /**
     * Registers a new generic item from its id.
     */
    public static ResourceKey<Item> create(String name) {
        return ResourceKey.create(Registries.ITEM, Lipartefacts.id(name));
    }

}
