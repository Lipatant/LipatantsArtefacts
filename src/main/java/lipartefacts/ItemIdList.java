package lipartefacts;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class ItemIdList {

    public static final ResourceKey<Item> AXOLOTL_CHARM = create("axolotl_charm");
    public static final ResourceKey<Item> CHICKEN_CHARM = create("chicken_charm");
    public static final ResourceKey<Item> CRAB_CHARM = create("crab_charm");
    public static final ResourceKey<Item> ENDERMITE_CHARM = create("endermite_charm");
    public static final ResourceKey<Item> MUSIC_DISC_GROOVOTATIONAL = create("music_disc_groovotational");
    public static final ResourceKey<Item> MUSIC_DISC_MINERAL_WINTER = create("music_disc_mineral_winter");
    public static final ResourceKey<Item> MUSIC_DISC_MOMENT_OF_MELTING_SOLITUDE = create("music_disc_moment_of_melting_solitude");
    public static final ResourceKey<Item> MUSIC_DISC_SPACE_CASTER = create("music_disc_space_caster");
    public static final ResourceKey<Item> MUSIC_DISC_TOKYOS_GOING_DOWN = create("music_disc_tokyos_going_down");
    public static final ResourceKey<Item> RAVAGER_CHARM = create("ravager_charm");
    public static final ResourceKey<Item> STRIDER_CHARM = create("strider_charm");

    /**
     * Registers a new generic item from its id.
     */
    public static ResourceKey<Item> create(String name) {
        return ResourceKey.create(Registries.ITEM, Lipartefacts.id(name));
    }

}
