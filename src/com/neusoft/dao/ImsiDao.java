package com.neusoft.dao;

import java.util.List;

import com.neusoft.ddmk.damin.Imsi;

public interface ImsiDao {

	public List<Imsi> listFsbs();
		
	public List<Imsi> listFsbsByDateAndJh(Imsi imsi,String queryCondition);
}
