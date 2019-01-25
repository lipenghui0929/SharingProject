package com.neusoft.dao;

import java.util.List;

import com.neusoft.ddmk.damin.Jsb;
import com.neusoft.ddmk.damin.Page;

public interface JsbDao {

	public List<Jsb> listJsbs(Page page);
	
	public List<Jsb> listJsbsByDateAndJh(Jsb queryjsb,String queryCondition,Page page);
	
    public void insertJsb(Jsb jsb);
	
	public void deleteJsb(String id);
	
	public void updateJsb(Jsb jsb);
	
	public int getConut(Jsb jsb,String queryCondition);
}
