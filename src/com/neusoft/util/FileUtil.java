package com.neusoft.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	/**
	 * ����ָ����Ŀ¼����Ŀ¼�µ�.xls��׺��Excel�ļ�
	 * @param path
	 * @param type
	 * @return
	 */
	public static List<File> findExcelFile(String path,String type) {
		List<File> list = new ArrayList<File>();
		try {
			File f = new File(path);
			File[] fs = f.listFiles();
			for (int j = 0; j < fs.length; j++) {
				if (fs[j].getName().endsWith(type + ".xls")){
					// ����xls��׺���ļ�
					list.add(fs[j]);
				}
					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	public static void main(String[] args) {
		List<File> list = findExcelFile(SystemUtil.getProjectRootPath(),"ģ��");
		for (File f : list) {
		System.out.println(f.getName());
		}
	}

}
