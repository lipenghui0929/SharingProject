package com.neusoft.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;

import com.neusoft.util.XMLUtil;

/**
 * �����ļ���imsi�����е��ֶ���Ϣ�Ĵ�����
 * @author chenzhenhua
 *
 */
public class FieldViewSetting {
	
	/**
	 * ��ȡ�����ļ���imsi�����е��ֶ���Ϣ
	 * @param document
	 * @param LevelsName
	 * @return
	 */
	public static Map<String, Map<String, String>> getTotalFieldsInfo(Document document,List<String> LevelsName){
		Map<String, String> volumeFieldsInfo = null;//�������ֶμ���
		Map<String, String> fileFieldsInfo = null;//�ļ������ֶμ���
		Map<String, String> electronicalFieldsInfo = null;//imsi�����ֶμ���
		Map<String, Map<String, String>> totalFieldsInfo = null;
		for (int i = 0; i < LevelsName.size(); i++) {
			String level = LevelsName.get(i);
			if("��������".equals(level)){
				volumeFieldsInfo = XMLUtil.getFieldsInfo(document, level);
			}else if ("��������".equals(level)) {
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
			totalFieldsInfo.put("��������", volumeFieldsInfo);
		}
		if(fileFieldsInfo != null){
			totalFieldsInfo.put("��������", fileFieldsInfo);
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
