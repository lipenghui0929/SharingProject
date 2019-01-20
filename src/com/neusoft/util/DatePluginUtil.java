package com.neusoft.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Date;
import java.util.Locale;

import com.eltima.components.ui.DatePicker;

public class DatePluginUtil {

	public static DatePicker getDatePicker() {
	    final DatePicker datepick;
	    // ��ʽ
	    //String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
	    String DefaultFormat = "yyyy-MM-dd";
	    // ��ǰʱ��
	    Date date = new Date();
	    // ����
        Font font = new Font("Times New Roman", Font.BOLD, 14);

        Dimension dimension = new Dimension(177, 24);
        int[] hilightDays = { 1, 3, 5, 7 };

        int[] disabledDays = { 4, 6, 5, 9 };
        //���췽������ʼʱ�䣬ʱ����ʾ��ʽ�����壬�ؼ���С��
        datepick = new DatePicker(null, DefaultFormat, font, dimension);
        datepick.setLocation(137, 83);//������ʼλ��
        /*
        //Ҳ����setBounds()ֱ�����ô�С��λ��
        datepick.setBounds(137, 83, 177, 24);
        */
        // ����һ���·�����Ҫ������ʾ������
        datepick.setHightlightdays(hilightDays, Color.red);
        // ����һ���·��в���Ҫ�����ӣ��ʻ�ɫ��ʾ
        datepick.setDisableddays(disabledDays);
       // ���ù���
        datepick.setLocale(Locale.CANADA);
        // ����ʱ�����ɼ�
        datepick.setTimePanleVisible(true);
		return datepick;
	 }
}
