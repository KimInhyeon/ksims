package com.ksinfo.common.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ksinfo.common.dto.LoginDto;
import com.ksinfo.common.exception.IMSYSException;
import com.ksinfo.common.exception.MemberException;
import com.ksinfo.common.service.LoginService;
import com.ksinfo.common.util.MessageUtils;
import com.ksinfo.common.util.SecurityUtil;

@Controller
public class LoginCheckController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String messageNotUser = "KS_IMSYS_MEMERR_001_JV";
	private static final String messageIncorrectPW = "KS_IMSYS_MEMERR_002_JV";
	private static final String messageSystemErr = "KS_IMSYS_SYSERR_S01_JV";
	
	@Autowired
	LoginService loginservice;
	
	@Autowired
	Environment env;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView getMainView(ModelAndView model) {

        model.addObject("mainFlg", "1");
        model.setViewName("main");
        return model;
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView getLoginView(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();	
		List<LoginDto> ListLoginDto = null;
		
		//リクエストからユーザIDとPW取得
		String emp_id = request.getParameter("userId");
		String password = request.getParameter("userPw");
		
		//PW暗号化処理
		SecurityUtil securitiUtil = new SecurityUtil();
		String secPassword = securitiUtil.encryptSHA256(password);

		//inputLoginDtoへユーザIDセット
		LoginDto inputLoginDto = new LoginDto();
		inputLoginDto.setEmp_id(emp_id);

		try {
			//ユーザIDからログインを行ったユーザの情報取得
			ListLoginDto = loginservice.getLoginMemberList(inputLoginDto);
		} catch (DataAccessException e) {
			String messages = MessageUtils.getMessage(messageSystemErr);
			throw new IMSYSException(messages, e);
	    }
		
		//取得したユーザ情報が一つではない場合
		if (ListLoginDto.size() != 1) {
            String messages = MessageUtils.getMessage(messageNotUser, new Object[] { emp_id });
            throw new MemberException(messages);
        }

		//取得したユーザ情報のPWと入力PWが異なる場合
		if (!secPassword.equals(ListLoginDto.get(0).getPassword())) {
            String messages = MessageUtils.getMessage(messageIncorrectPW);
            throw new MemberException(messages);
        }

		HttpSession session = request.getSession();
		session.setAttribute("login", 1); // 로그인 플래그 설정 [0:로그인실패, 1:로그인완료]
		session.setAttribute("sid", ListLoginDto.get(0).getEmp_id());
		session.setAttribute("sname", ListLoginDto.get(0).getEmp_name());
		session.setAttribute("adminFlg", ListLoginDto.get(0).getAuth_code());
		String[] fieldCode = ListLoginDto.get(0).getField_code().substring(1,ListLoginDto.get(0).getField_code().length()-1).split(",");
		session.setAttribute("fieldCode", fieldCode);
		
		modelAndView.addObject("mainFlg", "1");
		modelAndView.setViewName("main");
		return modelAndView;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logoutSession(HttpSession session, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        session = request.getSession();
        session.invalidate();

        modelAndView.setViewName("redirect:/main");
        return modelAndView;
    }
	
}
