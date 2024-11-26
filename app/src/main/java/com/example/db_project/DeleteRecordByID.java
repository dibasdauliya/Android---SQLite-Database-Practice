package com.example.db_project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class DeleteRecordByID extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_record_by_id);

        TextView titleText = findViewById(R.id.titleTextView);
        titleText.setText("Delete Player by ID");

        MaterialButton goHome = findViewById(R.id.goHome);
        goHome.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));

        TextInputEditText idInput = findViewById(R.id.idEditText);

        MaterialButton viewAll = findViewById(R.id.viewAll);
//        viewAll.setVisibility(View.VISIBLE);
        viewAll.setOnClickListener(v -> startActivity(new Intent(this, AllRecords.class)));

        MaterialButton delPlayer = findViewById(R.id.actionBtn);
        delPlayer.setText("Delete Player");
        delPlayer.setOnClickListener(v -> {
            String idVal = idInput.getText().toString();

            if (idVal.isEmpty()) {
                Toast.makeText(this, "It's empty", Toast.LENGTH_SHORT).show();
                return;
            }

            DBAdapter db = new DBAdapter(this);
            db.open();
            try {
                boolean c = db.deleteContact(Long.parseLong(idVal));
                if (c)
                    Toast.makeText(this, "Player is deleted!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Player isn't deleted!", Toast.LENGTH_LONG).show();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid ID", Toast.LENGTH_SHORT).show();
            } finally {
                db.close();
            }
        });

    }
}