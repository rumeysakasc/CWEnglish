package com.rorix.cwenglishmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class Listening extends AppCompatActivity {
    private ImageButton image;
    private Button plyBtn,plyBtn2;
    private TextView zmnAkis,zmnAkis1;
    private SeekBar positionBar,positionBar2;
    MediaPlayer mp,mp2;
    int totalTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening);
        image=findViewById(R.id.imageButton8);
        zmnAkis=findViewById(R.id.zmnAk);
        positionBar=findViewById(R.id.barS);
        zmnAkis1=findViewById(R.id.zmnAk1);
        plyBtn2=findViewById(R.id.play2);
        plyBtn=findViewById(R.id.play1);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Listening.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        //Media

        mp=MediaPlayer.create(this,R.raw.listen1computer);
        mp.setLooping(true);
        mp.seekTo(0);
        mp.setVolume(0.5f,0.5f);
        totalTime = mp.getDuration();

        mp2=MediaPlayer.create(this,R.raw.listening2);
        mp2.setLooping(true);
        mp2.seekTo(0);
        mp2.setVolume(0.5f,0.5f);
        totalTime = mp2.getDuration();
        ///


        positionBar.setMax(totalTime);
        positionBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if(fromUser){
                            mp.seekTo(progress);
                            positionBar.setProgress(progress);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }

                }
        );

        positionBar2.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if(fromUser){
                            mp2.seekTo(progress);
                            positionBar2.setProgress(progress);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }

                }
        );

        //////////////////////////
        //Thread//
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(mp != null){
                       try {
                            Message msg= new Message();
                            msg.what = mp.getCurrentPosition();
                            handler.sendMessage(msg);
                            Thread.sleep(1000);
                       } catch (InterruptedException e){

                       }
                    }
                }
            }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(mp2 != null){
                    try {
                        Message msg1= new Message();
                        msg1.what = mp2.getCurrentPosition();
                        handler.sendMessage(msg1);
                        Thread.sleep(1000);
                    } catch (InterruptedException e){

                    }
                }
            }
        }).start();


///////////////////////////////
        //play
        plyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mp.isPlaying()){
                    mp.pause();
                    mp2.start();
                    plyBtn.setBackgroundResource(R.drawable.stop);
                }
                else {
                    mp.start();
                    mp2.pause();
                    plyBtn.setBackgroundResource(R.drawable.ply);
                }
            }
        });
        plyBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mp2.isPlaying()){
                    mp2.pause();
                    mp.start();
                    plyBtn.setBackgroundResource(R.drawable.stop);
                }
                else {
                    mp2.start();
                    mp.pause();
                    plyBtn.setBackgroundResource(R.drawable.ply);
                }
            }
        });

    }

/////////////////////////
    private Handler handler= new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            int currentPosition =msg.what;
            positionBar.setProgress(currentPosition);

            String totalTime = createTimeLabel(currentPosition);
            zmnAkis.setText(totalTime);
        }
    };

    private Handler handler1= new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            int currentPosition1 =msg.what;
            positionBar2.setProgress(currentPosition1);

            String totalTime = createTimeLabel(currentPosition1);
            zmnAkis1.setText(totalTime);
        }
    };
    //////////////////////////////////
    public String createTimeLabel(int time){
        String timeLabel ="";
        int min = time/1000/60;
        int sec = time/1000%60;
        timeLabel = min +":";
        if (sec<10) timeLabel+="0";
            timeLabel += sec;
            return timeLabel;

    }

}
