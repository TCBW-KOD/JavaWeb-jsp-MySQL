package com.web.news.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
	// 接受请求的方法
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 先设置响应编码
		response.setContentType("text/html;charset=UTF-8");
		// 网页的输出流
		PrintWriter out = response.getWriter();
		out.print("The first Servlet Program");
		
		out.flush();
		out.close();
	}
}
