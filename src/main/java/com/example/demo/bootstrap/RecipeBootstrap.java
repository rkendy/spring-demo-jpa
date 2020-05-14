package com.example.demo.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.Category;
import com.example.demo.model.Difficulty;
import com.example.demo.model.Ingredient;
import com.example.demo.model.Notes;
import com.example.demo.model.Recipe;
import com.example.demo.model.UnitOfMeasure;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.RecipeRepository;
import com.example.demo.repository.UnitOfMeasureRepository;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    // Constructor injection. No need to @Autowire repositories
    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
            UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        categoryRepository.count();
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {
        UnitOfMeasure eachUom = getUnitOfMeasureByDescription("Each");
        UnitOfMeasure tablespoonUom = getUnitOfMeasureByDescription("Tablespoon");
        UnitOfMeasure teaspoonUom = getUnitOfMeasureByDescription("Teaspoon");
        UnitOfMeasure dashUom = getUnitOfMeasureByDescription("Dash");
        UnitOfMeasure cupUom = getUnitOfMeasureByDescription("Cup");

        Category americanCategory = getCategoryByDescription("American");
        Category mexicanCategory = getCategoryByDescription("Mexican");

        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("Directions...");
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Notes...");
        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(getIngredient("ripe avocados", new BigDecimal(12), eachUom, guacRecipe));
        guacRecipe.addIngredient(getIngredient("Kosher salt", new BigDecimal(".5"), teaspoonUom, guacRecipe));
        guacRecipe.addIngredient(getIngredient("lime juice", new BigDecimal(2), tablespoonUom, guacRecipe));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(guacRecipe);
        return recipes;
    }

    private UnitOfMeasure getUnitOfMeasureByDescription(String description) {
        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByDescription(description);
        if (!uom.isPresent()) {
            throw new RuntimeException("Expected UOM " + description + " Not Found");
        }
        return uom.get();
    }

    private Category getCategoryByDescription(String description) {
        Optional<Category> category = categoryRepository.findByDescription(description);
        if (!category.isPresent()) {
            throw new RuntimeException("Expected Category " + description + " Not Found");
        }
        return category.get();
    }

    private Ingredient getIngredient(String desc, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        Ingredient ingredient = new Ingredient();
        ingredient.setDescription(desc);
        ingredient.setAmount(amount);
        ingredient.setUnitOfMeasure(uom);
        ingredient.setRecipe(recipe);
        return ingredient;
    }

}