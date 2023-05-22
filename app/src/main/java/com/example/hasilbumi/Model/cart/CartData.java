package com.example.hasilbumi.Model.cart;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CartData implements Parcelable {

    @SerializedName("product_id")
    private String product_id;

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("product_name")
    private String namaproduk;

    @SerializedName("qty")
    private String qty;

    @SerializedName("harga")
    private String harga;

    @SerializedName("satuan")
    private String satuan;

    @SerializedName("berat")
    private String berat;

    @SerializedName("product_image")
    private String product_image;

    @SerializedName("harga_awal")
    private String harga_awal;

    @SerializedName("stok")
    private String stok;

    public CartData() {
    }

    public CartData(Parcel in) {
        product_id  = in.readString();
        user_id     = in.readString();
        namaproduk  = in.readString();
        harga       = in.readString();
        satuan      = in.readString();
        berat       = in.readString();
        product_image = in.readString();
    }



    public static final Creator<CartData> CREATOR = new Creator<CartData>() {
        @Override
        public CartData createFromParcel(Parcel in) {
            return new CartData(in);
        }

        @Override
        public CartData[] newArray(int size) {
            return new CartData[size];
        }
    };


    public void setProduct_id(String product_id){
        this.product_id = product_id;
    }

    public String getProduct_id(){
        return product_id;
    }


    public void setUser_id(String user_id){
        this.user_id = user_id;
    }

    public String getUser_id(){
        return user_id;
    }


    public String getNamaproduk() {
        return namaproduk;
    }

    public void setNamaproduk(String namaproduk) {
        this.namaproduk = namaproduk;
    }


    public void setqty(String qty){
        this.qty = qty;
    }

    public String getqty(){
        return qty;
    }


    public void setHarga(String harga){
        this.harga = harga;
    }

    public String getHarga(){
        return harga;
    }


    public void setSatuan(String satuan){
        this.satuan = satuan;
    }

    public String getSatuan(){
        return satuan;
    }

    public void setBerat(String berat){
        this.berat = berat;
    }

    public String getBerat(){
        return berat;
    }


    public String getproduct_image() {
        return product_image;
    }

    public void setproduct_image(String product_image) {
        this.product_image = product_image;
    }


    public String getHarga_awal() {
        return harga_awal;
    }

    public void setHarga_awal(String harga_awal) {
        this.harga_awal = harga_awal;
    }


    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(product_id);
        dest.writeString(user_id);
        dest.writeString(qty);
        dest.writeString(harga);
        dest.writeString(satuan);
        dest.writeString(berat);
    }
}
