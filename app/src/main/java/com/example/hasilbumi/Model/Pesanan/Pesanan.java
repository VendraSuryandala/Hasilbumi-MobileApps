package com.example.hasilbumi.Model.Pesanan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pesanan {

	@SerializedName("data")
	List<PesananData> pesananData;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public List<PesananData> getPesananData() {
		return pesananData;
	}

	public void setPesananData(List<PesananData> pesananData) {
		this.pesananData = pesananData;
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