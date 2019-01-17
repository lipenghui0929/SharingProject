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
 * properties属性文件的工具类
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
	 * 根据路径，获取到Properties文件对象
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
	 * 根据key修改Properties文件中该key的值(主要是换皮肤功能的时候调用该方法)
	 * @param key
	 * @param value
	 */
	public static boolean setSkinProper(String key, String value) {
		boolean flag = true;
		/**
		 * 将文件加载到内存中，在内存中修改key对应的value值，再将文件保存
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
