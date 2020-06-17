package com.example.demo.service;

import java.util.Set;

import com.example.demo.model.Recipe;

public interface RecipeService {
    Set<Recipe> getRecipies();

    Recipe save(Recipe recipe);

    void delete(Recipe recipe);
}