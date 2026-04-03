package com.example.q4_photogallery;

import android.app.AlertDialog;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.util.Date;

public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String path = getIntent().getStringExtra("path");
        File file = new File(path);

        ImageView img = findViewById(R.id.detailImage);
        TextView details = findViewById(R.id.txtDetails);
        Button btnDelete = findViewById(R.id.btnDelete);

        img.setImageBitmap(BitmapFactory.decodeFile(path));
        details.setText("Name: " + file.getName() +
                "\nPath: " + path +
                "\nSize: " + (file.length() / 1024) + " KB" +
                "\nDate: " + new Date(file.lastModified()));

        btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Delete Photo")
                    .setMessage("Are you sure?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        file.delete();
                        finish(); // Go back to gallery
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }
}