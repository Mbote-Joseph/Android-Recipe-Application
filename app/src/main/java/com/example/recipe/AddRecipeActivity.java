package com.example.recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddRecipeActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextIngredients;
    private EditText editTextInstructions;
    private Button buttonAdd;

    private RecipeViewModel recipeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        editTextName = findViewById(R.id.editTextName);
        editTextIngredients = findViewById(R.id.editTextIngredients);
        editTextInstructions = findViewById(R.id.editTextInstructions);
        buttonAdd = findViewById(R.id.buttonAdd);

        recipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRecipe();
            }
        });
    }

    private void addRecipe() {
        String name = editTextName.getText().toString().trim();
        String ingredients = editTextIngredients.getText().toString().trim();
        String instructions = editTextInstructions.getText().toString().trim();

        if (name.isEmpty() || ingredients.isEmpty() || instructions.isEmpty()) {
            // Handle validation and show error messages if any field is empty
            return;
        }

        Recipe recipe = new Recipe(0, name, ingredients, instructions);
        recipeViewModel.insert(recipe);

        finish(); // Close the activity after adding the recipe
    }
}
