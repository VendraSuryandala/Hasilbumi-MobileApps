package com.example.hasilbumi.Model.cart;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cart {

    @SerializedName("data")
    List<CartData> cartData;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public List<CartData> getData() {
        return cartData;
    }

    public void setData(List<CartData> cartData) {
        this.cartData = cartData;
    }


    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }


    public void setStatus(boolean status){
        this.status = status;
    }

    public boolean isStatus(){
        return status;
    }

}
