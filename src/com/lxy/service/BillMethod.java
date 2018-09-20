package com.lxy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lxy.dao.Db_util;
import com.lxy.entity.Bill;

/**
*@author 作者:lxy
*@version 创建时间:
*类说明:账单数据处理方法
*/ 

public class BillMethod {
	Connection cn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
  
	/*
	 * 分页查询返回一个集合对象
	 * */
	
	public ArrayList<Bill> getBill(int a, int b)
	{
		ArrayList<Bill> list  = new ArrayList<Bill>();
		String sql = "  select * from (select b.* ,rownum r from mode_bill b) where r>"+(a-1)*b+" and r<="+a*b;
		try {
			cn = Db_util.getConnection();
			ps=cn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next())
			{
				Bill bill= new Bill();
				bill.setId(rs.getString(1));
				bill.setName(rs.getString(2));
				bill.setNum(rs.getInt(3));
				bill.setPrice(rs.getInt(4));
				bill.setFlag(rs.getString(5));
				bill.setPname(rs.getString(6));
				bill.setDetail(rs.getString(7));
				bill.setDate(rs.getDate(8));
				list.add(bill);
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
	 * 根据给定的表名查询出所有记录数
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
	 * 根据商品名称和是否付款进行组合查询
	 */
	public ArrayList<Bill> getBillByName(String name,String flag)
	{
		ArrayList<Bill> list  = new ArrayList<Bill>();
		String sql;
		if(flag.equals("3"))//是否付款一栏为空
		{
		 sql = "select * from mode_bill where bill_name like'%"+name+"%'";
		}
		else
		{
			sql = "select * from mode_bill where bill_name like'%"+name+"%' and bill_flag='"+flag+"'" ;
		}
		System.out.println(sql); 
		try {
			cn = Db_util.getConnection();
			ps=cn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next())
			{
				Bill  bill = new Bill();
				bill.setId(rs.getString(1));
				bill.setName(rs.getString(2));
				bill.setNum(rs.getInt(3));
				bill.setPrice(rs.getInt(4));
				bill.setFlag(rs.getString(5));
				bill.setPname(rs.getString(6));
				bill.setDetail(rs.getString(7));
				bill.setDate(rs.getDate(8));
				list.add(bill);
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
	 * 添加账单
	 * */
	public boolean add(Bill bill) {
		boolean flag=false;
		try {
			cn =Db_util.getConnection();
			String sql = "insert into mode_bill values(seq1.nextval,?,?,?,?,?,?,sysdate)";
	
			ps=cn.prepareStatement(sql);
			ps.setString(1, bill.getName());
			ps.setInt(2, bill.getNum());
			ps.setInt(3, bill.getPrice());
			ps.setString(4, bill.getFlag());
			ps.setString(5, bill.getPname());
			ps.setString(6, bill.getDetail());
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
	 * 修改账单信息
	 * */
	public boolean update(Bill bill)
	{
		boolean flag=false;
		try {
			cn =Db_util.getConnection();
			String sql = "update mode_bill set bill_name=?,bill_num=?,bill_price=?,bill_flag=?,provider_name=?,bill_detail=? where bill_id=?";
	
			ps=cn.prepareStatement(sql);
			ps.setString(1, bill.getName());
			ps.setInt(2, bill.getNum());
			ps.setInt(3, bill.getPrice());
			ps.setString(4, bill.getFlag());
			ps.setString(5, bill.getPname());
			ps.setString(6, bill.getDetail());
			ps.setString(7, bill.getId());
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
	 * 根据id查询相关数据
	 */
	 
	public Bill  getById(String id) {
		
		
		Bill bill  = new Bill();
			
			try {
				cn=Db_util.getConnection();
				String sql = "select * from mode_bill where bill_id = '"+id+"'";
				System.out.println(sql);
				ps= cn.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next())
				{
					bill.setId(rs.getString(1));
					bill.setId(rs.getString(1));
					bill.setName(rs.getString(2));
					bill.setNum(rs.getInt(3));
					bill.setPrice(rs.getInt(4));
					bill.setFlag(rs.getString(5).equals("是")?"1":"0");
					bill.setPname(rs.getString(6));
					bill.setDetail(rs.getString(7));
					bill.setDate(rs.getDate(8));
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return bill;
	
	}
	/*
	 * 根据id删除账单
	 * */
	public int delete(String id)
	 {
		 int re=0;
		
		
		 try {
			 cn=Db_util.getConnection();
		 ps=cn.prepareStatement("delete from mode_bill where  bill_id = ?");
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
