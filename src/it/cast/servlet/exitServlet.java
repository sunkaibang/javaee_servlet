package it.cast.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class exitServlet extends HttpServlet {

	/**
	 * 退出，判断是teacher还是manager，把对应的session中内容清空
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		
		String path=request.getContextPath()+"/LoginWeb/Login.jsp";
		String iden=request.getParameter("iden");
		if(iden!=null&&!iden.trim().isEmpty()){
			if(iden.equals("teacher")){
				request.getSession().setAttribute("session_teacher", null);
			}else if(iden.equals("manager")){
				request.getSession().setAttribute("session_manager", null);
			}
			response.getWriter().write("<script >window.parent.location.href='"+path+"';</script>");
			response.getWriter().flush();
		}
		
	}

}
