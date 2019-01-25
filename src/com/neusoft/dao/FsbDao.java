package com.neusoft.dao;

import java.util.List;

import com.neusoft.ddmk.damin.Fsb;
import com.neusoft.ddmk.damin.Page;

public interface FsbDao {

    public List<Fsb> listFsbs(Page page);
	
	public List<Fsb> listFsbsByDateAndJh(Fsb queryfsb,String queryCondition,Page page);
	
	public void insertFsb(Fsb fsb);
	
	public void deleteFsb(String id);
	
	public void updateFsb(Fsb fsb);
	
	public void insertFsbs(List<Fsb> fsbs);
	
	public int getConut(Fsb fsb,String queryCondition);
}
