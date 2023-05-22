package com.example.hasilbumi;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class data_produk extends AppCompatActivity {
    ImageView fotoproduk;
    TextView idproduct, namaproduk, txstok,stok,harga_jual,satuan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_produk);
        idproduct  = findViewById(R.id.idproduct);
        fotoproduk = findViewById(R.id.fotoproduk);
        namaproduk = findViewById(R.id.namaproduk);
        txstok     = findViewById(R.id.txstok);
        stok       = findViewById(R.id.stok);
        harga_jual = findViewById(R.id.harga_jual);
        satuan     = findViewById(R.id.satuan);
    }

    public ImageView getFotoproduk() {
        return fotoproduk;
    }

    public TextView getIdproduct (){
        return idproduct;
    }

    public TextView getNamaproduk() {
        return namaproduk;
    }

    public TextView getSatuan() {
        return satuan;
    }

    public TextView getTxstok() {
        return txstok;
    }

    public TextView getStok() {
        return stok;
    }

    public TextView getHarga_jual() {
        return harga_jual;
    }
}