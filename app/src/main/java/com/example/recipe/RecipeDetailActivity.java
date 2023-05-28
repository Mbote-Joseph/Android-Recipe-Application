package com.example.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;


public class RecipeDetailActivity extends AppCompatActivity {
    private TextView textViewName;
    private TextView textViewIngredients;
    private TextView textViewInstructions;
    private RecipeViewModel recipeViewModel;
    private Recipe currentRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        textViewName = findViewById(R.id.textViewName);
        textViewIngredients = findViewById(R.id.textViewIngredients);
        textViewInstructions = findViewById(R.id.textViewInstructions);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(MainActivity.EXTRA_RECIPE)) {
            currentRecipe = intent.getParcelableExtra(MainActivity.EXTRA_RECIPE);
            if (currentRecipe != null) {
                displayRecipeDetails(currentRecipe);
                Button buttonEdit = findViewById(R.id.buttonEdit);
                buttonEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editRecipe(currentRecipe);
                    }
                });
                Button buttonDelete = findViewById(R.id.buttonDelete);
                buttonDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteRecipe(currentRecipe);
                    }
                });
            }
        }
    }

    private void displayRecipeDetails(Recipe recipe) {
        textViewName.setText(recipe.getName());
        textViewIngredients.setText(recipe.getIngredients());
        textViewInstructions.setText(recipe.getInstructions());
    }

    private void editRecipe(Recipe recipe) {
        Intent intent = new Intent(this, EditRecipeActivity.class);
        intent.putExtra(MainActivity.EXTRA_RECIPE, recipe);
        startActivity(intent);
    }

    private void deleteRecipe(Recipe recipe) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Recipe");
        builder.setMessage("Are you sure you want to delete this recipe?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                recipeViewModel.delete(recipe);
                finish();
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
