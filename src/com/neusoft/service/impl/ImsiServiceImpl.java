package com.neusoft.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.neusoft.dao.ImsiDao;
import com.neusoft.dao.impl.ImsiDaoImpl;
import com.neusoft.ddmk.damin.Imsi;
import com.neusoft.ddmk.damin.Page;
import com.neusoft.service.ImsiService;
import com.neusoft.util.JDBCAccessUtil;

public class ImsiServiceImpl implements ImsiService {

	private ImsiDao imsiDao = new ImsiDaoImpl();
	
	@Override
	public List<Imsi> listImsis(Page page) {
		Connection conn = null;
		List<Imsi> imsis = null;
		try{
			conn = JDBCAccessUtil.getConnection("send.url");
			conn.setAutoCommit(false);
			imsis = imsiDao.listImsis(page);
			conn.commit();
			return imsis;
		}catch(Exception e){
			try {conn.rollback();} catch (SQLException e1) {}
			e.printStackTrace();
		}finally {
			JDBCAccessUtil.close(conn);
		}
		return imsis;
	}

	@Override
	public Boolean saveImsi(Imsi imsi) {
		Boolean flag = true;
		Connection conn = null;
		try{
			conn = JDBCAccessUtil.getConnection("send.url");
			conn.setAutoCommit(false);
			imsiDao.insertImsi(imsi);
			conn.commit();
		}catch(Exception e){
			flag = false;
			try {conn.rollback();} catch (SQLException e1) {}
			throw new RuntimeException("保存失败",e);
		}finally {
			JDBCAccessUtil.close(conn);
		}
		return flag;
	}

	@Override
	public Boolean removeImsi(Integer id) {
		Boolean flag = true;
		Connection conn = null;
		try{
			conn = JDBCAccessUtil.getConnection("send.url");
			conn.setAutoCommit(false);
			imsiDao.deleteImsi(id);
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
	public Boolean modifyImsi(Imsi imsi) {
		Boolean flag = true;
		Connection conn = null;
		try{
			conn = JDBCAccessUtil.getConnection("send.url");
			conn.setAutoCommit(false);
			imsiDao.updateImsi(imsi);
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
	public void saveImsis(List<Imsi> imsis) {
		Connection conn = null;
		try{
			conn = JDBCAccessUtil.getConnection("send.url");
			conn.setAutoCommit(false);
			imsiDao.insertImsis(imsis);
			conn.commit();
		}catch(Exception e){
			try {conn.rollback();} catch (SQLException e1) {}
			throw new RuntimeException("保存失败",e);
		}finally {
			JDBCAccessUtil.close(conn);
		}

	}

	@Override
	public int getConut() {
		Connection conn = null;
		int count = 0;
		try{
			conn = JDBCAccessUtil.getConnection("send.url");
			conn.setAutoCommit(false);
			count = imsiDao.getConut();
			conn.commit();
		}catch(Exception e){
			try {conn.rollback();} catch (SQLException e1) {}
			throw new RuntimeException("查询条数出错",e);
		}finally {
			JDBCAccessUtil.close(conn);
		}
		return count;
	}

}
