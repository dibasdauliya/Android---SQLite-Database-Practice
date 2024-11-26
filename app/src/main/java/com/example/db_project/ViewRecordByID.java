package com.example.db_project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class ViewRecordByID extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_record_by_id);

        MaterialButton goHome = findViewById(R.id.goHome);
        goHome.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));

        MaterialButton viewAll = findViewById(R.id.viewAll);
        viewAll.setOnClickListener(v -> startActivity(new Intent(this, AllRecords.class)));

        TextInputEditText idInput = findViewById(R.id.idEditText);

        MaterialButton viewPlayer = findViewById(R.id.actionBtn);
        viewPlayer.setOnClickListener(v -> {
            String idVal = idInput.getText().toString();

            if (idVal.isEmpty()) {
                Toast.makeText(this, "It's empty", Toast.LENGTH_SHORT).show();
                return;
            }

//            Toast.makeText(this, idVal, Toast.LENGTH_SHORT).show();

            DBAdapter db = new DBAdapter(this);
            db.open();
            try {
                Cursor c = db.getContact(Long.parseLong(idVal));
                if (c.moveToFirst())
//                    Toast.makeText(this, "Contact Found", Toast.LENGTH_SHORT).show();
                     DisplayContact(c);
                else
                    Toast.makeText(this, "No player found", Toast.LENGTH_LONG).show();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid ID", Toast.LENGTH_SHORT).show();
            } finally {
                db.close();
            }
        });

    }

    private void DisplayContact(Cursor c) {
        Toast.makeText(this,
                "id: " + c.getString(0) + ", " +
                        "Name: " + c.getString(1) + ", " +
                        "Country:  " + c.getString(2) + ", " +
                "Position:  " + c.getString(3),
                Toast.LENGTH_LONG).show();

    }
}