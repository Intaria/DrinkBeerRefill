package lekavar.lma.drinkbeer.compat;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;

import java.util.Map;
import java.util.function.BiConsumer;

public interface IRecipeInjector {
    void injectRecipes(Map<ResourceLocation, Recipe<?>> existed, BiConsumer<ResourceLocation, Recipe<?>> inject);
}
