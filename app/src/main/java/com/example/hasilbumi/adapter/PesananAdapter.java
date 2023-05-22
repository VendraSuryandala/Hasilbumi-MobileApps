package com.example.hasilbumi.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hasilbumi.API.EndPoint;
import com.example.hasilbumi.API.apiinterface;
import com.example.hasilbumi.Model.Pesanan.Pesanan;
import com.example.hasilbumi.Model.Pesanan.PesananData;
import com.example.hasilbumi.R;
import com.example.hasilbumi.activity_buktibayar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesananAdapter extends RecyclerView.Adapter<PesananAdapter.HolderPesan> {
    Context context;
    List<PesananData> psnList;
    List<PesananData> ordrList;
    private String order_id;

    public PesananAdapter(Context context, List<PesananData> psnList) {
        this.context = context;
        this.psnList = psnList;
    }

    @NonNull
    @Override
    public HolderPesan onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_pesanan, parent, false);
        HolderPesan holder = new HolderPesan(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderPesan holder, int position) {
        holder.user_id.setText(psnList.get(position).getUser_id());
        holder.idorder.setText(psnList.get(position).getIdorder());
        holder.datetime.setText(psnList.get(position).getDatetime());
        holder.total_harga.setText(psnList.get(position).getTotal_harga());
        holder.kurir.setText(psnList.get(position).getOrder_kurir());
        holder.layanan.setText(psnList.get(position).getOrder_layanan());
        holder.status.setText(psnList.get(position).getStatus());

    }

    @Override
    public int getItemCount() {
        return psnList.size();
    }


    class HolderPesan extends RecyclerView.ViewHolder {
        TextView user_id, idorder, datetime, total_harga, kurir, layanan, status;

        public HolderPesan(@NonNull View itemView) {
            super(itemView);
            user_id = itemView.findViewById(R.id.user_id);
            idorder = itemView.findViewById(R.id.idorder);
            datetime = itemView.findViewById(R.id.datetime);
            kurir = itemView.findViewById(R.id.kurir);
            layanan = itemView.findViewById(R.id.layanan);
            total_harga = itemView.findViewById(R.id.total_harga);
            status = itemView.findViewById(R.id.status);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder dialogPesan = new AlertDialog.Builder(context);
                    dialogPesan.setMessage("ingin melakukan pembayaran? ");
                    dialogPesan.setTitle("Perhatian");
                    dialogPesan.setIcon(R.mipmap.ic_mainlogo);
                    dialogPesan.setCancelable(true);

                    order_id = idorder.getText().toString();

                    dialogPesan.setPositiveButton("Kirim bukti bayar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            OrderData();
                            dialog.dismiss();
                        }
                    });

                    dialogPesan.show();
                    return false;
                }
            });

        }
    }

    private void OrderData() {
        apiinterface apiData = EndPoint.getClient().create(apiinterface.class);
        Call<Pesanan> call = apiData.SelectbuktiResponse(order_id);
        call.enqueue(new Callback<Pesanan>() {
            @Override
            public void onResponse(Call<Pesanan> call, Response<Pesanan> response) {
                if (response.body() != null && response.isSuccessful() && response.body().isStatus()) {

                    ordrList = response.body().getPesananData();

                    String varidorder = ordrList.get(0).getIdorder();

                    Intent kirim = new Intent(context, activity_buktibayar.class);
                    kirim.putExtra("yId", varidorder);
                    context.startActivity(kirim);

                } else {
                    Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pesanan> call, Throwable t) {
                Toast.makeText(context, "Gagal!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}