package it.cast.servlet;

import it.cast.dao.MagazineDao;
import it.cast.domain.Magazine;
import it.cast.tools.BaseServlet;
import it.cast.tools.CommonsUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 负责添加书，类别，出版社和添加时所需的ajax校验
 */
public class BServlet extends BaseServlet {
	private MagazineDao md=new MagazineDao();

	public void addBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//添加书

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		
		Magazine user=CommonsUtils.toBean(request.getParameterMap(), Magazine.class);
		user.setUuid(CommonsUtils.uuid());
		String birthday=user.getYear()+"-"+user.getMonth()+"-"+user.getDay();
		DateFormat format=DateFormat.getDateInstance();
		try {
			user.setDate(format.parse(birthday));
			String sql="insert into MagazineInfo values(?,?,?,?,?,?,?,?,?)";
			Object[] params={user.getUuid(),user.getId(),user.getName(),user.getClassId(),user.getPublishHouseId(),
					new java.sql.Date(user.getDate().getTime()),user.getEditNumber(),user.getIntroduce(),user.getState()};
			md.AddData(sql, params);
			request.setAttribute("msg", "添加成功");
			request.getRequestDispatcher("/web/success.jsp").forward(request, response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void addClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//添加类别

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");

		Magazine user = CommonsUtils.toBean(request.getParameterMap(),
				Magazine.class);
		user.setUuid(CommonsUtils.uuid());
		String sql = "insert into MagazineClassInfo values(?,?,?)";
		Object[] params = { user.getUuid(), user.getId(), user.getName() };
		try {
			md.AddData(sql, params);
			request.setAttribute("msg", "添加成功");
			request.getRequestDispatcher("/web/success.jsp").forward(request, response);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void addPublish(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//添加出版社

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		
		Magazine user = CommonsUtils.toBean(request.getParameterMap(),
				Magazine.class);
		user.setUuid(CommonsUtils.uuid());
		String sql = "insert into PublishHouseInfo values(?,?,?)";
		Object[] params = { user.getUuid(), user.getId(), user.getName() };
		try {
			md.AddData(sql, params);
			request.setAttribute("msg", "添加成功");
			request.getRequestDispatcher("/web/success.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void ajaxPublish(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		ajaxResponse(request,response,"PublishHouseInfo");
	}
	
	public void ajaxBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		ajaxResponse(request,response,"MagazineInfo");
	}
	
	public void ajaxClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		ajaxResponse(request,response,"MagazineClassInfo");
	}
	
	public void ajaxResponse(HttpServletRequest request, HttpServletResponse response,String table){
		String id=request.getParameter("id");
		String sql="select * from "+table+" where id=?";
		Object[] params={id};
		try {
			List<Magazine> magList=md.getData(sql, params);
			if(magList.size()==0){
				response.getWriter().write("1");	//不存在，可以使用
			}else{
				response.getWriter().write("0");	//存在，不可以使用
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
