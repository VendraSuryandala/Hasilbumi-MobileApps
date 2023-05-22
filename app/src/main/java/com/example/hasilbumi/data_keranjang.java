package com.example.hasilbumi;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class data_keranjang extends AppCompatActivity {

    TextView product_id,user_id,namaproduk,qty,harga,satuan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_keranjang);

        product_id  = findViewById(R.id.product_id);
        user_id     = findViewById(R.id.user_id);
        namaproduk  = findViewById(R.id.namaproduk);
        qty         = findViewById(R.id.qty);
        harga       = findViewById(R.id.total);
        satuan      = findViewById(R.id.satuan);
    }
}