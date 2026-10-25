package lipartefacts;

import java.util.function.Function;

import lipartefacts.artefacts.ArtefactList;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemList {

    public static final ResourceKey<CreativeModeTab> CREATIVE_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Lipartefacts.id("creative_tab"));

    public static final CreativeModeTab CREATIVE_TAB = FabricCreativeModeTab.builder()
        .displayItems((params, output) -> {
            ArtefactList.getList().forEach((i) -> {
                output.accept(i);
            });
            ArtefactList.getItemStackList(params.holders()).forEach((i) -> {
                output.accept(i);
            });
        })
        .icon(() -> new ItemStack(ArtefactList.CHARM.ENDERMITE_CHARM))
        .title(Component.translatable("itemGroup.lipartefacts"))
        .build();

    /**
     * Initializes the class.
     */
    public static void initialize() {
        ArtefactList.initialize();
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CREATIVE_TAB_KEY, CREATIVE_TAB);
    }

    /**
     * Registers a given item.
     */
    public static Item register(ResourceKey<Item> itemKey, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        Item item = itemFactory.apply(settings.setId(itemKey));

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }

    /**
     * Registers a given item.
     */
    public static Item register(ResourceKey<Item> itemKey, Function<Item.Properties, Item> itemFactory) {
        return register(itemKey, itemFactory, new Item.Properties());
    }

}
