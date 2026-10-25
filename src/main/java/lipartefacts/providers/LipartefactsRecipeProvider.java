package lipartefacts.providers;

import java.util.concurrent.CompletableFuture;

import org.jspecify.annotations.NonNull;

import lipartefacts.artefacts.ArtefactList;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.crafting.Recipe;

public class LipartefactsRecipeProvider extends FabricRecipeProvider {

    class RecipeProviderInstance extends RecipeProvider {

        public RecipeProviderInstance(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
            super(recipeOutput, advancementOutput);
        }

        @Override
        public void buildRecipes() {
            ArtefactList.buildRecipes(this, this.output);
        }

    }

    public LipartefactsRecipeProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(Provider registries, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
        return new RecipeProviderInstance(recipes, advancements);
    }

    @Override
    public @NonNull String getName() {
        return "Lipatant's Artefacts Recipe Provider";
    }

}
