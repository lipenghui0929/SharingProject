package com.neusoft.service;

import java.util.List;

import com.neusoft.ddmk.damin.Jsb;

public interface JsbService {
	
	List<Jsb> listJsbs();
	
	List<Jsb> listJsbsByDateAndJh(Jsb jsb);
	
    public Boolean saveJsb(Jsb jsb);
	
	public Boolean removeJsb(String id);
	
	public Boolean modifyJsb(Jsb fjsb);
}
