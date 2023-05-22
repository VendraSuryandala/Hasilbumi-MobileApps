package com.example.hasilbumi;


import static com.example.hasilbumi.API.EndPoint.PRODUCT_URL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.hasilbumi.API.EndPoint;
import com.example.hasilbumi.API.apiinterface;
import com.example.hasilbumi.Model.Produk;
import com.example.hasilbumi.adapter.ProdukAdapter;
import com.example.hasilbumi.adapter.RecyclerViewInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class activity_produk extends Activity implements RecyclerViewInterface {

    apiinterface apiinterface;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    List<Produk> list = new ArrayList<>();
    ProdukAdapter produkAdapter;
    RelativeLayout btnback,btnhome, btnkeranjang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        apiinterface = EndPoint.getClient().create(com.example.hasilbumi.API.apiinterface.class);
        btnback         = findViewById(R.id.btnback);
        btnhome         = findViewById(R.id.btnhome);
        btnkeranjang    = findViewById(R.id.btnkeranjang);

        getData();

        produkAdapter = new ProdukAdapter(getApplicationContext(), list,this);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(produkAdapter);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_produk.this, ActivityLanding.class));
            }
        });

        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_produk.this, ActivityLanding.class));
            }
        });

        btnkeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_produk.this, KeranjangActivity.class));
            }
        });


    }

    private void getData() {
        list.clear();
        progressBar.setVisibility(View.VISIBLE);

        // membuat request JSON
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(PRODUCT_URL,
                new Response.Listener<JSONArray>() {

                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);

                            Produk item1 = new Produk();

                            item1.setIdproduct (jsonObject.getString("idproduct"));
                            item1.setProduct_name(jsonObject.getString("product_name"));
                            item1.setProduct_image(jsonObject.getString("product_image"));
                            item1.setStok(jsonObject.getString("stok"));
                            item1.setHarga_jual(jsonObject.getString("harga_jual"));
                            item1.setSatuan(jsonObject.getString("satuan"));
                            item1.setBerat(jsonObject.getString("berat"));
                            list.add(item1);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                        produkAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(activity_produk.this, "Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });

        // menambah request ke request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(activity_produk.this, activitydetailproduk.class);
        intent.putExtra("idproduct", list.get(position).getIdproduct());
        intent.putExtra("product_name", list.get(position).getProduct_name());
        intent.putExtra("stok", list.get(position).getStok());
        intent.putExtra("harga_jual", list.get(position).getHarga_jual());
        intent.putExtra("satuan", list.get(position).getSatuan());
        intent.putExtra("berat", list.get(position).getBerat());
        startActivity(intent);
    }
}


