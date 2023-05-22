package com.example.hasilbumi;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.hasilbumi.Model.Login.LoginData;
import com.example.hasilbumi.Model.Pesanan.PesananData;
import com.example.hasilbumi.Model.Register.RegisterData;
import com.example.hasilbumi.Model.cart.CartData;

import java.util.HashMap;

public class SessionManager {


    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    //user Data
    public static final String IS_LOGIN = "isLoggedIn";
    public static final String idusers  = "idusers";
    public static final String user_name = "user_name";
    public static final String user_telp = "user_telp";
    public static final String user_fullname = "user_fullname";


    //User cart Data
    public static final String product_id = "product_id";
    public static final String user_id = "user_id";
    public static final String product_name = "namaproduk";
    public static final String product_image = "product_image";
    public static final String qty = "qty";
    public static final String harga = "harga";
    public static final String satuan = "satuan";
    public static final String berat = "berat";

    //Pesanan Data
    public static final String idorder = "idorder";
    public static final String subtotal = "subtotal";
    public static final String total_harga = "total_harga";
    public static final String order_prov = "order_prov";
    public static final String order_kab = "order_kab";
    public static final String order_kec = "order_kec";
    public static final String order_kodepos = "order_kodepos";
    public static final String order_address = "order_address";
    public static final String order_kurir = "order_kurir";
    public static final String order_layanan = "order_layanan";


    public SessionManager (Context context){
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    //untuk menyimpan session login pada user ketika berhasil login
    public void createloginsession(LoginData user){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(idusers, String.valueOf(user.getIdusers()));
        editor.putString(user_name, user.getUserName());
        editor.putString(user_telp, user.getUserTelp());
        editor.putString(user_fullname, user.getUserFullname());
        editor.commit();
    }

    public void createregistersession(RegisterData user){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(user_name, user.getUserName());
        editor.putString(user_telp, user.getUserTelp());
        editor.putString(user_fullname, user.getUserFullname());
        editor.commit();
    }


    public void usercartsession(CartData user) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(product_id, user.getProduct_id());
        editor.putString(user_id, user.getUser_id());
        editor.putString(product_name, user.getNamaproduk());
        editor.putString(qty, user.getqty());
        editor.putString(harga, user.getHarga());
        editor.putString(satuan, user.getSatuan());
        editor.putString(berat, user.getBerat());
        editor.putString(product_image, user.getproduct_image());
    }

    public void pesanansession(PesananData user){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(idorder, user.getIdorder());
        editor.putString(user_id, user.getUser_id());
        editor.putString(subtotal, user.getSubtotal());
        editor.putString(total_harga, user.getTotal_harga());
        editor.putString(order_prov, user.getOrder_prov());
        editor.putString(order_kab, user.getOrder_kab());
        editor.putString(order_kec, user.getOrder_kec());
        editor.putString(order_kodepos, user.getOrder_kodepos());
        editor.putString(order_address, user.getOrder_address());
        editor.putString(order_kurir, user.getOrder_kurir());
        editor.putString(order_layanan, user.getOrder_layanan());
        editor.commit();
    }

    //memanggil data user yang login
    public HashMap<String,String> getUserDetail(){
        HashMap<String,String> user = new HashMap<>();
        user.put(idusers, sharedPreferences.getString(idusers,null));
        user.put(user_name, sharedPreferences.getString(user_name,null));
        user.put(user_telp, sharedPreferences.getString(user_telp,null));
        user.put(user_fullname, sharedPreferences.getString(user_fullname,null));
        return user;
    }


    //memanggil User cart Data yang tersimpan
    public HashMap<String,String> getUserCartDetail(){
        HashMap<String,String> user = new HashMap<>();
        user.put(product_id, sharedPreferences.getString(product_id,null));
        user.put(user_id, sharedPreferences.getString(user_id,null));
        user.put(product_name, sharedPreferences.getString(product_name,null));
        user.put(qty, sharedPreferences.getString(qty,null));
        user.put(harga, sharedPreferences.getString(harga,null));
        user.put(satuan, sharedPreferences.getString(satuan,null));
        user.put(product_image, sharedPreferences.getString(product_image,null));
        return user;
    }

    //memanggil User Pesanan Data yang tersimpan
    public HashMap<String,String> getPesananDetail(){
        HashMap<String,String> user = new HashMap<>();
        user.put(idorder, sharedPreferences.getString(idorder,null));
        user.put(user_id, sharedPreferences.getString(user_id,null));
        user.put(subtotal, sharedPreferences.getString(subtotal,null));
        user.put(total_harga, sharedPreferences.getString(total_harga,null));
        user.put(order_prov, sharedPreferences.getString(order_prov,null));
        user.put(order_kab, sharedPreferences.getString(order_kab,null));
        user.put(order_kec, sharedPreferences.getString(order_kec,null));
        user.put(order_kodepos, sharedPreferences.getString(order_kodepos,null));
        user.put(order_address, sharedPreferences.getString(order_address,null));
        user.put(order_kurir, sharedPreferences.getString(order_kurir,null));
        user.put(order_layanan, sharedPreferences.getString(order_layanan,null));
        return user;
    }


    public void logoutSession(){
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGIN,false);
    }

}
