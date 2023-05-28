package com.example.recipe;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface RecipeDao {
    void insert(Recipe recipe);
    void update(Recipe recipe);
    void delete(Recipe recipe);
    LiveData<List<Recipe>> getAllRecipes();
}