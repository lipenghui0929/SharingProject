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
import com.neusoft.ddmk.damin.Page;
import com.neusoft.util.JDBCAccessUtil;

public class FsbDaoImpl implements FsbDao {
	
	private String  SELECVT_SQL  = "select * from fsb ";

	@Override
	public List<Fsb> listFsbs(Page page) {
		List<Fsb> fsbs = new ArrayList<Fsb>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = JDBCAccessUtil.getConnection("send.url");
			pstm = conn.prepareStatement(SELECVT_SQL+" limit ?,?");
			pstm.setInt(1, page.getPageNow()*page.getPageSize());
			pstm.setInt(2, page.getPageSize());
			
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
	public List<Fsb> listFsbsByDateAndJh(Fsb queryfsb,String queryCondition,Page page) {
		List<Fsb> fsbs = new ArrayList<Fsb>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = JDBCAccessUtil.getConnection("send.url");
			pstm = conn.prepareStatement(SELECVT_SQL+queryCondition+" limit ?,?");
			
			if(queryfsb.isQueryforSjh() && !queryfsb.isQueryforBjh() && !queryfsb.isQueryforSj()){
				pstm.setString(1, queryfsb.getSjh());
				pstm.setInt(2, page.getPageNow()*page.getPageSize());
				pstm.setInt(3, page.getPageSize());
			}else if(!queryfsb.isQueryforSjh() && queryfsb.isQueryforBjh() && !queryfsb.isQueryforSj()){
				pstm.setString(1, queryfsb.getBjh());
				pstm.setInt(2, page.getPageNow()*page.getPageSize());
				pstm.setInt(3, page.getPageSize());
			}else if(!queryfsb.isQueryforSjh() && !queryfsb.isQueryforBjh() && queryfsb.isQueryforSj()){
				pstm.setDate(1, new java.sql.Date(queryfsb.getSj().getTime()));
				pstm.setInt(2, page.getPageNow()*page.getPageSize());
				pstm.setInt(3, page.getPageSize());
			}else if(queryfsb.isQueryforSjh() && queryfsb.isQueryforBjh() && !queryfsb.isQueryforSj()){
				pstm.setString(1, queryfsb.getSjh());
				pstm.setString(2, queryfsb.getBjh());
				pstm.setInt(3, page.getPageNow()*page.getPageSize());
				pstm.setInt(4, page.getPageSize());
			}else if(queryfsb.isQueryforSjh() && !queryfsb.isQueryforBjh() && queryfsb.isQueryforSj()){
				pstm.setString(1, queryfsb.getSjh());
				pstm.setDate(2, new java.sql.Date(queryfsb.getSj().getTime()));
				pstm.setInt(3, page.getPageNow()*page.getPageSize());
				pstm.setInt(4, page.getPageSize());
			}else if(!queryfsb.isQueryforSjh() && queryfsb.isQueryforBjh() && queryfsb.isQueryforSj()){
				pstm.setString(1, queryfsb.getBjh());
				pstm.setDate(2, new java.sql.Date(queryfsb.getSj().getTime()));
				pstm.setInt(3, page.getPageNow()*page.getPageSize());
				pstm.setInt(4, page.getPageSize());
			}else if(queryfsb.isQueryforSjh() && queryfsb.isQueryforBjh() && queryfsb.isQueryforSj()){
				pstm.setString(1, queryfsb.getSjh());
				pstm.setString(2, queryfsb.getBjh());
				pstm.setDate(3, new java.sql.Date(queryfsb.getSj().getTime()));
				pstm.setInt(4, page.getPageNow()*page.getPageSize());
				pstm.setInt(5, page.getPageSize());
			}else{
				pstm.setInt(1, page.getPageNow()*page.getPageSize());
				pstm.setInt(2, page.getPageSize());
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
			throw new RuntimeException("����ʧ��",e);
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
			throw new RuntimeException("ɾ��ʧ��",e);
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
			
			if(!"".equals(fsb.getMc()) && fsb.getMc() != null ){
				pstm.setString(1, fsb.getMc());
			}else{
				pstm.setNull(1, Types.VARCHAR);
			}
			
			if(!"".equals(fsb.getNc()) && fsb.getNc() != null ){
				pstm.setString(2, fsb.getNc());
			}else{
				pstm.setNull(2, Types.VARCHAR);
			}
			
			if(!"".equals(fsb.getGh()) && fsb.getGh() != null ){
				pstm.setString(3, fsb.getGh());
			}else{
				pstm.setNull(3, Types.VARCHAR);
			}
			
			if(!"".equals(fsb.getDkh()) && fsb.getDkh() != null ){
				pstm.setInt(4, fsb.getDkh());
			}else{
				pstm.setNull(4, Types.INTEGER);
			}
			
			if(!"".equals(fsb.getKch()) && fsb.getKch() != null ){
				pstm.setInt(5, fsb.getKch());
			}else{
				pstm.setNull(5, Types.INTEGER);
			}
			
			if(!"".equals(fsb.getImsi()) && fsb.getImsi() != null ){
				pstm.setString(6, fsb.getImsi());
			}else{
				pstm.setNull(6, Types.VARCHAR);
			}
			
			if(!"".equals(fsb.getBjh()) && fsb.getBjh() != null ){
				pstm.setString(7, fsb.getBjh());
			}else{
				pstm.setNull(7, Types.VARCHAR);
			}
			
			if(!"".equals(fsb.getSjh()) && fsb.getSjh() != null ){
				pstm.setString(8, fsb.getSjh());
			}else{
				pstm.setNull(8, Types.VARCHAR);
			}
			
			if(!"".equals(fsb.getLx()) && fsb.getLx() != null ){
				pstm.setString(9, fsb.getLx());
			}else{
				pstm.setNull(9, Types.VARCHAR);
			}
			
			if(!"".equals(fsb.getNr()) && fsb.getNr() != null ){
				pstm.setString(10, fsb.getNr());
			}else{
				pstm.setNull(10, Types.VARCHAR);
			}
			
			if(!"".equals(fsb.getCs()) && fsb.getCs() != null ){
				pstm.setInt(11, fsb.getCs());
			}else{
				pstm.setNull(11, Types.INTEGER);
			}
			
			if(!"".equals(fsb.getXx()) && fsb.getXx() != null ){
				pstm.setString(12, fsb.getXx());
			}else{
				pstm.setNull(12, Types.VARCHAR);
			}
			
			if( fsb.getSj() != null ){
				pstm.setDate(13, new java.sql.Date(fsb.getSj().getTime()));
			}else{
				pstm.setNull(13, Types.TIMESTAMP);
			}
			
			if(!"".equals(fsb.getBz()) && fsb.getBz() != null ){
				pstm.setString(14, fsb.getBz());
			}else{
				pstm.setNull(14, Types.VARCHAR);
			}
			
			if(!"".equals(fsb.getZt()) && fsb.getZt() != null ){
				pstm.setString(15, fsb.getZt());
			}else{
				pstm.setNull(15, Types.VARCHAR);
			}
			
			pstm.setString(16, fsb.getId());
			
			pstm.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("�޸�ʧ��",e);
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
			throw new RuntimeException("����ʧ��",e);
		}finally {
			JDBCAccessUtil.close(pstm);
		}
		
	}

	@Override
	public int getConut(Fsb queryfsb,String queryCondition) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try{
			
			String sql = "select count(*) from fsb "+queryCondition;
			conn = JDBCAccessUtil.getConnection("");
			pstm = conn.prepareStatement(sql);
			
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
			rs.next();
			count = rs.getInt(1);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("��ѯ��������",e);
		}finally {
			JDBCAccessUtil.close(rs,pstm);
		}
		return count;
	}

}
