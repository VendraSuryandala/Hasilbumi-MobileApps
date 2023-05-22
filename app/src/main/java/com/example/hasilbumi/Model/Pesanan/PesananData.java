package com.example.hasilbumi.Model.Pesanan;

import com.google.gson.annotations.SerializedName;

public class PesananData {

	@SerializedName("idorder")
	private String idorder;

	@SerializedName("user_id")
	private String user_id;

	@SerializedName("datetime")
	private String datetime;

	@SerializedName("subtotal")
	private String subtotal;

	@SerializedName("total_harga")
	private String total_harga;

	@SerializedName("order_prov")
	private String order_prov;

	@SerializedName("order_kab")
	private String order_kab;

	@SerializedName("order_kec")
	private String order_kec;

	@SerializedName("order_kodepos")
	private String order_kodepos;

	@SerializedName("order_address")
	private String order_address;

	@SerializedName("order_kurir")
	private String order_kurir;

	@SerializedName("order_layanan")
	private String order_layanan;

	@SerializedName("status")
	private String status;


	public String getIdorder() {
		return idorder;
	}

	public void setIdorder(String idorder) {
		this.idorder = idorder;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public String getTotal_harga() {
		return total_harga;
	}

	public void setTotal_harga(String total_harga) {
		this.total_harga = total_harga;
	}

	public String getOrder_prov() {
		return order_prov;
	}

	public void setOrder_prov(String order_prov) {
		this.order_prov = order_prov;
	}

	public String getOrder_kab() {
		return order_kab;
	}

	public void setOrder_kab(String order_kab) {
		this.order_kab = order_kab;
	}

	public String getOrder_kec() {
		return order_kec;
	}

	public void setOrder_kec(String order_kec) {
		this.order_kec = order_kec;
	}

	public String getOrder_kodepos() {
		return order_kodepos;
	}

	public void setOrder_kodepos(String order_kodepos) {
		this.order_kodepos = order_kodepos;
	}

	public String getOrder_address() {
		return order_address;
	}

	public void setOrder_address(String order_address) {
		this.order_address = order_address;
	}

	public String getOrder_kurir() {
		return order_kurir;
	}

	public void setOrder_kurir(String order_kurir) {
		this.order_kurir = order_kurir;
	}

	public String getOrder_layanan() {
		return order_layanan;
	}

	public void setOrder_layanan(String order_layanan) {
		this.order_layanan = order_layanan;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}