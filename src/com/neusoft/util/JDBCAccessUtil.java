package com.neusoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import java.io.InputStream;



public class JDBCAccessUtil {
	
	private static  Properties p = new Properties();
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	static{
		InputStream is = JDBCAccessUtil.class.getResourceAsStream("/jdbc.properties");
		try {p.load(is);} catch (Exception e) {}
	}
   
   public static Connection getConnection(String url)throws Exception{
	   Connection conn = tl.get();
	   if(tl.get() == null){
		   Class.forName(p.getProperty("mysql.driver"));
	   
		   conn = DriverManager.getConnection(p.getProperty("mysql.url"),p.getProperty("mysql.username"),p.getProperty("mysql.password"));
		   tl.set(conn);
		   return conn;
	   }
		   return conn;
   }
 
    public static void close(ResultSet rs,PreparedStatement pstmt,Connection conn){
	   if(rs != null) try {rs.close();} catch (Exception e2) {}
	   if(pstmt != null) try {pstmt.close();} catch (Exception e2) {}
	   if(conn != null)  try {conn.close();tl.remove();} catch (Exception e2) {}
   }
   
   public static void close(PreparedStatement pstmt,Connection conn){
	   if(pstmt != null) try {pstmt.close();} catch (Exception e2) {}
	   if(conn != null)  try {conn.close();tl.remove();} catch (Exception e2) {}
   }
   public static void close(ResultSet rs,PreparedStatement pstmt){
	   if(rs != null) try {rs.close();} catch (Exception e2) {}
	   if(pstmt != null) try {pstmt.close();} catch (Exception e2) {}
   }
   public static void close(Connection conn){
	   if(conn != null)  try {conn.close();tl.remove();} catch (Exception e2) {}
   }
   public static void close(PreparedStatement pstmt){
	  
	   if(pstmt != null) try {pstmt.close();} catch (Exception e2) {}
   }
}