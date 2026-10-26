package lipartefacts.artefacts.enchantments;

import java.util.Collection;

import lipartefacts.providers.LipartefactsEnchantmentTagProvider;
import net.minecraft.advancements.predicates.DamageSourcePredicate;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.ApplyMobEffect;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;

public class ArtefactEnchantmentVampirism extends ArtefactEnchantment {

    public Enchantment.Builder getEnchantmentDefinition(BootstrapContext<Enchantment> context) {
        return Enchantment.enchantment(
            Enchantment.definition(
                context.lookup(Registries.ITEM).getOrThrow(ItemTags.MELEE_WEAPON_ENCHANTABLE),
                context.lookup(Registries.ITEM).getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                2,
                1,
                Enchantment.dynamicCost(10, 20),
                Enchantment.dynamicCost(60, 20),
                4,
                EquipmentSlotGroup.MAINHAND
            )
        )
        .exclusiveWith(context.lookup(Registries.ENCHANTMENT).getOrThrow(LipartefactsEnchantmentTagProvider.EXCLUSIVE_SET_POST_ATTACK_POTION_EFFECTS))
        .withEffect(
            EnchantmentEffectComponents.POST_ATTACK,
            EnchantmentTarget.ATTACKER,
            EnchantmentTarget.ATTACKER,
            new ApplyMobEffect(
                HolderSet.direct(MobEffects.REGENERATION),
                LevelBasedValue.constant(1.0F),
                LevelBasedValue.constant(1.0F),
                LevelBasedValue.constant(2.0F),
                LevelBasedValue.constant(2.0F)
            ),
            DamageSourceCondition.hasDamageSource(
                DamageSourcePredicate.Builder.damageType()
                    .isDirect(true)
            )
        );
    }

    public Collection<TagKey<Enchantment>> getEnchantmentTagList() {
        Collection<TagKey<Enchantment>> list = super.getEnchantmentTagList();

        list.add(LipartefactsEnchantmentTagProvider.EXCLUSIVE_SET_POST_ATTACK_POTION_EFFECTS);
        return list;
    }

    public String getName() {
        return "vampirism";
    };

    public Rarity getRarity() {
        return Rarity.EPIC;
    };

}
