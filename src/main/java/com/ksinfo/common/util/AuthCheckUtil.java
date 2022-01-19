package com.ksinfo.common.util;

import com.ksinfo.common.exception.AuthException;

public class AuthCheckUtil {
	public static void authCheckAdmin(String authCode) {
		if (!authCode.equals("01")) {
			String messages = MessageUtils.getMessage("KS_IMSYS_SYSERR_S02_JV");
			throw new AuthException(messages);
		}
	}
	
	public static void authCheckAdminAndSelf(String authCode, String sessionId, String inputId) {
		if (!sessionId.equals(inputId)) {
			if(!authCode.equals("01")) {
				String messages = MessageUtils.getMessage("KS_IMSYS_SYSERR_S02_JV");
				throw new AuthException(messages);
			}
		}
	}
	
	public static void authCheckLeaderAndSelf(String authCode, String sessionId, String inputId) {
		if (!sessionId.equals(inputId)) {
			if(!authCode.equals("01") && !authCode.equals("02")) {
				String messages = MessageUtils.getMessage("KS_IMSYS_SYSERR_S02_JV");
				throw new AuthException(messages);
			}
		}
	}
	
	public static void authCheckSelf(String sessionId, String inputId) {
		if (!sessionId.equals(inputId)) {
			String messages = MessageUtils.getMessage("KS_IMSYS_SYSERR_S02_JV");
			throw new AuthException(messages);
		}
	}
	
	
}
