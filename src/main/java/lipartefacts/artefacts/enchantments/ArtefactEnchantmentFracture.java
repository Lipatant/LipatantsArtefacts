package lipartefacts.artefacts.enchantments;

import lipartefacts.providers.LipartefactsItemTagProvider;
import net.minecraft.advancements.predicates.DamageSourcePredicate;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.ApplyMobEffect;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;

public class ArtefactEnchantmentFracture extends ArtefactEnchantment {

    public Enchantment.Builder getEnchantmentDefinition(BootstrapContext<Enchantment> context) {
        return Enchantment.enchantment(
            Enchantment.definition(
                context.lookup(Registries.ITEM).getOrThrow(LipartefactsItemTagProvider.ENCHANTABLE_FRACTURE),
                context.lookup(Registries.ITEM).getOrThrow(LipartefactsItemTagProvider.ENCHANTABLE_FRACTURE),
                2,
                1,
                Enchantment.dynamicCost(10, 20),
                Enchantment.dynamicCost(60, 20),
                4,
                EquipmentSlotGroup.MAINHAND
            )
        )
        .withEffect(
            EnchantmentEffectComponents.POST_ATTACK,
            EnchantmentTarget.ATTACKER,
            EnchantmentTarget.VICTIM,
            new ApplyMobEffect(
                HolderSet.direct(MobEffects.SLOWNESS),
                LevelBasedValue.constant(1.0F),
                LevelBasedValue.constant(1.0F),
                LevelBasedValue.constant(3.0F),
                LevelBasedValue.constant(3.0F)
            ),
            DamageSourceCondition.hasDamageSource(
                DamageSourcePredicate.Builder.damageType()
                    .isDirect(true)
            )
        );
    }

    public String getName() {
        return "fracture";
    };

    public Rarity getRarity() {
        return Rarity.RARE;
    };

}
