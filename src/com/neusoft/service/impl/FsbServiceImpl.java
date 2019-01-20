package com.neusoft.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.neusoft.dao.FsbDao;
import com.neusoft.dao.impl.FsbDaoImpl;
import com.neusoft.ddmk.damin.Fsb;
import com.neusoft.ddmk.damin.Jsb;
import com.neusoft.service.FsbService;
import com.neusoft.util.JDBCAccessUtil;

public class FsbServiceImpl implements FsbService{

	private FsbDao fsbDao = new FsbDaoImpl();
	
	@Override
	public List<Fsb> listFsbs() {
		Connection conn = null;
		List<Fsb> fsbs = null;
		try{
			conn = JDBCAccessUtil.getConnection();
			conn.setAutoCommit(false);
			fsbs = fsbDao.listFsbs();
			conn.commit();
			return fsbs;
		}catch(Exception e){
			try {conn.rollback();} catch (SQLException e1) {}
			e.printStackTrace();
		}finally {
			JDBCAccessUtil.close(conn);
		}
		return fsbs;
	}

	@Override
	public List<Fsb> listFsbsByDateAndJh(Fsb queryfsb) {
		Connection conn = null;
		List<Fsb> fsbs = null;
		try{
			conn = JDBCAccessUtil.getConnection();
			conn.setAutoCommit(false);
			fsbs = fsbDao.listFsbsByDateAndJh(queryfsb, getQueryCondition(queryfsb));
			conn.commit();
			return fsbs;
		}catch(Exception e){
			try {conn.rollback();} catch (SQLException e1) {}
			e.printStackTrace();
		}finally {
			JDBCAccessUtil.close(conn);
		}
		return fsbs;
	}

	public String getQueryCondition(Fsb queryfsb){
		//生产查询条件 where ** = ?
		StringBuffer sb = new StringBuffer("where 1=1 ");
		if(queryfsb.isQueryforSjh() && !queryfsb.isQueryforBjh() && !queryfsb.isQueryforSj()){
			sb.append("and sjh = ? ");
		}else if(!queryfsb.isQueryforSjh() && queryfsb.isQueryforBjh() && !queryfsb.isQueryforSj()){
			sb.append("and bjh = ? ");
		}else if(!queryfsb.isQueryforSjh() && !queryfsb.isQueryforBjh() && queryfsb.isQueryforSj()){
			sb.append("and sj = ? ");
		}else if(queryfsb.isQueryforSjh() && queryfsb.isQueryforBjh() && !queryfsb.isQueryforSj()){
			sb.append("and sjh = ? and bjh = ?");
		}else if(queryfsb.isQueryforSjh() && !queryfsb.isQueryforBjh() && queryfsb.isQueryforSj()){
			sb.append("and sjh = ? and sj = ?");
		}else if(!queryfsb.isQueryforSjh() && queryfsb.isQueryforBjh() && queryfsb.isQueryforSj()){
			sb.append("and bjh = ? and sj = ?");
		}else if(queryfsb.isQueryforSjh() && queryfsb.isQueryforBjh() && queryfsb.isQueryforSj()){
			sb.append("and sjh = ? and bjh = ? and sj = ?");
		}
		return sb.toString();
		
	}

	@Override
	public Boolean saveFsb(Fsb fsb) {
		Boolean flag = true;
		fsb.setId(UUID.randomUUID().toString());
		Connection conn = null;
		try{
			conn = JDBCAccessUtil.getConnection();
			conn.setAutoCommit(false);
			fsbDao.insertFsb(fsb);
			conn.commit();
		}catch(Exception e){
			flag = false;
			try {conn.rollback();} catch (SQLException e1) {}
			e.printStackTrace();
		}finally {
			JDBCAccessUtil.close(conn);
		}
		return flag;
	}

	@Override
	public Boolean removeFsb(String id) {
		Boolean flag = true;
		Connection conn = null;
		try{
			conn = JDBCAccessUtil.getConnection();
			conn.setAutoCommit(false);
			fsbDao.deleteFsb(id);
			conn.commit();
		}catch(Exception e){
			flag = false;
			try {conn.rollback();} catch (SQLException e1) {}
			e.printStackTrace();
		}finally {
			JDBCAccessUtil.close(conn);
		}
		return flag;
		
	}

	@Override
	public Boolean modifyFsb(Fsb fsb) {
		Boolean flag = true;
		Connection conn = null;
		try{
			conn = JDBCAccessUtil.getConnection();
			conn.setAutoCommit(false);
			fsbDao.updateFsb(fsb);
			conn.commit();
		}catch(Exception e){
			flag = false;
			try {conn.rollback();} catch (SQLException e1) {}
			e.printStackTrace();
		}finally {
			JDBCAccessUtil.close(conn);
		}
		return flag;
		
	}
}
