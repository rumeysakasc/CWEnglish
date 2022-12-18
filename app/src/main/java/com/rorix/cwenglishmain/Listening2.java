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

public class Listening2 extends AppCompatActivity {
    private ImageButton image;
    private Button plyBtn,next_bton,previous_bton;
    private TextView zmnAkis;
    private SeekBar positionBar;
    MediaPlayer mp;
    int totalTime;
    int[] songs = {R.raw.listening2, R.raw.listening3, R.raw.listen1computer};
    int currentSong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening2);
        image=findViewById(R.id.imageButton8);
        zmnAkis=findViewById(R.id.zmnAk);
        positionBar=findViewById(R.id.barS);
        plyBtn=findViewById(R.id.play1);
        next_bton=findViewById(R.id.next_bton);
        previous_bton=findViewById(R.id.previous_bton);


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.pause();
                Intent intent = new Intent(Listening2.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        //Media

        mp=MediaPlayer.create(this,songs[currentSong]);
        mp.setLooping(true);
        mp.seekTo(0);
        mp.setVolume(0.5f,0.5f);
        totalTime = mp.getDuration();


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



///////////////////////////////
        //play
        plyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mp.isPlaying()){
                    mp.start();
                    plyBtn.setBackgroundResource(R.drawable.stop);
                }
                else {
                    mp.pause();
                    plyBtn.setBackgroundResource(R.drawable.ply);
                }
            }
        });
        next_bton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Şu anda oynatılan müziği durdur
                mp.stop();
                // Şu anki müziğin indeksini bir arttır ve mod işlemi ile sıfırdan başa dön
                currentSong = (currentSong + 1) % songs.length;
                // Bir sonraki müziği oluştur ve oynat
                mp = MediaPlayer.create(Listening2.this, songs[currentSong]);
                mp.start();
            }
        });
        previous_bton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Şu anda oynatılan müziği durdur
                mp.stop();
                // Şu anki müziğin indeksini bir azalt ve mod işlemi ile sondan başa dön
                currentSong = (currentSong + songs.length - 1) % songs.length;
                // Önceki müziği oluştur ve oynat
                mp = MediaPlayer.create(Listening2.this,songs[currentSong]);
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

    ///////////////////
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
