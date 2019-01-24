package com.neusoft.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.dao.FsbDao;
import com.neusoft.ddmk.damin.Fsb;
import com.neusoft.ddmk.damin.Jsb;
import com.neusoft.util.JDBCAccessUtil;

public class FsbDaoImpl implements FsbDao {
	
	private String  SELECVT_SQL  = "select * from fsb ";

	@Override
	public List<Fsb> listFsbs() {
		List<Fsb> fsbs = new ArrayList<Fsb>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = JDBCAccessUtil.getConnection("send.url");
			pstm = conn.prepareStatement(SELECVT_SQL);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Fsb fsb=new Fsb();
				fsb.setId(rs.getString(1));
				fsb.setMc(rs.getString(2));
				fsb.setNc(rs.getString(3));
				fsb.setGh(rs.getString(4));
				fsb.setDkh(rs.getInt(5));
				fsb.setKch(rs.getInt(6));
				fsb.setImsi(rs.getString(7));
				fsb.setBjh(rs.getString(8));
				fsb.setSjh(rs.getString(9));
				fsb.setLx(rs.getString(10));
				fsb.setNr(rs.getString(11));
				fsb.setCs(rs.getInt(12));
				fsb.setXx(rs.getString(13));
				fsb.setSj(rs.getDate(14));
				fsb.setBz(rs.getString(15));
				fsb.setZt(rs.getString(16));
				fsbs.add(fsb);
				//System.out.println(jsb.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCAccessUtil.close(rs, pstm);
		}
		return fsbs;
	}

	@Override
	public List<Fsb> listFsbsByDateAndJh(Fsb queryfsb, String queryCondition) {
		List<Fsb> fsbs = new ArrayList<Fsb>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = JDBCAccessUtil.getConnection("send.url");
			pstm = conn.prepareStatement(SELECVT_SQL+queryCondition);
			
			if(queryfsb.isQueryforSjh() && !queryfsb.isQueryforBjh() && !queryfsb.isQueryforSj()){
				pstm.setString(1, queryfsb.getSjh());
			}else if(!queryfsb.isQueryforSjh() && queryfsb.isQueryforBjh() && !queryfsb.isQueryforSj()){
				pstm.setString(1, queryfsb.getBjh());
			}else if(!queryfsb.isQueryforSjh() && !queryfsb.isQueryforBjh() && queryfsb.isQueryforSj()){
				pstm.setDate(1, new java.sql.Date(queryfsb.getSj().getTime()));
			}else if(queryfsb.isQueryforSjh() && queryfsb.isQueryforBjh() && !queryfsb.isQueryforSj()){
				pstm.setString(1, queryfsb.getSjh());
				pstm.setString(2, queryfsb.getBjh());
			}else if(queryfsb.isQueryforSjh() && !queryfsb.isQueryforBjh() && queryfsb.isQueryforSj()){
				pstm.setString(1, queryfsb.getSjh());
				pstm.setDate(2, new java.sql.Date(queryfsb.getSj().getTime()));
			}else if(!queryfsb.isQueryforSjh() && queryfsb.isQueryforBjh() && queryfsb.isQueryforSj()){
				pstm.setString(1, queryfsb.getBjh());
				pstm.setDate(2, new java.sql.Date(queryfsb.getSj().getTime()));
			}else if(queryfsb.isQueryforSjh() && queryfsb.isQueryforBjh() && queryfsb.isQueryforSj()){
				pstm.setString(1, queryfsb.getSjh());
				pstm.setString(2, queryfsb.getBjh());
				pstm.setDate(3, new java.sql.Date(queryfsb.getSj().getTime()));
			}
			
			rs = pstm.executeQuery();
			while (rs.next()) {
				Fsb fsb=new Fsb();
				fsb.setId(rs.getString(1));
				fsb.setMc(rs.getString(2));
				fsb.setNc(rs.getString(3));
				fsb.setGh(rs.getString(4));
				fsb.setDkh(rs.getInt(5));
				fsb.setKch(rs.getInt(6));
				fsb.setImsi(rs.getString(7));
				fsb.setBjh(rs.getString(8));
				fsb.setSjh(rs.getString(9));
				fsb.setLx(rs.getString(10));
				fsb.setNr(rs.getString(11));
				fsb.setCs(rs.getInt(12));
				fsb.setXx(rs.getString(13));
				fsb.setSj(rs.getDate(14));
				fsb.setBz(rs.getString(15));
				fsb.setZt(rs.getString(16));
				fsbs.add(fsb);
				//System.out.println(fsbs.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCAccessUtil.close(rs, pstm);
		}
		return fsbs;
	}

	@Override
	public void insertFsb(Fsb fsb) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try{
			String sql = "insert into fsb (id,mc,nc,gh,dkh,kch,imsi,bjh,sjh,lx,nr,cs,xx,sj,bz,zt) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			conn = JDBCAccessUtil.getConnection("send.url");
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, fsb.getId());
			pstm.setString(2, fsb.getMc());
			pstm.setString(3, fsb.getNc());
			pstm.setString(4, fsb.getGh());
			pstm.setInt(5, fsb.getDkh());
			pstm.setInt(6, fsb.getKch());
			pstm.setString(7, fsb.getImsi());
			pstm.setString(8, fsb.getBjh());
			pstm.setString(9, fsb.getSjh());
			pstm.setString(10, fsb.getLx());
			pstm.setString(11, fsb.getNr());
			pstm.setInt(12, fsb.getCs());
			pstm.setString(13, fsb.getXx());
			pstm.setDate(14, new java.sql.Date(fsb.getSj().getTime()));
			pstm.setString(15, fsb.getBz());
			pstm.setString(16, fsb.getZt());
			
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
	public void deleteFsb(String id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try{
			String sql = "delete from  fsb where id = ?";
			conn = JDBCAccessUtil.getConnection("send.url");
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, id);	
			pstm.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("É¾³ýÊ§°Ü",e);
		}finally {
			JDBCAccessUtil.close(pstm);
		}
		
	}

	@Override
	public void updateFsb(Fsb fsb) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try{
			String sql = "update fsb set "
					+ "mc = ?,nc = ?,gh = ?,"
					+ "dkh = ?,kch = ?,imsi = ?,"
					+ "bjh = ?,sjh = ?,lx = ?,nr = ?,"
					+ "cs = ?,xx = ?,sj = ?,bz = ?,zt = ? "
					+ "where id = ?";
			
			conn = JDBCAccessUtil.getConnection("send.url");
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, fsb.getMc());
			pstm.setString(2, fsb.getNc());
			pstm.setString(3, fsb.getGh());
			pstm.setInt(4, fsb.getDkh());
			pstm.setInt(5, fsb.getKch());
			pstm.setString(6, fsb.getImsi());
			pstm.setString(7, fsb.getBjh());
			pstm.setString(8, fsb.getSjh());
			pstm.setString(9, fsb.getLx());
			pstm.setString(10, fsb.getNr());
			pstm.setInt(11, fsb.getCs());
			pstm.setString(12, fsb.getXx());
			pstm.setDate(13, new java.sql.Date(fsb.getSj().getTime()));
			pstm.setString(14, fsb.getBz());
			pstm.setString(15, fsb.getZt());
			pstm.setString(16, fsb.getId());
			
			pstm.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("É¾³ýÊ§°Ü",e);
		}finally {
			JDBCAccessUtil.close(pstm);
		}
		
	}

	@Override
	public void insertFsbs(List<Fsb> fsbs) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try{
			String sql = "insert into fsb (id,mc,nc,gh,dkh,kch,imsi,bjh,sjh,lx,nr,cs,xx,sj,bz,zt) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			conn = JDBCAccessUtil.getConnection("send.url");
			pstm = conn.prepareStatement(sql);
			
			for (Fsb fsb : fsbs) {
				
				pstm.setString(1, fsb.getId());
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
				pstm.setNull(16, Types.VARCHAR);;
				
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

}
