package lipartefacts.providers;

import java.util.concurrent.CompletableFuture;

import lipartefacts.artefacts.ArtefactList;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;

public class LipartefactsEnchantmentTagProvider extends FabricTagsProvider<Enchantment> {

    public LipartefactsEnchantmentTagProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
        super(output, Registries.ENCHANTMENT, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        ArtefactList.ENCHANTMENT.getEnchantmentTagList().forEach((t, l) -> {
            builder(t).addAll(l);
        });
    }

}
