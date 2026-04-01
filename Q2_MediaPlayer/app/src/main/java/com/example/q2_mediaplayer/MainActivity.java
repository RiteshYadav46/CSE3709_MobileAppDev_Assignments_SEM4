package com.example.q2_mediaplayer;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView videoView = findViewById(R.id.videoView);

        // A public sample MP4 link
        String videoUrl = "https://www.w3schools.com/html/mov_bbb.mp4";
        Uri uri = Uri.parse(videoUrl);
        videoView.setVideoURI(uri);

        // This adds the Play/Pause/Seek bar buttons
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.start();
    }
}