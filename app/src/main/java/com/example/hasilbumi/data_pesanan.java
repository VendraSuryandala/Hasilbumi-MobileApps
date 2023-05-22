package com.example.hasilbumi;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class data_pesanan extends AppCompatActivity {

    TextView user_id,idorder,datetime,total_harga,kurir,layanan,status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_pesanan);

        user_id = findViewById(R.id.user_id);
        idorder = findViewById(R.id.idorder);
        datetime = findViewById(R.id.datetime);
        kurir   = findViewById(R.id.kurir);
        layanan = findViewById(R.id.layanan);
        total_harga = findViewById(R.id.total_harga);
        status  = findViewById(R.id.status);
    }
}