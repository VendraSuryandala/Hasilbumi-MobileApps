package com.example.hasilbumi;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hasilbumi.API.EndPoint;
import com.example.hasilbumi.API.apiinterface;
import com.example.hasilbumi.Model.Register.Register;
import com.example.hasilbumi.Model.Register.RegisterData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class activity_register extends Activity implements View.OnClickListener {

    EditText Fullname, Nomor, Username, Password;
    Button btnRegis;
    ProgressBar progressBar;
    CheckBox ShowPass;
    String user_fullname, user_telp, user_name, user_password;
    apiinterface apiinterface;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Fullname    = findViewById(R.id.txFullname);
        Nomor       = findViewById(R.id.txNomor);
        Username    = findViewById(R.id.txUsername);
        Password    = findViewById(R.id.txPassword);
        progressBar = findViewById(R.id.progressBarRegister);
        btnRegis    = findViewById(R.id.btnRegis);
        progressBar.setVisibility(View.GONE);
        ShowPass = findViewById(R.id.showPass);

        sessionManager = new SessionManager(activity_register.this);


        btnRegis.setOnClickListener(this);

        ShowPass.setOnClickListener(v -> {
            if (ShowPass.isChecked()) {
                //Saat Checkbox dalam keadaan Checked, maka password akan di tampilkan
                Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                //Jika tidak, maka password akan di sembuyikan
                Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegis:
                user_fullname   = Fullname.getText().toString();
                user_telp       = Nomor.getText().toString();
                user_name       = Username.getText().toString();
                user_password   = Password.getText().toString();

                if (Fullname.length() == 0){
                    Fullname.setError("Masukkan Nama Panjang!");
                }else if (Nomor.length() == 0) {
                    Nomor.setError("Masukkan Nomor Telpon!");
                }else if (Username.length() == 0) {
                    Username.setError("Masukkan Nama!");
                }else if (Password.length() == 0) {
                    Password.setError("Masukkan Password!");
                }else {
                    Register(user_fullname,user_telp,user_name,user_password);
                }
        }
}

    private void Register(String user_fullname, String user_telp, String user_name, String user_password) {
        progressBar.setVisibility(View.VISIBLE);

        apiinterface = EndPoint.getClient().create(apiinterface.class);
        Call<Register> call = apiinterface.registerResponse(user_name,user_password,user_fullname,user_telp);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {

                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){

                    RegisterData registerData = response.body().getData();
                    sessionManager.createregistersession(registerData);

                    //untuk berpindah jika berhasil
                    Toast.makeText(activity_register.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity_register.this,activity_login.class);
                    startActivity(intent);
                    progressBar.setVisibility(View.GONE);

                }else {
                    Toast.makeText(activity_register.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(activity_register.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });


    }

}








