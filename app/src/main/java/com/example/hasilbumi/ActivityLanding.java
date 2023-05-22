package com.example.hasilbumi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hasilbumi.API.apiinterface;
import com.example.hasilbumi.Model.cart.CartData;

import java.util.ArrayList;
import java.util.List;


public class ActivityLanding extends AppCompatActivity {

    TextView txnama,idusers;
    Button menu_profile;
    Button menu_produk;
    Button menu_keranjang;
    RelativeLayout  btnlogout;
    SessionManager sessionManager;
    apiinterface getdata;
    List<CartData> cartList = new ArrayList<>();
    String user_name,users_id;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        sessionManager = new SessionManager(ActivityLanding.this);

        txnama = findViewById(R.id.textnama);
        idusers= findViewById(R.id.textid);
        menu_profile = findViewById(R.id.menu_tentang);
        menu_produk = findViewById(R.id.menu_produk);
        menu_keranjang = findViewById(R.id.menu_keranjang);
        btnlogout = findViewById(R.id.btnlogout);


        user_name = sessionManager.getUserDetail().get("user_name");
        txnama.setText(user_name);

        users_id = sessionManager.getUserDetail().get("idusers");
        idusers.setText(users_id);

        if(!sessionManager.isLoggedIn()){
            movetoLogin();
        }

        menu_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityLanding.this, Activity_profile.class));
            }
        });

        menu_produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityLanding.this, activity_produk.class));
            }
        });

        menu_keranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users_id = idusers.getText().toString();
                startActivity(new Intent(ActivityLanding.this, KeranjangActivity.class));
                //getData();
            }
        });


     btnlogout.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             sessionManager.logoutSession();
             movetoLogin();
         }
     });

    }


//    private void getData(){
//
//        apiinterface getdata = EndPoint.getClient().create(apiinterface.class);
//        Call<Cart> ambildata = getdata.GetCartResponse(users_id);
//
//        ambildata.enqueue(new Callback<Cart>() {
//            @Override
//            public void onResponse(Call<Cart> call, Response<Cart> response) {
//
//                if (response.body() != null && response.isSuccessful() && response.body().isStatus()) {
//
//                    cartList = response.body().getData();
//                    Intent intent = new Intent(ActivityLanding.this,KeranjangActivity.class);
//                    startActivity(intent);
//                    finish();
//
//                } else {
//                    Toast.makeText(ActivityLanding.this,"Tidak ada data keranjang", Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<Cart> call, Throwable t) {
//                Toast.makeText(ActivityLanding.this,"Gagal menampilkan data", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


    private void movetoLogin() {
        Intent intent = new Intent (ActivityLanding.this,activity_login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

}