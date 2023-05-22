package com.example.hasilbumi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hasilbumi.API.apiinterface;

public class Activity_profile extends AppCompatActivity {

    RelativeLayout btnback, btnhome, btnkeranjang;
    TextView idusers, fullname, username, telp;
    SessionManager sessionManager;
    apiinterface apiinterface;
    ImageView lihatpesanan,lihatbukti,ubahdata;
    String user_id,user_fullname,user_name,user_telp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        idusers  = findViewById(R.id.user_id);
        fullname = findViewById(R.id.txFullname);
        username = findViewById(R.id.txUsername);
        telp     = findViewById(R.id.txNomor);
        lihatpesanan = findViewById(R.id.lihatpesanan);
        ubahdata     = findViewById(R.id.ubahprofil);

        sessionManager = new SessionManager(Activity_profile.this);

        btnback = findViewById(R.id.btnback);
        btnhome = findViewById(R.id.btnhome);
        btnkeranjang = findViewById(R.id.btnkeranjang);

        user_id = sessionManager.getUserDetail().get("idusers");
        idusers.setText(user_id);

        user_fullname = sessionManager.getUserDetail().get("user_fullname");
        fullname.setText(user_fullname);

        user_name = sessionManager.getUserDetail().get("user_name");
        username.setText(user_name);

        user_telp = sessionManager.getUserDetail().get("user_telp");
        telp.setText(user_telp);


        lihatpesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_profile.this, activity_lihatpesanan.class));
            }
        });


        ubahdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_profile.this, activity_editprofile.class));
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
                startActivity(new Intent(Activity_profile.this, ActivityLanding.class));
            }
        });

        btnkeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_profile.this, KeranjangActivity.class));
            }
        });

    }

}




