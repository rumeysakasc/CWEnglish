package com.rorix.cwenglishmain;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.VideoView;

public class Videos extends AppCompatActivity {
    private VideoView videV,videV2,videV3;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        videV=findViewById(R.id.videoView);
        videV2=findViewById(R.id.videoView2);
        videV3=findViewById(R.id.videoView3);


        String videoPath = "android.resource://"+getPackageName()+"/" + R.raw.videos2;
        Uri uri = Uri.parse(videoPath);
        videV.setVideoURI(uri);
        MediaController mediaController = new MediaController((this));
        videV.setMediaController(mediaController);
        mediaController.setAnchorView(videV);

        String videoPath2 = "android.resource://"+getPackageName()+"/" + R.raw.videos3;
        Uri uri2 = Uri.parse(videoPath2);
        videV2.setVideoURI(uri2);
        MediaController mediaController2 = new MediaController((this));
        videV2.setMediaController(mediaController2);
        mediaController.setAnchorView(videV2);

        String videoPath3 = "android.resource://"+getPackageName()+"/" + R.raw.videos1;
        Uri uri3 = Uri.parse(videoPath3);
        videV3.setVideoURI(uri3);
        MediaController mediaController3 = new MediaController((this));
        videV3.setMediaController(mediaController3);
        mediaController.setAnchorView(videV3);


    }
}