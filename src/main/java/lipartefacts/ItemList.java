package lipartefacts;

import java.util.function.Function;

import lipartefacts.artefacts.Artefact;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.Equippable;

public class ItemList {

    public static final Identifier OFFHAND_SCALE_ID = Identifier.withDefaultNamespace("offhand.scale");

    public static final Item ENDERMITE_CHARM = register(ItemIdList.ENDERMITE_CHARM, Artefact::new, new Item.Properties()
        .attributes(ItemAttributeModifiers.builder()
            .add(Attributes.SCALE, new AttributeModifier(OFFHAND_SCALE_ID, -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.OFFHAND)
            .build()
        )
        .component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.OFFHAND).build())
        .rarity(Rarity.EPIC)
        .stacksTo(1)
    );

    public static final ResourceKey<CreativeModeTab> CREATIVE_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Lipartefacts.id("creative_tab"));

    public static final CreativeModeTab CREATIVE_TAB = FabricCreativeModeTab.builder()
        .displayItems((params, output) -> {
            output.accept(ItemList.ENDERMITE_CHARM);
        })
        .icon(() -> new ItemStack(ItemList.ENDERMITE_CHARM))
        .title(Component.translatable("itemGroup.lipartefacts"))
        .build();

    /**
     * Initializes the class.
     */
    public static void initialize() {
        initializeCreativeModeTab();
    }

    /**
     * Initializes the creative inventory.
     */
    public static void initializeCreativeModeTab() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CREATIVE_TAB_KEY, CREATIVE_TAB);
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
            .register((creativeModeTab) -> creativeModeTab.accept(ItemList.ENDERMITE_CHARM));
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
