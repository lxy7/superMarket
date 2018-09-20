package com.lxy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lxy.entity.User;
import com.lxy.service.Page;
import com.lxy.service.UseMethod;

/**
*@author 作者:lxy
*@version 创建时间:
*类说明:处理用户的action
*/ 
public class UserServlet extends HttpServlet {
	String ad;
	String ad1;
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
		case  "go":
			go(req,resp);
			break;
			case "login":
				dologin(req,resp);
				break;
			case "user":
				getUser(req,resp);			
				break;
				case "select":
					Select(req,resp);
					break;
				case "adduser":
					add(req,resp);
					break;
				case "isExist":
					isExist(req,resp);
					break;
				case "select_input":
					selInput(req,resp);
					break;
				case"address_cou":
			try {
				getAddress_cou(req,resp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					break ;
				case"address_cit":
			try {
				getAddress_cit(req,resp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					break ;
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
 * 删除用户信息
 * */

	private void delete(HttpServletRequest req, HttpServletResponse resp) {
		 id=req.getParameter("id");
			UseMethod bm= new UseMethod();
			int f=bm.delete(id);
			PrintWriter out;
			try {
				out=resp.getWriter();
				if(f!=0);
				{
					out.print("<script>alert('删除成功！'); location.href='user.jsp';</script>");

				}
				if(f==0)
				{
					out.print("<script>alert('删除失败！'); location.href='user.jsp';</script>");}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
		
	}

	/**
	 * 携带数据进入更新页面
	 * */

	private void getData(HttpServletRequest req, HttpServletResponse resp) {
		UseMethod bm= new UseMethod();
	     id = req.getParameter("id");
		User bill = bm.getUserById(id);
		req.getSession().setAttribute("user", bill);
		System.out.println("该id的详细信息为"+bill.toString());
		PrintWriter out;
		try {
			out=resp.getWriter();
			out.print("<script> location.href='userUpdate.jsp';</script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * 更新数据
	 * */

	private void update(HttpServletRequest req, HttpServletResponse resp) {
		
		String name = req.getParameter("username");
		String password= req.getParameter("password");
		String password1= req.getParameter("passwprd1");
		String sex = req.getParameter("sex").equals("1")? "男":"女";
		int age = Integer.parseInt(req.getParameter("age"));
		int phone = Integer.parseInt(req.getParameter("mobile"));
	    String cou = req.getParameter("sel3");
	    String address;
	    address=ad+ad1+cou;
	    System.out.println("allad"+address);
		//String auth = req.getParameter("auth").equals("1")? "经理":"普通用户";
		User user  = new User();
		user.setId(id);
		user.setAddress(address);
		System.out.println(user.getAddress());
		user.setAge(age);
		user.setName(name);
		user.setPassword(password);
		user.setPhone(phone);
		//user.setRight(auth);
		user.setSex(sex);
		UseMethod um =  new UseMethod();
		boolean flag = um.update(user);
		PrintWriter out;
		try {
			out=resp.getWriter();
			if(flag);
			{
				out.print("<script>alert('修改成功！'); location.href='user.jsp';</script>");

			}
			if(flag==false)
			{
				out.print("<script>alert('修改失败！'); location.href='user.jsp';</script>");}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	/**
	 * 根据前台传来的省份查询出对应的市级城市并将数据传给前台
	 * */
	private void getAddress_cit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String b1 = req.getParameter("p_name");
		String name =new String(b1.getBytes("ISO8859-1"),"utf-8");
		
			ad=name;
			System.out.println("ad:"+ad);
		
		System.out.println(name);
		UseMethod am = new UseMethod();
		String a = am.getArea(name);
		 String b ="";
		if(a!=null&&!a.equals(""))
		{
	       b = a.substring(0, a.length()-1);
	      System.out.println("所有名字："+b);
		}
		resp.getWriter().write(b);
		
	}
	/**
	 * 根据前台传来的市级城市查询出对应的市级城市并将数据传给前台
	 * */
	private void getAddress_cou(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String b1 = req.getParameter("cname");
		String name =new String(b1.getBytes("ISO8859-1"),"utf-8");
		ad1=name;
		System.out.println(ad1+"市级");
		UseMethod am = new UseMethod();
		String a = am.getSmall(name);
		 String b ="";
		if(a!=null&&!a.equals(""))
		{
	       b = a.substring(0, a.length()-1);
	      System.out.println("所有名字："+b);
		}
		resp.getWriter().write(b);
		
	}

		
	/**
	 * 增加用户体验，在查询的时候，输入用户名，实时提醒输入
	 * */

	private void selInput(HttpServletRequest req, HttpServletResponse resp) {
	
		String allname="";
		UseMethod um =  new UseMethod();
		
		String name = req.getParameter("userName");
		String a = um.selectName(name);
		System.out.println(a);
		if(a!=null&&!a.equals(""))
		{
	      allname = a.substring(0, a.length()-1);
	      System.out.println("所有名字："+allname);
		}
		try {
		
			resp.getWriter().write(allname);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	/**
	 * 根据用户名判断是否存在
	 * */
	private void isExist(HttpServletRequest req, HttpServletResponse resp) {
		String name = req.getParameter("username");
		UseMethod um =  new UseMethod();
		String msg= um.isExist(name);
		System.out.println(msg);
		try {
			resp.getWriter().write(msg);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 注册 新增用户
	 * */
	private void add(HttpServletRequest req, HttpServletResponse resp) {
		
		String name = req.getParameter("username");
		String password= req.getParameter("password");
		String password1= req.getParameter("passwprd1");
		String sex = req.getParameter("sex").equals("1")? "男":"女";
		int age = Integer.parseInt(req.getParameter("age"));
		int phone = Integer.parseInt(req.getParameter("mobile"));
	    String cou = req.getParameter("sel3");
	    String address;
	    address=ad+ad1+cou;
	    System.out.println("allad"+address);
		//String auth = req.getParameter("auth").equals("1")? "经理":"普通用户";
		User user  = new User();
		user.setAddress(address);
		System.out.println(user.getAddress());
		user.setAge(age);
		user.setName(name);
		user.setPassword(password);
		user.setPhone(phone);
		//user.setRight(auth);
		user.setSex(sex);
		UseMethod um =  new UseMethod();
		boolean flag = um.add(user);
		PrintWriter out;
		try {
			out=resp.getWriter();
			if(flag);
			{
				out.print("<script>alert('新增成功！'); location.href='user.jsp';</script>");

			}
			if(flag==false)
			{
				out.print("<script>alert('新增失败！'); location.href='user.jsp';</script>");}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	/**
	 * 查找用户
	 * */

	private void Select(HttpServletRequest req, HttpServletResponse resp) {
		UseMethod um =  new UseMethod();
		String name = req.getParameter("userName");
		req.setAttribute("selectname", name);
		ArrayList<User> list  = um.getUserByName(name);
		req.getSession().setAttribute("userlist", list);
		try {
			req.getRequestDispatcher("user.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 用户信息分页
	 * */
	private void getUser(HttpServletRequest req, HttpServletResponse resp) {
		
		Page  page = new Page();
		UseMethod um =  new UseMethod();
		System.out.println(req.getParameter("currentpage"));
		int currentpage =Integer.parseInt( req.getParameter("currentpage"));
		int num = um.getCount("mod_user");
		req.getSession().setAttribute("currentpage",currentpage);
		req.getSession().setAttribute("num", num);
		page.setNum(num);
		req.getSession().setAttribute("pagecount", page.getPageCount());
		ArrayList<User> list  = um.getUser(currentpage, page.getPagenum());
		req.getSession().setAttribute("userlist", list);
		 try {
			req.getRequestDispatcher("user.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 登陆处理，成功进入系统，失败给出相应的提示
	 * */
	
	public void dologin(HttpServletRequest req, HttpServletResponse resp)  
	{
	
		UseMethod um =  new UseMethod();
		String  name = req.getParameter("name");
		PrintWriter out;
		String  password = req.getParameter("password");
		boolean f = um.getValid(name, password);
		try {
			//req.setCharacterEncoding("utf-8");
			out=resp.getWriter();
			if (f) {
				resp.setCharacterEncoding("utf-8");
				req.setCharacterEncoding("utf-8");
				req.getSession().setAttribute("name", name);
				out.print("<script>alert('login success!'); location.href='index.jsp';</script>");

			} 
			else {
				req.setAttribute("newFlag", "用户名或密码错误！");
               req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 点击退出登陆返回到登陆界面，也可以通过js实现该功能
	 * */
	private void go(HttpServletRequest req, HttpServletResponse resp) {
		
		PrintWriter out;
		
			try {
				req.getSession().invalidate();
				//System.out.println("hahah");
				out=resp.getWriter();
				out.print("<script>alert('Log out!'); location.href='login.jsp';</script>");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			
	}
	

}

