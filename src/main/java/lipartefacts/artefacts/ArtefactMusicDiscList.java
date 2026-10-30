package lipartefacts.artefacts;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import lipartefacts.artefacts.music_discs.ArtefactMusicDisc;
import lipartefacts.providers.LipartefactsItemTagProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;

public class ArtefactMusicDiscList extends ArtefactListModule {

    public final ArtefactMusicDisc GROOVOTATIONAL = new ArtefactMusicDisc(
        "groovotational", Rarity.UNCOMMON,
        86, 3
    );
    public final ArtefactMusicDisc MINERAL_WINTER = new ArtefactMusicDisc(
        "mineral_winter", Rarity.UNCOMMON,
        124, 4
    );
    public final ArtefactMusicDisc MOMENT_OF_MELTING_SOLITUDE = new ArtefactMusicDisc(
        "moment_of_melting_solitude", Rarity.UNCOMMON,
        83, 2
    );
    public final ArtefactMusicDisc SPACE_CASTER = new ArtefactMusicDisc(
        "space_caster", Rarity.UNCOMMON,
        103, 1
    );
    public final ArtefactMusicDisc TOYKOS_GOING_DOWN = new ArtefactMusicDisc(
        "tokyos_going_down", Rarity.UNCOMMON,
        124, 5
    );

    public void bootstrap(BootstrapContext<JukeboxSong> context) {
        getMusicDiscList().forEach((m) -> {
            m.bootstrap(context);
        });
    }

    public Collection<ResourceKey<Item>> getIdList() {
        Collection<ResourceKey<Item>> list = super.getIdList();

        getMusicDiscList().forEach((m) -> {
            list.add(m.MUSIC_DISC_ID);
        });
        return list;
    }

    public Map<TagKey<Item>, Collection<ResourceKey<Item>>> getItemTagList() {
        Map<TagKey<Item>, Collection<ResourceKey<Item>>> list = super.getItemTagList();

        list.put(LipartefactsItemTagProvider.MUSIC_DISC, getIdList());
        return list;
    }

    public Collection<Item> getList() {
        Collection<Item> list = super.getList();

        getMusicDiscList().forEach((m) -> {
            list.add(m.MUSIC_DISC);
        });
        return list;
    }

    public LootPool.Builder getLootPool() {
        LootPool.Builder lootPool = LootPool.lootPool();

        getList().forEach((m) -> {
            lootPool.add(LootItem.lootTableItem(m));
        });
        return lootPool;
    }

    public LootPool.Builder getLootPool(Rarity rarity) {
        LootPool.Builder lootPool = LootPool.lootPool();

        getMusicDiscList().forEach((m) -> {
            if (m.getRarity() == rarity) lootPool.add(LootItem.lootTableItem(m.MUSIC_DISC));
        });
        return lootPool;
    }

    protected List<ArtefactMusicDisc> getMusicDiscList() {
        return List.of(
            GROOVOTATIONAL,
            MINERAL_WINTER,
            MOMENT_OF_MELTING_SOLITUDE,
            SPACE_CASTER,
            TOYKOS_GOING_DOWN
        );
    }

}
