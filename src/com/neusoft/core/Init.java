package com.neusoft.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.neusoft.util.FileUtil;
import com.neusoft.util.SystemUtil;

/**
 * 将rxtx文件拷贝到C:\Windows\System32目录中
 * @author Administrator
 *
 */
public class Init {
	
	/**
	 * 初始化
	 */
	public void init(){
		String relativelyPath=SystemUtil.getProjectRootPath(); 
		System.out.println(relativelyPath+"\\data\\rxtxParallel.dll");
		File fileTarget = new File("C:\\Windows\\System32\\rxtxParallel.dll");// 取得目标目录
		if(!fileTarget.exists()){
			File rxtxParallelsource=new File(relativelyPath+"\\data\\rxtxParallel.dll");
			try {
				new FileUtil().copyFileUsingFileStreams(rxtxParallelsource, fileTarget);
				System.out.println("复制rxtxParallel文件成功");
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		}
		File rxtxSerial = new File("C:\\Windows\\System32\\rxtxSerial.dll");// 取得目标目录
		if(!rxtxSerial.exists()){
			File rxtxSerialsource=new File(relativelyPath+"\\data\\rxtxSerial.dll");
			try {
				new FileUtil().copyFileUsingFileStreams(rxtxSerialsource, rxtxSerial);
				System.out.println("复制rxtxSerial文件成功");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Init().init();
	}
}
