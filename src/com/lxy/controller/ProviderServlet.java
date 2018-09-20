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

import com.lxy.entity.Provider;
import com.lxy.service.Page;
import com.lxy.service.ProviderMethod;
import com.lxy.service.UseMethod;
/**
*@author 作者:lxy
*@version 创建时间:
*类说明:处理供应商的action
*/
public class ProviderServlet extends HttpServlet {
String id;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {
		resp.setCharacterEncoding("utf-8");
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String action = req.getParameter("action");
		switch(action)
		{
			
			case "provider":
				getprovider(req,resp);
				break;
				case "select":
					Select(req,resp);
					break;
				case "add":
					add(req,resp);
					break;
				case "update":
				    update(req,resp);
				    break;
				case "data":
				getData(req,resp);
				break;
				case "delete":
					delete(req,resp);
					break;
		}
	}
/**
 * 删除供应商
 * */
	private void delete(HttpServletRequest req, HttpServletResponse resp) {
		 id=req.getParameter("id");
			ProviderMethod bm= new ProviderMethod();
			int f=bm.delete(id);
			PrintWriter out;
			try {
				out=resp.getWriter();
				if(f!=0);
				{
					out.print("<script>alert('删除成功！'); location.href='provider.jsp';</script>");

				}
				if(f==0)
				{
					out.print("<script>alert('删除失败！'); location.href='provider.jsp';</script>");}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
			
	}

	/**
	 * 获取对应id的供应商信息并跳转到更新界面
	 * */
	private void getData(HttpServletRequest req, HttpServletResponse resp) {
		ProviderMethod bm= new ProviderMethod();
		 id = req.getParameter("id");
		Provider bill  = bm.getProviderById(id);
		req.getSession().setAttribute("pro", bill);
		System.out.println("该id的详细信息为"+bill.toString());
		PrintWriter out;
		try {
			out=resp.getWriter();
			out.print("<script> location.href='providerUpdate.jsp';</script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	/**
	 * 更新供应商信息，其中包括地址信息，地址采用了三级联动所以在更新时，需要对数据进行拼接处理
	 * */
	private void update(HttpServletRequest req, HttpServletResponse resp) {
		String name = req.getParameter("proName");
		String detail= req.getParameter("proDesc");
		String contact= req.getParameter("contact");
		int phone = Integer.parseInt(req.getParameter("phone"));
		String a1 = req.getParameter("sel1");
		String a2 = req.getParameter("sel2");
		String a3 = req.getParameter("sel3");
		if(a1.equals("直辖市"))
		{
			a1="";
		}
		String address=a1+a2+a3;
		System.out.println("地址为"+address);
		Provider  pro =new Provider();
		pro.setAddress(address);
		pro.setName(name);
		pro.setPeoson(contact);
		pro.setDetail(detail);
		pro.setPhone(phone);
		pro.setId(id);
		UseMethod um =  new UseMethod();
		ProviderMethod pm = new ProviderMethod();
		PrintWriter out;
		  boolean flag;
		  flag=pm.update(pro);
		try {
			out=resp.getWriter();
			if(flag);
			{
				out.print("<script>alert('修改成功！'); location.href='provider.jsp';</script>");

			}
			if(flag==false)
			{
				out.print("<script>alert('修改失败！'); location.href='provider.jsp';</script>");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	/**
	 * 添加供用商
	 * */
	private void add(HttpServletRequest req, HttpServletResponse resp) {
		String name = req.getParameter("proName");
		String detail= req.getParameter("proDesc");
		String contact= req.getParameter("contact");
		int phone = Integer.parseInt(req.getParameter("phone"));
		String a1 = req.getParameter("sel1");
		String a2 = req.getParameter("sel2");
		String a3 = req.getParameter("sel3");
		if(a1.equals("直辖市"))
		{
			a1="";
		}
		String address=a1+a2+a3;
		System.out.println("地址为"+address);
		Provider  pro =new Provider();
		pro.setAddress(address);
		pro.setName(name);
		pro.setPeoson(contact);
		pro.setDetail(detail);
		pro.setPhone(phone);
		
		UseMethod um =  new UseMethod();
		ProviderMethod pm = new ProviderMethod();
		PrintWriter out;
		  boolean flag;
		  flag=pm.add(pro);
		try {
			out=resp.getWriter();
			if(flag);
			{
				out.print("<script>alert('新增成功！'); location.href='provider.jsp';</script>");

			}
			if(flag==false)
			{
				out.print("<script>alert('新增失败！'); location.href='provider.jsp';</script>");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	/**
	 * 查找供应商
	 * */
	private void Select(HttpServletRequest req, HttpServletResponse resp) {
		
		ProviderMethod pm = new ProviderMethod();
		String name = req.getParameter("providerName");
		String desc = req.getParameter("providerDesc");
		req.setAttribute("selectname", name);
		List<Provider> list = pm.getProviderByName(name, desc);
		req.getSession().setAttribute("prolist", list);
		try {
			req.getRequestDispatcher("provider.jsp").forward(req, resp);
		} catch (ServletException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	/**
	 * 供用商的分页展示
	 * */
	private void getprovider(HttpServletRequest req, HttpServletResponse resp) {
		Page  page = new Page();
		UseMethod um = new UseMethod();
		ProviderMethod pm = new ProviderMethod();
		System.out.println(req.getParameter("currentpage"));
		int currentpage =Integer.parseInt( req.getParameter("currentpage"));
		int num = um.getCount("mod_provider");
		req.getSession().setAttribute("currentpage",currentpage);
		req.getSession().setAttribute("num", num);
		page.setNum(num);
		req.getSession().setAttribute("pagecount", page.getPageCount());
		ArrayList<Provider> list  = pm.getProvider(currentpage, page.getPagenum());
		req.getSession().setAttribute("prolist", list);
		 try {
			req.getRequestDispatcher("provider.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
	}

}

