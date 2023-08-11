package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


@WebServlet("/user-info/*")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Gson gson = new Gson();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String json = "[\"번호1\", \"번호2\", \"번호3\"]";
		
		List<String> list = new ArrayList<>();
		list.add("번호1");
		list.add("번호2");
		list.add("번호3");
		String json3 = gson.toJson(list);
		out.print(json3);
		
//		for(String str : list) {
//			json += "\"" + str + "\",";
//		}
//		json = json.substring(0)
//		out.print(list);
		
		String json2 = "[";
		for(int i = 1; i <= 3; i++) {
			json2 +="\"번호" + i +  "\",";
		}
		json2 += "]";
		
//		out.print(json);
		
//		out.print(json2);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
