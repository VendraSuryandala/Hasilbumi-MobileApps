package com.example.hasilbumi.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EndPoint {
    public static final String BASE_URL     = "http://192.168.100.11/rest-api/";
    public static final String PRODUCT_URL  = "http://192.168.100.11/rest-api/produk.php";
    public static final String IMAGE_URL    = "http://192.168.100.11/public_html/uploads/products/";
    public static final String CART_URL     = "http://192.168.100.11/keranjang.php";

        private static Retrofit retrofit;

    public static Retrofit getClient() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}

