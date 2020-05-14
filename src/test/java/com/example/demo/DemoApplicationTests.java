package com.example.demo;

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
class DemoApplicationTests {

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
		String str = "%s ID is %d for %s";
		Optional<Category> category = categoryRepository.findByDescription("Italian");
		System.out.println(String.format(str, "Category", category.get().getId(), category.get().getDescription()));

		Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureReporitory.findByDescription("Cup");
		System.out.println(
				String.format(str, "UnitOfMeasure", unitOfMeasure.get().getId(), unitOfMeasure.get().getDescription()));

	}

	@Test
	void serviceTest() {
		Set<Recipe> recipes = recipeService.getRecipies();
		recipes.forEach(recipe -> {
			System.out.println(recipe);
		});
	}

}
