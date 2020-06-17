package com.example.demo.service;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.model.Recipe;
import com.example.demo.repository.RecipeRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceImplJUnit5Test {

    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    RecipeServiceImpl recipeService;

    // JUnit5: org.junit.jupiter.api.Test:
    @Test
    public void getRecipes() throws Exception {
        Recipe recipe = new Recipe();
        Set<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipies();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void saveRecipe() {
        Recipe recipe = new Recipe();
        when(recipeRepository.save(any())).thenReturn(recipe);
        Recipe returned = recipeService.save(recipe);
        assertSame(recipe, returned);
        verify(recipeRepository).save(any());
    }

    @Test
    public void deleteRecipe() {
        Recipe recipe = new Recipe();
        recipeService.delete(recipe);
        verify(recipeRepository).delete(any());
    }

}