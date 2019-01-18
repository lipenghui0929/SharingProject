package com.neusoft.core;

public class Snippet {

	public static void main(String[] args) {
        String cn = "ATK-SIM800C 中英文短信测试";
        System.out.println(cnToUnicode(cn));
        // 字符串 : \u5f00\u59cb\u4efb\u52a1 ，由于 \ 在java里是转义字符，要写出下面这种形式
        String unicode = "00410054004b002d00530049004d00380030003000430020004e2d0082f10065870077ed004fe1006d4b008bd5";
        System.out.println(unicodeToCn(unicode));
    }
    
	/**
	 * Unicode转换成中文
	 * @param unicode
	 * @return
	 */
    private static String unicodeToCn(String unicode) {
        /** 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格*/
        String[] strs = unicode.split("00");
        String returnStr = "";
        // 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""。
        for (int i = 1; i < strs.length; i++) {
          returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
        }
        return returnStr;
    }
    
    /**
     * 中文转换成Unicode
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
     * 中文转换成Unicode
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
     * 字符串转化成为16进制字符串
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
