package com.neusoft.service;

import java.util.List;

import com.neusoft.ddmk.damin.Jsb;
import com.neusoft.ddmk.damin.Page;

public interface JsbService {
	
	List<Jsb> listJsbs(Page page);
	
	List<Jsb> listJsbsByDateAndJh(Jsb jsb,Page page);
	
    public Boolean saveJsb(Jsb jsb);
	
	public Boolean removeJsb(String id);
	
	public Boolean modifyJsb(Jsb jsb);
	
	public int getConut(Jsb Jsb);
}
