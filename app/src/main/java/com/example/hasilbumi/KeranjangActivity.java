package com.example.hasilbumi;

import static android.view.View.GONE;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hasilbumi.API.EndPoint;
import com.example.hasilbumi.API.apiinterface;
import com.example.hasilbumi.Model.cart.Cart;
import com.example.hasilbumi.Model.cart.CartData;
import com.example.hasilbumi.adapter.KeranjangAdapter;
import com.example.hasilbumi.adapter.RecyclerViewKeranjang;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KeranjangActivity extends Activity implements RecyclerViewKeranjang {


    RelativeLayout btnback, btnhome, btnproduk;
    ProgressBar progressBar;
    apiinterface apiinterface;
    TextView User_id,totalHarga;
    ImageView btnkeranjang;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    List<CartData> cartList = new ArrayList<>();
    SessionManager sessionManager;
    KeranjangAdapter keranjangAdapter;
    String user_id;
    int totalharga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);

        sessionManager = new SessionManager(KeranjangActivity.this);

        btnback = findViewById(R.id.btnback);
        btnhome = findViewById(R.id.btnhome);
        btnproduk = findViewById(R.id.btnproduk);
        User_id = findViewById(R.id.user_id);
        totalHarga = findViewById(R.id.totalharga);
        btnkeranjang = findViewById(R.id.btnkeranjang);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        apiinterface = EndPoint.getClient().create(com.example.hasilbumi.API.apiinterface.class);

        user_id = sessionManager.getUserDetail().get("idusers");
        User_id.setText(user_id);

        user_id = User_id.getText().toString();

        GetData(user_id);
        progressBar.setVisibility(GONE);

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mMessageReceiver,new IntentFilter("totalharga"));

        btnkeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KeranjangActivity.this, activity_pesanan.class));
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
                startActivity(new Intent(KeranjangActivity.this, ActivityLanding.class));
            }
        });

        btnproduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KeranjangActivity.this, activity_produk.class));
            }
        });

    }


    public void GetData(String user_id) {
        apiinterface = EndPoint.getClient().create(apiinterface.class);
        Call<Cart> cartcall = apiinterface.GetCartResponse(user_id);

        cartcall.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(@NonNull Call<Cart> call, @NonNull Response<Cart> response) {

                if(response.body() != null && response.isSuccessful() && response.body().isStatus()) {

                    cartList = response.body().getData();

                    keranjangAdapter = new KeranjangAdapter(KeranjangActivity.this, cartList);

                    recyclerView.setAdapter(keranjangAdapter);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    keranjangAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(GONE);
                }else {
                    Toast.makeText(KeranjangActivity.this, "Tidak ada data keranjang", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Toast.makeText(KeranjangActivity.this, "Gagal menampilkan data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalharga = intent.getIntExtra("totalAmount",0);
            totalHarga.setText(""+totalharga);

        }
    };


}