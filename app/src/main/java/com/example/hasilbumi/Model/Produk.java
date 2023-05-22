package com.example.hasilbumi.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Produk implements Parcelable {
    String product_name;
    String product_image;
    String stok;
    String harga_jual;
    String satuan;
    String idproduct;
    String berat;
    String produk_image;

    protected Produk(Parcel in) {
        idproduct  = in.readString();
        product_name = in.readString();
        product_image = in.readString();
        stok = in.readString();
        harga_jual = in.readString();
        satuan = in.readString();
        berat = in.readString();
        produk_image = in.readString();
    }
    public Produk() {
    }

    public static final Creator<Produk> CREATOR = new Creator<Produk>() {
        @Override
        public Produk createFromParcel(Parcel in) {
            return new Produk(in);
        }

        @Override
        public Produk[] newArray(int size) {
            return new Produk[size];
        }
    };

    public String getIdproduct() {
        return idproduct ;
    }

    public void setIdproduct (String idproduct ) {
        this.idproduct  = idproduct ;
    }


    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }


    public String getHarga_jual() {
        return harga_jual;
    }

    public void setHarga_jual(String harga_jual) {
        this.harga_jual = harga_jual;
    }


    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }


    public String getBerat() { return berat;}

    public void setBerat(String berat) { this.berat = berat;}


    public String getProduct_name(){
        return product_name;
    }

    public void setProduct_name(String product_name){
        this.product_name = product_name;
    }


    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image){
        this.product_image = product_image;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idproduct);
        dest.writeString(product_name);
        dest.writeString(product_image);
        dest.writeString(stok);
        dest.writeString(harga_jual);
        dest.writeString(satuan);
        dest.writeString(berat);
        dest.writeString(produk_image);
    }
}
