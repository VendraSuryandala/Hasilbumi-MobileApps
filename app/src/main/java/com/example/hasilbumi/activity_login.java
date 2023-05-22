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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.hasilbumi.API.EndPoint;
import com.example.hasilbumi.API.apiinterface;
import com.example.hasilbumi.Model.Login.Login;
import com.example.hasilbumi.Model.Login.LoginData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_login extends Activity implements View.OnClickListener {

    private EditText Username, Password;
    TextView regis;
    Button BtnLogin;
    private CheckBox ShowPass;
    String user_name,user_password;
    apiinterface apiinterface;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = findViewById(R.id.txUsername);
        Password = findViewById(R.id.txPassword);
        regis = findViewById(R.id.txt_register);
        ShowPass = findViewById(R.id.showPass);
        BtnLogin = findViewById(R.id.BtnLogin);


        //menampilkan show password
        ShowPass.setOnClickListener(v -> {
            if (ShowPass.isChecked()) {
                //Saat Checkbox dalam keadaan Checked, maka password akan di tampilkan
                Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                //Jika tidak, maka password akan di sembuyikan
                Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        BtnLogin.setOnClickListener(this);
        regis.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BtnLogin:
                user_name = Username.getText().toString();
                user_password = Password.getText().toString();
                if (Username.length() == 0) {
                    Username.setError("Masukkan Username!");
                }else if (Password.length() == 0) {
                    Password.setError("Masukkan Password!");
                }else {
                    login(user_name, user_password);
                }
                break;

            case R.id.txt_register:
                Intent intent = new Intent(this,activity_register.class);
                startActivity(intent);
                break;
        }

}

    private void login(String user_name, String user_password) {

        apiinterface = EndPoint.getClient().create(apiinterface.class);
        Call<Login> loginCall = apiinterface.loginResponse(user_name,user_password);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {

                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){

                    sessionManager = new SessionManager(activity_login.this);
                    LoginData loginData = response.body().getData();
                    sessionManager.createloginsession(loginData);


                    //untuk berpindah jika berhasil
                    Toast.makeText(activity_login.this, response.body().getData().getUserName(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity_login.this,ActivityLanding.class);
                    startActivity(intent);
                    finish();

                }else {
                    Toast.makeText(activity_login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Login> call, @NonNull Throwable t) {
                Toast.makeText(activity_login.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    }
