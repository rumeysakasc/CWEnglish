package com.rorix.cwenglishmain;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;

public class Videos2 extends AppCompatActivity {
    // VideoView nesnesi
    private VideoView videoView;

    // ListView nesnesi
    private ListView listView;

    // Videoları tutan ArrayList
    private ArrayList<String> videoList;

    // ArrayAdapter nesnesi
    private ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos2);
        // VideoView ve ListView nesnelerini bağlama
        videoView = (VideoView) findViewById(R.id.video_view);
        listView = (ListView) findViewById(R.id.list_view);
        // Videoları tutan ArrayList'i oluşturma ve VideoListAdapter'a geçirme
        videoList=new ArrayList<>();
        videoList.add("Computer Conversation");
        videoList.add("Computer Device");
        videoList.add("Computer Cirisis");
        videoList.add("Computer Networks Work?");
        videoList.add("Computer ");
        videoList.add("Computer Device");
        videoList.add("Computer Lesson");
        adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,videoList);
        // ListView'ı VideoListAdapter ile bağlama
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position){
                    case 0:
                        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.videos9));
                        break;
                    case 1:
                        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.videos2));
                        break;
                    case 2:
                        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.videos3));
                        break;
                    case 3:
                        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.videos8));
                        break;
                    case 4:
                        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.videos6));
                        break;
                    case 5:
                        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.videos4));
                        break;
                    case 6:
                        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.videos1));
                        break;
                    default:
                        break;
                }
                videoView.setMediaController(new MediaController(Videos2.this));//VideoView'in üzerinde oynatma/durdurma,
                // ses düzeyi veya video ilerleme özelliklerini kontrol etmek için kullanılır.
                videoView.requestFocus();//dokunmatik kullanım isteği
                videoView.start();
            }
        });
    }

}