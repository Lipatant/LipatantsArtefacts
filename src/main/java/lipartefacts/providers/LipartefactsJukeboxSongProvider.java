package lipartefacts.providers;

import java.util.concurrent.CompletableFuture;

import org.jspecify.annotations.NonNull;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;

public class LipartefactsJukeboxSongProvider extends FabricDynamicRegistryProvider {

    public LipartefactsJukeboxSongProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.JUKEBOX_SONG));
    }

    @Override
    public @NonNull String getName() {
        return "Lipatant's Artefacts Jukebox Song Provider";
    }

}
