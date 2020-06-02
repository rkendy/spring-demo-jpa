package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

import com.example.demo.model.Category;
import com.example.demo.model.Recipe;
import com.example.demo.model.UnitOfMeasure;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.UnitOfMeasureRepository;
import com.example.demo.service.RecipeService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTestsIT {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UnitOfMeasureRepository unitOfMeasureReporitory;

	@Autowired
	private RecipeService recipeService;

	@Test
	void contextLoads() {
	}

	@Test
	void repositoryTest() {
		String str = "=====>>> %s ID is %d for %s";
		Optional<Category> category = categoryRepository.findByDescription("Italian");
		System.out.println(String.format(str, "Category", category.get().getId(), category.get().getDescription()));
		assertNotNull(category.get());

		Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureReporitory.findByDescription("Cup");
		assertNotNull(unitOfMeasure.get());

		System.out.println(
				String.format(str, "UnitOfMeasure", unitOfMeasure.get().getId(), unitOfMeasure.get().getDescription()));

	}

	@Test
	void serviceTest() {
		Set<Recipe> recipes = recipeService.getRecipies();
		assertEquals(recipes.size(), 1);

		recipes.forEach(recipe -> {
			System.out.println("========================================>>> Description: " + recipe.getDescription());
			System.out.println("========================================>>> Difficulty: " + recipe.getDifficulty());
			recipe.getCategories().forEach(category -> {
				System.out.println("============>>> Category: " + category.getDescription());
			});
			recipe.getIngredients().forEach(ingredient -> {
				String str = ingredient.getDescription() + ":" + ingredient.getAmount() + ":"
						+ ingredient.getUnitOfMeasure().getDescription();
				System.out.println("============>>> Ingredient: " + str);
			});
		});
	}

}
