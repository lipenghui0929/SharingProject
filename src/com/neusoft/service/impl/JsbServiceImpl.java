package com.neusoft.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.neusoft.dao.JsbDao;
import com.neusoft.dao.impl.JsbDaoImpl;
import com.neusoft.ddmk.damin.Jsb;
import com.neusoft.ddmk.damin.Page;
import com.neusoft.service.JsbService;
import com.neusoft.util.JDBCAccessUtil;

public class JsbServiceImpl implements JsbService {

	private JsbDao jsbDao = new JsbDaoImpl();
	@Override
	public List<Jsb> listJsbs(Page page) {
		Connection conn = null;
		List<Jsb> jsbs = null;
		try{
			conn = JDBCAccessUtil.getConnection("receive.url");
			conn.setAutoCommit(false);
			jsbs = jsbDao.listJsbs(page);
			conn.commit();
			return jsbs;
		}catch(Exception e){
			try {conn.rollback();} catch (SQLException e1) {}
			e.printStackTrace();
		}finally {
			JDBCAccessUtil.close(conn);
		}
		return jsbs;
	}
	@Override
	public List<Jsb> listJsbsByDateAndJh(Jsb jsb,Page page) {
		Connection conn = null;
		List<Jsb> jsbs = null;
		try{
			conn = JDBCAccessUtil.getConnection("receive.url");
			conn.setAutoCommit(false);
			jsbs = jsbDao.listJsbsByDateAndJh(jsb, getQueryCondition(jsb),page);
			conn.commit();
			return jsbs;
		}catch(Exception e){
			try {conn.rollback();} catch (SQLException e1) {}
			e.printStackTrace();
		}finally {
			JDBCAccessUtil.close(conn);
		}
		return jsbs;
	}
	
	public String getQueryCondition(Jsb queryjsb){
		//生产查询条件 where ** = ?
		StringBuffer sb = new StringBuffer("where 1=1 ");
		if(queryjsb.isQueryforSjh() && !queryjsb.isQueryforBjh() && !queryjsb.isQueryforSj()){
			sb.append("and sjh = ? ");
		}else if(!queryjsb.isQueryforSjh() && queryjsb.isQueryforBjh() && !queryjsb.isQueryforSj()){
			sb.append("and bjh = ? ");
		}else if(!queryjsb.isQueryforSjh() && !queryjsb.isQueryforBjh() && queryjsb.isQueryforSj()){
			sb.append("and sj = ? ");
		}else if(queryjsb.isQueryforSjh() && queryjsb.isQueryforBjh() && !queryjsb.isQueryforSj()){
			sb.append("and sjh = ? and bjh = ?");
		}else if(queryjsb.isQueryforSjh() && !queryjsb.isQueryforBjh() && queryjsb.isQueryforSj()){
			sb.append("and sjh = ? and sj = ?");
		}else if(!queryjsb.isQueryforSjh() && queryjsb.isQueryforBjh() && queryjsb.isQueryforSj()){
			sb.append("and bjh = ? and sj = ?");
		}else if(queryjsb.isQueryforSjh() && queryjsb.isQueryforBjh() && queryjsb.isQueryforSj()){
			sb.append("and sjh = ? and bjh = ? and sj = ?");
		}
		return sb.toString();
		
	}
	@Override
	public Boolean saveJsb(Jsb jsb) {
		Boolean flag = true;
		Connection conn = null;
		try{
			conn = JDBCAccessUtil.getConnection("receive.url");
			conn.setAutoCommit(false);
			jsbDao.insertJsb(jsb);
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
	public Boolean removeJsb(String id) {
		Boolean flag = true;
		Connection conn = null;
		try{
			conn = JDBCAccessUtil.getConnection("receive.url");
			conn.setAutoCommit(false);
			jsbDao.deleteJsb(id);
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
	public Boolean modifyJsb(Jsb jsb) {
		Boolean flag = true;
		Connection conn = null;
		try{
			conn = JDBCAccessUtil.getConnection("receive.url");
			conn.setAutoCommit(false);
			jsbDao.updateJsb(jsb);
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
	public int getConut(Jsb Jsb) {
		Connection conn = null;
		int count = 0;
		try{
			conn = JDBCAccessUtil.getConnection("send.url");
			conn.setAutoCommit(false);
			count = jsbDao.getConut(Jsb, getQueryCondition(Jsb));
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
