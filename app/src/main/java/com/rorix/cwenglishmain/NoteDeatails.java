package com.rorix.cwenglishmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteDeatails extends AppCompatActivity {
    EditText  textContent;
    ImageButton saveBtn,backBtn;
    ListView listV;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    HashMap<String, Object> write = new HashMap<>();
    ArrayAdapter<String> adapter;
    ArrayList<String> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_deatails);

        textContent=findViewById(R.id.write_content);
        saveBtn=findViewById(R.id.save_btn);
        listV = findViewById(R.id.listWrite);
        backBtn=findViewById(R.id.backBtn);
        adapter= new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,list);
        listV.setAdapter(adapter);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sentence= textContent.getText().toString();
                list.add(sentence);
                listV.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                textContent.setText("");
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoteDeatails.this,MainActivity2.class);
                startActivity(intent);
            }
        });
    }

}