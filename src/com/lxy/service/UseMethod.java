package com.lxy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lxy.dao.Db_util;
import com.lxy.entity.User;

/**
*@author 作者:lxy
*@version 创建时间:
*类说明:用户数据处理方法
*/ 

public class UseMethod {
	
	
	Connection cn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
  
	/*
	 * 登陆验证 用户名和密码匹配 返回true
	 * */
	public boolean getValid(String name,String password)
	{
		
		int flag = 0;
		
		String sql ="select user_account,user_password from mod_user";
				
				try {
					cn= Db_util.getConnection();
					ps=cn.prepareStatement(sql);
					rs=ps.executeQuery();
					
					while(rs.next())
					{
						if (name.equals(rs.getString(1))&&password.equals(rs.getString(2)))
						
						{
							
							flag = 1;
							break;
						}
						
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch bloc
					}finally{
						Db_util.release(cn, ps, rs);
					}
				if(flag==1)
				{
					return true;
				}
				else
				{
					return false;
				}
							
						
	}
	
	
	/*
	 * 用户分页方法
	 * */
	public ArrayList<User> getUser(int a, int b)
	{
		ArrayList<User> list  = new ArrayList<User>();
		String sql = "  select * from (select p.* ,rownum r from mod_user p) where r>"+(a-1)*b+" and r<="+a*b;
		try {
			cn = Db_util.getConnection();
			ps=cn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next())
			{
				User user = new User();
				user.setId(rs.getString(1));
				user.setName(rs.getString(2));
				user.setSex(rs.getString(4));
				user.setAge(rs.getInt(5));
				user.setPhone(rs.getInt(6));
				user.setAddress(rs.getString(7));
				//user.setRight(rs.getString(8));
				list.add(user);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Db_util.release(cn, ps, rs);
		}
		return list;
		
	}
	
	/**
	 * 传入表名称 查询出数据总数，分页多次回用到，提高代码的可重用性。
	 */
	public int getCount(String tablename){
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql="select count(1) c from "+tablename;
		int count=0;
		try {
			conn=Db_util.getConnection();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
				count=rs.getInt("c");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Db_util.release(cn, ps, rs);
		}
		return count;

}
	
	/*
	 * 根据姓名查询
	 */
	public ArrayList<User> getUserByName(String name)
	{
		ArrayList<User> list  = new ArrayList<User>();
		String sql = "select * from mod_user where user_account like'"+name+"%'";
		System.out.println(sql);
		try {
			cn = Db_util.getConnection();
			ps=cn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next())
			{
				User user = new User();
				user.setId(rs.getString(1));
				user.setName(rs.getString(2));
				user.setSex(rs.getString(4));
				user.setAge(rs.getInt(5));
				user.setPhone(rs.getInt(6));
				user.setAddress(rs.getString(7));
				//user.setRight(rs.getString(8));
				list.add(user);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Db_util.release(cn, ps, rs);
		}
		return list;
		
	}
	
	/*
	 * 添加用户信息
	 */
	public boolean add(User us) {
		boolean flag=false;
		try {
			cn =Db_util.getConnection();
			String sql = "insert into mod_user values(seq3.nextval,?,?,?,?,?,?)";
			ps=cn.prepareStatement(sql);
			ps.setString(1, us.getName());
			ps.setString(2, us.getPassword());
			ps.setString(3, us.getSex());
			ps.setInt(4, us.getAge());
			ps.setInt(5, us.getPhone());
			ps.setString(6, us.getAddress());
			//ps.setString(7, us.getRight());
			int re=ps.executeUpdate();
			System.out.println(sql);
			if(re!=0)
			{
				flag=true;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
		
	}
	
	/*
	 * 判断账户名是否存在
	 */
	public String  isExist(String name) {
		String msg="no";
		
		try {
			
			cn=Db_util.getConnection();
			String sql = "select user_account from mod_user where user_account = '"+name+"'";
			ps= cn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next())
			{
				msg="yes";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
		
	}
	
	/*
	 *  根据姓名模糊查询并将查询结果进行拼接处理
	 *  
	 */
	public String selectName(String name) {
		String allname="";
     try {
			
			cn=Db_util.getConnection();
			String sql = "select user_account from mod_user where user_account like '%"+name+"%'";
  System.out.println(sql);
			ps= cn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next())
			{
				allname +=rs.getString(1)+",";
			}
		
		
     }catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     return allname;
}
	/*
	 * 根据前台所传递的省名查询所在市
	 */
	public String getArea(String name)
	{
		String a="";
		try {
			cn=Db_util.getConnection();
			String sql="select add_name from mod_address_cit where parent_id=(select add_id from mod_address_pro where add_name = '"+name+"')";
			ps=cn.prepareStatement(sql);
			System.out.println(sql);
			
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				a+=rs.getString(1)+",";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	
	/*
	 * 根据前台所传递的市名查询所在县
	 * */
	public String getSmall(String name)
	{
		String a="";
		try {
			cn=Db_util.getConnection();
			String sql="select add_name from mod_address_cou where parent_id=(select add_id from mod_address_cit where add_name = '"+name+"')";
			ps=cn.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				a+=rs.getString(1)+",";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	
	
	/*
	 * 根据id查询
	 */
	
	public User getUserById(String id)
	{
		User user = new User();
		String sql = "select * from mod_user where user_id ='"+id+"'";
		System.out.println(sql);
		try {
			cn = Db_util.getConnection();
			ps=cn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next())
			{
				
				user.setId(rs.getString(1));
				user.setName(rs.getString(2));
				user.setSex(rs.getString(4));
				user.setAge(rs.getInt(5));
				user.setPhone(rs.getInt(6));
				user.setAddress(rs.getString(7));
				//user.setRight(rs.getString(8));
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Db_util.release(cn, ps, rs);
		}
		return user;
	}
	/*
	 * 修改用户信息
	 * */
	public boolean update(User user)
	{
		boolean flag=false;
		try {
			cn =Db_util.getConnection();
			String sql = "update mod_user set user_account=?, user_password=?, user_sex=?,user_age=?, user_phone=?,user_address=? where user_id=?";
	
			ps=cn.prepareStatement(sql);
			ps.setString(1,  user.getName());
			ps.setInt(4, user.getAge());
			ps.setInt(5, user.getPhone());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getSex());
			ps.setString(6, user.getAddress());
			ps.setString(7, user.getId());
			System.out.println(sql);
			//ps.setDate(7, bill.getDate());
			//ps.setString(7, us.getRight());
			int re=ps.executeUpdate();
			if(re!=0)
			{
				flag=true;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
		
	}
	/*
	 * 删除用户信息
	 */
	public int delete(String id)
	 {
		 int re=0;
		
		
		 try {
			 cn=Db_util.getConnection();
		 ps=cn.prepareStatement("delete from mod_user where user_id = ?");
		   ps.setString(1,id);
		   
			   re =ps.executeUpdate();
				System.out.println("删除"+re);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return re;
	 }
	
	

}
