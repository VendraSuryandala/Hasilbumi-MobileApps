package com.example.hasilbumi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hasilbumi.API.EndPoint;
import com.example.hasilbumi.API.apiinterface;
import com.example.hasilbumi.Model.cart.Cart;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activitydetailkeranjang extends AppCompatActivity {

    apiinterface apiinterface;
    RelativeLayout btnback,btnhome, btnkeranjang;
    TextView idproduct,User_id,namaproduk,harga_jual,harga_jual1,harga_final,quantitynumber,Satuan, Berat,Stok;
    ImageView fotoproduk,btnubah;
    ImageButton plusquantity, minusquantity;
    SessionManager sessionManager;
    String product_id ,user_id,product_name,harga,qty,satuan,berat, harga_awal,stok;
    private String xId, xproduk, xnama, xqty, xharga, xsatuan, xberat, xawal, xstok;
    int quantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailkeranjang);

        User_id     = findViewById(R.id.user_id);
        idproduct   = findViewById(R.id.idproduct);
        namaproduk  = findViewById(R.id.namaproduk);
        harga_jual  = findViewById(R.id.harga_jual);
        harga_jual1 = findViewById(R.id.harga_jual1);
        harga_final = findViewById(R.id.harga_final);
        Satuan      = findViewById(R.id.satuan);
        Berat       = findViewById(R.id.berat);
        Stok        = findViewById(R.id.stok);
        apiinterface    = EndPoint.getClient().create(com.example.hasilbumi.API.apiinterface.class);

        plusquantity    = findViewById(R.id.addquantity);
        minusquantity   = findViewById(R.id.subquantity);
        quantitynumber = findViewById(R.id.quantity);

        btnubah         = findViewById(R.id.btnubah);
        btnback         = findViewById(R.id.btnback);
        btnhome         = findViewById(R.id.btnhome);
        btnkeranjang    = findViewById(R.id.btnkeranjang);

        Intent terima = getIntent();
        xId     = terima.getStringExtra("xId");
        xproduk = terima.getStringExtra("xproduk");
        xnama   = terima.getStringExtra("xnama");
        xqty    = terima.getStringExtra("xqty");
        xharga  = terima.getStringExtra("xharga");
        xsatuan = terima.getStringExtra("xsatuan");
        xberat  = terima.getStringExtra("xberat");
        xawal   = terima.getStringExtra("xawal");
        xstok   = terima.getStringExtra("xstok");

        User_id.setText(xId);
        idproduct.setText(xproduk);
        namaproduk.setText(xnama);
        quantitynumber.setText(xqty);
        harga_jual.setText(xawal);
        harga_jual1.setText(xawal);
        harga_final.setText(xharga);
        Satuan.setText(xsatuan);
        Berat.setText(xberat);
        Stok.setText(xstok);

        Double RP = Double.parseDouble(harga_jual.getText().toString());
        DecimalFormat Rp = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp.");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        Rp.setDecimalFormatSymbols(formatRp);
        harga_jual.setText(String.valueOf(Rp.format(RP)));

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activitydetailkeranjang.this, ActivityLanding.class));
            }
        });

        btnkeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activitydetailkeranjang.this, KeranjangActivity.class));
            }
        });



        plusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int basePrice = Integer.parseInt(harga_jual1.getText().toString());

                if (quantity == Integer.parseInt(Stok.getText().toString())) {
                    Toast.makeText(activitydetailkeranjang.this, "Tidak dapat melebihi stok", Toast.LENGTH_SHORT).show();
                } else {
                    quantity++;
                    displayQuantity();
                    int harga_Jual = basePrice * quantity;
                    String setnewPrice = String.valueOf(harga_Jual);
                    harga_final.setText(setnewPrice);
                }
            }
        });

        minusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int basePrice = Integer.parseInt(harga_jual1.getText().toString());

                if (quantity == 1) {
                    Toast.makeText(activitydetailkeranjang.this, "Tidak dapat kurang dari 1", Toast.LENGTH_SHORT).show();
                }else {
                    quantity--;
                    displayQuantity();
                    int harga_Jual = basePrice * quantity;
                    String setnewPrice = String.valueOf(harga_Jual);
                    harga_final.setText(setnewPrice);
                }
            }
        });

        btnubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_id        = User_id.getText().toString();
                product_id     = idproduct.getText().toString();
                product_name   = namaproduk.getText().toString();
                qty            = quantitynumber.getText().toString();
                satuan         = Satuan.getText().toString();
                harga          = harga_final.getText().toString();
                berat          = Berat.getText().toString();
                harga_awal     = harga_jual1.getText().toString();
                stok           = Stok.getText().toString();
                UbahCart(user_id,product_id,product_name,qty,satuan,berat,harga,harga_awal,stok);
            }
        });

    }

    private void UbahCart(String user_id, String product_id, String product_name, String qty, String satuan, String berat, String harga, String harga_awal, String stok) {

        apiinterface = EndPoint.getClient().create(apiinterface.class);
        Call<Cart> call = apiinterface.EditCartResponse(product_id,user_id,product_name,qty,harga,satuan,berat,harga_awal,stok);
        call.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.body() != null && response.isSuccessful() && response.body().isStatus()) {
                    //ketika berhasil
                    Toast.makeText(activitydetailkeranjang.this, "Berhasil input keranjang", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activitydetailkeranjang.this, KeranjangActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Toast.makeText(activitydetailkeranjang.this, "Berhasil Input ke keranjang", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void displayQuantity() {
        quantitynumber.setText(String.valueOf(quantity));
    }



    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}