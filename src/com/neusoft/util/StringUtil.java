package com.neusoft.util;

import java.util.UUID;

public class StringUtil {

	/**
	 * ����·���ַ�����ȡ��׺,�жϺ�׺�Ƿ���seas��β�ģ�seas��׺��β��ʾ�Ϸ��������ʾ�Ƿ�
	 * 
	 * @param path
	 * @return
	 */
	public static boolean getSuffixes(String path) {
		boolean flag = false;
		int index = path.lastIndexOf(".");
		if (index == -1) {
			return flag;
		}
		String suffixes = path.substring(index + 1, path.length());
		if ("seas".equalsIgnoreCase(suffixes)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * �ҳ����ַ����ַ������ܹ��м���
	 * 
	 * @param str
	 * @param findStr
	 * @return
	 */
	public static int getStrCount(String str, String findStr) {
		int count = 0;
		int offset = 0;
		while ((offset = str.indexOf(findStr, offset)) != -1) {
			offset = offset + findStr.length();
			count++;
		}
		return count;
	}

	/**
	 * �ҳ����ַ����ַ������ܹ��м���
	 * 
	 * @param str
	 * @param findStr
	 * @return
	 */
	public static String getNewStr(String str) {
		String newStr = str.substring(0, str.length() - 1);
		return newStr;
	}

	/**
	 * ����excel�ļ�����ȡ����������
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getDocumentNameByFileName(String fileName) {
		int index = fileName.lastIndexOf("ģ��.xls");
		return fileName.substring(0, index);

	}

	/**
	 * �Ƿ��ǵ�������ڵ�
	 * 
	 * @param nodeName
	 * @return
	 */
	public static boolean isArchiveCategory(String nodeName) {
		boolean flag = false;
		if (!"��������".equals(nodeName) && !"��������".equals(nodeName)
				&& !"�����ļ�".equals(nodeName) && !nodeName.contains("ţţ����")) {
			flag = true;
		}
		return flag;

	}

	/**
	 * ͨ��UUID����һ��id
	 * 
	 * @return
	 */
	public static String createIdByUUID() {
		String string = UUID.randomUUID().toString();
		// ȥ����-������
		String str = string.substring(0, 8) + string.substring(9, 13)
				+ string.substring(14, 18) + string.substring(19, 23)
				+ string.substring(24);
		return str;

	}
	/**
	 * �ж��ַ����Ƿ�Ϊ����
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
		  for (int i = 0; i < str.length(); i++){
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
		 }


	public static void main(String[] args) {
		// System.out.println(isArchiveCategory("��������"));
		// System.out.println(getSuffixes("C:/Users/neusoft/Desktop/format.seas"));
		// getStrCount("�ַ���,500,������,", ",");
		// System.out.println(getDocumentNameByFileName("��ѧ����hello����ģ��.xls"));
		System.out.println(createIdByUUID());
	}

}
