package com.neusoft.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期时间的工具类
 * @author chenzhenhua
 *
 */
public class DateUtil {

	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD_EEEE_HH_MM_SS = "yyyy年MM月dd日 EEEE HH:mm:ss";
	static SimpleDateFormat sdf = null;
	/**
	 * 根据日期格式获取当前时间
	 * @param dateFormat
	 * @return
	 */
	public static String getTime(String dateFormat) {
		sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(new Date());

	}
	
	/**
	 * 得到当前时间(得到时间的格式是如：2016年05月06日 星期四 14:42:53)
	 * @return
	 */
	public static String getNow() {
		return getTime(YYYY_MM_DD_EEEE_HH_MM_SS);

	}

}
