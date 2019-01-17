package com.neusoft.jdbc.access;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.ddmk.damin.Jsb;

public class JDBCAccess {
	
	private String url = "jdbc:Access:///e:/mk/receive.mdb";

	public static void main(String[] args) {
		// url表示需要连接的数据源的位置，此时使用的是JDBC-ODBC桥的连接方式，url是"jdbc:odbc:数据源名"
		String url = "jdbc:Access:///e:/mk/receive.mdb";
		try {
			Class.forName("com.hxtt.sql.access.AccessDriver");
			Connection conn = DriverManager.getConnection(url);
			Statement stat = conn.createStatement();
			String sql = "select * from jsb";
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {
				Jsb jsb=new Jsb();
				jsb.setId(rs.getInt(1));
				jsb.setMc(rs.getString(2));
				jsb.setNc(rs.getString(3));
				jsb.setGh(rs.getString(4));
				jsb.setDkh(rs.getInt(5));
				jsb.setKch(rs.getInt(6));
				jsb.setImsi(rs.getString(7));
				jsb.setSjh(rs.getString(8));
				jsb.setBjh(rs.getString(9));
				jsb.setNr(rs.getString(10));
				jsb.setBz(rs.getString(11));
				System.out.println(jsb.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public List<Jsb> listJsbs(){
		List<Jsb> jsbs = new ArrayList<Jsb>();
		try {
			Class.forName("com.hxtt.sql.access.AccessDriver");
			Connection conn = DriverManager.getConnection(url);
			Statement stat = conn.createStatement();
			String sql = "select * from jsb";
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {
				Jsb jsb=new Jsb();
				jsb.setId(rs.getInt(1));
				jsb.setMc(rs.getString(2));
				jsb.setNc(rs.getString(3));
				jsb.setGh(rs.getString(4));
				jsb.setDkh(rs.getInt(5));
				jsb.setKch(rs.getInt(6));
				jsb.setImsi(rs.getString(7));
				jsb.setSjh(rs.getString(8));
				jsb.setBjh(rs.getString(9));
				jsb.setNr(rs.getString(10));
				jsb.setBz(rs.getString(11));
				jsbs.add(jsb);
				//System.out.println(jsb.toString());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsbs;
	}
}
