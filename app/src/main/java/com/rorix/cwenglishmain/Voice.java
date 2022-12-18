package com.rorix.cwenglishmain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;

import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;


public class Voice extends AppCompatActivity {



    private static int MICROPHONE_PERMISSION_CODE=200;
    MediaRecorder mediaRecorder;
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);


        if(isMicrophonePresent()){
            getMicrophonePermission();
        }
    }
    public void kaydi_Baslat(View v){

        try {
            mediaRecorder= new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setOutputFile(getRecordingFilePath());
            mediaRecorder.prepare();
            mediaRecorder.start();

            Toast.makeText(this, "Recording is started", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public void kaydi_Durdur(View v){

            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder=null;

            Toast.makeText(this, "Recording is stopped..", Toast.LENGTH_SHORT).show();


    }
    public void oynat(View v){

        try {
            mPlayer=new MediaPlayer();
            mPlayer.setDataSource(getRecordingFilePath());
            mPlayer.prepare();
            mPlayer.start();
            Toast.makeText(this, "Kayıt Oynatılıyor.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean isMicrophonePresent(){
        if(this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)){
            return true;
        }
        else {
            return false;
        }
    }
    private  void getMicrophonePermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
        ==PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.RECORD_AUDIO},MICROPHONE_PERMISSION_CODE);
        }
    }
    private String getRecordingFilePath(){
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File audio = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file= new File(audio,"RecordingFile"+".mp3");
        return file.getPath();
    }
 }