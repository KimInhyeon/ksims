package com.ksinfo.common.util;

public class BoardUtil {
	public static String encodeContent(String content) {
		String ret = content;
		try {
		ret = ret.replace("&", "&amp;");
		ret = ret.replace("<", "&lt;");
		ret = ret.replace(">", "&gt;");
		} catch (NullPointerException e) {
		e.printStackTrace();
		}
		return ret;
		}

		public static String decodeContent(String content) {
		String ret = content;
		try {
		ret = ret.replace("&amp;", "&");
		ret = ret.replace("&lt;", "<");
		ret = ret.replace("&gt;", ">");
		}
		catch (NullPointerException e){
		e.printStackTrace();
		}
		return ret;
		}
}
