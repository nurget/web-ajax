package com.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.web.common.CommonView;
import com.web.service.UserInfoService;
import com.web.service.impl.UserInfoServiceImpl;
import com.web.vo.UserInfoVO;

@WebServlet("/user-info/*")
public class UserInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Gson gson = new Gson();
    private UserInfoService userInfoService = new UserInfoServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String json = "";
        String uri = request.getRequestURI();
        int idx = uri.lastIndexOf("/") + 1;
        uri = uri.substring(idx);
        
        String cmd = CommonView.getCmd(request);
        if ("list".equals(cmd)) {
        	List<UserInfoVO> uiList = userInfoService.selectUserInfoList(null);
        	json = gson.toJson(uiList);
//			request.setAttribute("uiList", userInfoService.selectUserInfoList(null));
        } else if ("one".equals(cmd)) {
        	int uiNum = Integer.parseInt(request.getParameter("uiNum"));
        	UserInfoVO user = userInfoService.selectUserInfo(uiNum);
//            String uiNum = request.getParameter("uiNum");
            json = gson.toJson(user);
        }
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	int result = 0;
    	BufferedReader br = request.getReader();
    	StringBuffer sb = new StringBuffer();
    	String str = null;
    	while((str=br.readLine())!=null) {
    		sb.append(str);
    	}
    	UserInfoVO user = gson.fromJson(sb.toString(), UserInfoVO.class);
        String cmd = CommonView.getCmd(request);
        String json = "";

        if ("insert".equals(cmd)) {
        	result = userInfoService.insertUserInfo(user);
            
        } else if ("update".equals(cmd)) {
//            String uiNum = request.getParameter("uiNum");
//            String uiName = request.getParameter("uiName");
//
//            user.setUiNum(Integer.parseInt(uiNum));
//            user.setUiName(uiName);
//
//            json = gson.toJson(result);
//        	String uiBirth = uiBirth.replace("-", "");
        	result = userInfoService.updateUserInfo(user);
        } else if ("delete".equals(cmd)) {
        	
        	
            String uiNum = request.getParameter("uiNum");
            json = gson.toJson(result);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json);
    }
}
