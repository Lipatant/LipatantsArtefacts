package lipartefacts.artefacts.enchantments;

import java.util.Collection;

import lipartefacts.Lipartefacts;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentAttributeEffect;

public class ArtefactEnchantmentImperilment extends ArtefactEnchantment {

    public Enchantment.Builder getEnchantmentDefinition(BootstrapContext<Enchantment> context) {
        return Enchantment.enchantment(
            Enchantment.definition(
                context.lookup(Registries.ITEM).getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                2,
                1,
                Enchantment.dynamicCost(10, 20),
                Enchantment.dynamicCost(60, 20),
                4,
                EquipmentSlotGroup.ARMOR
            )
        )
        .exclusiveWith(context.lookup(Registries.ENCHANTMENT).getOrThrow(EnchantmentTags.ARMOR_EXCLUSIVE))
        .withEffect(
            EnchantmentEffectComponents.ATTRIBUTES,
            new EnchantmentAttributeEffect(
                Lipartefacts.id("enchantment.imperilment"),
                Attributes.ATTACK_DAMAGE,
                LevelBasedValue.perLevel(0.16F, 0.04F),
                AttributeModifier.Operation.ADD_MULTIPLIED_BASE
            )
        );
    }

    public Collection<TagKey<Enchantment>> getEnchantmentTagList() {
        Collection<TagKey<Enchantment>> list = super.getEnchantmentTagList();

        list.add(EnchantmentTags.ARMOR_EXCLUSIVE);
        return list;
    }

    public String getName() {
        return "imperilment";
    };

    public Rarity getRarity() {
        return Rarity.UNCOMMON;
    };

}
