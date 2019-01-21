package com.neusoft.dao;

import java.util.List;

import com.neusoft.ddmk.damin.Fsb;

public interface FsbDao {

    public List<Fsb> listFsbs();
	
	public List<Fsb> listFsbsByDateAndJh(Fsb queryfsb,String queryCondition);
	
	public void insertFsb(Fsb fsb);
	
	public void deleteFsb(String id);
	
	public void updateFsb(Fsb fsb);
}
