package com.xuanyue.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.xuanyue.bean.UserInfo;


public class DBHandler {
	String url = "jdbc:mysql://localhost:3306/xuanyue_reader?useUnicode=true&characterEncoding=utf-8";		//数据库链接路径
	String user="root";			//管理员
	String psd="123456";		//管理员密码
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	public Connection getConn(){		//获得数据库链接
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, psd);
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		return conn;
	}
	
	/**
	 * 查询数据
	 * @param sql   	数据查询语句
	 * @param args		查询语句中的参数
	 * @return
	 */
	
	public String query(String sql,String[] args){
		String result="";
		try{
			conn=getConn();
			System.out.println(sql);
			ps=conn.prepareStatement(sql);
			for(int i=0;i<args.length;i++){
				ps.setString(i+1, args[i]);
			}			
			rs=ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();			
			int count=rsmd.getColumnCount();
			System.out.println(count);
			while (rs.next()) {
				for(int i=1;i<=count;i++){
					result+=rs.getString(i)+"*";
				}				
			}
			System.out.println(result);
		}catch (Exception ex) {
			ex.printStackTrace();
		}		
		return result;		
	}
	
	/**
	 * 插入数据
	 * @param sql
	 * @param args
	 * @return
	 */
	public boolean insert(String sql,String[] args){
		boolean flag=false;
		try{
			conn=getConn();
			System.out.println(sql);
			ps=conn.prepareStatement(sql);
			for(int i=0;i<args.length;i++){
				ps.setString(i+1, args[i]);
			}			
			int i=ps.executeUpdate();
			System.out.println(i);
			if(i==1){
				flag=true;
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}		
		return flag;
	}
	
	/**
	 * 删除数据
	 * @param sql
	 * @param args
	 * @return
	 */
	public boolean delete(String sql,String[] args){
		boolean flag=false;
		try{
			conn=getConn();
			System.out.println(sql);
			ps=conn.prepareStatement(sql);
			for(int i=0;i<args.length;i++){
				ps.setString(i+1, args[i]);
			}			
			int i=ps.executeUpdate();
			System.out.println(i);
			if(i==1){
				flag=true;
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}		
		return flag;
	}
	
	/**
	 * 更新数据
	 * @param sql
	 * @param args
	 * @return
	 */
	
	public boolean update(String sql,String[] args){
		boolean flag=false;
		
		try{
			conn=getConn();
			System.out.println(sql);
			ps=conn.prepareStatement(sql);
			for(int i=0;i<args.length;i++){
				ps.setString(i+1, args[i]);
			}			
			int i=ps.executeUpdate();
			System.out.println(i);
			if(i==1){
				flag=true;
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}	
		
		
		return flag;
		
	}
	
	/**
	 * 查询用户是否存在
	 * 1、登录时判断是否成功
	 * @param sql
	 * @param args
	 * @return
	 */
	public boolean checkUser(String sql,String[] args){
		boolean flag=false;
		try{
			conn=getConn();
			ps=conn.prepareStatement(sql);
			for(int i=0;i<args.length;i++){
				ps.setString(i+1, args[i]);
			}			
			rs=ps.executeQuery();
			if(rs.next()){
				flag=true;
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}		
		return flag;
	}
	
	/**
	 * 根据用户名获取UID
	 * @param username
	 * @return
	 */
	public String getUID(String username){
	
		String sql="select uid from user where user_name=?";
		try{
			conn=getConn();
			ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			rs=ps.executeQuery();
			while (rs.next()) {
				return rs.getString(1);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}		
		return null;
	}
	
	/**
	 * 根据UID获取用户信息
	 * @param uid
	 * @return
	 */
	public UserInfo getUserInfo(String uid){
		String sql="select * from user_info where uid=?";
		UserInfo info=new UserInfo();
		try{
			conn=getConn();
			ps=conn.prepareStatement(sql);
			ps.setString(1, uid);			
			rs=ps.executeQuery();
			while (rs.next()) {
				info.uid=rs.getString(1);
				info.nickname=rs.getString(2);
				info.sex=rs.getString(3);
				info.birthday=rs.getString(4);
				info.head_portrait=rs.getString(5);
				info.signature=rs.getString(6);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}		
		
		return info;
	}
	/**
	 * 根据uid获得用户书架
	 * @param uid
	 * @return
	 */
	public List<String> getBookcase(String uid){
		String sql="select book_title from bookcase_info where uid=?";
		List<String> bookcase=new ArrayList<>();
		try{
			conn=getConn();
			ps=conn.prepareStatement(sql);
			ps.setString(1, uid);			
			rs=ps.executeQuery();
		
		
			ResultSetMetaData rsmd = rs.getMetaData();			
			int count=rsmd.getColumnCount();
			System.out.println(count);
			while (rs.next()) {
				for(int i=1;i<=count;i++){
					bookcase.add(rs.getString(i));
				}				
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		return bookcase;
	}
	
	
	

}
