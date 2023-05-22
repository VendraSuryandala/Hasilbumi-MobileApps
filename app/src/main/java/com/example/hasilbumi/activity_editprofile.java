package com.example.hasilbumi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hasilbumi.API.EndPoint;
import com.example.hasilbumi.API.apiinterface;
import com.example.hasilbumi.Model.user_profile.UserProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_editprofile extends AppCompatActivity {

    RelativeLayout btnback, btnhome, btnkeranjang;
    TextView user_id, fullname, username, telp;
    SessionManager sessionManager;
    apiinterface apiinterface;
    ImageView ubahprofil;
    String idusers,user_fullname,user_name,user_telp;
    private String xuser_fullname, xuser_name, xuser_telp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        user_id  = findViewById(R.id.user_id);
        fullname = findViewById(R.id.txFullname);
        username = findViewById(R.id.txUsername);
        telp     = findViewById(R.id.txNomor);
        ubahprofil = findViewById(R.id.ubahprofil);

        sessionManager = new SessionManager(activity_editprofile.this);

        btnback = findViewById(R.id.btnback);
        btnhome = findViewById(R.id.btnhome);
        btnkeranjang = findViewById(R.id.btnkeranjang);

        idusers = sessionManager.getUserDetail().get("idusers");
        user_id.setText(idusers);

        user_fullname = sessionManager.getUserDetail().get("user_fullname");
        fullname.setText(user_fullname);

        user_name = sessionManager.getUserDetail().get("user_name");
        username.setText(user_name);

        user_telp = sessionManager.getUserDetail().get("user_telp");
        telp.setText(user_telp);

        ubahprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idusers          = user_id.getText().toString();
                xuser_fullname   = fullname.getText().toString();
                xuser_name       = username.getText().toString();
                xuser_telp       = telp.getText().toString();

                if (fullname.length() == 0) {
                    fullname.setError("Input nama panjang anda!");
                } else if (username.length() == 0) {
                    username.setError("Input nama panggilan anda!");
                } else if (telp.length() == 0) {
                    telp.setError("Input nomor telepon anda!");
                } else {
                    Ubahdata();
                }
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
                startActivity(new Intent(activity_editprofile.this, ActivityLanding.class));
            }
        });

        btnkeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_editprofile.this, KeranjangActivity.class));
            }
        });

    }

    private void Ubahdata() {

        apiinterface = EndPoint.getClient().create(apiinterface.class);
        Call<UserProfile> editCall = apiinterface.edituserResponse(idusers,xuser_name,xuser_fullname,xuser_telp);
        editCall.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(@NonNull Call<UserProfile> call, @NonNull Response<UserProfile> response) {

                    Toast.makeText(activity_editprofile.this, "Berhasil, Silahkan login ulang", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity_editprofile.this, activity_login.class);
                    startActivity(intent);
                    finish();

            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                Toast.makeText(activity_editprofile.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}