package lipartefacts.artefacts.enchantments;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;

public class ArtefactEnchantmentDoubleShot extends ArtefactEnchantment {

    public Enchantment.Builder getEnchantmentDefinition(BootstrapContext<Enchantment> context) {
        return Enchantment.enchantment(
            Enchantment.definition(
                context.lookup(Registries.ITEM).getOrThrow(ItemTags.BOW_ENCHANTABLE),
                5,
                1,
                Enchantment.dynamicCost(45, 8),
                Enchantment.dynamicCost(65, 8),
                4,
                EquipmentSlotGroup.MAINHAND
            )
        )
        .withEffect(
            EnchantmentEffectComponents.PROJECTILE_COUNT,
            new AddValue(LevelBasedValue.perLevel(1.0F, 1.0F))
        )
        .withEffect(
            EnchantmentEffectComponents.PROJECTILE_SPREAD,
            new AddValue(LevelBasedValue.perLevel(2.0F, 1.0F))
        );
    }

    public String getName() {
        return "double_shot";
    };

    public Rarity getRarity() {
        return Rarity.RARE;
    };

}
