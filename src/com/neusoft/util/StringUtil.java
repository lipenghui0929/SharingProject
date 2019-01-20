package com.neusoft.util;

import java.util.UUID;

public class StringUtil {

	/**
	 * 根据路径字符串获取后缀,判断后缀是否是seas结尾的，seas后缀结尾表示合法，否则表示非法
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
	 * 找出该字符在字符串中总共有几个
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
	 * 找出该字符在字符串中总共有几个
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
	 * 根据excel文件名截取档案门类名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getDocumentNameByFileName(String fileName) {
		int index = fileName.lastIndexOf("模板.xls");
		return fileName.substring(0, index);

	}

	/**
	 * 是否是档案门类节点
	 * 
	 * @param nodeName
	 * @return
	 */
	public static boolean isArchiveCategory(String nodeName) {
		boolean flag = false;
		if (!"接收数据".equals(nodeName) && !"发送数据".equals(nodeName)
				&& !"电子文件".equals(nodeName) && !nodeName.contains("牛牛管理")) {
			flag = true;
		}
		return flag;

	}

	/**
	 * 通过UUID生成一个id
	 * 
	 * @return
	 */
	public static String createIdByUUID() {
		String string = UUID.randomUUID().toString();
		// 去掉“-”符号
		String str = string.substring(0, 8) + string.substring(9, 13)
				+ string.substring(14, 18) + string.substring(19, 23)
				+ string.substring(24);
		return str;

	}
	/**
	 * 判断字符串是否为数字
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
		// System.out.println(isArchiveCategory("档案管理"));
		// System.out.println(getSuffixes("C:/Users/neusoft/Desktop/format.seas"));
		// getStrCount("字符型,500,不可以,", ",");
		// System.out.println(getDocumentNameByFileName("科学技术hello档案模板.xls"));
		System.out.println(createIdByUUID());
	}

}
