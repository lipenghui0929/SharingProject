package com.neusoft.service;

import java.util.List;

import com.neusoft.ddmk.damin.Fsb;
import com.neusoft.ddmk.damin.Page;

public interface FsbService {

	public List<Fsb> listFsbs(Page page);
		
	public List<Fsb> listFsbsByDateAndJh(Fsb queryfsb,Page page);
	
	public Boolean saveFsb(Fsb fsb);
	
	public Boolean removeFsb(String id);
	
	public Boolean modifyFsb(Fsb fsb);
	
	public void saveFsbs(List<Fsb> fsbs);
	
	public int getConut(Fsb fsb);
}
