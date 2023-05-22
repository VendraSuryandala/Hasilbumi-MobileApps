package com.example.hasilbumi.Model.user_profile;

import com.google.gson.annotations.SerializedName;

public class UserProfile{

	@SerializedName("data")
	private UserProfileData data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public void setData(UserProfileData data){
		this.data = data;
	}

	public UserProfileData getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String isStatus(){
		return status;
	}
}