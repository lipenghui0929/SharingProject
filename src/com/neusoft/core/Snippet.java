package com.neusoft.core;

public class Snippet {

	public static void main(String[] args) {
        String cn = "ATK-SIM800C ��Ӣ�Ķ��Ų���";
        System.out.println(cnToUnicode(cn));
        // �ַ��� : \u5f00\u59cb\u4efb\u52a1 ������ \ ��java����ת���ַ���Ҫд������������ʽ
        String unicode = "00410054004b002d00530049004d00380030003000430020004e2d0082f10065870077ed004fe1006d4b008bd5";
        System.out.println(unicodeToCn(unicode));
    }
    
	/**
	 * Unicodeת��������
	 * @param unicode
	 * @return
	 */
    private static String unicodeToCn(String unicode) {
        /** �� \ u �ָ��Ϊjavaע��Ҳ��ʶ��unicode������м����һ���ո�*/
        String[] strs = unicode.split("00");
        String returnStr = "";
        // ����unicode�ַ����� \ u ��ͷ����˷ָ���ĵ�һ���ַ���""��
        for (int i = 1; i < strs.length; i++) {
          returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
        }
        return returnStr;
    }
    
    /**
     * ����ת����Unicode
     * @param cn
     * @return
     */
    public static String cnToUnicode(String cn) {
        char[] chars = cn.toCharArray();
        String returnStr = "";
        for (int i = 0; i < chars.length; i++) {
          returnStr += "\\u00" + Integer.toString(chars[i], 16);
        }
        return returnStr;
    }
    
    /**
     * ����ת����Unicode
     * @param cn
     * @return
     */
    public static String cnToUnicodeHm(String cn) {
        char[] chars = cn.toCharArray();
        String returnStr = "";
        for (int i = 0; i < chars.length; i++) {
        	String up=Integer.toString(chars[i], 16).toUpperCase();
        	if(null!=up&&up.length()<=2){
        		returnStr += "00"+up;
        	}else{
        		returnStr += up;
        	}
        }
        return returnStr;
    }
    
    /**
     * �ַ���ת����Ϊ16�����ַ���
     * @param s
     * @return
     */
    public static String strTo16(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }
}
