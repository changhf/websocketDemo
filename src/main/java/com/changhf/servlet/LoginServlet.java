package com.changhf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/login") // url路径，这里要加上/，表示当前项目路径下
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -8836785940532919846L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String username = req.getParameter("username");
		req.getSession().setAttribute("username", username);
		resp.sendRedirect("chatPage.jsp");
	}
}
