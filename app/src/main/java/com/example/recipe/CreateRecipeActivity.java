package com.example.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateRecipeActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextIngredients;
    private EditText editTextInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        editTextName = findViewById(R.id.editTextName);
        editTextIngredients = findViewById(R.id.editTextIngredients);
        editTextInstructions = findViewById(R.id.editTextInstructions);

        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRecipe();
            }
        });
    }

    private void saveRecipe() {
        String name = editTextName.getText().toString().trim();
        String ingredients = editTextIngredients.getText().toString().trim();
        String instructions = editTextInstructions.getText().toString().trim();

        if (name.isEmpty() || ingredients.isEmpty() || instructions.isEmpty()) {
            // Display an error message indicating that all fields must be filled
            return;
        }

        Recipe recipe = new Recipe(0,name, ingredients, instructions);

        Intent resultIntent = new Intent();
        resultIntent.putExtra(MainActivity.EXTRA_RECIPE, recipe);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
