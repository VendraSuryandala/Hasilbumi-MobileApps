package com.example.hasilbumi.Model.Register;

import com.google.gson.annotations.SerializedName;

public class RegisterData {


	@SerializedName("user_telp")
	private String user_telp;

	@SerializedName("user_name")
	private String user_name;

	@SerializedName("user_fullname")
	private String user_fullname;


	public void setUserTelp(String user_telp){
		this.user_telp = user_telp;
	}

	public String getUserTelp(){
		return user_telp;
	}

	public void setUserName(String user_name){
		this.user_name = user_name;
	}

	public String getUserName(){
		return user_name;
	}

	public void setUserFullname(String user_fullname){
		this.user_fullname = user_fullname;
	}

	public String getUserFullname(){
		return user_fullname;
	}
}