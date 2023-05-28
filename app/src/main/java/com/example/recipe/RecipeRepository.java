package com.example.recipe;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class RecipeRepository {

    private RecipeDatabaseHelper databaseHelper;
    private MutableLiveData<List<Recipe>> allRecipes;

    public RecipeRepository(Application application) {
        databaseHelper = new RecipeDatabaseHelper(application);
        allRecipes = new MutableLiveData<>();
        loadAllRecipes();
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return allRecipes;
    }

    public void insert(Recipe recipe) {
        databaseHelper.insertRecipe(recipe);
        loadAllRecipes(); // Update LiveData with new data
    }

    public void update(Recipe recipe) {
        databaseHelper.updateRecipe(recipe);
        loadAllRecipes(); // Update LiveData with new data
    }

    public void delete(Recipe recipe) {
        databaseHelper.deleteRecipe(recipe);
        loadAllRecipes(); // Update LiveData with new data
    }

    private void loadAllRecipes() {
        List<Recipe> recipes = databaseHelper.getAllRecipes();
        allRecipes.setValue(recipes); // Set the new value to LiveData
    }
}
