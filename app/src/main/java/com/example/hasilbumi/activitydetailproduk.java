package com.example.hasilbumi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hasilbumi.API.EndPoint;
import com.example.hasilbumi.API.apiinterface;
import com.example.hasilbumi.Model.cart.Cart;
import com.example.hasilbumi.adapter.RecyclerViewInterface;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activitydetailproduk extends AppCompatActivity implements RecyclerViewInterface {

    apiinterface apiinterface;
    RelativeLayout btnback,btnhome, btnkeranjang;
    TextView idproduct,User_id,namaproduk,Stok,harga_jual,harga_jual1,harga_final,quantitynumber,Satuan, Berat;
    ImageView fotoproduk,btnmskkeranjang;
    ImageButton plusquantity, minusquantity;
    SessionManager sessionManager;
    String product_id ,user_id,product_name,harga,qty,satuan,berat,harga_awal,stok;
    int quantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailproduk);

        User_id     = findViewById(R.id.user_id);
        idproduct   = findViewById(R.id.idproduct);
        namaproduk  = findViewById(R.id.namaproduk);
        Stok        = findViewById(R.id.stok);
        harga_jual  = findViewById(R.id.harga_jual);
        harga_jual1 = findViewById(R.id.harga_jual1);
        harga_final = findViewById(R.id.harga_final);
        Satuan      = findViewById(R.id.satuan);
        Berat       = findViewById(R.id.berat);
        apiinterface = EndPoint.getClient().create(com.example.hasilbumi.API.apiinterface.class);
        sessionManager = new SessionManager(activitydetailproduk.this);


        plusquantity = findViewById(R.id.addquantity);
        minusquantity = findViewById(R.id.subquantity);
        quantitynumber = findViewById(R.id.quantity);

        btnmskkeranjang = findViewById(R.id.btnmskkeranjang);
        btnback         = findViewById(R.id.btnback);
        btnhome         = findViewById(R.id.btnhome);
        btnkeranjang    = findViewById(R.id.btnkeranjang);

        getIncomingExtra();

        user_id = sessionManager.getUserDetail().get("idusers");
        User_id.setText(user_id);


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
                startActivity(new Intent(activitydetailproduk.this, activity_produk.class));
            }
        });

        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activitydetailproduk.this, ActivityLanding.class));
            }
        });

        btnkeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activitydetailproduk.this, KeranjangActivity.class));
            }
        });

        btnmskkeranjang.setOnClickListener(new View.OnClickListener() {
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
                SaveCart(user_id,product_id,product_name,qty,satuan,berat,harga,harga_awal,stok);
            }
        });

        plusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int basePrice = Integer.parseInt(harga_jual1.getText().toString());

                if (quantity == Integer.parseInt(Stok.getText().toString())) {
                    Toast.makeText(activitydetailproduk.this, "Tidak dapat melebihi stok", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(activitydetailproduk.this, "Tidak dapat kurang dari 1", Toast.LENGTH_SHORT).show();
                }else {
                    quantity--;
                    displayQuantity();
                    int harga_Jual = basePrice * quantity;
                    String setnewPrice = String.valueOf(harga_Jual);
                    harga_final.setText(setnewPrice);
                }
            }
        });


    }

    private void getIncomingExtra() {
        if(getIntent().hasExtra("product_name")
                && getIntent().hasExtra("idproduct")
                && getIntent().hasExtra("stok")
                && getIntent().hasExtra("harga_jual")
                && getIntent().hasExtra("satuan")
                && getIntent().hasExtra("berat")){

            String Idproduct  = getIntent().getStringExtra("idproduct");
            String Namaproduk = getIntent().getStringExtra("product_name");
            String STok = getIntent().getStringExtra("stok");
            String Harga = getIntent().getStringExtra("harga_jual");
            String SAtuan = getIntent().getStringExtra("satuan");
            String BErat = getIntent().getStringExtra("berat");

            setDataActivity(Idproduct ,Namaproduk, STok, Harga,SAtuan, BErat);

        }
    }

    private void setDataActivity(String Idproduct ,String Namaproduk, String STok, String Harga_jual,String SAtuan, String BErat) {
        idproduct.setText(Idproduct);
        namaproduk.setText(Namaproduk);
        Stok.setText(STok);
        harga_jual.setText(Harga_jual);
        harga_jual1.setText(Harga_jual);
        harga_final.setText(Harga_jual);
        Satuan.setText(SAtuan);
        Berat.setText(BErat);
    }

    private void displayQuantity() {
        quantitynumber.setText(String.valueOf(quantity));
    }


    private void SaveCart(String user_id, String product_id, String product_name, String qty, String satuan,String berat, String harga, String harga_awal, String stok) {

        apiinterface = EndPoint.getClient().create(apiinterface.class);
        Call<Cart> call = apiinterface.UserCartResponse(product_id,user_id,product_name,qty,harga,satuan,berat, harga_awal,stok);
        call.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(@NonNull Call<Cart> call, @NonNull Response<Cart> response) {
                    //ketika berhasil
                    Toast.makeText(activitydetailproduk.this, "Produk sudah dikeranjang", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Toast.makeText(activitydetailproduk.this, "Berhasil input keranjang", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activitydetailproduk.this, KeranjangActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}