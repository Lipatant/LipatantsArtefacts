package lipartefacts.artefacts;

import java.util.Collection;
import java.util.List;

import lipartefacts.artefacts.charms.ArtefactCharm;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class ArtefactCharmList extends ArtefactListModule {

    protected final Identifier OFFHAND_ATTACK_KNOCKBACK = Identifier.withDefaultNamespace("offhand.attack_knockback");
    protected final Identifier OFFHAND_BLOCK_INTERACTION_RANGE = Identifier.withDefaultNamespace("offhand.block_interaction_range");
    protected final Identifier OFFHAND_BURNING_TIME = Identifier.withDefaultNamespace("offhand.burning_time");
    protected final Identifier OFFHAND_MAX_HEALTH = Identifier.withDefaultNamespace("offhand.max_health");
    protected final Identifier OFFHAND_SAFE_FALL_DISTANCE = Identifier.withDefaultNamespace("offhand.safe_fall_distance");
    protected final Identifier OFFHAND_SCALE_ID = Identifier.withDefaultNamespace("offhand.scale");

    public final ArtefactCharm AXOLTL = new ArtefactCharm(
        "axolotl", Rarity.UNCOMMON,
        Attributes.MAX_HEALTH, new AttributeModifier(OFFHAND_MAX_HEALTH, 4.0, AttributeModifier.Operation.ADD_VALUE)
    ).setVariantList(List.of("blue", "cyan", "gold", "lucy", "wild"));
    public final ArtefactCharm CHICKEN = new ArtefactCharm(
        "chicken", Rarity.UNCOMMON,
        Attributes.SAFE_FALL_DISTANCE, new AttributeModifier(OFFHAND_SAFE_FALL_DISTANCE, 1.0, AttributeModifier.Operation.ADD_VALUE)
    );
    public final ArtefactCharm CRAB = new ArtefactCharm(
        "crab", Rarity.UNCOMMON,
        Attributes.BLOCK_INTERACTION_RANGE, new AttributeModifier(OFFHAND_BLOCK_INTERACTION_RANGE, 1.0, AttributeModifier.Operation.ADD_VALUE)
    ).setVariantList(List.of("", "clay", "dirt", "gravel", "sand", "spruce_planks"));
    public final ArtefactCharm ENDERMITE = new ArtefactCharm(
        "endermite", Rarity.EPIC,
        Attributes.SCALE, new AttributeModifier(OFFHAND_SCALE_ID, -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    );
    public final ArtefactCharm RAVAGER = new ArtefactCharm(
        "ravager", Rarity.RARE,
        Attributes.ATTACK_KNOCKBACK, new AttributeModifier(OFFHAND_ATTACK_KNOCKBACK, 1.0, AttributeModifier.Operation.ADD_VALUE)
    );
    public final ArtefactCharm STRIDER = new ArtefactCharm(
        "strider", Rarity.UNCOMMON,
        Attributes.BURNING_TIME, new AttributeModifier(OFFHAND_BURNING_TIME, -1.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
    );

    protected List<ArtefactCharm> getCharmList() {
        return List.of(
            AXOLTL,
            CHICKEN,
            CRAB,
            ENDERMITE,
            RAVAGER,
            STRIDER
        );
    }

    public Collection<ResourceKey<Item>> getIdList() {
        Collection<ResourceKey<Item>> list = super.getIdList();

        getCharmList().forEach((c) -> {
            list.add(c.CHARM_ID);
        });
        return list;
    }

    public Collection<ItemStack> getItemStackList(HolderLookup.Provider registries) {
        Collection<ItemStack> list = super.getItemStackList(registries);

        getCharmList().forEach((c) -> {
            list.addAll(c.getItemStackList(registries));
        });
        return list;
    }

}
