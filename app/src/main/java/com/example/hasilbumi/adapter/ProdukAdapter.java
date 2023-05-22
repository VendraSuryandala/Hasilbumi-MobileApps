package com.example.hasilbumi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hasilbumi.API.EndPoint;
import com.example.hasilbumi.Model.Produk;
import com.example.hasilbumi.R;

import java.util.List;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.MyviewHolder>{

    private  final RecyclerViewInterface recyclerViewInterface;

    Context context;
    List<Produk> list;


    public ProdukAdapter(Context context, List<Produk> list, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.list = list;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_produk, parent, false);
        return new MyviewHolder(itemView, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        holder.idproduct.setText(list.get(position).getIdproduct());
        holder.namaproduk.setText(list.get(position).getProduct_name());
        holder.stok.setText(list.get(position).getStok());
        holder.satuan.setText(list.get(position).getSatuan());
        holder.berat.setText(list.get(position).getBerat());
        Glide.with(context).load(EndPoint.IMAGE_URL + list.get(position)
                .getProduct_image())
                .apply(new RequestOptions().override(350,350))
                .into(holder.fotoproduk);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder{
        TextView idproduct,namaproduk,stok,harga_jual,satuan,berat;
        ImageView fotoproduk;


        public MyviewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            idproduct   = itemView.findViewById(R.id.idproduct);
            namaproduk  = itemView.findViewById(R.id.namaproduk);
            fotoproduk  = itemView.findViewById(R.id.fotoproduk);
            stok        = itemView.findViewById(R.id.stok);
            harga_jual  = itemView.findViewById(R.id.harga_jual);
            satuan      = itemView.findViewById(R.id.satuan);
            berat       = itemView.findViewById(R.id.berat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface !=null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClicked(pos);
                        }
                    }
                }
            });
        }
    }
}

