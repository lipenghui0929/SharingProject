package com.neusoft.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.dao.ImsiDao;
import com.neusoft.ddmk.damin.Fsb;
import com.neusoft.ddmk.damin.Imsi;
import com.neusoft.ddmk.damin.Page;
import com.neusoft.util.JDBCAccessUtil;

public class ImsiDaoImpl implements ImsiDao {
	
	private String  SELECVT_SQL  = "select * from imsi ";

	@Override
	public List<Imsi> listImsis(Page page) {
		List<Imsi> imsis = new ArrayList<Imsi>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = JDBCAccessUtil.getConnection("imsi.url");
			pstm = conn.prepareStatement(SELECVT_SQL+" limit ?,?");
			pstm.setInt(1, page.getPageNow()*page.getPageSize());
			pstm.setInt(2, page.getPageSize());
			
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


	@Override
	public List<Imsi> listImsisByDateAndJh(Imsi qimsi, String queryCondition) {
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


	@Override
	public void insertImsi(Imsi imsi) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try{
			String sql = "insert into imsi (imei,imsi,haoma,ccid,sn,jx,rjbb,cs,cmiit,a,b,zc,struts,beizhu1,beizhu2) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			conn = JDBCAccessUtil.getConnection("send.url");
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, imsi.getImei());
			pstm.setString(2, imsi.getImsi());
			pstm.setString(3, imsi.getHaoma());
			pstm.setString(4, imsi.getCcid());
			pstm.setString(5, imsi.getSn());
			pstm.setString(6, imsi.getJx());
			pstm.setString(7, imsi.getRjbb());
			pstm.setString(8, imsi.getCs());
			pstm.setString(9, imsi.getCmiit());
			pstm.setString(10, imsi.getA());
			pstm.setString(11, imsi.getB());
			pstm.setString(12, imsi.getZc());
			pstm.setString(13, imsi.getStruts());
			pstm.setString(14, imsi.getBeizhu1());
			pstm.setString(15, imsi.getBeizhu2());
			
			pstm.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("±£´æÊ§°Ü",e);
		}finally {
			JDBCAccessUtil.close(pstm);
		}
	}


