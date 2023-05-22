package com.example.hasilbumi;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;



public class activity_tambahproduk extends AppCompatActivity {

    private ImageView fotoproduk;
    private EditText namaproduk;

    Button TombolSimpan;
    ProgressBar progressBar;

    private static final int IMAGE_REQUEST = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahproduk);

        fotoproduk = findViewById(R.id.imageView);
        namaproduk = findViewById(R.id.namaproduk);

        TombolSimpan = findViewById(R.id.buttonUpdate);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        fotoproduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        TombolSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}




