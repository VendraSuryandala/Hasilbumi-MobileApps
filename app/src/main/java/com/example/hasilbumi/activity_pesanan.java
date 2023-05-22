package com.example.hasilbumi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hasilbumi.API.EndPoint;
import com.example.hasilbumi.API.apiinterface;
import com.example.hasilbumi.Model.Pesanan.Pesanan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_pesanan extends Activity implements View.OnClickListener {

    private Spinner spinnerkurir, spinnerlayanan;
    RelativeLayout btnback, btnhome, btnkeranjang;
    TextView User_id, totalharga, username, order_Prov, order_Kab, order_Kec, order_Kodepos, order_Address;
    Button btnpesanan;
    SessionManager sessionManager;
    apiinterface apiinterface;
    String user_name, user_id,subtotal, total_harga, order_prov, order_kab, order_kec, order_kodepos, order_address, order_kurir, order_layanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan);

        btnback = findViewById(R.id.btnback);
        btnkeranjang = findViewById(R.id.btnkeranjang);
        btnhome = findViewById(R.id.btnhome);

        User_id = findViewById(R.id.user_id);
        username = findViewById(R.id.username);
        totalharga = findViewById(R.id.totalharga);
        order_Prov = findViewById(R.id.txprov);
        order_Kab = findViewById(R.id.txkab);
        order_Kec = findViewById(R.id.txkec);
        order_Kodepos = findViewById(R.id.txkodepos);
        order_Address = findViewById(R.id.txaddress);
        spinnerkurir = findViewById(R.id.rowkurir);
        spinnerlayanan = findViewById(R.id.rowlayanan);
        btnpesanan = findViewById(R.id.btnpesanan);

        sessionManager = new SessionManager(activity_pesanan.this);

        user_name = sessionManager.getUserDetail().get("user_name");
        username.setText(user_name);

        user_id = sessionManager.getUserDetail().get("idusers");
        User_id.setText(user_id);

        btnpesanan.setOnClickListener(this);

        //row kurir
        List<Kurir> kurirList = new ArrayList<>();
        Kurir kurir1 = new Kurir("JNE");
        kurirList.add(kurir1);
        Kurir kurir2 = new Kurir("Kantor POS");
        kurirList.add(kurir2);

        ArrayAdapter<Kurir> adapter = new ArrayAdapter<Kurir>(this,
                android.R.layout.simple_spinner_item, kurirList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerkurir.setAdapter(adapter);
        spinnerkurir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Kurir kurir = (Kurir) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //row layanan
        List<Layanan> layananList = new ArrayList<>();
        Layanan layanan1 = new Layanan("OKE-Ongkos Kirim Ekonomis");
        layananList.add(layanan1);
        Layanan layanan2 = new Layanan("REG-Layanan Reguler");
        layananList.add(layanan2);
        Layanan layanan3 = new Layanan("YES-Yakin Esok Sampai");
        layananList.add(layanan3);

        ArrayAdapter<Layanan> adapter1 = new ArrayAdapter<Layanan>(this,
                android.R.layout.simple_spinner_item, layananList);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerlayanan.setAdapter(adapter1);
        spinnerlayanan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Layanan layanan = (Layanan) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_pesanan.this, ActivityLanding.class));
            }
        });

        btnkeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_pesanan.this, KeranjangActivity.class));
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnpesanan:
                user_id       = User_id.getText().toString();
                subtotal      = totalharga.getText().toString();
                total_harga   = totalharga.getText().toString();
                order_prov    = order_Prov.getText().toString();
                order_kab     = order_Kab.getText().toString();
                order_kec     = order_Kec.getText().toString();
                order_kodepos = order_Kodepos.getText().toString();
                order_address = order_Address.getText().toString();
                order_kurir   = spinnerkurir.getSelectedItem().toString();
                order_layanan = spinnerlayanan.getSelectedItem().toString();


                if (order_Prov.length() == 0) {
                    order_Prov.setError("Input provinsi anda berada!");
                } else if (order_Kab.length() == 0) {
                    order_Kab.setError("Input kabupaten anda!");
                } else if (order_Kec.length() == 0) {
                    order_Kec.setError("Input Kecamatan anda!");
                } else if (order_Kodepos.length() == 0) {
                    order_Kodepos.setError("Isikan kode pos anda!");
                } else if (order_Address.length() == 0) {
                    order_Address.setError("Alamat harus di isi!");
                } else {
                    Pesanan(user_id, subtotal, total_harga, order_prov, order_kab, order_kec, order_kodepos,
                            order_address, order_layanan, order_kurir);
                }
        }
    }

        private void Pesanan (String user_id, String subtotal, String total_harga, String order_prov, String order_kab, String order_kec, String order_kodepos, String order_address, String order_layanan, String order_kurir){

            apiinterface = EndPoint.getClient().create(apiinterface.class);
            Call<Pesanan> call = apiinterface.PesananResponse(user_id,subtotal,total_harga,order_prov,order_kab,order_kec,order_kodepos,order_address,order_kurir,order_layanan);
            call.enqueue(new Callback<Pesanan>() {
                @Override
                public void onResponse(Call<Pesanan> call, Response<Pesanan> response) {

                    if (response.body() != null && response.isSuccessful() && response.body().isStatus()) {

                        //untuk berpindah jika berhasil
                        Toast.makeText(activity_pesanan.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(activity_pesanan.this, ActivityLanding.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(activity_pesanan.this, "Pesanan gagal!", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<Pesanan> call, Throwable t) {
                    Toast.makeText(activity_pesanan.this, "Pesan produk berhasil", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity_pesanan.this, ActivityLanding.class);
                    startActivity(intent);
                }
            });
        }



}