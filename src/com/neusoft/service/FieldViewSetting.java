package com.neusoft.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;

import com.neusoft.util.XMLUtil;

/**
 * 案卷，文件和imsi的所有的字段信息的处理类
 * @author chenzhenhua
 *
 */
public class FieldViewSetting {
	
	/**
	 * 获取案卷，文件和imsi的所有的字段信息
	 * @param document
	 * @param LevelsName
	 * @return
	 */
	public static Map<String, Map<String, String>> getTotalFieldsInfo(Document document,List<String> LevelsName){
		Map<String, String> volumeFieldsInfo = null;//案卷级的字段集合
		Map<String, String> fileFieldsInfo = null;//文件级的字段集合
		Map<String, String> electronicalFieldsInfo = null;//imsi级的字段集合
		Map<String, Map<String, String>> totalFieldsInfo = null;
		for (int i = 0; i < LevelsName.size(); i++) {
			String level = LevelsName.get(i);
			if("接收数据".equals(level)){
				volumeFieldsInfo = XMLUtil.getFieldsInfo(document, level);
			}else if ("发送数据".equals(level)) {
				fileFieldsInfo = XMLUtil.getFieldsInfo(document, level);
			}else{
				//imsi
				electronicalFieldsInfo = XMLUtil.getFieldsInfo(document, level);
			}
		}
		if(volumeFieldsInfo != null || fileFieldsInfo != null || electronicalFieldsInfo != null){
			totalFieldsInfo = new HashMap<String, Map<String, String>>();
		}
		if(volumeFieldsInfo != null){
			totalFieldsInfo.put("接收数据", volumeFieldsInfo);
		}
		if(fileFieldsInfo != null){
			totalFieldsInfo.put("发送数据", fileFieldsInfo);
		}
		if(electronicalFieldsInfo != null){
			totalFieldsInfo.put("imsi", electronicalFieldsInfo);
		}
		return totalFieldsInfo;
	}
	
	
//	public void getTwoDimensionArray(Map<String, Map<String, String>> totalFieldsInfo){
//		totalFieldsInfo.containsKey(key)
//	}

}
