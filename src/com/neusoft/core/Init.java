package com.neusoft.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.neusoft.util.FileUtil;
import com.neusoft.util.SystemUtil;

/**
 * ��rxtx�ļ�������C:\Windows\System32Ŀ¼��
 * @author Administrator
 *
 */
public class Init {
	
	/**
	 * ��ʼ��
	 */
	public void init(){
		String relativelyPath=SystemUtil.getProjectRootPath(); 
		System.out.println(relativelyPath+"\\data\\rxtxParallel.dll");
		File fileTarget = new File("C:\\Windows\\System32\\rxtxParallel.dll");// ȡ��Ŀ��Ŀ¼
		if(!fileTarget.exists()){
			File rxtxParallelsource=new File(relativelyPath+"\\data\\rxtxParallel.dll");
			try {
				new FileUtil().copyFileUsingFileStreams(rxtxParallelsource, fileTarget);
				System.out.println("����rxtxParallel�ļ��ɹ�");
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		}
		File rxtxSerial = new File("C:\\Windows\\System32\\rxtxSerial.dll");// ȡ��Ŀ��Ŀ¼
		if(!rxtxSerial.exists()){
			File rxtxSerialsource=new File(relativelyPath+"\\data\\rxtxSerial.dll");
			try {
				new FileUtil().copyFileUsingFileStreams(rxtxSerialsource, rxtxSerial);
				System.out.println("����rxtxSerial�ļ��ɹ�");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Init().init();
	}
}
