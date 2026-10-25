package lipartefacts.providers;

import java.util.concurrent.CompletableFuture;

import org.jspecify.annotations.NonNull;

import lipartefacts.artefacts.ArtefactList;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class LipartefactsItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

    public static final TagKey<Item> MUSIC_DISC = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "music_discs"));

    public LipartefactsItemTagProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        ArtefactList.getItemTagList().forEach((t, l) -> {
            builder(t).addAll(l).setReplace(false);
        });
    }

    @Override
    public @NonNull String getName() {
        return "Lipatant's Artefacts Item Tag Provider";
    }

}
