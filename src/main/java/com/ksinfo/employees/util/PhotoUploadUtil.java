package com.ksinfo.employees.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ksinfo.common.util.PathCheckUtil;
import com.ksinfo.common.util.RootPathUtil;

public class PhotoUploadUtil {
	public static HashMap<String, String> uploadPhoto(MultipartHttpServletRequest request) {

		// Photo Upload Start
		ArrayList<String> fileUrlList = new ArrayList<String>();
		ArrayList<String> fileNameList = new ArrayList<String>();
		String rootUploadDir = RootPathUtil.getRootPath(request);
		HashMap<String, String> map = new HashMap<String, String>();

		PathCheckUtil.pathChekAndMakeDir(rootUploadDir);

		MultipartFile mFile = null;
		String orgFileName = ""; // 진짜 파일명
		String sysFileName = ""; // 변환된 파일명
		for (int j = 0; j < request.getMultiFileMap().get("btn").size(); j++) {
			String[] fileNames = new String[j + 1];
			String[] filePaths = new String[j + 1];
			String umeji = "btn";

			mFile = request.getMultiFileMap().get(umeji).get(j);
			fileNames[j] = mFile.getOriginalFilename();
			filePaths[j] = rootUploadDir + mFile.getOriginalFilename();

			orgFileName = mFile.getOriginalFilename();// 첨부 안하면 여기서 널
			if (!fileNames[j].isEmpty() && orgFileName.isEmpty()) {
				fileNameList.add(j, fileNames[j]);
				fileUrlList.add(j, filePaths[j]);
			}
			try {
				if (!orgFileName.isEmpty()) {
					mFile.transferTo(new File(rootUploadDir + sysFileName + orgFileName));
					fileUrlList.add(rootUploadDir + sysFileName + orgFileName);
					fileNameList.add(orgFileName);
				} else {
					fileUrlList.add("");
					fileNameList.add("");
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		// Photo Upload End
		map.put("fileUrl", fileUrlList.get(0));
		map.put("fileName", fileNameList.get(0));
		return map;
	}
}
