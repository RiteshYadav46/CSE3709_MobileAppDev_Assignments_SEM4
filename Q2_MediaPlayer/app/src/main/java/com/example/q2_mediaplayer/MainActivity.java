package com.example.q2_mediaplayer;

import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private VideoView videoView;
    private boolean isMediaLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);

        // FIX: Force the video surface to the top layer
        videoView.setZOrderOnTop(true);
        videoView.getHolder().setFormat(PixelFormat.TRANSPARENT);

        Button btnOpenURL = findViewById(R.id.btnOpenURL);
        Button btnOpenLocal = findViewById(R.id.btnOpenLocal);
        Button btnPlay = findViewById(R.id.btnPlay);
        Button btnPause = findViewById(R.id.btnPause);
        Button btnStop = findViewById(R.id.btnStop);
        Button btnRestart = findViewById(R.id.btnRestart);

        // a) Play 6-second Video from res/raw/sample_video.mp4
        btnOpenLocal.setOnClickListener(v -> {
            isMediaLoaded = true;
            String path = "android.resource://" + getPackageName() + "/" + R.raw.sample_video;
            videoView.setVideoURI(Uri.parse(path));
            videoView.setOnPreparedListener(mp -> videoView.start());
            videoView.requestFocus();
        });

        // b) Stream Video from URL
        btnOpenURL.setOnClickListener(v -> {
            isMediaLoaded = true;
            String videoUrl = "https://www.w3schools.com/html/mov_bbb.mp4";
            videoView.setVideoURI(Uri.parse(videoUrl));
            videoView.setOnPreparedListener(mp -> videoView.start());
            videoView.requestFocus();
        });

        btnPlay.setOnClickListener(v -> { if (isMediaLoaded) videoView.start(); });
        btnPause.setOnClickListener(v -> { if (isMediaLoaded) videoView.pause(); });
        btnStop.setOnClickListener(v -> { if (isMediaLoaded) videoView.stopPlayback(); });
        btnRestart.setOnClickListener(v -> { if (isMediaLoaded) { videoView.seekTo(0); videoView.start(); } });
    }
}