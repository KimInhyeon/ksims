package com.ksinfo.common.util;

import java.io.File;

import com.ksinfo.common.exception.IMSYSException;

public class PathCheckUtil {
	public static void pathChekAndMakeDir(String dest) {
		File folder = new File(dest);
		
        if (!folder.exists()) {
            try {
                folder.mkdir();
            } catch (Exception e) {
    			String messages = MessageUtils.getMessage("KS_IMSYS_FLDMSG_001_JV");
    			throw new IMSYSException(messages);
            }
        }		
	}
}
