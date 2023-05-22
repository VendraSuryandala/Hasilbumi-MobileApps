package com.example.hasilbumi.API;

import com.example.hasilbumi.Model.Login.Login;
import com.example.hasilbumi.Model.Pesanan.Pesanan;
import com.example.hasilbumi.Model.Register.Register;
import com.example.hasilbumi.Model.cart.Cart;
import com.example.hasilbumi.Model.user_profile.UserProfile;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface apiinterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<Login> loginResponse(
            @Field("user_name") String user_name,
            @Field("user_password") String user_password
            );

    @FormUrlEncoded
    @POST("register.php")
    Call<Register> registerResponse(
            @Field("user_name") String user_name,
            @Field("user_password") String user_password,
            @Field("user_fullname") String user_fullname,
            @Field("user_telp") String user_telp
            );

    @FormUrlEncoded
    @POST("editprofile.php")
    Call<UserProfile> edituserResponse(
            @Field("idusers") String idusers,
            @Field("user_name") String user_name,
            @Field("user_fullname") String user_fullname,
            @Field("user_telp") String user_telp
    );

    @FormUrlEncoded
    @POST("addkeranjang.php")
    Call<Cart> UserCartResponse(
             @Field("product_id") String product_id,
             @Field("user_id") String user_id,
             @Field("product_name") String product_name,
             @Field("qty") String qty,
             @Field("harga") String harga,
             @Field("satuan") String satuan,
             @Field("berat") String berat,
             @Field("harga_awal") String harga_awal,
             @Field("stok") String stok
             );

    @FormUrlEncoded
    @POST("editkeranjang.php")
    Call<Cart> EditCartResponse(
            @Field("product_id") String product_id,
            @Field("user_id") String user_id,
            @Field("product_name") String product_name,
            @Field("qty") String qty,
            @Field("harga") String harga,
            @Field("satuan") String satuan,
            @Field("berat") String berat,
            @Field("harga_awal") String harga_awal,
            @Field("stok") String stok
    );

    @FormUrlEncoded
    @POST("getkeranjang.php")
    Call<Cart> GetCartResponse(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("hpskeranjang.php")
    Call<Cart> HapusCartResponse(
            @Field("product_id") String Product_id
    );

    @FormUrlEncoded
    @POST("selectkeranjang.php")
    Call<Cart> SelectCartResponse(
            @Field("product_id") String Product_id
    );

    @FormUrlEncoded
    @POST("pesanan.php")
    Call<Pesanan> PesananResponse(
            @Field("user_id") String user_id,
            @Field("subtotal") String subtotal,
            @Field("total_harga") String total_harga,
            @Field("order_prov") String order_prov,
            @Field("order_kab") String order_kab,
            @Field("order_kec") String order_kec,
            @Field("order_kodepos") String order_kodepos,
            @Field("order_address") String order_address,
            @Field("order_kurir") String order_kurir,
            @Field("order_layanan") String order_layanan
    );

    @FormUrlEncoded
    @POST("getpesanan.php")
    Call<Pesanan> GetPesananResponse(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("selectbukti.php")
    Call<Pesanan> SelectbuktiResponse(
            @Field("idorder") String order_id
    );


}
