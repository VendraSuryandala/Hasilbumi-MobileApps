package com.example.hasilbumi;

import static android.view.View.GONE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hasilbumi.API.EndPoint;
import com.example.hasilbumi.API.apiinterface;
import com.example.hasilbumi.Model.Pesanan.Pesanan;
import com.example.hasilbumi.Model.Pesanan.PesananData;
import com.example.hasilbumi.adapter.PesananAdapter;
import com.example.hasilbumi.adapter.RecyclerViewPesanan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_lihatpesanan extends Activity implements RecyclerViewPesanan {

    RelativeLayout btnback, btnhome, btnkeranjang;
    TextView User_id;
    ProgressBar progressBar;
    apiinterface apiinterface;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    List<PesananData> psnList = new ArrayList<>();
    SessionManager sessionManager;
    PesananAdapter pesananAdapter;


    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihatpesanan);

        User_id = findViewById(R.id.user_id);
        btnback = findViewById(R.id.btnback);
        btnhome = findViewById(R.id.btnhome);
        btnkeranjang = findViewById(R.id.btnkeranjang);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        apiinterface = EndPoint.getClient().create(com.example.hasilbumi.API.apiinterface.class);
        sessionManager = new SessionManager(activity_lihatpesanan.this);

        user_id = sessionManager.getUserDetail().get("idusers");
        User_id.setText(user_id);

        GetData(user_id);
        progressBar.setVisibility(GONE);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_lihatpesanan.this, ActivityLanding.class));
            }
        });

        btnkeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_lihatpesanan.this, KeranjangActivity.class));
            }
        });

    }

    private void GetData(String user_id) {
        apiinterface = EndPoint.getClient().create(apiinterface.class);
        Call<Pesanan> psncall = apiinterface.GetPesananResponse(user_id);
        psncall.enqueue(new Callback<Pesanan>() {
            @Override
            public void onResponse(Call<Pesanan> call, Response<Pesanan> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){

                    Toast.makeText(activity_lihatpesanan.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    psnList = response.body().getPesananData();

                    pesananAdapter = new PesananAdapter(activity_lihatpesanan.this, psnList);

                    recyclerView.setAdapter(pesananAdapter);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    pesananAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(GONE);
                }else {
                    Toast.makeText(activity_lihatpesanan.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Pesanan> call, Throwable t) {
                Toast.makeText(activity_lihatpesanan.this, "Gagal menampilkan data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}