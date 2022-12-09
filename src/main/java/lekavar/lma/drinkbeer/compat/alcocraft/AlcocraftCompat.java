package lekavar.lma.drinkbeer.compat.alcocraft;

import lekavar.lma.drinkbeer.DrinkBeer;
import lekavar.lma.drinkbeer.compat.IRecipeInjector;
import lekavar.lma.drinkbeer.recipes.BrewingRecipe;
// import net.hadrus.alcocraft.items.AlcoItems;
// import net.hadrus.alcocraft.recipes.KegRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class AlcocraftCompat implements IRecipeInjector {

    // Alcocraft has crashing issue. See issue https://github.com/hadrus/Alcocraft/issues/10.
    @Override
    public void injectRecipes(Map<ResourceLocation, Recipe<?>> existed, BiConsumer<ResourceLocation, Recipe<?>> inject) {
        /*var alcocraftRecipes = existed.entrySet().stream().filter(recipe -> recipe instanceof KegRecipe).collect(Collectors.toList());
        var drinkbeerRecipes = existed.entrySet().stream().filter(recipe -> recipe instanceof BrewingRecipe).collect(Collectors.toList());

        DrinkBeer.LOGGER.debug("Found {} Drink Beer brewing recipes", drinkbeerRecipes.size());
        DrinkBeer.LOGGER.debug("Found {} Alcocraft brewing recipes", alcocraftRecipes.size());

        alcocraftRecipes.forEach((e) ->{
            var id = new ResourceLocation(DrinkBeer.MOD_ID, "brewing/" + e.getKey().getNamespace() + e.getKey().getPath());
            var newRecipe = new BrewingRecipe(id,e.getValue().getIngredients(), AlcoItems.SPRUCE_EMPTY_MUG.get().getDefaultInstance(),18000,e.getValue().getResultItem().copy());
            inject.accept(id, newRecipe);
        });

        drinkbeerRecipes = existed.entrySet().stream().filter(recipe -> recipe instanceof BrewingRecipe).collect(Collectors.toList());
        DrinkBeer.LOGGER.debug("Inject finished. Found {} Drink Beer brewing recipes", drinkbeerRecipes.size());*/
    }
}
