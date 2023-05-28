package com.example.recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditRecipeActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextIngredients;
    private EditText editTextInstructions;
    private Button buttonSave;

    private RecipeViewModel recipeViewModel;
    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);

        editTextName = findViewById(R.id.editTextName);
        editTextIngredients = findViewById(R.id.editTextIngredients);
        editTextInstructions = findViewById(R.id.editTextInstructions);
        buttonSave = findViewById(R.id.buttonSave);

        recipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(MainActivity.EXTRA_RECIPE)) {
            recipe = intent.getParcelableExtra(MainActivity.EXTRA_RECIPE);
            if (recipe != null) {
                displayRecipeDetails(recipe);
            }
        }

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRecipe();
            }
        });
    }

    private void displayRecipeDetails(Recipe recipe) {
        editTextName.setText(recipe.getName());
        editTextIngredients.setText(recipe.getIngredients());
        editTextInstructions.setText(recipe.getInstructions());
    }

    private void saveRecipe() {
        String name = editTextName.getText().toString().trim();
        String ingredients = editTextIngredients.getText().toString().trim();
        String instructions = editTextInstructions.getText().toString().trim();

        if (name.isEmpty() || ingredients.isEmpty() || instructions.isEmpty()) {
            // Handle validation and show error messages if any field is empty
            return;
        }

        if (recipe != null) {
            recipe.setName(name);
            recipe.setIngredients(ingredients);
            recipe.setInstructions(instructions);
            recipeViewModel.update(recipe);
        }

        finish(); // Close the activity after saving the changes
    }
}
