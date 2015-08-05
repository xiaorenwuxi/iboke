package com.git.iboke.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.git.iboke.model.Imgtype;
import com.git.iboke.service.Demotest;
import com.git.iboke.util.JacksonUtil;

@Controller
@RequestMapping("/demo")
public class DemoController {

	@Autowired
	
	private Demotest demoservice;
	
	@RequestMapping("see")
	public void see(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String aid = request.getParameter("aid");
		int ids = Integer.parseInt(aid.trim());
		
		Imgtype imgtype = demoservice.select(ids);
		String typename= imgtype.getTypeName();
		String desc=imgtype.getTypeDesc();
		
		String data="{\"imgdesc\":\""+typename+"\" \"imgtype\" : \""+desc+"\"}";
		
		/*response.setContentType("application/json");
		
		PrintWriter out =response.getWriter();
		
		out.write(data);*/
		
		
		outPrintJson(JacksonUtil.toJSONString(data), response);
	}

	private void outPrintJson(String jsonString, HttpServletResponse response) {
		PrintWriter out = null;
		try {	
			response.setCharacterEncoding("UTF-8");		
			response.setContentType("text/plain;charset=UTF-8");
			out = response.getWriter();
			out.print(jsonString);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			out.flush();
			out.close();
		}
	}
	
	
}
