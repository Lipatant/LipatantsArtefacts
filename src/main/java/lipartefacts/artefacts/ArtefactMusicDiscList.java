package lipartefacts.artefacts;

import java.util.Collection;
import java.util.Map;

import lipartefacts.ItemIdList;
import lipartefacts.ItemList;
import lipartefacts.Lipartefacts;
import lipartefacts.providers.LipartefactsItemTagProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;

public class ArtefactMusicDiscList extends ArtefactListModule {

    private final Holder.Reference<SoundEvent> GROOVOTATIONAL_SOUND_EVENT = registerSoundEvent("music_disc.groovotational");
    private final Holder.Reference<SoundEvent> MINERAL_WINTER_SOUND_EVENT = registerSoundEvent("music_disc.mineral_winter");
    private final Holder.Reference<SoundEvent> MOMENT_OF_MELTING_SOLITUDE_SOUND_EVENT = registerSoundEvent("music_disc.moment_of_melting_solitude");
    private final Holder.Reference<SoundEvent> SPACE_CASTER_SOUND_EVENT = registerSoundEvent("music_disc.space_caster");
    private final Holder.Reference<SoundEvent> TOKYOS_GOING_DOWN_SOUND_EVENT = registerSoundEvent("music_disc.tokyos_going_down");

    private final ResourceKey<JukeboxSong> GROOVOTATIONAL_JUKEBOX_SONG = ResourceKey.create(Registries.JUKEBOX_SONG, Identifier.fromNamespaceAndPath(Lipartefacts.MOD_ID, "groovotational"));
    private final ResourceKey<JukeboxSong> MINERAL_WINTER_JUKEBOX_SONG = ResourceKey.create(Registries.JUKEBOX_SONG, Identifier.fromNamespaceAndPath(Lipartefacts.MOD_ID, "mineral_winter"));
    private final ResourceKey<JukeboxSong> MOMENT_OF_MELTING_SOLITUDE_JUKEBOX_SONG = ResourceKey.create(Registries.JUKEBOX_SONG, Identifier.fromNamespaceAndPath(Lipartefacts.MOD_ID, "moment_of_melting_solitude"));
    private final ResourceKey<JukeboxSong> SPACE_CASTER_JUKEBOX_SONG = ResourceKey.create(Registries.JUKEBOX_SONG, Identifier.fromNamespaceAndPath(Lipartefacts.MOD_ID, "space_caster"));
    private final ResourceKey<JukeboxSong> TOKYOS_GOING_DOWN_JUKEBOX_SONG = ResourceKey.create(Registries.JUKEBOX_SONG, Identifier.fromNamespaceAndPath(Lipartefacts.MOD_ID, "tokyos_going_down"));

    public final Item MUSIC_DISC_GROOVOTATIONAL = ItemList.register(ItemIdList.MUSIC_DISC_GROOVOTATIONAL, Artefact::new, createProperties(GROOVOTATIONAL_JUKEBOX_SONG)
        .rarity(Rarity.UNCOMMON)
    );

    public final Item MUSIC_DISC_MINERAL_WINTER = ItemList.register(ItemIdList.MUSIC_DISC_MINERAL_WINTER, Artefact::new, createProperties(MINERAL_WINTER_JUKEBOX_SONG)
        .rarity(Rarity.UNCOMMON)
    );

    public final Item MUSIC_DISC_MOMENT_OF_MELTING_SOLITUDE = ItemList.register(ItemIdList.MUSIC_DISC_MOMENT_OF_MELTING_SOLITUDE, Artefact::new, createProperties(MOMENT_OF_MELTING_SOLITUDE_JUKEBOX_SONG)
        .rarity(Rarity.UNCOMMON)
    );

    public final Item MUSIC_DISC_SPACE_CASTER = ItemList.register(ItemIdList.MUSIC_DISC_SPACE_CASTER, Artefact::new, createProperties(SPACE_CASTER_JUKEBOX_SONG)
        .rarity(Rarity.UNCOMMON)
    );

    public final Item MUSIC_DISC_TOKYOS_GOING_DOWN = ItemList.register(ItemIdList.MUSIC_DISC_TOKYOS_GOING_DOWN, Artefact::new, createProperties(TOKYOS_GOING_DOWN_JUKEBOX_SONG)
        .rarity(Rarity.UNCOMMON)
    );

    public void bootstrap(BootstrapContext<JukeboxSong> context) {
        registerJukeboxSong(context, GROOVOTATIONAL_JUKEBOX_SONG, GROOVOTATIONAL_SOUND_EVENT, 86, 3);
        registerJukeboxSong(context, MINERAL_WINTER_JUKEBOX_SONG, MINERAL_WINTER_SOUND_EVENT, 124, 4);
        registerJukeboxSong(context, MOMENT_OF_MELTING_SOLITUDE_JUKEBOX_SONG, MOMENT_OF_MELTING_SOLITUDE_SOUND_EVENT, 83, 2);
        registerJukeboxSong(context, SPACE_CASTER_JUKEBOX_SONG, SPACE_CASTER_SOUND_EVENT, 103, 1);
        registerJukeboxSong(context, TOKYOS_GOING_DOWN_JUKEBOX_SONG, TOKYOS_GOING_DOWN_SOUND_EVENT, 124, 5);
    }

    public Item.Properties createProperties(ResourceKey<JukeboxSong> jukeboxSong) {
        return super.createProperties()
            .jukeboxPlayable(jukeboxSong)
            .stacksTo(1)
        ;
    }

    public Map<TagKey<Item>, Collection<ResourceKey<Item>>> getItemTagList() {
        Map<TagKey<Item>, Collection<ResourceKey<Item>>> list = super.getItemTagList();

        list.put(LipartefactsItemTagProvider.MUSIC_DISC, getIdList());
        return list;
    }

    public Collection<ResourceKey<Item>> getIdList() {
        Collection<ResourceKey<Item>> list = super.getIdList();

        list.add(ItemIdList.MUSIC_DISC_GROOVOTATIONAL);
        list.add(ItemIdList.MUSIC_DISC_MINERAL_WINTER);
        list.add(ItemIdList.MUSIC_DISC_MOMENT_OF_MELTING_SOLITUDE);
        list.add(ItemIdList.MUSIC_DISC_SPACE_CASTER);
        list.add(ItemIdList.MUSIC_DISC_TOKYOS_GOING_DOWN);
        return list;
    }

    public Collection<Item> getList() {
        Collection<Item> list = super.getList();

        list.add(MUSIC_DISC_GROOVOTATIONAL);
        list.add(MUSIC_DISC_MINERAL_WINTER);
        list.add(MUSIC_DISC_MOMENT_OF_MELTING_SOLITUDE);
        list.add(MUSIC_DISC_SPACE_CASTER);
        list.add(MUSIC_DISC_TOKYOS_GOING_DOWN);
        return list;
    }

    public LootPool.Builder getLootPool() {
        LootPool.Builder lootPool = LootPool.lootPool();

        getList().forEach((m) -> {
            lootPool.add(LootItem.lootTableItem(m));
        });
        return lootPool;
    }

    protected  void registerJukeboxSong(BootstrapContext<JukeboxSong> context, ResourceKey<JukeboxSong> key, Holder.Reference<SoundEvent> soundEvent, int lengthInSeconds, int comparatorOutput) {
        context.register(key, new JukeboxSong(soundEvent, Component.translatable(Util.makeDescriptionId("jukebox_song", key.identifier())), lengthInSeconds, comparatorOutput));
    }

    protected Holder.Reference<SoundEvent> registerSoundEvent(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(Lipartefacts.MOD_ID, name);
        return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

}
