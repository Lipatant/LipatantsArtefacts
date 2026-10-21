package lipartefacts.artefacts.armors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.Equippable;

public class ArtefactArmorAttribute {

    class Attribute {

        public double amount = 0.0;
        public Map<ArmorType, Double> amountMap = Collections.emptyMap();
        public Holder<net.minecraft.world.entity.ai.attributes.Attribute> attribute;
        public AttributeModifier.Operation operation;

        public Attribute(Holder<net.minecraft.world.entity.ai.attributes.Attribute> attribute, double amount, AttributeModifier.Operation operation) {
            this.amount = amount;
            this.attribute = attribute;
            this.operation = operation;
        }

        public Attribute(Holder<net.minecraft.world.entity.ai.attributes.Attribute> attribute, Map<ArmorType, Double> amountMap, AttributeModifier.Operation operation) {
            this.amountMap = amountMap;
            this.attribute = attribute;
            this.operation = operation;
        }

    }

    protected ArrayList<Attribute> attributes = new ArrayList<Attribute>();
    public int durability = -1;
    public Holder<SoundEvent> equipSound = SoundEvents.ARMOR_EQUIP_GENERIC;
    public ResourceKey<EquipmentAsset> equipmentAsset = null;
    public Set<ArmorType> equipmentAssetIgnored = Set.of();
    public int enchantmentValue = -1;
    public TagKey<Item> repairIngredient = null;

    public ArtefactArmorAttribute() {}

    public Equippable createEquippable(final ArmorType type) {
        Equippable.Builder equippable = Equippable.builder(type.getSlot());

        if (equipSound != null) equippable.setEquipSound(equipSound);
        if (equipmentAsset != null && !equipmentAssetIgnored.contains(type)) equippable.setAsset(equipmentAsset);
        return equippable.build();
    }

    public ItemAttributeModifiers createItemAttributeModifiers(final ArmorType type) {
        ItemAttributeModifiers.Builder modifiers = createItemAttributeModifiersBuilder(type);

        return modifiers.build();
    }

    public ItemAttributeModifiers.Builder createItemAttributeModifiersBuilder(final ArmorType type) {
        EquipmentSlotGroup equipmentSlotGroup = EquipmentSlotGroup.bySlot(type.getSlot());
        Identifier identifier = Identifier.withDefaultNamespace("armor." + type.getName());
        ItemAttributeModifiers.Builder modifiers = ItemAttributeModifiers.builder();

        attributes.forEach((attribute) -> {
            double amount = attribute.amountMap.getOrDefault(type, attribute.amount);

            if (attribute.operation == AttributeModifier.Operation.ADD_VALUE) {
                if (amount == 0.0) return;
            } else {
                if (amount == 1.0) return;
            }
            modifiers.add(attribute.attribute, new AttributeModifier(identifier, amount, attribute.operation), equipmentSlotGroup);
        });
        return modifiers;
    }

    public Item.Properties createProperties(final ArmorType type) {
        return createProperties(type, new Item.Properties());
    }

    public Item.Properties createProperties(final ArmorType type, Item.Properties source) {
        if (durability >= 0) source.durability(type.getDurability(durability));
        if (enchantmentValue >= 0) source.enchantable(enchantmentValue);
        if (repairIngredient != null) source.repairable(repairIngredient);
        source.attributes(createItemAttributeModifiers(type));
        source.component(DataComponents.EQUIPPABLE, createEquippable(type));
        return source;
    }

    public ArtefactArmorAttribute attributeAdd(Holder<net.minecraft.world.entity.ai.attributes.Attribute> attribute, double amount, AttributeModifier.Operation operation) {
        this.attributes.add(new Attribute(attribute, amount, operation));
        return this;
    }

    public ArtefactArmorAttribute attributeAdd(Holder<net.minecraft.world.entity.ai.attributes.Attribute> attribute, Map<ArmorType, Double> amountMap, AttributeModifier.Operation operation) {
        this.attributes.add(new Attribute(attribute, amountMap, operation));
        return this;
    }

    public ArtefactArmorAttribute attributeAddArmor(Map<ArmorType, Integer> amountMap) {
        this.attributes.add(new Attribute(
            Attributes.ARMOR,
            amountMap.entrySet().stream().collect(Collectors.toMap(Entry::getKey, a -> (double)a.getValue().intValue())),
            AttributeModifier.Operation.ADD_VALUE
        ));
        return this;
    }

    public ArtefactArmorAttribute durability(int durability) {
        this.durability = durability;
        return this;
    }

    public ArtefactArmorAttribute enchantmentValue(int enchantmentValue) {
        this.enchantmentValue = enchantmentValue;
        return this;
    }

    public ArtefactArmorAttribute equipSound(Holder<SoundEvent> equipSound) {
        this.equipSound = equipSound;
        return this;
    }

    public ArtefactArmorAttribute equipmentAsset(ResourceKey<EquipmentAsset> equipmentAsset) {
        this.equipmentAsset = equipmentAsset;
        return this;
    }

    public ArtefactArmorAttribute equipmentAssetIgnored(Set<ArmorType> equipmentAssetIgnored) {
        this.equipmentAssetIgnored = equipmentAssetIgnored;
        return this;
    }

    public ArtefactArmorAttribute repairIngredient(TagKey<Item> repairIngredient) {
        this.repairIngredient = repairIngredient;
        return this;
    }

}
