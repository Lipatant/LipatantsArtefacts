package lipartefacts.providers;

import java.util.concurrent.CompletableFuture;

import org.jspecify.annotations.NonNull;

import lipartefacts.Lipartefacts;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class LipartefactsEntityTypeTagProvider extends FabricTagsProvider.EntityTypeTagsProvider {

    public static final TagKey<EntityType<?>> SENSITIVE_TO_ILLAGER_BANE = TagKey.create(Registries.ENTITY_TYPE, Lipartefacts.id("sensitive_to_illager_bane"));

    public LipartefactsEntityTypeTagProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        builder(SENSITIVE_TO_ILLAGER_BANE)
            .addOptionalTag(EntityTypeTags.ILLAGER)
        ;
    }

    @Override
    public @NonNull String getName() {
        return "Lipatant's Artefacts Entity Type Tag Provider";
    }

}
