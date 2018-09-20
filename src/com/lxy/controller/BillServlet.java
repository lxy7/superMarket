package com.lxy.controller;



import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.lxy.entity.Bill;
import com.lxy.service.BillMethod;
import com.lxy.service.Page;
import com.lxy.service.UseMethod;

/**
*@author 作者:lxy
*@version 创建时间:
*类说明:处理账单的action
*/
public class BillServlet extends HttpServlet {
	String id;
	/*
	 * 重写doget()方法。
	 * */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
	/*
	 * 重写dopost()方法。
	 * */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String action = req.getParameter("action");//获取前台的action
		switch(action)//根据不同的action调用不同的方法
		{
			
			case "bill":
				getBill(req,resp);
				break;
				case "select":
					select(req,resp);
					break;
				case "addBill":
					add(req,resp);
					break;
				case "update":
				    update(req,resp);
				    break;
				case "data":
				getDate(req,resp);
				break;
				case "delete":
					delete(req,resp);
					break;
		}
	}
 /*
  * 账单的删除处理
  * */
	private void delete(HttpServletRequest req, HttpServletResponse resp) {
		 id=req.getParameter("id");
		BillMethod bm= new BillMethod();
		int f=bm.delete(id);
		PrintWriter out;
		try {
			out=resp.getWriter();
			if(f!=0);
			{
				out.print("<script>alert('删除成功！'); location.href='bill.jsp';</script>");

			}
			if(f==0)
			{
				out.print("<script>alert('删除失败！'); location.href='bill.jsp';</script>");}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
	}

	/**
	 * 根据前台传的id来获取对应的账单对象
	 */
	private void getDate(HttpServletRequest req, HttpServletResponse resp) {
		BillMethod bm= new BillMethod();
		 id = req.getParameter("id");
		Bill bill = bm.getById(id);
		req.getSession().setAttribute("bill", bill);
		System.out.println("该id的详细信息为"+bill.toString());
		PrintWriter out;
		try {
			out=resp.getWriter();
			out.print("<script> location.href='billUpdate.jsp';</script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	/**
	 * 修改数据信息，修改完毕通知前台
	 */
	private void update(HttpServletRequest req, HttpServletResponse resp) {
		
		System.out.println("要修改的id"+id);
		String name = req.getParameter("name");
		String pname = req.getParameter("pname");
		int num= Integer.parseInt(req.getParameter("num"));
		int  price= Integer.parseInt(req.getParameter("money"));
		String dec = req.getParameter("discription");
		String flag = req.getParameter("isPay").equals("1")? "是":"否";
		//address = new String(req.getParameter("address").getBytes("ISO-8859-1"),"utf-8");
		System.out.println(dec);
		Bill bill = new Bill();
		bill.setId(id);
		bill.setDetail(dec);
		bill.setFlag(flag);
		bill.setName(name);
		bill.setPname(pname);
		bill.setNum(num);
		bill.setPrice(price);
		BillMethod bm = new BillMethod();
		boolean f=bm.update(bill);
		PrintWriter out;
		try {
			out=resp.getWriter();
			if(f);
			{
				out.print("<script>alert('修改成功！'); location.href='bill.jsp';</script>");

			}
			if(f==false)
			{
				out.print("<script>alert('修改失败！'); location.href='bill.jsp';</script>");}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
	}
	/*
	  * 账单的分页处理
	  * */
	private void getBill(HttpServletRequest req, HttpServletResponse resp) {
		Page  page = new Page();
		UseMethod um = new UseMethod();
		BillMethod bm = new BillMethod();
		//System.out.println(req.getParameter("currentpage"));
		int currentpage =Integer.parseInt( req.getParameter("currentpage"));
		int num = um.getCount("mode_bill");
		req.getSession().setAttribute("currentpage",currentpage);
		req.getSession().setAttribute("num", num);
		page.setNum(num);
		req.getSession().setAttribute("pagecount", page.getPageCount());
		ArrayList<Bill> list  = bm.getBill(currentpage, page.getPagenum());
		req.getSession().setAttribute("bilist", list);
		 try {
			req.getRequestDispatcher("bill.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	/*
	  * 账单的查找处理
	  * */
	private void select(HttpServletRequest req, HttpServletResponse resp) {
		BillMethod bm= new BillMethod();
		String name = req.getParameter("productName");
		String a= req.getParameter("payStatus");
		String flag;
		if (a.equals("3"))
			flag="3";
		else
		flag=a.equals("1")?"是":"否";	
		System.out.println(flag+name+a);
		List<Bill> list = bm.getBillByName(name, flag);
		req.getSession().setAttribute("bilist", list);
		 try {
				req.getRequestDispatcher("bill.jsp").forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	/**
	 * 账单信息的添加
	 */
	private void add(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String name = req.getParameter("name");
		String pname = req.getParameter("pname");
		int num= Integer.parseInt(req.getParameter("num"));
		int  price= Integer.parseInt(req.getParameter("money"));
		String dec = req.getParameter("discription");
		String flag = req.getParameter("isPay").equals("1")? "是":"否";
		//address = new String(req.getParameter("address").getBytes("ISO-8859-1"),"utf-8");
		System.out.println(dec);
		Bill bill = new Bill();
		bill.setDetail(dec);
		bill.setFlag(flag);
		bill.setName(name);
		bill.setPname(pname);
		bill.setNum(num);
		bill.setPrice(price);
		BillMethod bm = new BillMethod();
		boolean f=bm.add(bill);
		PrintWriter out;
		try {
			out=resp.getWriter();
			if(f);
			{
				out.print("<script>alert('新增成功！'); location.href='bill.jsp';</script>");

			}
			if(f==false)
			{
				out.print("<script>alert('新增失败！'); location.href='bill.jsp';</script>");}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}	
	}
}
	