	@Override
	public void deleteImsi(Integer id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try{
			String sql = "delete from  imsi where id = ?";
			conn = JDBCAccessUtil.getConnection("send.url");
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, id);	
			pstm.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("É¾³ýÊ§°Ü",e);
		}finally {
			JDBCAccessUtil.close(pstm);
		}
	}


	@Override
	public void updateImsi(Imsi imsi) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try{
			String sql = "update imsi set imei = ?,haoma = ?,ccid = ?,sn = ?,"
					+ "jx = ?,rjbb = ?,cs = ?,cmiit = ?,a = ?,b = ?,zc = ?,struts = ?,beizhu1 = ?,beizhu2 = ?,imsi = ? "
					+ "where id = ?";
			
			conn = JDBCAccessUtil.getConnection("send.url");
			pstm = conn.prepareStatement(sql);
			
			if(!"".equals(imsi.getImei()) && imsi.getImei() != null ){
				pstm.setString(1, imsi.getImei());
			}else{
				pstm.setNull(1, Types.VARCHAR);
			}
			
			if(!"".equals(imsi.getHaoma()) && imsi.getHaoma() != null ){
				pstm.setString(2, imsi.getHaoma());
			}else{
				pstm.setNull(2, Types.VARCHAR);
			}
			
			if(!"".equals(imsi.getCcid()) && imsi.getCcid() != null ){
				pstm.setString(3, imsi.getCcid());
			}else{
				pstm.setNull(3, Types.VARCHAR);
			}
			
			if(!"".equals(imsi.getSn()) && imsi.getSn() != null ){
				pstm.setString(4, imsi.getSn());
			}else{
				pstm.setNull(4, Types.VARCHAR);
			}
			
			if(!"".equals(imsi.getJx()) && imsi.getJx() != null ){
				pstm.setString(5, imsi.getJx());
			}else{
				pstm.setNull(5, Types.VARCHAR);
			}
			
			if(!"".equals(imsi.getRjbb()) && imsi.getRjbb() != null ){
				pstm.setString(6, imsi.getRjbb());
			}else{
				pstm.setNull(6, Types.VARCHAR);
			}
			
			if(!"".equals(imsi.getCs()) && imsi.getCs() != null ){
				pstm.setString(7, imsi.getCs());
			}else{
				pstm.setNull(7, Types.VARCHAR);
			}
			
			if(!"".equals(imsi.getCmiit()) && imsi.getCmiit() != null ){
				pstm.setString(8, imsi.getCmiit());
			}else{
				pstm.setNull(8, Types.VARCHAR);
			}
			
			if(!"".equals(imsi.getA()) && imsi.getA() != null ){
				pstm.setString(9, imsi.getA());
			}else{
				pstm.setNull(9, Types.VARCHAR);
			}
			
			if(!"".equals(imsi.getB()) && imsi.getB() != null ){
				pstm.setString(10, imsi.getB());
			}else{
				pstm.setNull(10, Types.VARCHAR);
			}
			
			if(!"".equals(imsi.getZc()) && imsi.getZc() != null ){
				pstm.setString(11, imsi.getZc());
			}else{
				pstm.setNull(11, Types.VARCHAR);
			}
			
			if(!"".equals(imsi.getStruts()) && imsi.getStruts() != null ){
				pstm.setString(12, imsi.getStruts());
			}else{
				pstm.setNull(12, Types.VARCHAR);
			}
			
			
			if(!"".equals(imsi.getBeizhu1()) && imsi.getBeizhu1() != null ){
				pstm.setString(13, imsi.getBeizhu1());
			}else{
				pstm.setNull(13, Types.VARCHAR);
			}
			
			if(!"".equals(imsi.getBeizhu2()) && imsi.getBeizhu2() != null ){
				pstm.setString(14, imsi.getBeizhu2());
			}else{
				pstm.setNull(14, Types.VARCHAR);
			}
			
			if(!"".equals(imsi.getImsi()) && imsi.getImsi() != null ){
				pstm.setString(15, imsi.getImsi());
			}else{
				pstm.setNull(15, Types.VARCHAR);
			}
			
			pstm.setInt(16, imsi.getId());
			
			pstm.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("ÐÞ¸ÄÊ§°Ü",e);
		}finally {
			JDBCAccessUtil.close(pstm);
		}
	}


	@Override
	public void insertImsis(List<Imsi> imsis) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try{
			String sql = "insert into imsi (imei,imsi,haoma,ccid,sn,jx,rjbb,cs,cmiit,a,b,zc,struts,beizhu1,beizhu2) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			conn = JDBCAccessUtil.getConnection("send.url");
			pstm = conn.prepareStatement(sql);
			
			for (Imsi imsi : imsis) {
				
			/*	pstm.setInt(1, imsi.getId());
				pstm.setNull(2, Types.VARCHAR);
				pstm.setNull(3, Types.VARCHAR);
				pstm.setNull(4, Types.VARCHAR);
				pstm.setNull(5, Types.INTEGER);
				pstm.setNull(6, Types.INTEGER);
				pstm.setNull(7, Types.VARCHAR);
				pstm.setString(8, fsb.getBjh());
				pstm.setString(9, fsb.getSjh());
				pstm.setNull(10, Types.VARCHAR);
				pstm.setNull(11, Types.VARCHAR);
				pstm.setNull(12, Types.INTEGER);
				pstm.setString(13, fsb.getXx());
				pstm.setNull(14, Types.TIMESTAMP);
				pstm.setNull(15, Types.VARCHAR);
				pstm.setNull(16, Types.VARCHAR);;*/
				
				pstm.addBatch();
			}
			
			
			pstm.executeBatch();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("±£´æÊ§°Ü",e);
		}finally {
			JDBCAccessUtil.close(pstm);
		}
		
	}


	@Override
	public int getConut() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try{
			
			String sql = "select count(*) from imsi ";
			conn = JDBCAccessUtil.getConnection("");
			pstm = conn.prepareStatement(sql);
			
			rs = pstm.executeQuery();
			rs.next();
			count = rs.getInt(1);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("²éÑ¯ÌõÊý³ö´í",e);
		}finally {
			JDBCAccessUtil.close(rs,pstm);
		}
		return count;
	}

}
