package com.neusoft.base;

import java.util.List;

import javax.swing.JCheckBox;

import com.neusoft.ddmk.damin.Fsb;
import com.neusoft.ddmk.damin.Jsb;
import com.neusoft.service.FsbService;
import com.neusoft.service.JsbService;
import com.neusoft.service.impl.FsbServiceImpl;
import com.neusoft.service.impl.JsbServiceImpl;

public class ColumndateUtil {
	
	private static JsbService jsbService = new JsbServiceImpl();
	private static FsbService fsbService = new FsbServiceImpl();
	
	public static Object[][] listJsbArray(int arrysSize){
		
		List<Jsb> list = jsbService.listJsbs();
		Object[][] datas = new Object[list.size()][arrysSize];
		if(list.size() > 0){
			for (int i = 0; i < list.size(); i++) {
				datas[i][0] = new Boolean(false);
				datas[i][1] = list.get(i).getId();
				datas[i][2] = list.get(i).getImsi();
				datas[i][3] = list.get(i).getSjh();
				datas[i][4] = list.get(i).getMc();
				datas[i][5] = list.get(i).getBjh();
				datas[i][6] = list.get(i).getNr();
				datas[i][7] = new Object();
				
			}
		}
		
		return datas;
		
	}
	
	public static Object[][] listJsbArray(Jsb jsb,int arrysSize){
		Object[][] datas = null;
		List<Jsb> list = jsbService.listJsbsByDateAndJh(jsb);
		if(list.size() > 0){
			datas = new Object[list.size()][arrysSize];
			for (int i = 0; i < list.size(); i++) {
				datas[i][0] = new Boolean(false);
				datas[i][1] = list.get(i).getId();
				datas[i][2] = list.get(i).getImsi();
				datas[i][3] = list.get(i).getSjh();
				datas[i][4] = list.get(i).getMc();
				datas[i][5] = list.get(i).getBjh();
				datas[i][6] = list.get(i).getNr();
				datas[i][7] = new Object();
			}
		}
		
		return datas;
		
	}
	
	public static Boolean saveJsb(Jsb jsb){
		   
		return jsbService.saveJsb(jsb);
		
	}
   
   public static Boolean removeJsb(String id){
	   
		return jsbService.removeJsb(id);
		
	}
   
   public static Boolean modifyJsb(Jsb jsb){
	   
		return jsbService.modifyJsb(jsb);
		
	}
   
	
   public static Object[][] listFsbArray(int arrysSize){
		
		List<Fsb> list = fsbService.listFsbs();
		System.out.println(list.size());
		Object[][] datas = null;
		if( list.size() > 0){
			System.out.println(arrysSize);
			datas = new Object[list.size()][arrysSize];
			for (int i = 0; i < list.size(); i++) {
				datas[i][0] = new Boolean(false);
				datas[i][1] = list.get(i).getId();
				datas[i][2] = list.get(i).getImsi();
				datas[i][3] = list.get(i).getSjh();
				datas[i][4] = list.get(i).getMc();
				datas[i][5] = list.get(i).getBjh();
				datas[i][6] = list.get(i).getNr();
				datas[i][7] = new Object();
			}
		}
		
		return datas;
		
	}
   
   public static Object[][] listFsbArray(Fsb fsb,int arrysSize){
		
	    Object[][] datas = null;
		List<Fsb> list = fsbService.listFsbsByDateAndJh(fsb);
		if(list.size() > 0){
			datas = new Object[list.size()][arrysSize];
			for (int i = 0; i < list.size(); i++) {
				datas[i][0] = new Boolean(false);
				datas[i][1] = list.get(i).getId();
				datas[i][2] = list.get(i).getImsi();
				datas[i][3] = list.get(i).getSjh();
				datas[i][4] = list.get(i).getMc();
				datas[i][5] = list.get(i).getBjh();
				datas[i][6] = list.get(i).getNr();
				datas[i][7] = new Object();
			}
		}
		
		return datas;
		
	}
   
   public static Boolean saveFsb(Fsb fsb){
	   
		return fsbService.saveFsb(fsb);
		
	}
   
   public static Boolean removeFsb(String id){
	   
		return fsbService.removeFsb(id);
		
	}
   
   public static Boolean modifyFsb(Fsb fsb){
	   
		return fsbService.modifyFsb(fsb);
		
	}
	
}