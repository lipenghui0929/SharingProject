package com.neusoft.service;

import java.util.List;

import com.neusoft.ddmk.damin.Fsb;

public interface FsbService {

	public List<Fsb> listFsbs();
		
	public List<Fsb> listFsbsByDateAndJh(Fsb queryfsb);
	
	public Boolean saveFsb(Fsb fsb);
	
	public Boolean removeFsb(String id);
	
	public Boolean modifyFsb(Fsb fsb);
}
