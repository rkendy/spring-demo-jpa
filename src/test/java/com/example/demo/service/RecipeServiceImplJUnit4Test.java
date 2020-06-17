package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.model.Recipe;
import com.example.demo.repository.RecipeRepository;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RecipeServiceImplJUnit4Test {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    // JUnit4:
    @org.junit.Test
    public void getRecipes() throws Exception {
        Recipe recipe = new Recipe();
        Set<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipies();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }

}