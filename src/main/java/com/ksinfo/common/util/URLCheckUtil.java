package com.ksinfo.common.util;

import javax.servlet.http.HttpServletRequest;

import com.ksinfo.common.exception.WrongAccessException;

public class URLCheckUtil {

	public static void urlCheck(HttpServletRequest req) {
		String strReferer = req.getHeader("referer");
		 if (strReferer == null){
			 String messages = MessageUtils.getMessage("KS_IMSYS_SYSERR_S03_JV");
			 throw new WrongAccessException(messages);
		 }
	}
}
