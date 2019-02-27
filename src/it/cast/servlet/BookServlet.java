package it.cast.servlet;

import it.cast.dao.MagazineDao;
import it.cast.dao.ManagerTeacherDao;
import it.cast.domain.Magazine;
import it.cast.domain.ManagerTeacher;
import it.cast.domain.PageBean;
import it.cast.tools.BaseServlet;
import it.cast.tools.CommonsUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 负责查询的Servlet
 */
public class BookServlet extends BaseServlet {
	private int ps=10;	//每页10行记录
	private MagazineDao md=new MagazineDao();

	public void classQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//类别查询
		int pc=getPageCode(request);	//得到当前页面
		PageBean<Magazine> pb=md.findAll(pc, ps, "MagazineClassInfo");
		pb.setUrl(getUrl(request));
		request.setAttribute("request_pb", pb);
		request.getRequestDispatcher("/web/classQuery.jsp").forward(request, response);

	}
	
	public void publishQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//出版社查询
		int pc=getPageCode(request);
		PageBean<Magazine> pb=md.findAll(pc, ps, "PublishHouseInfo");
		pb.setUrl(getUrl(request));
		request.setAttribute("request_pb", pb);
		request.getRequestDispatcher("/web/publishQuery.jsp").forward(request, response);
	}
	
	public void bookQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//图书查询
		int pc=getPageCode(request);
		PageBean<Magazine> pb=md.findAll(pc, ps, "MagazineInfo");
		pb.setUrl(getUrl(request));
		request.setAttribute("request_pb", pb);
		request.getRequestDispatcher("/web/bookQuery.jsp").forward(request, response);
	}
	
	public void teacherQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//教师查询
		int pc=getPageCode(request);	//得到当前页面
		ManagerTeacherDao md=new ManagerTeacherDao();
		PageBean<ManagerTeacher> pb=md.findAll(pc, ps, "TeacherInfo");
		pb.setUrl(getUrl(request));
		
		List<ManagerTeacher> teachers=pb.getBeanList();
		for(int i=0;i<teachers.size();i++){
			ManagerTeacher mt=teachers.get(i);
			if(mt.getPicture()!=null&&!mt.getPicture().trim().isEmpty()){
				String oldPath=mt.getPicture();
				String newPath=oldPath.substring(oldPath.indexOf("webapps")+7);
				mt.setPicture(newPath.replace("\\", "/"));
			}
		}
		
		
		request.setAttribute("request_pb", pb);
		request.getRequestDispatcher("/web/teacherQuery.jsp").forward(request, response);
	}
	
	public int getPageCode(HttpServletRequest request){
		//得到当前页面
		String value=request.getParameter("pc");
		if(value==null||value.trim().isEmpty())
			return 1;
		return Integer.parseInt(value);
		
	}
	
	public void supBookQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//高级图书查询
		
		Magazine criteria=CommonsUtils.toBean(request.getParameterMap(), Magazine.class);
		//是否选择了年月日
		String isSelectDate=request.getParameter("modPic");
		if(isSelectDate.equals("no")){
			criteria.setYear(null);
		}
		try {
			criteria=encoding(criteria);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		int pc=getPageCode(request);
		PageBean<Magazine> pb=md.criteriaQuery(criteria,pc,ps);
		pb.setUrl(getUrl(request));
		request.setAttribute("request_pb", pb);
		request.getRequestDispatcher("/web/bookQuery.jsp").forward(request, response);	
	}
	
	private String getUrl(HttpServletRequest request)
	{
		//高级查询的时候得到限制条件
	   String contextPath=request.getContextPath();
	   String servletPath=request.getServletPath();
	   String queryString=request.getQueryString();
	   if(queryString.contains("&pc=")){
	     int index=queryString.lastIndexOf("&pc=");
	     queryString=queryString.substring(0,index);
	   }
	   return contextPath+servletPath+"?"+queryString;
	}
	
	public Magazine encoding(Magazine criteria) throws Exception
	{
		//处理编码
		String id=criteria.getId();
		String name=criteria.getName();
		String classId=criteria.getClassId();
		String publishHouseId=criteria.getPublishHouseId();
		String year=criteria.getYear();
		String month=criteria.getMonth();
		String day=criteria.getDay();
		String editNumber=criteria.getEditNumber();
	  if(id!=null&&!id.trim().isEmpty())
	  {
		  id=new String(id.getBytes("iso-8859-1"),"utf-8");
		  criteria.setId(id);
	   }
	  if(name!=null&&!name.trim().isEmpty())
	  {
		  name=new String(name.getBytes("iso-8859-1"),"utf-8");
		  criteria.setName(name);
	   }
	  if(classId!=null&&!classId.trim().isEmpty())
	  {
		  classId=new String(classId.getBytes("iso-8859-1"),"utf-8");
		  criteria.setClassId(classId);
	   }
	  if(publishHouseId!=null&&!publishHouseId.trim().isEmpty())
	  {
		  publishHouseId=new String(publishHouseId.getBytes("iso-8859-1"),"utf-8");
		  criteria.setPublishHouseId(publishHouseId);
	   }
	  if(year!=null&&!year.trim().isEmpty())
	  {
		  year=new String(year.getBytes("iso-8859-1"),"utf-8");
		  criteria.setYear(year);
	   }
	  if(month!=null&&!month.trim().isEmpty())
	  {
		  month=new String(month.getBytes("iso-8859-1"),"utf-8");
		  criteria.setMonth(month);
	   }
	  if(day!=null&&!day.trim().isEmpty())
	  {
		  day=new String(day.getBytes("iso-8859-1"),"utf-8");
		  criteria.setDay(day);
	   }
	  if(editNumber!=null&&!editNumber.trim().isEmpty())
	  {
		  editNumber=new String(editNumber.getBytes("iso-8859-1"),"utf-8");
		  criteria.setEditNumber(editNumber);
	   }
	  return criteria;
	}
	
	public void supTeaQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//高级教师查询

		ManagerTeacher criteria=CommonsUtils.toBean(request.getParameterMap(), ManagerTeacher.class);
		String isSelectDate=request.getParameter("modPic");
		if(isSelectDate.equals("no")){
			criteria.setYear(null);
		}
		try {
			criteria=encodingMT(criteria);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		int pc=getPageCode(request);
		ManagerTeacherDao md=new ManagerTeacherDao();
		PageBean<ManagerTeacher> pb=md.criteriaQuery(criteria,pc,ps);
		
		List<ManagerTeacher> teachers=pb.getBeanList();
		for(int i=0;i<teachers.size();i++){
			ManagerTeacher mt=teachers.get(i);
			if(mt.getPicture()!=null){
				String oldPath=mt.getPicture();
				String newPath=oldPath.substring(oldPath.indexOf("webapps")+7);
				mt.setPicture(newPath.replace("\\", "/"));
			}
		}
		
		pb.setUrl(getUrl(request));
		request.setAttribute("request_pb", pb);
		request.getRequestDispatcher("/web/teacherQuery.jsp").forward(request, response);	
	}
	
	public ManagerTeacher encodingMT(ManagerTeacher criteria) throws Exception
	{
		//处理编码
		String id=criteria.getId();
		String realName=criteria.getRealName();
		String year=criteria.getYear();
		String month=criteria.getMonth();
		String day=criteria.getDay();
		String editNumber=criteria.getTelnumber();
		String email=criteria.getEmail();
	  if(id!=null&&!id.trim().isEmpty())
	  {
		  id=new String(id.getBytes("iso-8859-1"),"utf-8");
		  criteria.setId(id);
	   }
	  if(realName!=null&&!realName.trim().isEmpty())
	  {
		  realName=new String(realName.getBytes("iso-8859-1"),"utf-8");
		  criteria.setRealName(realName);
	   }
	  if(year!=null&&!year.trim().isEmpty())
	  {
		  year=new String(year.getBytes("iso-8859-1"),"utf-8");
		  criteria.setYear(year);
	   }
	  if(month!=null&&!month.trim().isEmpty())
	  {
		  month=new String(month.getBytes("iso-8859-1"),"utf-8");
		  criteria.setMonth(month);
	   }
	  if(day!=null&&!day.trim().isEmpty())
	  {
		  day=new String(day.getBytes("iso-8859-1"),"utf-8");
		  criteria.setDay(day);
	   }
	  if(editNumber!=null&&!editNumber.trim().isEmpty())
	  {
		  editNumber=new String(editNumber.getBytes("iso-8859-1"),"utf-8");
		  criteria.setTelnumber(editNumber);
	   }
	  
	  if(email!=null&&!email.trim().isEmpty())
	  {
		  email=new String(email.getBytes("iso-8859-1"),"utf-8");
		  criteria.setEmail(email);
	   }
	  return criteria;
	}
	
}
