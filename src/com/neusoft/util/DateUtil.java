package com.neusoft.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ����ʱ��Ĺ�����
 * @author chenzhenhua
 *
 */
public class DateUtil {

	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD_EEEE_HH_MM_SS = "yyyy��MM��dd�� EEEE HH:mm:ss";
	static SimpleDateFormat sdf = null;
	/**
	 * �������ڸ�ʽ��ȡ��ǰʱ��
	 * @param dateFormat
	 * @return
	 */
	public static String getTime(String dateFormat) {
		sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(new Date());

	}
	
	/**
	 * �õ���ǰʱ��(�õ�ʱ��ĸ�ʽ���磺2016��05��06�� ������ 14:42:53)
	 * @return
	 */
	public static String getNow() {
		return getTime(YYYY_MM_DD_EEEE_HH_MM_SS);

	}

}
