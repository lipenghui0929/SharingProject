package com.neusoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
	
	/**
	 * �����ļ�
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	public static void copyFileUsingFileStreams(File source, File dest)
	        throws IOException {    
	    InputStream input = null;    
	    OutputStream output = null;    
	    try {
	           input = new FileInputStream(source);
	           output = new FileOutputStream(dest);        
	           byte[] buf = new byte[1024];        
	           int bytesRead;        
	           while ((bytesRead = input.read(buf)) > 0) {
	               output.write(buf, 0, bytesRead);
	           }
	    } finally {
	        input.close();
	        output.close();
	    }
	}

	public static void main(String[] args) {
		List<File> list = findExcelFile(SystemUtil.getProjectRootPath(),"ģ��");
		for (File f : list) {
		System.out.println(f.getName());
		}
	}

}
