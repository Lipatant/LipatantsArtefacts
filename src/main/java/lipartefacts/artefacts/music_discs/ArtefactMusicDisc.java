package lipartefacts.artefacts.music_discs;

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

public class ArtefactMusicDisc {

    private final ResourceKey<JukeboxSong> JUKEBOX_SONG;
    private final Holder.Reference<SoundEvent> SOUND_EVENT;

    public final Item MUSIC_DISC;
    public final ResourceKey<Item> MUSIC_DISC_ID;

    private final int comparatorOutput;
    private final int lengthInSeconds;
    private final String name;
    private final Rarity rarity;

    public ArtefactMusicDisc(String name, Rarity rarity, int lengthInSeconds, int comparatorOutput) {
        this.comparatorOutput = comparatorOutput;
        this.lengthInSeconds = lengthInSeconds;
        this.name = name;
        this.rarity = rarity;
        this.SOUND_EVENT = registerSoundEvent("music_disc." + getName());
        this.JUKEBOX_SONG = ResourceKey.create(Registries.JUKEBOX_SONG, Identifier.fromNamespaceAndPath(Lipartefacts.MOD_ID, getName()));
        this.MUSIC_DISC_ID = ItemIdList.create("music_disc_" + getName());
        this.MUSIC_DISC = ItemList.register(MUSIC_DISC_ID, Item::new, createProperties());
    }

    public void bootstrap(BootstrapContext<JukeboxSong> context) {
        context.register(
            JUKEBOX_SONG,
            new JukeboxSong(
                SOUND_EVENT,
                Component.translatable(Util.makeDescriptionId("jukebox_song", JUKEBOX_SONG.identifier())),
                lengthInSeconds,
                comparatorOutput
            )
        );
    }

    public Item.Properties createProperties() {
        Item.Properties properties = new Item.Properties()
            .jukeboxPlayable(JUKEBOX_SONG)
            .stacksTo(1)
        ;
        Rarity rarity = getRarity();

        if (rarity != null) properties.rarity(rarity);
        return properties;
    }

    public final String getName() {
        return this.name;
    };

    public final Rarity getRarity() {
        return this.rarity;
    };

    protected Holder.Reference<SoundEvent> registerSoundEvent(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(Lipartefacts.MOD_ID, name);
        return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

}
