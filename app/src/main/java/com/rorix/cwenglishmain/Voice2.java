package com.rorix.cwenglishmain;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class Voice2 extends AppCompatActivity {


    private static int MICROPHONE_PERMISSION_CODE = 200;
    MediaRecorder mediaRecorder;
    MediaPlayer mPlayer;
    ListView listView;
    int currentRecording = 0;
    List<String> recordings;
    ImageButton img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice2);
        listView = findViewById(R.id.listView);
        img=findViewById(R.id.imageButton8);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Voice2.this,MainActivity2.class);
                startActivity(intent);
            }
        });

        if (isMicrophonePresent()) {
            getMicrophonePermission();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                currentRecording = i;
            }
        });
        // Kaydedilen ses dosyalarını tutan listeyi oluştur
        recordings = new ArrayList<>();
       // ListView'ı güncelle
        updateListView();
    }


    private void updateListView(){
        // Adapter nesnesini oluştur
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recordings);
        // Adapter'i listView'a ata
        listView.setAdapter(adapter);
    }

    public void kaydi_Baslat(View v) {

        try {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);//ses kaynağı mikrofon
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);//ses  formatı
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);//kodlayıcıxı
            mediaRecorder.setOutputFile(getRecordingFilePath());//Üretilecek çıktı dosyasının yolunu ayarlar.
            mediaRecorder.prepare();
            mediaRecorder.start();

            Toast.makeText(this, "Recording is started", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void kaydi_Durdur(View v) {

        mediaRecorder.stop();
        mediaRecorder.release();//Bu MediaRecorder nesnesiyle ilişkili kaynakları serbest bırakır.
        recordings.add(getRecordingFilePath());

        Toast.makeText(this, "Recording is stopped..", Toast.LENGTH_SHORT).show();
        updateListView();

    }

    public void oynat(View v) {

        try {
            mPlayer = new MediaPlayer();
            mPlayer.setDataSource(recordings.get(currentRecording));//Kullanılacak veri kaynağını (dosya yolu) ayarlar.
            mPlayer.prepare();//oynatıcıyı oynatma için eşzamanlı olarak hazırlamak için kullanılan, Android'deki sınıfın bir yöntemidir
            mPlayer.start();
            Toast.makeText(this, "The Recording is Playing..", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    private boolean isMicrophonePresent() {//cihazın mikrofonu olup olmadığını gösteren bir boole değeri döndüren bir yöntemi tanımlar.
        if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) {
            //PackageManager cihazda yüklü olan uygulama paketleri hakkında bilgi sağlar.//
            //Yöntem öncelikle sınıfın yöntemini kullanarak aygıtın FEATURE_MICROPHONEsistem özelliğine sahip
            // olup olmadığını kontrol eder. Aygıtın özelliği varsa, yöntem döndürür . Aksi halde geri döner .
            return true;
        } else {
            return false;
        }
    }

    private void getMicrophonePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.RECORD_AUDIO}, MICROPHONE_PERMISSION_CODE);
        }
    }



    private String getRecordingFilePath(){
        String fileName = "Recording_" + new SimpleDateFormat("", Locale.getDefault()).format(new Date()) + ".mp3";
        return getExternalFilesDir("").getAbsolutePath() + "/" + fileName;//dosya yolunu döndürür.
    }

}