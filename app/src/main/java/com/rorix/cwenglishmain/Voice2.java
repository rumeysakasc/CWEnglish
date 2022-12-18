package com.rorix.cwenglishmain;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Voice2 extends AppCompatActivity {
    // MediaRecorder nesnesi
    MediaRecorder recorder;
    // Kaydedilen ses dosyalarını tutacak liste
    List<String> recordings;
    // ListView nesnesi
    ListView listView;
    // Dinleme butonu
    Button playButton;
    int currentRecording=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice2);
        // MediaRecorder nesnesini oluştur
        recorder = new MediaRecorder();
        // Kaydetme ve durdurma düğmelerini tanımla
        Button recordButton = findViewById(R.id.kaydi_baslat);
        Button stopButton = findViewById(R.id.kaydi_durdur);
// Dinleme butonunu ve listeyi tanımla
        playButton = findViewById(R.id.oynat);
        listView = findViewById(R.id.listView);

// Kaydetme düğmesine tıklandığında ses kaydetme işlemini başlat
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ses kaydı başlat
                startRecording();
            }
        });

// Durdurma düğmesine tıklandığında ses kaydetme işlemini durdur
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ses kaydı durdur
                stopRecording();
            }
        });

// Dinleme butonuna tıklandığında seçilen ses dosyasını oynat
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Seçilen ses dosyasını oynat
                playRecording();
            }
        });

// ListView'a tıklandığında seçilen ses dosyasını seç
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Seçilen ses dosyasının indeksini kaydet
                currentRecording = i;
            }
        });

// Kaydedilen ses dosyalarını tutan listeyi oluştur
        recordings = new ArrayList<>();
// ListView'ı güncelle
        updateListView();

    }
    // Ses kaydetme işlemini başlatan fonksiyon
    private void startRecording() {
        // MediaRecorder nesnesini hazırla
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(getFileName());
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            // MediaRecorder'ı başlat
            recorder.prepare();
            recorder.start();
            Toast.makeText(this, "Recording is started", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Ses kaydetme işlemini durduran fonksiyon
    private void stopRecording() {
        // MediaRecorder'ı durdur
        recorder.stop();
        // MediaRecorder'ı sıfırla
        recorder.reset();
        // Kaydedilen ses dosyasını listeye ekle
        recordings.add(getFileName());
        Toast.makeText(this, "Recording is stopped..", Toast.LENGTH_SHORT).show();
        // ListView'ı güncelle
        updateListView();
    }
    // Seçilen ses dosyasını oynatan fonksiyon
    private void playRecording() {
        // MediaPlayer nesnesini oluştur
        MediaPlayer player = new MediaPlayer();
        try {
            // MediaPlayer'ı dosya ile bağla
            player.setDataSource(recordings.get(currentRecording));
            // MediaPlayer'ı hazırla
            player.prepare();
            // MediaPlayer'ı oynat
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ListView'ı güncelleyen fonksiyon
    private void updateListView() {
        // Adapter nesnesini oluştur
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recordings);
        // Adapter'i listView'a ata
        listView.setAdapter(adapter);
    }

    // Dosya adı oluşturan fonksiyon
    private String getFileName() {
        // Dosya adı oluştur
        String fileName = "Recording_" + new SimpleDateFormat("", Locale.getDefault()).format(new Date()) + ".mp3";
        // Dosya yolunu döndür
        return getExternalFilesDir("").getAbsolutePath() + "/" + fileName;
    }
}