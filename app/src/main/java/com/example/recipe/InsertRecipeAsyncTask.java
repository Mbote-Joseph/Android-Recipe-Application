package com.example.recipe;

import android.os.AsyncTask;

public class InsertRecipeAsyncTask extends AsyncTask<Recipe, Void, Void> {
    private RecipeDao recipeDao;

    public InsertRecipeAsyncTask(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    @Override
    protected Void doInBackground(Recipe... recipes) {
        recipeDao.insert(recipes[0]);
        return null;
    }
}
