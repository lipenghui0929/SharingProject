package com.neusoft.base;

import java.util.List;

import com.neusoft.ddmk.damin.Fsb;
import com.neusoft.ddmk.damin.Imsi;
import com.neusoft.ddmk.damin.Jsb;
import com.neusoft.ddmk.damin.Page;
import com.neusoft.service.FsbService;
import com.neusoft.service.ImsiService;
import com.neusoft.service.JsbService;
import com.neusoft.service.impl.FsbServiceImpl;
import com.neusoft.service.impl.ImsiServiceImpl;
import com.neusoft.service.impl.JsbServiceImpl;

public class ColumndateUtil {
	
	private static JsbService jsbService = new JsbServiceImpl();
	private static FsbService fsbService = new FsbServiceImpl();
	private static ImsiService imsiService = new ImsiServiceImpl();
	
	public static Object[][] listJsbArray(int arrysSize,Page page){
		
		List<Jsb> list = jsbService.listJsbs(page);
		Object[][] datas = null;
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
	
	public static Object[][] listJsbArray(Jsb jsb,int arrysSize,Page page){
		Object[][] datas = null;
		List<Jsb> list = jsbService.listJsbsByDateAndJh(jsb,page);
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
   
   public static Boolean removeJsb(Integer id){
	   
		return jsbService.removeJsb(id);
		
	}
   
   public static Boolean modifyJsb(Jsb jsb){
	   
		return jsbService.modifyJsb(jsb);
		
	}
   
   public static int getConutForJsb(Jsb jsb){
	   
		return jsbService.getConut(jsb);
		
	}
	
   /*================================================FSB============================================================================= */
   public static Object[][] listFsbArray(int arrysSize,Page page){
		
		List<Fsb> list = fsbService.listFsbs(page);
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
   
   public static Object[][] listFsbArray(Fsb fsb,int arrysSize,Page page){
		
	    Object[][] datas = null;
		List<Fsb> list = fsbService.listFsbsByDateAndJh(fsb,page);
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
 
	public static void saveFsbs(List<Fsb> fsbs){
		   
		 //保存数据
		fsbService.saveFsbs(fsbs);
		
	}
   
   public static Boolean removeFsb(String id){
	   
		return fsbService.removeFsb(id);
		
	}
   
   public static Boolean modifyFsb(Fsb fsb){
	   
		return fsbService.modifyFsb(fsb);
		
	}
   
   public static int getConutForFsb(Fsb fsb){
	   
		return fsbService.getConut(fsb);
		
	}
   
   /*================================================IMSI============================================================================= */ 
   public static Object[][] listImsiArray(int arrysSize,Page page){
		
		List<Imsi> list = imsiService.listImsis(page);
		System.out.println(list.size());
		Object[][] datas = null;
		if( list.size() > 0){
			System.out.println(arrysSize);
			datas = new Object[list.size()][arrysSize];
			for (int i = 0; i < list.size(); i++) {
				datas[i][0] = new Boolean(false);
				
				datas[i][1] = list.get(i).getId();
				datas[i][2] = list.get(i).getImei();
				datas[i][3] = list.get(i).getImsi();
				datas[i][4] = list.get(i).getHaoma();
				datas[i][5] = list.get(i).getCcid();
				datas[i][6] = list.get(i).getSn();
				datas[i][7] = list.get(i).getJx();
                datas[i][8] = list.get(i).getRjbb();
				datas[i][9] = list.get(i).getCs();
				datas[i][10] = list.get(i).getCmiit();
				datas[i][11] = list.get(i).getA();
				datas[i][12] = list.get(i).getB();
				datas[i][13] = list.get(i).getZc();
				datas[i][14] = list.get(i).getStruts();
				datas[i][15] = list.get(i).getBeizhu1();
				datas[i][16] = list.get(i).getBeizhu2();
				
				datas[i][17] = new Object();
			}
		}
		
		return datas;
		
	}
 
   
   public static Boolean saveImsi(Imsi imsi){
	   
		return imsiService.saveImsi(imsi);
		
	}
   
   public static Boolean removeImsi(Integer id){
	   
		return imsiService.removeImsi(id);
		
	}
   
   public static Boolean modifyImsi(Imsi imsi){
	   
		return imsiService.modifyImsi(imsi);
		
	}
   
   public static int getConutForImsi(){
	   
		return imsiService.getConut();
		
	}
   
	public static void saveImsis(List<Imsi> imsis){
		   
		 //保存数据
		imsiService.saveImsis(imsis);
		
	}
	
}