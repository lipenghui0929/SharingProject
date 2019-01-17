package com.neusoft.util;

import java.io.File;
import java.io.IOException;

public class SystemUtil {
	
	public static void main(String[] args) {
		
//		System.out.println(getRootPath());
	}
	
	/**
	 * 得到项目的根路径
	 * @return
	 */
	public static String getProjectRootPath() {
		File directory = new File("");// 参数为空
		String path = null;
		try {
			path = directory.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
