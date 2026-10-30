package lipartefacts.artefacts.armors;

import java.util.List;

import lipartefacts.artefacts.ArtefactArmorList;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

public class ArtefactArmorSetIncognito extends ArtefactArmorSet {

    private final String NAME;

    public ArtefactArmorSetIncognito(String name) {
        this.NAME = name;
        super();
    }

    public ArtefactArmorAttribute createArmorAttribute() {
        return super.createArmorAttribute()
            .attributeAddArmor(ArtefactArmorList.makeDefenseLeather())
            .attributeAdd(Attributes.NAME_TAG_DISTANCE, -1.0F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .attributeAdd(Attributes.WAYPOINT_TRANSMIT_RANGE, -1.0F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .durability(5)
            .enchantmentValue(15)
            .repairIngredient(Items.PAPER)
        ;
    }

    public String getName() {
        return NAME;
    }

    public Rarity getRarity() {
        return Rarity.UNCOMMON;
    }

    public List<ArtefactArmorSetType> getRegisteredList() {
        return List.of(
            ArtefactArmorSetType.HELMET
        );
    }

}
