package it.cast.tools;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		arg0.setCharacterEncoding("utf-8");
		arg1.setContentType("text/html;charset=utf-8");
		String methodName=arg0.getParameter("method");
		if(methodName==null||methodName.trim().isEmpty())
			throw new RuntimeException("您没有给出参数");
		Class c=this.getClass();
		Method method=null;
		try{
			method=c.getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
		}catch(Exception e){throw new RuntimeException("没有该方法");}
		try{
			method.invoke(this, arg0,arg1);
		}catch(Exception e){
			System.out.println("调用的方法内部出现了异常");
			throw new RuntimeException(e);
		}
	}
}
