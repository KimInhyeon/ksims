package com.ksinfo.common.util;

public class StringChangedUtil {
		public static boolean compare(String before, String after) {
			
			if(ObjectUtil.isEmpty(before)) {
				before="";
			}
			
			if(ObjectUtil.isEmpty(after)) {
				after="";
			}
			
			if (!before.trim().equals(after.trim())) {
				return true;
			}
			
			return false;
	}

}
