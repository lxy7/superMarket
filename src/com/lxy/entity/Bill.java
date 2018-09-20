package com.lxy.entity;

import java.sql.Date;

public class Bill {
	@Override
	public String toString() {
		return "Bill [id=" + id + ", name=" + name + ", num=" + num + ", price=" + price + ", pname=" + pname
				+ ", flag=" + flag + ", detail=" + detail + ", date=" + date + "]";
	}
	private String id;
	private String name;
	private int num;
	private int price;
	private String pname;
	private String flag;
	private String detail;
	private Date  date;
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	

}
