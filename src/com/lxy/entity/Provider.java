package com.lxy.entity;

public class Provider {
	private String id;
	private String name;
	private String detail;
	private String peoson;
	private int phone;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPeoson() {
		return peoson;
	}
	public void setPeoson(String peoson) {
		this.peoson = peoson;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	private String address;
	public Provider() {
		super();
		// TODO Auto-generated constructor stub
	}

}
