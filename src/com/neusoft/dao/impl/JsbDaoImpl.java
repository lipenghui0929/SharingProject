package com.neusoft.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.dao.JsbDao;
import com.neusoft.ddmk.damin.Jsb;
import com.neusoft.ddmk.damin.Page;
import com.neusoft.util.JDBCAccessUtil;

public class JsbDaoImpl implements JsbDao{

	private String  SELECVT_SQL  = "select * from jsb ";
	
	@Override
	public List<Jsb> listJsbs(Page page) {
		List<Jsb> jsbs = new ArrayList<Jsb>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = JDBCAccessUtil.getConnection("receive.url");
			pstm = conn.prepareStatement(SELECVT_SQL+" limit ?,?");
			pstm.setInt(1, page.getPageNow()*page.getPageSize());
			pstm.setInt(2, page.getPageSize());
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
	public List<Jsb> listJsbsByDateAndJh(Jsb queryjsb,String queryCondition,Page page) {
		List<Jsb> jsbs = new ArrayList<Jsb>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = JDBCAccessUtil.getConnection("receive.url");
			pstm = conn.prepareStatement(SELECVT_SQL+queryCondition+" limit ?,?");
			
			if(queryjsb.isQueryforSjh() && !queryjsb.isQueryforBjh() && !queryjsb.isQueryforSj()){
				pstm.setString(1, queryjsb.getSjh());
				pstm.setInt(2, page.getPageNow()*page.getPageSize());
				pstm.setInt(3, page.getPageSize());
			}else if(!queryjsb.isQueryforSjh() && queryjsb.isQueryforBjh() && !queryjsb.isQueryforSj()){
				pstm.setString(1, queryjsb.getBjh());
				pstm.setInt(2, page.getPageNow()*page.getPageSize());
				pstm.setInt(3, page.getPageSize());
			}else if(!queryjsb.isQueryforSjh() && !queryjsb.isQueryforBjh() && queryjsb.isQueryforSj()){
				pstm.setDate(1, new java.sql.Date(queryjsb.getSj().getTime()));
				pstm.setInt(2, page.getPageNow()*page.getPageSize());
				pstm.setInt(3, page.getPageSize());
			}else if(queryjsb.isQueryforSjh() && queryjsb.isQueryforBjh() && !queryjsb.isQueryforSj()){
				pstm.setString(1, queryjsb.getSjh());
				pstm.setString(2, queryjsb.getBjh());
				pstm.setInt(3, page.getPageNow()*page.getPageSize());
				pstm.setInt(4, page.getPageSize());
			}else if(queryjsb.isQueryforSjh() && !queryjsb.isQueryforBjh() && queryjsb.isQueryforSj()){
				pstm.setString(1, queryjsb.getSjh());
				pstm.setDate(2, new java.sql.Date(queryjsb.getSj().getTime()));
				pstm.setInt(3, page.getPageNow()*page.getPageSize());
				pstm.setInt(4, page.getPageSize());
			}else if(!queryjsb.isQueryforSjh() && queryjsb.isQueryforBjh() && queryjsb.isQueryforSj()){
				pstm.setString(1, queryjsb.getBjh());
				pstm.setDate(2, new java.sql.Date(queryjsb.getSj().getTime()));
				pstm.setInt(3, page.getPageNow()*page.getPageSize());
				pstm.setInt(4, page.getPageSize());
			}else if(queryjsb.isQueryforSjh() && queryjsb.isQueryforBjh() && queryjsb.isQueryforSj()){
				pstm.setString(1, queryjsb.getSjh());
				pstm.setString(2, queryjsb.getBjh());
				pstm.setDate(3, new java.sql.Date(queryjsb.getSj().getTime()));
				pstm.setInt(4, page.getPageNow()*page.getPageSize());
				pstm.setInt(5, page.getPageSize());
			}else{
				pstm.setInt(1, page.getPageNow()*page.getPageSize());
				pstm.setInt(2, page.getPageSize());
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

	@Override
	public void insertJsb(Jsb jsb) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try{
			String sql = "insert into jsb (mc,nc,gh,dkh,kch,imsi,sjh,bjh,nr,sj,bz)  values (?,?,?,?,?,?,?,?,?,?,?)";
			conn = JDBCAccessUtil.getConnection("receive.url");
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, jsb.getMc());
			pstm.setString(2, jsb.getNc());
			pstm.setString(3, jsb.getGh());
			pstm.setInt(4, jsb.getDkh());
			pstm.setInt(5, jsb.getKch());
			pstm.setString(6, jsb.getImsi());
			pstm.setString(7, jsb.getSjh());
			pstm.setString(8, jsb.getBjh());
			pstm.setString(9, jsb.getNr());
			pstm.setDate(10, new java.sql.Date(jsb.getSj().getTime()));
			pstm.setString(11, jsb.getBz());
			
			pstm.executeUpdate();
			//pstm.executeBatch();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("±£´æÊ§°Ü",e);
		}finally {
			JDBCAccessUtil.close(pstm);
		}
		
	}

	@Override
	public void deleteJsb(Integer id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try{
			String sql = "delete from  jsb where id = ?";
			conn = JDBCAccessUtil.getConnection("receive.url");
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
	public void updateJsb(Jsb jsb) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try{
			String sql = "update jsb set mc = ?,nc = ?,gh = ?,dkh = ?,kch = ?,imsi = ?,sjh = ?,bjh = ?,nr = ?,sj = ?,bz = ?"
					+ "where id = ?";
			
			conn = JDBCAccessUtil.getConnection("receive.url");
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, jsb.getMc());
			pstm.setString(2, jsb.getNc());
			pstm.setString(3, jsb.getGh());
			pstm.setInt(4, jsb.getDkh());
			pstm.setInt(5, jsb.getKch());
			pstm.setString(6, jsb.getImsi());
			pstm.setString(7, jsb.getSjh());
			pstm.setString(8, jsb.getBjh());
			pstm.setString(9, jsb.getNr());
			pstm.setDate(10, new java.sql.Date(jsb.getSj().getTime()));
			pstm.setString(11, jsb.getBz());
			pstm.setInt(12, jsb.getId());
			
			pstm.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("É¾³ýÊ§°Ü",e);
		}finally {
			JDBCAccessUtil.close(pstm);
		}
		
	}

	@Override
	public int getConut(Jsb jsb, String queryCondition) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try{
			
			String sql = "select count(*) from jsb "+queryCondition;
			conn = JDBCAccessUtil.getConnection("");
			pstm = conn.prepareStatement(sql);
			
			if(jsb.isQueryforSjh() && !jsb.isQueryforBjh() && !jsb.isQueryforSj()){
				pstm.setString(1, jsb.getSjh());
			}else if(!jsb.isQueryforSjh() && jsb.isQueryforBjh() && !jsb.isQueryforSj()){
				pstm.setString(1, jsb.getBjh());
			}else if(!jsb.isQueryforSjh() && !jsb.isQueryforBjh() && jsb.isQueryforSj()){
				pstm.setDate(1, new java.sql.Date(jsb.getSj().getTime()));
			}else if(jsb.isQueryforSjh() && jsb.isQueryforBjh() && !jsb.isQueryforSj()){
				pstm.setString(1, jsb.getSjh());
				pstm.setString(2, jsb.getBjh());
			}else if(jsb.isQueryforSjh() && !jsb.isQueryforBjh() && jsb.isQueryforSj()){
				pstm.setString(1, jsb.getSjh());
				pstm.setDate(2, new java.sql.Date(jsb.getSj().getTime()));
			}else if(!jsb.isQueryforSjh() && jsb.isQueryforBjh() && jsb.isQueryforSj()){
				pstm.setString(1, jsb.getBjh());
				pstm.setDate(2, new java.sql.Date(jsb.getSj().getTime()));
			}else if(jsb.isQueryforSjh() && jsb.isQueryforBjh() && jsb.isQueryforSj()){
				pstm.setString(1, jsb.getSjh());
				pstm.setString(2, jsb.getBjh());
				pstm.setDate(3, new java.sql.Date(jsb.getSj().getTime()));
			}
			
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
