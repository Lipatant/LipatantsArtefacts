package lipartefacts.artefacts.enchantments;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.jspecify.annotations.NonNull;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import lipartefacts.Lipartefacts;
import lipartefacts.providers.LipartefactsEnchantmentTagProvider;
import net.minecraft.advancements.predicates.DamageSourcePredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;
import net.minecraft.world.phys.Vec3;

public class ArtefactEnchantmentCallToChaos extends ArtefactEnchantment {

    public record ArtefactEnchantmentCallToChaosEntityEffect(LevelBasedValue amplifier) implements EnchantmentEntityEffect {

        private static List<Holder<MobEffect>> MOB_EFFECT_LIST = List.of(
            // Bad
            MobEffects.BLINDNESS,
            MobEffects.INSTANT_DAMAGE,
            MobEffects.LEVITATION,
            MobEffects.SLOWNESS,
            MobEffects.WEAKNESS,
            // Neutral
            MobEffects.HASTE,
            MobEffects.INFESTED,
            MobEffects.MINING_FATIGUE,
            MobEffects.OOZING,
            MobEffects.SLOW_FALLING,
            MobEffects.WEAVING,
            MobEffects.WIND_CHARGED,
            // Benefic
            MobEffects.ABSORPTION,
            MobEffects.FIRE_RESISTANCE,
            MobEffects.INSTANT_HEALTH,
            MobEffects.JUMP_BOOST,
            MobEffects.RESISTANCE,
            MobEffects.SATURATION,
            MobEffects.SPEED,
            MobEffects.STRENGTH
        );
        private static List<Holder<MobEffect>> MOB_EFFECT_INSTANT_LIST = List.of(
            MobEffects.INSTANT_DAMAGE,
            MobEffects.INSTANT_HEALTH
        );

        public static final MapCodec<ArtefactEnchantmentCallToChaosEntityEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                LevelBasedValue.CODEC.fieldOf("amplifier").forGetter(ArtefactEnchantmentCallToChaosEntityEffect::amplifier)
            ).apply(instance, ArtefactEnchantmentCallToChaosEntityEffect::new)
        );

        @Override
        public void apply(ServerLevel serverLevel, int level, EnchantedItemInUse context, Entity target, Vec3 pos) {
            if (target instanceof LivingEntity) {
                Holder<MobEffect> mobEffect = MOB_EFFECT_LIST.get(
                    ThreadLocalRandom.current().nextInt(MOB_EFFECT_LIST.size())
                );
                ((LivingEntity)target).addEffect(new MobEffectInstance(
                    mobEffect,
                    MOB_EFFECT_INSTANT_LIST.contains(mobEffect) ? 1 : 20,
                    Math.round(this.amplifier.calculate(level))
                ));
            }
        }

        @Override
        public @NonNull MapCodec<? extends EnchantmentEntityEffect> codec() {
            return CODEC;
        }

    }

    public final MapCodec<ArtefactEnchantmentCallToChaosEntityEffect> ENCHANTMENT_ENTITY_EFFECT = Registry.register(
        BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE,
        Lipartefacts.id(getName()),
        ArtefactEnchantmentCallToChaosEntityEffect.CODEC
    );

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
            new ArtefactEnchantmentCallToChaosEntityEffect(
                LevelBasedValue.perLevel(0.0F, 1.0F)
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
        return "call_to_chaos";
    };

    public Rarity getRarity() {
        return Rarity.EPIC;
    };

}
