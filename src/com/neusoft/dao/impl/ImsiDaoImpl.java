package com.neusoft.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.dao.ImsiDao;
import com.neusoft.ddmk.damin.Fsb;
import com.neusoft.ddmk.damin.Imsi;
import com.neusoft.ddmk.damin.Jsb;
import com.neusoft.util.JDBCAccessUtil;

public class ImsiDaoImpl implements ImsiDao {
	
	private String  SELECVT_SQL  = "select * from imsi ";

	@Override
	public List<Imsi> listFsbs() {
		List<Imsi> imsis = new ArrayList<Imsi>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = JDBCAccessUtil.getConnection("imsi.url");
			pstm = conn.prepareStatement(SELECVT_SQL);
			rs = pstm.executeQuery();
			while (rs.next()) {
				
				Imsi imsi=new Imsi();
				imsi.setId(rs.getInt(1));
				imsi.setImei(rs.getString(2));
				imsi.setHaoma(rs.getString(3));
				imsi.setCcid(rs.getString(4));
				imsi.setSn(rs.getString(5));
				imsi.setJx(rs.getString(6));
				imsi.setRjbb(rs.getString(7));
				imsi.setCs(rs.getString(8));
				imsi.setCmiit(rs.getString(9));
				imsi.setA(rs.getString(10));
				imsi.setB(rs.getString(11));
				imsi.setZc(rs.getString(12));
				imsi.setBeizhu1(rs.getString(13));
				imsi.setBeizhu2(rs.getString(14));
				
				imsis.add(imsi);
				//System.out.println(jsb.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCAccessUtil.close(rs, pstm);
		}
		return imsis;
	}


	@Override
	public List<Imsi> listFsbsByDateAndJh(Imsi qimsi, String queryCondition) {
		List<Imsi> imsis = new ArrayList<Imsi>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = JDBCAccessUtil.getConnection("imsi.url");
			pstm = conn.prepareStatement(SELECVT_SQL+queryCondition);
			
			
			rs = pstm.executeQuery();
			while (rs.next()) {
				
				Imsi imsi=new Imsi();
				imsi.setId(rs.getInt(1));
				imsi.setImei(rs.getString(2));
				imsi.setHaoma(rs.getString(3));
				imsi.setCcid(rs.getString(4));
				imsi.setSn(rs.getString(5));
				imsi.setJx(rs.getString(6));
				imsi.setRjbb(rs.getString(7));
				imsi.setCs(rs.getString(8));
				imsi.setCmiit(rs.getString(9));
				imsi.setA(rs.getString(10));
				imsi.setB(rs.getString(11));
				imsi.setZc(rs.getString(12));
				imsi.setBeizhu1(rs.getString(13));
				imsi.setBeizhu2(rs.getString(14));
				
				imsis.add(imsi);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCAccessUtil.close(rs, pstm);
		}
		return imsis;
	}

}
