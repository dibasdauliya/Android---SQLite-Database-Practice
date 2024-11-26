package com.example.db_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        GridView gridView = findViewById(R.id.gridView);

        List<GridOption> options = new ArrayList<>();
        options.add(new GridOption(R.drawable.ic_add, "Add Player"));
        options.add(new GridOption(R.drawable.ic_view_all, "See All Players"));
        options.add(new GridOption(R.drawable.view_one, "See One Player by ID"));
        options.add(new GridOption(R.drawable.delete, "Delete Player"));

        GridAdapter adapter = new GridAdapter(this, options);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((adapterView, view, i, l) -> {

//            Log.d("MainActivity", "i: "+i);

            switch (i) {
                case 0:
                    startActivity(new Intent(MainActivity.this, AddRecord.class));
                    break;
                case 1:
                    startActivity(new Intent(MainActivity.this, AllRecords.class));
                    break;
                case 2:
                    startActivity(new Intent(MainActivity.this, ViewRecordByID.class));
                    break;
                case 3:
                    startActivity(new Intent(MainActivity.this, DeleteRecordByID.class));
                    break;
            }
        });

    }
}