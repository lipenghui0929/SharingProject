package com.neusoft.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.dao.JsbDao;
import com.neusoft.ddmk.damin.Jsb;
import com.neusoft.util.JDBCAccessUtil;

public class JsbDaoImpl implements JsbDao{

	private String  SELECVT_SQL  = "select * from jsb ";
	
	@Override
	public List<Jsb> listJsbs() {
		List<Jsb> jsbs = new ArrayList<Jsb>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = JDBCAccessUtil.getConnection("receive.url");
			pstm = conn.prepareStatement(SELECVT_SQL);
			rs = pstm.executeQuery();
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
				jsb.setSj(rs.getDate(11));
				jsb.setBz(rs.getString(12));
				jsbs.add(jsb);
				//System.out.println(jsb.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCAccessUtil.close(rs, pstm);
		}
		return jsbs;
	}

	@Override
	public List<Jsb> listJsbsByDateAndJh(Jsb queryjsb,String queryCondition) {
		List<Jsb> jsbs = new ArrayList<Jsb>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = JDBCAccessUtil.getConnection("receive.url");
			pstm = conn.prepareStatement(SELECVT_SQL+queryCondition);
			
			if(queryjsb.isQueryforSjh() && !queryjsb.isQueryforBjh() && !queryjsb.isQueryforSj()){
				pstm.setString(1, queryjsb.getSjh());
			}else if(!queryjsb.isQueryforSjh() && queryjsb.isQueryforBjh() && !queryjsb.isQueryforSj()){
				pstm.setString(1, queryjsb.getBjh());
			}else if(!queryjsb.isQueryforSjh() && !queryjsb.isQueryforBjh() && queryjsb.isQueryforSj()){
				pstm.setDate(1, new java.sql.Date(queryjsb.getSj().getTime()));
			}else if(queryjsb.isQueryforSjh() && queryjsb.isQueryforBjh() && !queryjsb.isQueryforSj()){
				pstm.setString(1, queryjsb.getSjh());
				pstm.setString(2, queryjsb.getBjh());
			}else if(queryjsb.isQueryforSjh() && !queryjsb.isQueryforBjh() && queryjsb.isQueryforSj()){
				pstm.setString(1, queryjsb.getSjh());
				pstm.setDate(2, new java.sql.Date(queryjsb.getSj().getTime()));
			}else if(!queryjsb.isQueryforSjh() && queryjsb.isQueryforBjh() && queryjsb.isQueryforSj()){
				pstm.setString(1, queryjsb.getBjh());
				pstm.setDate(2, new java.sql.Date(queryjsb.getSj().getTime()));
			}else if(queryjsb.isQueryforSjh() && queryjsb.isQueryforBjh() && queryjsb.isQueryforSj()){
				pstm.setString(1, queryjsb.getSjh());
				pstm.setString(2, queryjsb.getBjh());
				pstm.setDate(3, new java.sql.Date(queryjsb.getSj().getTime()));
			}
			
			rs = pstm.executeQuery();
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
				jsb.setSj(rs.getDate(11));
				jsb.setBz(rs.getString(12));
				jsbs.add(jsb);
				//System.out.println(jsb.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCAccessUtil.close(rs, pstm);
		}
		return jsbs;
	}

}
