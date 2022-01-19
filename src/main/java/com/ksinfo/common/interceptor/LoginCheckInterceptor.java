package com.ksinfo.common.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

    private static final String urlCheck          = "/ksims/login";
    private static final String urlCheckMain = "/ksims/main";
    private static final String urlCheckCss      = "/ksims/css";
    private static final String urlCheckJs      = "/ksims/js";
    private static final String urlCheckImage      = "/ksims/image";
    private static final String urlCheckFont      = "/ksims/font";
    private static final String urlCheckDist      = "/ksims/dist";
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object Handler) throws Exception {
        HttpSession session = request.getSession(false);

        if (request.getRequestURI().toString().equals(urlCheck)
            || request.getRequestURI().toString().equals(urlCheckMain)
            || request.getRequestURI().toString().startsWith(urlCheckCss)
            || request.getRequestURI().toString().startsWith(urlCheckJs)
            || request.getRequestURI().toString().startsWith(urlCheckImage)
            || request.getRequestURI().toString().startsWith(urlCheckFont)
            || request.getRequestURI().toString().startsWith(urlCheckDist)) {
            return true;
        }

        if (session == null) {
            alertAndRedirect(response);
            return false;
        }

        String emp_id = (String) session.getAttribute("sid");

        if (emp_id == null) {
            alertAndRedirect(response);
            return false;
        }

        return true;
    }

    private void alertAndRedirect(HttpServletResponse response) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter printwriter = response.getWriter();
        printwriter.print("<script>alert('ログインしてください。'); location.href='/ksims/main';</script>");
        printwriter.flush();
        printwriter.close();
    }
}