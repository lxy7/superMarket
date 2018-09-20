package com.lxy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lxy.dao.Db_util;
import com.lxy.entity.Provider;

/**
*@author 作者:lxy
*@version 创建时间:
*类说明:供应商处理方法
*/ 

public class ProviderMethod {
	
	Connection cn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	/*
	 * 分页查询
	 * */
	public ArrayList<Provider> getProvider(int a, int b)
	{
		ArrayList<Provider> list  = new ArrayList<Provider>();
		String sql = "  select * from (select p.* ,rownum r from mod_provider p) where r>"+(a-1)*b+" and r<="+a*b;
		try {
			cn = Db_util.getConnection();
			ps=cn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next())
			{
				Provider pro = new Provider();
				pro.setId(rs.getString(1));
				pro.setName(rs.getString(2));
				pro.setDetail(rs.getString(3));
				pro.setPeoson(rs.getString(4));
				pro.setPhone(rs.getInt(5));
				pro.setAddress(rs.getString(6));
				list.add(pro);
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
	 * 根据供应商名称和描述组合查询
	 */
	public ArrayList<Provider> getProviderByName(String name,String desc)
	{
		ArrayList<Provider> list  = new ArrayList<Provider>();
		String sql;
		if(desc.equals(""))
		sql= "select * from mod_provider where provider_name='"+name+"'";
		else
			sql= "select * from mod_provider where provider_name='"+name+"'and provider_desc like'%"+desc+"%'";
		System.out.println(sql);
		try {
			cn = Db_util.getConnection();
			ps=cn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next())
			{
				Provider pro = new Provider();
				pro.setId(rs.getString(1));
				pro.setName(rs.getString(2));
				pro.setDetail(rs.getString(3));
				pro.setPeoson(rs.getString(4));
				pro.setPhone(rs.getInt(5));
				pro.setAddress(rs.getString(6));
				list.add(pro);
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
	 * 添加供用商信息处理
	 * */
	public boolean add(Provider pro) {
		boolean flag=false;
		try {
			cn =Db_util.getConnection();
			String sql = "insert into mod_provider values(seq2.nextval,?,?,?,?,?)";
			ps=cn.prepareStatement(sql);
			ps.setString(1, pro.getName());
			ps.setString(2, pro.getDetail());
			ps.setString(3, pro.getPeoson());
			ps.setInt(4, pro.getPhone());
			ps.setString(5, pro.getAddress());
		
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
	/**
	 * 根据id获取供应商信息
	 * */
	public Provider getProviderById(String id)
	{
		Provider pro = new Provider();
		String sql;
		
		sql= "select * from mod_provider where provider_id='"+id+"'";
		
		System.out.println(sql);
		try {
			cn = Db_util.getConnection();
			ps=cn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next())
			{
				
				pro.setId(rs.getString(1));
				pro.setName(rs.getString(2));
				pro.setDetail(rs.getString(3));
				pro.setPeoson(rs.getString(4));
				pro.setPhone(rs.getInt(5));
				pro.setAddress(rs.getString(6));
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Db_util.release(cn, ps, rs);
		}
		return pro;
		
	}
	
	
	/*
	 * 修改用户信息
	 * */
	public boolean update(Provider pro)
	{
		boolean flag=false;
		try {
			cn =Db_util.getConnection();
			String sql = "update mod_provider set  provider_name=?, provider_desc=?, contact=?,phonenumber=?, address=? where provider_id=?";
	
			ps=cn.prepareStatement(sql);
			ps.setString(1,  pro.getName());
			ps.setInt(4, pro.getPhone());
			
			ps.setString(2, pro.getDetail());
			ps.setString(3, pro.getPeoson());
			ps.setString(5, pro.getAddress());
			ps.setString(6, pro.getId());
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
	
	/**
	 * 删除供用商信息
	 * */
	public int delete(String id)
	 {
		 int re=0;
		
		
		 try {
			 cn=Db_util.getConnection();
		 ps=cn.prepareStatement("delete from mod_provider where provider_id = ?");
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






