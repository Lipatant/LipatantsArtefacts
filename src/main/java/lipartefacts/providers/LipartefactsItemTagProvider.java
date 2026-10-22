package lipartefacts.providers;

import java.util.concurrent.CompletableFuture;

import org.jspecify.annotations.NonNull;

import lipartefacts.artefacts.ArtefactArmorList;
import lipartefacts.artefacts.ArtefactMusicDiscList;
import lipartefacts.artefacts.armors.ArtefactArmorSetType;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class LipartefactsItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

    public static final TagKey<Item> MUSIC_DISC = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "music_discs"));

    public LipartefactsItemTagProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        builder(ItemTags.CAULDRON_CAN_REMOVE_DYE)
            .add(ArtefactArmorList.SNIFFER.HELMET_ID)
            .add(ArtefactArmorList.SNIFFER.LEGGINGS_ID)
            .setReplace(false)
        ;
        builder(ItemTags.CHEST_ARMOR)
            .addAll(ArtefactArmorList.getIdList(ArtefactArmorSetType.CHESTPLATE))
            .setReplace(false)
        ;
        builder(ItemTags.FOOT_ARMOR)
            .addAll(ArtefactArmorList.getIdList(ArtefactArmorSetType.BOOTS))
            .setReplace(false)
        ;
        builder(ItemTags.HEAD_ARMOR)
            .addAll(ArtefactArmorList.getIdList(ArtefactArmorSetType.HELMET))
            .setReplace(false)
        ;
        builder(ItemTags.LEG_ARMOR)
            .addAll(ArtefactArmorList.getIdList(ArtefactArmorSetType.LEGGINGS))
            .setReplace(false)
        ;
        builder(MUSIC_DISC)
            .addAll(ArtefactMusicDiscList.getIdList())
            .setReplace(false)
        ;
        builder(ItemTags.PIGLIN_SAFE_ARMOR)
            .addAll(ArtefactArmorList.PIGLIN.getIdList())
            .setReplace(false)
        ;
    }

    @Override
    public @NonNull String getName() {
        return "Lipatant's Artefacts Tag Provider";
    }

}
