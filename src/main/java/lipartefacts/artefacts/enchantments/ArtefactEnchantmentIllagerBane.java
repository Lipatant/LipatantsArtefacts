package lipartefacts.artefacts.enchantments;

import java.util.Collection;

import lipartefacts.providers.LipartefactsEntityTypeTagProvider;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.predicates.entity.EntityTypePredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;

public class ArtefactEnchantmentIllagerBane extends ArtefactEnchantment {

    public Enchantment.Builder getEnchantmentDefinition(BootstrapContext<Enchantment> context) {
        return Enchantment.enchantment(
            Enchantment.definition(
                context.lookup(Registries.ITEM).getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                context.lookup(Registries.ITEM).getOrThrow(ItemTags.MELEE_WEAPON_ENCHANTABLE),
                2,
                1,
                Enchantment.dynamicCost(10, 20),
                Enchantment.dynamicCost(60, 20),
                4,
                EquipmentSlotGroup.MAINHAND
            )
        )
        .exclusiveWith(context.lookup(Registries.ENCHANTMENT).getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
        .withEffect(
            EnchantmentEffectComponents.DAMAGE,
            new AddValue(LevelBasedValue.perLevel(12.5F, 2.5F)),
            LootItemEntityPropertyCondition.hasProperties(
                LootContext.EntityTarget.THIS,
                EntityPredicate.Builder.entity()
                    .entityType(EntityTypePredicate.of(context.lookup(Registries.ENTITY_TYPE), LipartefactsEntityTypeTagProvider.SENSITIVE_TO_ILLAGER_BANE))
            )
        );
    }

    public Collection<TagKey<Enchantment>> getEnchantmentTagList() {
        Collection<TagKey<Enchantment>> list = super.getEnchantmentTagList();

        list.add(EnchantmentTags.DAMAGE_EXCLUSIVE);
        return list;
    }

    public String getName() {
        return "illager_bane";
    };

    public Rarity getRarity() {
        return Rarity.RARE;
    };

}
