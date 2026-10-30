package lipartefacts.artefacts.charms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lipartefacts.ItemIdList;
import lipartefacts.ItemList;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.Equippable;

public class ArtefactCharm {

    public final Item CHARM;
    public final ResourceKey<Item> CHARM_ID;

    private final Holder<Attribute> attribute;
    private final AttributeModifier attributeModifier;
    private final String name;
    private final Rarity rarity;
    private List<String> variantList = List.of();

    public ArtefactCharm(String name, Rarity rarity) {
        this(name, rarity, null, null);
    }

    public ArtefactCharm(String name, Rarity rarity, Holder<Attribute> attribute, AttributeModifier attributeModifier) {
        this.attribute = attribute;
        this.attributeModifier = attributeModifier;
        this.name = name;
        this.rarity = rarity;
        this.CHARM_ID = ItemIdList.create(getName() + "_charm");
        this.CHARM = ItemList.register(CHARM_ID, Item::new, createProperties());
    }

    public Item.Properties createProperties() {
        Item.Properties properties = new Item.Properties()
            .component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.OFFHAND).build())
            .stacksTo(1)
        ;
        Rarity rarity = getRarity();

        if (attribute != null && attributeModifier != null) properties.attributes(ItemAttributeModifiers.builder()
            .add(attribute, attributeModifier, EquipmentSlotGroup.OFFHAND)
            .build()
        );
        if (rarity != null) properties.rarity(rarity);
        return properties;
    }

    public Collection<ItemStack> getItemStackList(HolderLookup.Provider registries) {
        Collection<ItemStack> list = new ArrayList<ItemStack>();

        if (variantList.isEmpty()) {
            list.add(new ItemStack(CHARM));
        } else {
            variantList.forEach((v) -> {
                ItemStack itemStack = new ItemStack(CHARM);
                if (!v.isEmpty()) itemStack.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(List.of(), List.of(), List.of(v), List.of()));
                list.add(itemStack);
            });
        }
        return list;
    }

    public final String getName() {
        return this.name;
    };

    public final Rarity getRarity() {
        return this.rarity;
    };

    public final boolean hasVariantList() {
        return !this.variantList.isEmpty();
    };

    public final ArtefactCharm setVariantList(List<String> variantList) {
        this.variantList = variantList;
        return this;
    };

}
