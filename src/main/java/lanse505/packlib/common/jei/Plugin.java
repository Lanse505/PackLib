package lanse505.packlib.common.jei;

import mezz.jei.api.*;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

@JEIPlugin
public class Plugin implements IModPlugin {
    public static IRecipeRegistry recipeRegistry;
    public static IJeiHelpers jeiHelpers;
    public static IIngredientRegistry ingredientRegistry;

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        jeiHelpers = registry.getJeiHelpers();
    }

    @Override
    public void register(IModRegistry registry) {
        ingredientRegistry = registry.getIngredientRegistry();

    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        recipeRegistry = jeiRuntime.getRecipeRegistry();

    }
}
