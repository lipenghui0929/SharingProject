package com.neusoft.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	/**
	 * 根据指定的目录查找目录下的.xls后缀的Excel文件
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
					// 查找xls后缀的文件
					list.add(fs[j]);
				}
					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	public static void main(String[] args) {
		List<File> list = findExcelFile(SystemUtil.getProjectRootPath(),"模板");
		for (File f : list) {
		System.out.println(f.getName());
		}
	}

}
