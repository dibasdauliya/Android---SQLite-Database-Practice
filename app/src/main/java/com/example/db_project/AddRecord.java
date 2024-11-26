package com.example.db_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class AddRecord extends AppCompatActivity {
    private TextInputEditText fullNameEditText;
    private TextInputEditText countryEditText;
    private AutoCompleteTextView positionDropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        fullNameEditText = findViewById(R.id.fullNameEditText);
        countryEditText = findViewById(R.id.countryEditText);
        positionDropdown = findViewById(R.id.positionDropdown);
        MaterialButton addPlayerButton = findViewById(R.id.addPlayerButton);
        MaterialButton goHomeButton = findViewById(R.id.goHome);


        setupPositionDropdown();

        addPlayerButton.setOnClickListener(v -> {
            if (validateInputs()) {
                savePlayer();
            }
        });

        goHomeButton.setOnClickListener(v -> {
            startActivity(new Intent(AddRecord.this, MainActivity.class));
        });
    }

    private void setupPositionDropdown() {
        List<String> positions = new ArrayList<>();
        positions.add("Goalkeeper (GK)");
        positions.add("Center Back (CB)");
        positions.add("Left Back (LB)");
        positions.add("Right Back (RB)");
        positions.add("Defensive Midfielder (DM)");
        positions.add("Central Midfielder (CM)");
        positions.add("Attacking Midfielder (AM)");
        positions.add("Left Winger (LW)");
        positions.add("Right Winger (RW)");
        positions.add("Striker (ST)");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                positions
        );

        positionDropdown.setAdapter(adapter);
    }


    private boolean validateInputs() {
        String fullName = fullNameEditText.getText().toString().trim();
        String country = countryEditText.getText().toString().trim();
        String position = positionDropdown.getText().toString().trim();

        // Check if any field is empty
        if (fullName.isEmpty()) {
            fullNameEditText.setError("Full Name is required");
            return false;
        }

        if (country.isEmpty()) {
            countryEditText.setError("Country is required");
            return false;
        }

        if (position.isEmpty()) {
            positionDropdown.setError("Position is required");
            return false;
        }

        return true;
    }

    private void savePlayer() {
        String fullName = fullNameEditText.getText().toString().trim();
        String country = countryEditText.getText().toString().trim();
        String position = positionDropdown.getText().toString().trim();

        DBAdapter db = new DBAdapter(this);


        db.open();

        db.insertContact(fullName, position, country);
        db.close();

        Toast.makeText(this, "Player Added: " + fullName, Toast.LENGTH_SHORT).show();

        clearFields();
    }

    private void clearFields() {
        fullNameEditText.setText("");
        countryEditText.setText("");
        positionDropdown.setText("");
    }
}