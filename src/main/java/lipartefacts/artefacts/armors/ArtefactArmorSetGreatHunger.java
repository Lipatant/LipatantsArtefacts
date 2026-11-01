package lipartefacts.artefacts.armors;

import java.util.List;
import java.util.function.Function;

import org.jspecify.annotations.Nullable;

import lipartefacts.artefacts.Artefact;
import lipartefacts.artefacts.ArtefactArmorList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

public class ArtefactArmorSetGreatHunger extends ArtefactArmorSet {

    public class ArtefactArmorSetGreatHungerPiece extends Artefact {

        public ArtefactArmorSetGreatHungerPiece(Properties properties) {
            super(properties);
        }

        @Override
        public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
            super.inventoryTick(itemStack, level, owner, slot);
            if (level.isClientSide()) return;
            if (owner instanceof LivingEntity) {
                ItemStack equipment = ((LivingEntity) owner).getItemBySlot(EquipmentSlot.HEAD);
                if (equipment.is(HELMET)) {
                    ((LivingEntity) owner).removeEffect(MobEffects.HUNGER);
                }
            }
        }

    }

    public ArtefactArmorAttribute createArmorAttribute() {
        return super.createArmorAttribute()
            .attributeAddArmor(ArtefactArmorList.makeDefenseDiamond())
            .attributeAdd(Attributes.ARMOR_TOUGHNESS, 2.0F, AttributeModifier.Operation.ADD_VALUE)
            .durability(33)
            .enchantmentValue(10)
            .repairIngredient(Items.COBBLESTONE)
        ;
    }

    public Item.Properties createProperties() {
        return new Item.Properties()
        ;
    }

	public Function<Item.Properties, Item> getItemFactory(ArtefactArmorSetType type) {
        if (type == ArtefactArmorSetType.UPGRADE_SMITHING_TEMPLATE) {
            return getItemFactoryUpgradeSmithingTemplate();
        }
        return (p) -> {
            Rarity rarity = (type == ArtefactArmorSetType.ELYTRA) ? Rarity.EPIC : getRarity();

            if (rarity != null) p.rarity(rarity);
            return new ArtefactArmorSetGreatHungerPiece(p);
        };
	}

    public String getName() {
        return "great_hunger";
    }

    public Rarity getRarity() {
        return Rarity.UNCOMMON;
    };

    public List<ArtefactArmorSetType> getRegisteredList() {
        return List.of(
            ArtefactArmorSetType.HELMET
        );
    }

}
