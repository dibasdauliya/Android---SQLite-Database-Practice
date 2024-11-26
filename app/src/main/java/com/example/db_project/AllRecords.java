package com.example.db_project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AllRecords extends AppCompatActivity {
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_records);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button goHome = findViewById(R.id.goHome);
        goHome.setOnClickListener(v -> {
            startActivity(new Intent(AllRecords.this, MainActivity.class));
        });

        DBAdapter db = new DBAdapter(this);

        tableLayout = findViewById(R.id.contact_table);

        db.open();
        Cursor cursor = db.getAllContacts();
        if (cursor.moveToFirst()) {
            do {
                addContactToTable(
                        cursor.getInt(cursor.getColumnIndexOrThrow("_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("position")),
                        cursor.getString(cursor.getColumnIndexOrThrow("country"))
                );
            } while (cursor.moveToNext());
        }
        db.close();
    }

    private void addContactToTable(int id, String email, String position, String country) {
        TableRow row = new TableRow(this);

//        just because instruction says to display "Toast" one after another
        Toast.makeText(this, "ID: " + id + ", Email: " + email + ", Position: " + position + ", Country: " + country, Toast.LENGTH_SHORT).show();

        row.setPadding(10, 10, 10, 10);

        TextView idView = new TextView(this);
        idView.setText(String.valueOf(id));
        idView.setBackground(ContextCompat.getDrawable(this, R.drawable.border));
        idView.setPadding(20, 20, 20, 20);
        row.addView(idView);

        TextView emailView = new TextView(this);
        emailView.setText(email);
        emailView.setBackground(ContextCompat.getDrawable(this, R.drawable.border));
        emailView.setPadding(20, 20, 20, 20);
        row.addView(emailView);

        TextView posView = new TextView(this);
        posView.setText(position);
        posView.setBackground(ContextCompat.getDrawable(this, R.drawable.border));
        posView.setPadding(20, 20, 20, 20);
        row.addView(posView);

        TextView countryView = new TextView(this);
        countryView.setText(country);
        countryView.setBackground(ContextCompat.getDrawable(this, R.drawable.border));
        countryView.setPadding(20, 20, 20, 20);
        row.addView(countryView);

        tableLayout.addView(row);
    }

}