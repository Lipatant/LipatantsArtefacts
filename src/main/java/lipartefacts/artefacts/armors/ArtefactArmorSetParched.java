package lipartefacts.artefacts.armors;

import java.util.List;
import java.util.function.Function;

import org.jspecify.annotations.Nullable;

import lipartefacts.artefacts.Artefact;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class ArtefactArmorSetParched extends ArtefactArmorSet {

    public class ArtefactArmorSetParchedPiece extends Artefact {

        public ArtefactArmorSetParchedPiece(Properties properties) {
            super(properties);
        }

        @Override
        public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
            super.inventoryTick(itemStack, level, owner, slot);
            if (level.isClientSide()) return;
            if (owner instanceof LivingEntity) {
                ItemStack equipment = ((LivingEntity) owner).getItemBySlot(EquipmentSlot.CHEST);
                if (equipment.is(CHESTPLATE)) {
                    ((LivingEntity) owner).removeEffect(MobEffects.WEAKNESS);
                }
            }
        }

    }

    public ArtefactArmorAttribute createArmorAttribute() {
        return super.createArmorAttribute()
            .durability(5)
            .enchantmentValue(15)
        ;
    }

	public Function<Item.Properties, Item> getItemFactory(ArtefactArmorSetType type) {
        if (type == ArtefactArmorSetType.UPGRADE_SMITHING_TEMPLATE) {
            return getItemFactoryUpgradeSmithingTemplate();
        }
        return (p) -> {
            Rarity rarity = (type == ArtefactArmorSetType.ELYTRA) ? Rarity.EPIC : getRarity();

            if (rarity != null) p.rarity(rarity);
            return new ArtefactArmorSetParchedPiece(p);
        };
	}

    public String getName() {
        return "parched";
    };

    public Rarity getRarity() {
        return Rarity.UNCOMMON;
    };

    public List<ArtefactArmorSetType> getRegisteredList() {
        return List.of(
            ArtefactArmorSetType.CHESTPLATE
        );
    }

}
