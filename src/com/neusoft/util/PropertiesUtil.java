package com.neusoft.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

/**
 * properties�����ļ��Ĺ�����
 * 
 * @author chenzhenhua
 *
 */
public class PropertiesUtil {
	public static Properties prop = null;
	public static Properties propSkin = null;
	public static String path = "src/view.properties";
	public static String skinPath = "src/skin.properties";
	
	
	static {
		prop = getProperties(path);
		propSkin = getProperties(skinPath);
	}
	
	/**
	 * ����·������ȡ��Properties�ļ�����
	 * @param path
	 * @return
	 */
	public static Properties getProperties(String path){
		Properties prop = null;
		try {
			InputStream inputStream = new FileInputStream(path);
			BufferedReader bf = new BufferedReader(new InputStreamReader(
					inputStream, "utf-8"));
			prop = new Properties();
			prop.load(bf);
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
		
	}
	

	/**
	 * ����key�޸�Properties�ļ��и�key��ֵ(��Ҫ�ǻ�Ƥ�����ܵ�ʱ����ø÷���)
	 * @param key
	 * @param value
	 */
	public static boolean setSkinProper(String key, String value) {
		boolean flag = true;
		/**
		 * ���ļ����ص��ڴ��У����ڴ����޸�key��Ӧ��valueֵ���ٽ��ļ�����
		 */
		propSkin.setProperty(key, value);
		try {
			FileOutputStream fos = new FileOutputStream(skinPath);
			propSkin.store(fos, "update Skin`newValue = " + value + " date = " + new Date());
			fos.close();
		} catch (FileNotFoundException e) {
			flag = false;
			e.printStackTrace();
		} catch (IOException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	public static void main(String[] args) {
//		System.out.println(prop.getProperty("MainFrame.title"));
//		setSkinProper("skin","default");
	}

}
