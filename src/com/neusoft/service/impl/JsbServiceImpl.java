package com.neusoft.service.impl;

import java.util.List;

import com.neusoft.ddmk.damin.Jsb;
import com.neusoft.jdbc.access.JDBCAccess;
import com.neusoft.service.JsbService;

public class JsbServiceImpl implements JsbService {

	private JDBCAccess jsbDao = new JDBCAccess();
	@Override
	public List<Jsb> listJsbs() {
		
		return jsbDao.listJsbs();
	}

}
