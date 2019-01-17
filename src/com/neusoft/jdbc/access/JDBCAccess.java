package com.neusoft.jdbc.access;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.neusoft.ddmk.damin.Jsb;

public class JDBCAccess {

	public static void main(String[] args) {
		// url��ʾ��Ҫ���ӵ�����Դ��λ�ã���ʱʹ�õ���JDBC-ODBC�ŵ����ӷ�ʽ��url��"jdbc:odbc:����Դ��"
		String url = "jdbc:Access:///imsi.mdb";
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
}
