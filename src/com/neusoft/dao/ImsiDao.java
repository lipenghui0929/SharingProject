package com.neusoft.dao;

import java.util.List;

import com.neusoft.ddmk.damin.Imsi;
import com.neusoft.ddmk.damin.Page;

public interface ImsiDao {

	public List<Imsi> listImsis(Page page);
		
	public List<Imsi> listImsisByDateAndJh(Imsi imsi,String queryCondition);
	
    public void insertImsi(Imsi imsi);
	
	public void deleteImsi(Integer id);
	
	public void updateImsi(Imsi imsi);
	
	public void insertImsis(List<Imsi> imsis);
	
	public int getConut();
}
