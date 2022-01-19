package com.ksinfo.common.util;

import java.io.File;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletRequestContext;

public class RootPathUtil extends ServletRequestContext {
	
    // Resourcesフォルダー(ファイル管理フォルダー定義)
    public final static String RESOURCES = "resources";
    
    public final static String IMAGES = "images";

    public RootPathUtil(HttpServletRequest request) {
        super(request);
    }

    public static String getRootPath(HttpServletRequest request) {
        String rootPath;

        rootPath = (Paths.get(request.getSession().getServletContext().getRealPath(File.separator) + File.separator + RESOURCES  + File.separator + IMAGES) +  File.separator).toString();

        return rootPath;
    }
}
