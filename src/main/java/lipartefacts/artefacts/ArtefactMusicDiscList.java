package lipartefacts.artefacts;

import lipartefacts.ItemIdList;
import lipartefacts.ItemList;
import lipartefacts.Lipartefacts;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.Rarity;

public class ArtefactMusicDiscList {

    private static final Holder.Reference<SoundEvent> GROOVOTATIONAL_SOUND_EVENT = registerSoundEvent("music_disc.groovotational");
    private static final Holder.Reference<SoundEvent> MINERAL_WINTER_SOUND_EVENT = registerSoundEvent("music_disc.mineral_winter");
    private static final Holder.Reference<SoundEvent> MOMENT_OF_MELTING_SOLITUDE_SOUND_EVENT = registerSoundEvent("music_disc.moment_of_melting_solitude");
    private static final Holder.Reference<SoundEvent> SPACE_CASTER_SOUND_EVENT = registerSoundEvent("music_disc.space_caster");
    private static final Holder.Reference<SoundEvent> TOKYOS_GOING_DOWN_SOUND_EVENT = registerSoundEvent("music_disc.tokyos_going_down");

    private static final ResourceKey<JukeboxSong> GROOVOTATIONAL_JUKEBOX_SONG = ResourceKey.create(Registries.JUKEBOX_SONG, Identifier.fromNamespaceAndPath(Lipartefacts.MOD_ID, "groovotational"));
    private static final ResourceKey<JukeboxSong> MINERAL_WINTER_JUKEBOX_SONG = ResourceKey.create(Registries.JUKEBOX_SONG, Identifier.fromNamespaceAndPath(Lipartefacts.MOD_ID, "mineral_winter"));
    private static final ResourceKey<JukeboxSong> MOMENT_OF_MELTING_SOLITUDE_JUKEBOX_SONG = ResourceKey.create(Registries.JUKEBOX_SONG, Identifier.fromNamespaceAndPath(Lipartefacts.MOD_ID, "moment_of_melting_solitude"));
    private static final ResourceKey<JukeboxSong> SPACE_CASTER_JUKEBOX_SONG = ResourceKey.create(Registries.JUKEBOX_SONG, Identifier.fromNamespaceAndPath(Lipartefacts.MOD_ID, "space_caster"));
    private static final ResourceKey<JukeboxSong> TOKYOS_GOING_DOWN_JUKEBOX_SONG = ResourceKey.create(Registries.JUKEBOX_SONG, Identifier.fromNamespaceAndPath(Lipartefacts.MOD_ID, "tokyos_going_down"));

    public static final Item MUSIC_DISC_GROOVOTATIONAL = ItemList.register(ItemIdList.MUSIC_DISC_GROOVOTATIONAL, Artefact::new, createProperties(GROOVOTATIONAL_JUKEBOX_SONG)
        .rarity(Rarity.UNCOMMON)
    );

    public static final Item MUSIC_DISC_MINERAL_WINTER = ItemList.register(ItemIdList.MUSIC_DISC_MINERAL_WINTER, Artefact::new, createProperties(MINERAL_WINTER_JUKEBOX_SONG)
        .rarity(Rarity.UNCOMMON)
    );

    public static final Item MUSIC_DISC_MOMENT_OF_MELTING_SOLITUDE = ItemList.register(ItemIdList.MUSIC_DISC_MOMENT_OF_MELTING_SOLITUDE, Artefact::new, createProperties(MOMENT_OF_MELTING_SOLITUDE_JUKEBOX_SONG)
        .rarity(Rarity.UNCOMMON)
    );

    public static final Item MUSIC_DISC_SPACE_CASTER = ItemList.register(ItemIdList.MUSIC_DISC_SPACE_CASTER, Artefact::new, createProperties(SPACE_CASTER_JUKEBOX_SONG)
        .rarity(Rarity.UNCOMMON)
    );

    public static final Item MUSIC_DISC_TOKYOS_GOING_DOWN = ItemList.register(ItemIdList.MUSIC_DISC_TOKYOS_GOING_DOWN, Artefact::new, createProperties(TOKYOS_GOING_DOWN_JUKEBOX_SONG)
        .rarity(Rarity.UNCOMMON)
    );

    /**
     * Initializes data.
     */
    public static void bootstrap(BootstrapContext<JukeboxSong> context) {
        registerJukeboxSong(context, GROOVOTATIONAL_JUKEBOX_SONG, GROOVOTATIONAL_SOUND_EVENT, 86, 3);
        registerJukeboxSong(context, MINERAL_WINTER_JUKEBOX_SONG, MINERAL_WINTER_SOUND_EVENT, 124, 4);
        registerJukeboxSong(context, MOMENT_OF_MELTING_SOLITUDE_JUKEBOX_SONG, MOMENT_OF_MELTING_SOLITUDE_SOUND_EVENT, 83, 2);
        registerJukeboxSong(context, SPACE_CASTER_JUKEBOX_SONG, SPACE_CASTER_SOUND_EVENT, 103, 1);
        registerJukeboxSong(context, TOKYOS_GOING_DOWN_JUKEBOX_SONG, TOKYOS_GOING_DOWN_SOUND_EVENT, 124, 5);
    }

    /**
     * Generates the default properties of a music disc.
     */
    public static Item.Properties createProperties(ResourceKey<JukeboxSong> jukeboxSong) {
        return ArtefactList.createProperties()
            .jukeboxPlayable(jukeboxSong)
            .stacksTo(1)
        ;
    }

    /**
     * Initializes the class.
     */
    public static void initialize() {}

    /**
     * Registers a jukebox song.
     */
    protected  static void registerJukeboxSong(BootstrapContext<JukeboxSong> context, ResourceKey<JukeboxSong> key, Holder.Reference<SoundEvent> soundEvent, int lengthInSeconds, int comparatorOutput) {
        context.register(key, new JukeboxSong(soundEvent, Component.translatable(Util.makeDescriptionId("jukebox_song", key.identifier())), lengthInSeconds, comparatorOutput));
    }

    /**
     * Registers a sound event.
     */
    @SuppressWarnings("null")
    protected static Holder.Reference<SoundEvent> registerSoundEvent(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(Lipartefacts.MOD_ID, name);
        return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

}
