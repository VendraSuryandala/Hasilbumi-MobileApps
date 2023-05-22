package com.example.hasilbumi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;


public class activity_kategori extends AppCompatActivity {
    private ImageButton bumbu, buah, sayuran;
    RelativeLayout btnback,btnhome, btnkeranjang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        buah = findViewById(R.id.buah);
        sayuran = findViewById(R.id.sayur);
        bumbu = findViewById(R.id.bumbu);
        btnback         = findViewById(R.id.btnback);
        btnhome         = findViewById(R.id.btnhome);
        btnkeranjang    = findViewById(R.id.btnkeranjang);



        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_kategori.this, ActivityLanding.class));
            }
        });

        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_kategori.this, ActivityLanding.class));
            }
        });

        btnkeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_kategori.this, KeranjangActivity.class));
            }
        });

    }

}