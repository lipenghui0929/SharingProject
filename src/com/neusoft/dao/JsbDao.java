package com.neusoft.dao;

import java.util.List;

import com.neusoft.ddmk.damin.Jsb;

public interface JsbDao {

	public List<Jsb> listJsbs();
	
	public List<Jsb> listJsbsByDateAndJh(Jsb queryjsb,String queryCondition);
}