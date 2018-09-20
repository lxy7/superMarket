package com.lxy.service;
/**
*@author 作者:lxy
*@version 创建时间:
*类说明:分页方法的封装
*/ 
public class Page {
	private int pageCount;
	private int num;
	private int pagenum=10;
	private int currentpage=1;
	public int getPageCount() {
		if(num%pagenum==0)
		{
			pageCount=num/pagenum; 
		}
		else
		{
			pageCount=num/pagenum+1; 
		}
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getPagenum() {
		return pagenum;
	}
	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
}
