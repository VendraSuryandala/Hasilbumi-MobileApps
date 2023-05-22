package com.example.hasilbumi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hasilbumi.API.apiinterface;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class activity_buktibayar extends AppCompatActivity {

    RelativeLayout btnback, btnhome, btnkeranjang;
    String Document_img1;
    SessionManager sessionManager;
    apiinterface apiinterface;
    TextView User_id,Order_id;
    EditText txtotal, txket;
    ImageView gambar,btnkirim;
    String user_id,total,keterangan;
    private String yId;

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        //mengambil dari galeri
        if (resultCode == RESULT_OK && requestCode == 20 && data != null){
            final Uri path = data.getData();
            Thread thread = new Thread(() -> {
                try {
                    InputStream inputStream = getContentResolver().openInputStream(path);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    gambar.post(() -> {
                        gambar.setImageBitmap(bitmap);
                        BitmapToString(bitmap);
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buktibayar);

        User_id  = findViewById(R.id.user_id);
        txtotal = findViewById(R.id.txtotalbyr);
        txket = findViewById(R.id.txketerangan);
        btnkirim = findViewById(R.id.btnkirim);
        gambar = findViewById(R.id.gambar);
        Order_id = findViewById(R.id.order_id);

        btnback = findViewById(R.id.btnback);
        btnhome = findViewById(R.id.btnhome);
        btnkeranjang = findViewById(R.id.btnkeranjang);

        sessionManager = new SessionManager(activity_buktibayar.this);

        user_id = sessionManager.getUserDetail().get("idusers");
        User_id.setText(user_id);

        Intent terima = getIntent();
        yId     = terima.getStringExtra("yId");
        Order_id.setText(yId);

        gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ambilgambardarigaleri();
            }
        });

        btnkirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kirim();
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
                startActivity(new Intent(activity_buktibayar.this, ActivityLanding.class));
            }
        });

        btnkeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_buktibayar.this, KeranjangActivity.class));
            }
        });


    }

    private void ambilgambardarigaleri() {
        final CharSequence[] items = {"Ambil dari galeri"};
        AlertDialog.Builder builder = new AlertDialog.Builder(activity_buktibayar.this);
        builder.setTitle(getString(R.string.app_name));
        builder.setIcon(R.mipmap.ic_mainlogo);
        builder.setItems(items, (dialog, item) -> {
            if (items[item].equals("Ambil dari galeri")) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Pilih gambar"), 20);
            }
        });
        builder.show();
    }

    private String BitmapToString(Bitmap userImage) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        userImage.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] b = baos.toByteArray();
        Document_img1 = Base64.encodeToString(b, Base64.DEFAULT);
        return Document_img1;
    }

    private void kirim() {

        final ProgressDialog loading = new ProgressDialog(activity_buktibayar.this);
        String user_id = User_id.getText().toString();
        String total = txtotal.getText().toString();
        String keterangan = txket.getText().toString();
        String idorder = Order_id.getText().toString();
        if (user_id!=null) {
            loading.setMessage("Please wait...");
            loading.show();
            loading.setCanceledOnTouchOutside(false);
            RetryPolicy mRetryPolicy = new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.100.11/rest-api/pembayaran.php",
            new Response.Listener<String>(){
                @Override
                public void onResponse(String response) {
                    loading.dismiss();
                    Toast.makeText(activity_buktibayar.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(activity_buktibayar.this, ActivityLanding.class));
                }
            },
            new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    loading.dismiss();
                    Toast.makeText(activity_buktibayar.this, "Pastikan data terinput", Toast.LENGTH_SHORT).show();
                }
            }) {
                protected Map<String, String> getParams() throws AuthFailureError{
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("user_id", user_id);
                    map.put("total", total);
                    map.put("keterangan", keterangan);
                    map.put("file", Document_img1);
                    map.put("idorder", idorder);
                    return map;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            stringRequest.setRetryPolicy(mRetryPolicy);
            requestQueue.add(stringRequest);
        }
    }



}