package com.neusoft.util;

import java.io.File;
import java.io.IOException;

public class SystemUtil {
	
	public static void main(String[] args) {
		
//		System.out.println(getRootPath());
	}
	
	/**
	 * �õ���Ŀ�ĸ�·��
	 * @return
	 */
	public static String getProjectRootPath() {
		File directory = new File("");// ����Ϊ��
		String path = null;
		try {
			path = directory.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
