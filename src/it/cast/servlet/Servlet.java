package it.cast.servlet;

import it.cast.dao.ManagerTeacherDao;
import it.cast.domain.ManagerTeacher;
import it.cast.tools.BaseServlet;
import it.cast.tools.CommonsUtils;
import it.cast.tools.CompressPicture;
import it.cast.tools.MyVerifyCode;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Servlet extends BaseServlet {

	public void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//注册管理员和教师

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		
		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload sfu=new ServletFileUpload(factory);
		ManagerTeacher mt=new ManagerTeacher();
		try {
			List<FileItem> fileItemList=sfu.parseRequest(request);
			//设置对象属性
			mt.setUuid(CommonsUtils.uuid());
			mt.setId(fileItemList.get(0).getString("utf-8"));
			mt.setPassword(fileItemList.get(1).getString("utf-8"));
			mt.setRealName(fileItemList.get(3).getString("utf-8"));
			String birthday=fileItemList.get(4).getString("utf-8")+"-"+fileItemList.get(5).getString("utf-8")+"-"+fileItemList.get(6).getString("utf-8");
			DateFormat format=DateFormat.getDateInstance();
			mt.setBirthday(format.parse(birthday));
			mt.setTelnumber(fileItemList.get(7).getString("utf-8"));
			FileItem pic=fileItemList.get(8);
			if(pic!=null)
				savePic(pic,request,mt);
			String identity=fileItemList.get(9).getString("utf-8");
			mt.setEmail(fileItemList.get(10).getString("utf-8"));
			
			ManagerTeacherDao mtd=new ManagerTeacherDao();
			if(identity.equals("teacher"))
			{
				String sql="insert into TeacherInfo values(?,?,?,?,?,?,?,?) ";
				Object[] params={mt.getUuid(),mt.getId(),mt.getPassword(),mt.getRealName(),new java.sql.Date(mt.getBirthday().getTime()),
						mt.getTelnumber(),mt.getEmail(),mt.getPicture()};
				mtd.AddData(sql, params);
				response.sendRedirect(request.getContextPath()+"/LoginWeb/Login.jsp");
			}else if(identity.equals("manager")){
				String sql="insert into ManagerInfo values(?,?,?,?,?,?,?,?) ";
				Object[] params={mt.getUuid(),mt.getId(),mt.getPassword(),mt.getRealName(),new java.sql.Date(mt.getBirthday().getTime()),
						mt.getTelnumber(),mt.getEmail(),mt.getPicture()};
				mtd.AddData(sql, params);
				response.sendRedirect(request.getContextPath()+"/LoginWeb/Login.jsp");
			}
		} catch (FileUploadException e){
			throw new RuntimeException();
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		
	}

	public void verifyCode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//发送验证码

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		
		String code=MyVerifyCode.imageAndText(response.getOutputStream());
		request.getSession().setAttribute("session_code", code);
	}
	
	public void sendEmail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//发送邮件

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		
		String email=request.getParameter("email");
		String text="";
		for(int i=1;i<=4;i++){
			text=text+(int)(Math.random()*10);
		}
		
		Properties props = new Properties();
		props.setProperty("mail.host","smtp.163.com");
		props.setProperty("mail.smtp.auth", "true");
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthenticator() {
				return new PasswordAuthentication("aa1033087876",
						"510812123321a");
			}
		};
		Session session = Session.getInstance(props, auth);
		session.setDebug(true);
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress("aa1033087876@163.com"));
			msg.setRecipients(RecipientType.TO, "1033087876@qq.com");
			
			msg.setSubject("验证码");
			msg.setContent(text, "text/html;charset=utf-8");
			Transport transport = session.getTransport("smtp");  
	        // 具体你使用邮箱的smtp地址和端口，应该到邮箱里面查看，如果使用了SSL，网易的端口应该是 465/994  
	        transport.connect("smtp.163.com", 25, "aa1033087876", "510812123321a");  
	        transport.sendMessage(msg, msg.getAllRecipients());  
	        transport.close();  
		} catch (AddressException e) {
			throw new RuntimeException(e);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		response.getWriter().write(text);
	}
	
	public void ajaxLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//判断用户名是否重复

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		
		String id=request.getParameter("id");
		String identity=request.getParameter("identity");
		try{
			ManagerTeacherDao mtd=new ManagerTeacherDao();
			if(identity.equals("teacher")){
				String sql="select * from TeacherInfo where id=?";
				Object[] params={id};
				ManagerTeacher getMT=mtd.getData(sql,params);
				if(getMT==null){
					response.getWriter().write("1");
				}else{
					response.getWriter().write("0");
				}
			}else if(identity.equals("manager")){
				String sql="select * from ManagerInfo where id=? ";
				Object[] params={id};
				ManagerTeacher getMT=mtd.getData(sql,params);
				if(getMT==null){
					response.getWriter().write("1");
				}else{
					response.getWriter().write("0");
				}
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//登录代码

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		//得到参数
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		String verifyCode=request.getParameter("verifyCode");
		String identity=request.getParameter("identity");
		ManagerTeacher mt=new ManagerTeacher();
		mt.setId(id);
		mt.setPassword(password);
		if(!verifyCode.equalsIgnoreCase((String)request.getSession().getAttribute("session_code"))){
			//校验验证码
			request.setAttribute("request_managerTeacher", mt);
			request.setAttribute("request_errorMsg", "验证码错误");
			request.getRequestDispatcher("/LoginWeb/Login.jsp").forward(request, response);
		}else{
			try{
				ManagerTeacherDao mtd=new ManagerTeacherDao();
				if(identity.equals("teacher")){
					String sql="select * from TeacherInfo where id=? and password=?";
					Object[] params={mt.getId(),mt.getPassword()};
					ManagerTeacher getMT=mtd.getData(sql,params);
					if(getMT==null){
						request.setAttribute("request_managerTeacher", mt);
						request.setAttribute("request_errorMsg", "账号错误");
						request.getRequestDispatcher("/LoginWeb/Login.jsp").forward(request, response);
					}else{
						request.getSession().setAttribute("session_teacher", getMT);
						request.getSession().setAttribute("session_identity", "teacher");
						response.sendRedirect(request.getContextPath()+"/TeacherWeb/Teacher.jsp");
					}
				}else if(identity.equals("manager")){
					String sql="select * from ManagerInfo where id=? and password=?";
					Object[] params={mt.getId(),mt.getPassword()};
					ManagerTeacher getMT=mtd.getData(sql,params);
					if(getMT==null){
						request.setAttribute("request_managerTeacher", mt);
						request.setAttribute("request_errorMsg", "账号错误");
						request.getRequestDispatcher("/LoginWeb/Login.jsp").forward(request, response);
					}else{
						request.getSession().setAttribute("session_manager", getMT);
						request.getSession().setAttribute("session_identity", "manager");
						response.sendRedirect(request.getContextPath()+"/web/Manager.jsp");
					}
				}
			}catch(Exception e){
				throw new RuntimeException(e);
			}
		}
	}
	
	public void savePic(FileItem file,HttpServletRequest request,ManagerTeacher mt){
		//保存图片
		String fileName=file.getName();
		int index=fileName.lastIndexOf("\\");
		if(index!=-1){
			fileName=fileName.substring(index+1);
		}
		String servletDir=request.getSession().getServletContext().getRealPath("/FileUpLoad");
		String newFile=servletDir+"\\"+fileName;
		File sPath=new File(newFile);
		try {
			file.write(sPath);
			CompressPicture imgCom = new CompressPicture(newFile,servletDir); 
	        imgCom.resizeFix(400, 400);
	        mt.setPicture(imgCom.getServletPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
