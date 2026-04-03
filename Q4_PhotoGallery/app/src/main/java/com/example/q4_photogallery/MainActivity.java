package com.example.q4_photogallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private File galleryFolder;
    private ArrayList<String> imagePaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        Button btnCapture = findViewById(R.id.btnCapture);

        // Create the folder
        galleryFolder = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "MyGallery");
        if (!galleryFolder.exists()) galleryFolder.mkdirs();

        btnCapture.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 101);
        });

        loadImages();

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("path", imagePaths.get(position));
            startActivity(intent);
        });
    }

    private void loadImages() {
        imagePaths = new ArrayList<>();
        File[] files = galleryFolder.listFiles();
        if (files != null) {
            for (File f : files) imagePaths.add(f.getAbsolutePath());
        }
        // Simplified Adapter for 2nd year level
        ImageAdapter adapter = new ImageAdapter(this, imagePaths);
        gridView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            saveImage(image);
            loadImages(); // Refresh gallery
        }
    }

    private void saveImage(Bitmap bmp) {
        File file = new File(galleryFolder, "IMG_" + System.currentTimeMillis() + ".jpg");
        try (FileOutputStream out = new FileOutputStream(file)) {
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, out);
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadImages(); // Refresh when coming back from delete
    }
}