package com.neusoft.service;

import java.util.List;

import com.neusoft.ddmk.damin.Fsb;
import com.neusoft.ddmk.damin.Imsi;
import com.neusoft.ddmk.damin.Page;

public interface ImsiService {

	public List<Imsi> listImsis(Page page);
		
	public Boolean saveImsi(Imsi imsi);
	
	public Boolean removeImsi(Integer id);
	
	public Boolean modifyImsi(Imsi imsi);
	
	public void saveImsis(List<Imsi> imsis);
	
	public int getConut();
}
