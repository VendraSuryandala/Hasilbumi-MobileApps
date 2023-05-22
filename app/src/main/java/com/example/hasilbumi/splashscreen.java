package com.example.hasilbumi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.widget.ProgressBar;
import android.widget.TextView;

public class splashscreen extends Activity {

    private ProgressBar progressBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        //menghilangkan ActionBar
        setContentView(R.layout.activity_splashscreen);

        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textview);

        final Handler handler = new Handler();

        handler.postDelayed(() -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();

        }, 3000L); //3000 L = 3 detik
    }
}