package com.ksinfo.common.util;

import org.springframework.stereotype.Service;

import com.ksinfo.common.dto.VerificationDto;

@Service
public class IdentificationUtil {
	
//	@Autowired
//	private SecurityUtil securityUtil;
//	@Autowired
//	private Service boardService;
	
	public static boolean identification(VerificationDto verificationDto) throws Exception {
		
		boolean userNum = true;
		String userId = verificationDto.getNumberConfirm();	
		String selUserId = verificationDto.getMumberNum();
		
		if(userId.equals(selUserId)) {
			userNum = true;
		} else {
			userNum = false;
		}
		
		return userNum;
	}
	
	
   
    public boolean password(VerificationDto verificationDto) throws Exception {
 
		SecurityUtil securityUtil = new SecurityUtil();

    	boolean pw = true;
    	verificationDto.getNumberConfirm();
    	verificationDto.getMumberPass();
    	
    	String securePassword = securityUtil.encryptSHA256(verificationDto.getMumberPass());	//入力されたデータ   복호화처리하기
   	
		String passwordConfirm = "123123暗号化"; //Service.selPass(verificationDto.getNumberConfirm());
    	
		if(securePassword.equals(passwordConfirm)) {
			pw = true;
		} else {
			pw = false;
		}
		

		return pw;
	}


}
