package lipartefacts.client;

import org.jspecify.annotations.NonNull;

import lipartefacts.artefacts.ArtefactList;
import lipartefacts.providers.LipartefactsEnchantmentProvider;
import lipartefacts.providers.LipartefactsEnchantmentTagProvider;
import lipartefacts.providers.LipartefactsEntityTypeTagProvider;
import lipartefacts.providers.LipartefactsItemTagProvider;
import lipartefacts.providers.LipartefactsJukeboxSongProvider;
import lipartefacts.providers.LipartefactsRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class LipartefactsDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(@NonNull FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(LipartefactsEnchantmentProvider::new);
        pack.addProvider(LipartefactsEnchantmentTagProvider::new);
        pack.addProvider(LipartefactsEntityTypeTagProvider::new);
        pack.addProvider(LipartefactsItemTagProvider::new);
        pack.addProvider(LipartefactsJukeboxSongProvider::new);
        pack.addProvider(LipartefactsRecipeProvider::new);
    }

    @Override
    public void buildRegistry(@NonNull RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.ENCHANTMENT, LipartefactsEnchantmentProvider::bootstrap);
        registryBuilder.add(Registries.JUKEBOX_SONG, ArtefactList.MUSIC_DISC::bootstrap);
    }

}
