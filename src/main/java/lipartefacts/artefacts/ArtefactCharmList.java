package lipartefacts.artefacts;

import java.util.Collection;

import lipartefacts.ItemIdList;
import lipartefacts.ItemList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.Equippable;

public class ArtefactCharmList extends ArtefactListModule {

    protected final Identifier OFFHAND_ATTACK_KNOCKBACK = Identifier.withDefaultNamespace("offhand.attack_knockback");
    protected final Identifier OFFHAND_BLOCK_INTERACTION_RANGE = Identifier.withDefaultNamespace("offhand.block_interaction_range");
    protected final Identifier OFFHAND_BURNING_TIME = Identifier.withDefaultNamespace("offhand.burning_time");
    protected final Identifier OFFHAND_MAX_HEALTH = Identifier.withDefaultNamespace("offhand.max_health");
    protected final Identifier OFFHAND_SAFE_FALL_DISTANCE = Identifier.withDefaultNamespace("offhand.safe_fall_distance");
    protected final Identifier OFFHAND_SCALE_ID = Identifier.withDefaultNamespace("offhand.scale");

    public final Item AXOLOTL_CHARM = ItemList.register(ItemIdList.AXOLOTL_CHARM, ArtefactCharm::new, createProperties()
        .attributes(ItemAttributeModifiers.builder()
            .add(Attributes.MAX_HEALTH, new AttributeModifier(OFFHAND_MAX_HEALTH, 4.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.OFFHAND)
            .build()
        )
        .rarity(Rarity.UNCOMMON)
    );
    public final Item CHICKEN_CHARM = ItemList.register(ItemIdList.CHICKEN_CHARM, ArtefactCharm::new, createProperties()
        .attributes(ItemAttributeModifiers.builder()
            .add(Attributes.SAFE_FALL_DISTANCE, new AttributeModifier(OFFHAND_SAFE_FALL_DISTANCE, 1.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.OFFHAND)
            .build()
        )
        .rarity(Rarity.UNCOMMON)
    );
    public final Item CRAB_CHARM = ItemList.register(ItemIdList.CRAB_CHARM, ArtefactCharm::new, createProperties()
        .attributes(ItemAttributeModifiers.builder()
            .add(Attributes.BLOCK_INTERACTION_RANGE, new AttributeModifier(OFFHAND_BLOCK_INTERACTION_RANGE, 1.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.OFFHAND)
            .build()
        )
        .rarity(Rarity.UNCOMMON)
    );
    public final Item ENDERMITE_CHARM = ItemList.register(ItemIdList.ENDERMITE_CHARM, ArtefactCharm::new, createProperties()
        .attributes(ItemAttributeModifiers.builder()
            .add(Attributes.SCALE, new AttributeModifier(OFFHAND_SCALE_ID, -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.OFFHAND)
            .build()
        )
        .rarity(Rarity.EPIC)
    );
    public final Item RAVAGER_CHARM = ItemList.register(ItemIdList.RAVAGER_CHARM, ArtefactCharm::new, createProperties()
        .attributes(ItemAttributeModifiers.builder()
            .add(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(OFFHAND_ATTACK_KNOCKBACK, 1.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.OFFHAND)
            .build()
        )
        .rarity(Rarity.RARE)
    );
    public final Item STRIDER_CHARM = ItemList.register(ItemIdList.STRIDER_CHARM, ArtefactCharm::new, createProperties()
        .attributes(ItemAttributeModifiers.builder()
            .add(Attributes.BURNING_TIME, new AttributeModifier(OFFHAND_BURNING_TIME, -1.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.OFFHAND)
            .build()
        )
        .fireResistant()
        .rarity(Rarity.UNCOMMON)
    );

    public Item.Properties createProperties() {
        return super.createProperties()
            .component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.OFFHAND).build())
            .stacksTo(1)
        ;
    }

    public Collection<ResourceKey<Item>> getIdList() {
        Collection<ResourceKey<Item>> list = super.getIdList();

        list.add(ItemIdList.AXOLOTL_CHARM);
        list.add(ItemIdList.CHICKEN_CHARM);
        list.add(ItemIdList.CRAB_CHARM);
        list.add(ItemIdList.ENDERMITE_CHARM);
        list.add(ItemIdList.STRIDER_CHARM);
        return list;
    }

    public Collection<Item> getList() {
        Collection<Item> list = super.getList();

        list.add(AXOLOTL_CHARM);
        list.add(CHICKEN_CHARM);
        list.add(CRAB_CHARM);
        list.add(ENDERMITE_CHARM);
        list.add(STRIDER_CHARM);
        return list;
    }

}
