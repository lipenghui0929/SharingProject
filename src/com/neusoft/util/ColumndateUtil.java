package com.neusoft.util;

import java.util.List;

import com.neusoft.ddmk.damin.Jsb;
import com.neusoft.service.JsbService;
import com.neusoft.service.impl.JsbServiceImpl;

public class ColumndateUtil {
	
	private static final int ARRAYS_SIZE = 6;
	
	public static Object[][] listJsbArray(){
		Object[][] datas = null;
		JsbService jsbService = new JsbServiceImpl();
		List<Jsb> list = jsbService.listJsbs();
		
		if(list.size() > 0){
			datas = new Object[list.size()][ARRAYS_SIZE];
			for (int i = 0; i < list.size(); i++) {
				datas[i][0] = list.get(i).getId();
				datas[i][1] = list.get(i).getImsi();
				datas[i][2] = list.get(i).getSjh();
				datas[i][3] = list.get(i).getMc();
				datas[i][4] = list.get(i).getBjh();
				datas[i][5] = list.get(i).getNr();
			}
		}
		
		return datas;
		
	}
	
}
