package com.rorix.cwenglishmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity2 extends AppCompatActivity {

    private ImageButton btnImg1, btnImg2, btnImg3, btnImg4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnImg1=findViewById(R.id.imageButton6);
        btnImg2=findViewById(R.id.imageButton3);
        btnImg3=findViewById(R.id.imageButton4);
        btnImg4=findViewById(R.id.imageButton5);
        btnImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,Listening.class);
                startActivity(intent);
            }
        });
        btnImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,NoteDeatails.class);
                startActivity(intent);
            }
        });
        btnImg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,Voice.class);
                startActivity(intent);
            }
        });
        btnImg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,Videos.class);
                startActivity(intent);
            }
        });


    }
}