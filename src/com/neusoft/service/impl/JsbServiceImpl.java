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
			conn = JDBCAccessUtil.getConnection();
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
		//生产查询条件 where ** = ?
		StringBuffer sb = new StringBuffer("where ");
		if(jsb.isQueryforSjh() && !jsb.isQueryforBjh()){
			sb.append("sjh = ? ");
		}else if(!jsb.isQueryforSjh() && jsb.isQueryforBjh()){
			sb.append("bjh = ? ");
		}else if(jsb.isQueryforSjh() && jsb.isQueryforBjh()){
			sb.append("sjh = ? and bjh = ?");
		}
		Connection conn = null;
		List<Jsb> jsbs = null;
		try{
			conn = JDBCAccessUtil.getConnection();
			conn.setAutoCommit(false);
			jsbs = jsbDao.listJsbsByDateAndJh(jsb, sb.toString());
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

}
