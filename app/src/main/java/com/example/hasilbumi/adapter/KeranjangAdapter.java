package com.example.hasilbumi.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hasilbumi.API.EndPoint;
import com.example.hasilbumi.API.apiinterface;
import com.example.hasilbumi.KeranjangActivity;
import com.example.hasilbumi.Model.cart.Cart;
import com.example.hasilbumi.Model.cart.CartData;
import com.example.hasilbumi.R;
import com.example.hasilbumi.activitydetailkeranjang;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KeranjangAdapter extends RecyclerView.Adapter<KeranjangAdapter.MyviewHolder> {

    Context context;
    List<CartData> cartList;
    private List<CartData> selectList;
    private String User_id,Product_id;
    int totalHarga = 0;

    public KeranjangAdapter(KeranjangActivity context, List<CartData> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_keranjang, parent, false);
        MyviewHolder holder = new MyviewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        holder.product_id.setText(cartList.get(position).getProduct_id());
        holder.user_id.setText(cartList.get(position).getUser_id());
        holder.product_name.setText(cartList.get(position).getNamaproduk());
        holder.qty.setText(cartList.get(position).getqty());
        holder.harga.setText(String.valueOf(cartList.get(position).getHarga()));
        holder.satuan.setText(cartList.get(position).getSatuan());
        holder.berat.setText(cartList.get(position).getBerat());
        holder.harga_awal.setText(cartList.get(position).getHarga_awal());
        holder.stok.setText(cartList.get(position).getStok());

        totalHarga = totalHarga + Integer.parseInt(cartList.get(position).getHarga());
        Intent intent = new Intent("totalharga");
        intent.putExtra("totalAmount",totalHarga);

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }


    class MyviewHolder extends RecyclerView.ViewHolder {
        TextView product_id,user_id,product_name,qty,harga,satuan,berat,harga_awal,stok;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            product_id  = itemView.findViewById(R.id.product_id);
            user_id     = itemView.findViewById(R.id.user_id);
            product_name= itemView.findViewById(R.id.namaproduk);
            qty         = itemView.findViewById(R.id.qty);
            harga       = itemView.findViewById(R.id.harga);
            satuan      = itemView.findViewById(R.id.satuan);
            berat       = itemView.findViewById(R.id.berat);
            harga_awal  = itemView.findViewById(R.id.harga_awal);
            stok        = itemView.findViewById(R.id.stok);

             itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder dialogPesan = new AlertDialog.Builder(context);
                    dialogPesan.setMessage("Data keranjang Anda: ");
                    dialogPesan.setTitle("Perhatian");
                    dialogPesan.setIcon(R.mipmap.ic_mainlogo);
                    dialogPesan.setCancelable(true);

                    User_id = user_id.getText().toString();
                    Product_id = product_id.getText().toString();

                    //Implementasi hapus data
                    dialogPesan.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            HapusData();
                            dialog.dismiss();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ((KeranjangActivity)context).GetData(User_id);
                                }
                            }, 300);
                        }
                    });

                    //Implementasi ubah data
                    dialogPesan.setNegativeButton("Ubah data", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            UbahData();
                            dialog.dismiss();
                        }
                    });

                    dialogPesan.show();
                    return false;
                }
            });
        }
    }

    private void UbahData() {
        apiinterface apiData = EndPoint.getClient().create(apiinterface.class);
        Call<Cart> selectData = apiData.SelectCartResponse(Product_id);
        selectData.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {

                    String message = response.body().getMessage();

                    selectList = response.body().getData();

                    String varuser_id = selectList.get(0).getUser_id();
                    String varproduct_id = selectList.get(0).getProduct_id();
                    String varnamaproduk = selectList.get(0).getNamaproduk();
                    String varqty = selectList.get(0).getqty();
                    String varharga = selectList.get(0).getHarga();
                    String varsatuan = selectList.get(0).getSatuan();
                    String varberat = selectList.get(0).getBerat();
                    String varawal  = selectList.get(0).getHarga_awal();
                    String varstok  = selectList.get(0).getStok();

//                    Toast.makeText(context,"Message : "+message+" | Data :"+varuser_id+" | "+varproduct_id+" | "+varnamaproduk+" | "+varqty+" | "+varharga+" | "+varberat, Toast.LENGTH_SHORT).show();

                   Intent kirim = new Intent(context, activitydetailkeranjang.class);
                   kirim.putExtra("xId", varuser_id);
                   kirim.putExtra("xproduk", varproduct_id);
                   kirim.putExtra("xnama", varnamaproduk);
                   kirim.putExtra("xqty", varqty);
                   kirim.putExtra("xharga", varharga);
                   kirim.putExtra("xsatuan", varsatuan);
                   kirim.putExtra("xberat", varberat);
                   kirim.putExtra("xawal", varawal);
                   kirim.putExtra("xstok", varstok);
                   context.startActivity(kirim);
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {

            }
        });

    }

    private void HapusData() {
        apiinterface apiData = EndPoint.getClient().create(apiinterface.class);
        Call<Cart> hapusData = apiData.HapusCartResponse(Product_id);
        hapusData.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){

                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }


}

