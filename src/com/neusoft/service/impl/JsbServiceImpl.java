package com.neusoft.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.neusoft.dao.JsbDao;
import com.neusoft.dao.impl.JsbDaoImpl;
import com.neusoft.ddmk.damin.Jsb;
import com.neusoft.service.JsbService;
import com.neusoft.util.JDBCAccessUtil;

public class JsbServiceImpl implements JsbService {

	private JsbDao jsbDao = new JsbDaoImpl();
	@Override
	public List<Jsb> listJsbs() {
		Connection conn = null;
		List<Jsb> jsbs = null;
		try{
			conn = JDBCAccessUtil.getConnection("receive.url");
			conn.setAutoCommit(false);
			jsbs = jsbDao.listJsbs();
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
	public List<Jsb> listJsbsByDateAndJh(Jsb jsb) {
		Connection conn = null;
		List<Jsb> jsbs = null;
		try{
			conn = JDBCAccessUtil.getConnection("receive.url");
			conn.setAutoCommit(false);
			jsbs = jsbDao.listJsbsByDateAndJh(jsb, getQueryCondition(jsb));
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

}
