package it.cast.servlet;

import it.cast.dao.MagazineDao;
import it.cast.dao.ManagerTeacherDao;
import it.cast.domain.Magazine;
import it.cast.domain.ManagerTeacher;
import it.cast.tools.BaseServlet;
import it.cast.tools.CommonsUtils;
import it.cast.tools.CompressPicture;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class updateData extends BaseServlet {
	private MagazineDao md=new MagazineDao();
	private ManagerTeacherDao mtd=new ManagerTeacherDao();

	public void deleteBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ɾ���ڿ�
		String uuid=request.getParameter("uuid");
		String isExistedSql="select *from MagazineInfo where uuid=?";
		String sql="delete from MagazineInfo where uuid=?";
		Object[] params={uuid};
		try {
			List<Magazine> mag=md.getData(isExistedSql, params);
			if(mag.size()==0){
				request.setAttribute("msg", "ɾ��ʧ��,���ڿ��ѱ�ɾ������ˢ��");
				request.getRequestDispatcher("/web/success.jsp").forward(request, response);
			}else{
				md.deleteData(sql, params);
				request.setAttribute("msg", "ɾ���ɹ�");
				request.getRequestDispatcher("/web/success.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void deleteCData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ɾ���ڿ����
		String id=request.getParameter("id");
		String classIsExisted="select * from MagazineClassInfo where id=?";
		String bookSql="select * from MagazineInfo where classId=?";
		String sql="delete from  MagazineClassInfo where id=?";
		Object[] params={id};
		
		try {
			List<Magazine> clas=md.getData(classIsExisted, params);
			if(clas==null){
				request.setAttribute("msg", "ɾ��ʧ��,������ѱ�ɾ������ˢ��");
				request.getRequestDispatcher("/web/success.jsp").forward(request, response);
			}else{
				List<Magazine> magList=md.getData(bookSql, params);
				if(magList.size()!=0){
					request.setAttribute("msg", "ɾ��ʧ��,���и�����ڿ�");
					request.getRequestDispatcher("/web/success.jsp").forward(request, response);
				}else{
					md.deleteData(sql, params);
					request.setAttribute("msg", "ɾ���ɹ�");
					request.getRequestDispatcher("/web/success.jsp").forward(request, response);
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void deleteHData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ɾ���ڿ�������
		String id=request.getParameter("id");
		String houseIsExisted="select * from  PublishHouseInfo where id=?";
		String sql="delete from  PublishHouseInfo where id=?";
		String bookSql="select * from MagazineInfo where classId=?";
		Object[] params={id};
		try {
			List<Magazine> clas=md.getData(houseIsExisted, params);
			if(clas==null){
				request.setAttribute("msg", "ɾ��ʧ��,�ó������ѱ�ɾ������ˢ��");
				request.getRequestDispatcher("/web/success.jsp").forward(request, response);
			}else{
				List<Magazine> magList=md.getData(bookSql, params);
				if(magList.size()!=0){
					
					request.setAttribute("msg", "ɾ��ʧ��,���иó�������ڿ�");
					request.getRequestDispatcher("/web/success.jsp").forward(request, response);
				}else{
					md.deleteData(sql, params);
					request.setAttribute("msg", "ɾ���ɹ�");
					request.getRequestDispatcher("/web/success.jsp").forward(request, response);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void deleteTeacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ɾ������Ա
		String id=request.getParameter("id");
		String teaIsExisted="select *from  TeacherInfo where id=?";
		String sql="delete from  TeacherInfo where id=?";
		Object[] params={id};
		try {
			List<Magazine> clas=md.getData(teaIsExisted, params);
			if(clas.size()==0){
				request.setAttribute("msg", "ɾ��ʧ��,�ý�ʦ�ѱ�ɾ������ˢ��");
				request.getRequestDispatcher("/web/success.jsp").forward(request, response);
			}else{
				md.deleteData(sql, params);
				request.setAttribute("msg", "ɾ���ɹ�");
				request.getRequestDispatcher("/web/success.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void modifyBookFirst(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * �޸��ڿ���ת�����޸Ľ���
		 */
		String id=request.getParameter("uuid");
		String bookSql="select * from MagazineInfo where uuid=?";
		Object[] params={id};
		try {
			List<Magazine> magList=md.getData(bookSql, params);
			request.setAttribute("request_book", magList.get(0));
			request.getRequestDispatcher("/web/modifyBook.jsp").forward(request, response);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void modifyBookSecond(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * �޸��ڿ����õ�Magazine��javabean�ٽ����޸�
		 */
		Magazine user=CommonsUtils.toBean(request.getParameterMap(), Magazine.class);
		String birthday=user.getYear()+"-"+user.getMonth()+"-"+user.getDay();
		DateFormat format=DateFormat.getDateInstance();
		try {
			user.setDate(format.parse(birthday));
			String sql="update  MagazineInfo set id=?,name=?,classId=?,publishHouseId=?,date=?,editNumber=?,introduce=?,state=? where uuid=?";
			Object[] params={user.getId(),user.getName(),user.getClassId(),user.getPublishHouseId(),
					new java.sql.Date(user.getDate().getTime()),user.getEditNumber(),user.getIntroduce(),user.getState(),user.getUuid()};
			md.modifyData(sql, params);
			request.setAttribute("msg", "�޸ĳɹ�");
			request.getRequestDispatcher("/web/success.jsp").forward(request, response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void modifyclassFirst(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * �޸����ת�����޸Ľ���
		 */
		String id=request.getParameter("id");
		String bookSql="select * from MagazineClassInfo where id=?";
		Object[] params={id};
		try {
			List<Magazine> magList=md.getData(bookSql, params);
			request.setAttribute("request_book", magList.get(0));
			request.getRequestDispatcher("/web/modifyClass.jsp").forward(request, response);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void modifyclassSecond(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * �޸�����޸��ڿ����õ�Magazine��javabean�ٽ����޸�
		 */
		Magazine user = CommonsUtils.toBean(request.getParameterMap(),
				Magazine.class);
		String sql = "update  MagazineClassInfo set name=? where uuid=?";
		Object[] params = { user.getName(),user.getUuid() };
		try {
			md.modifyData(sql, params);
			request.setAttribute("msg", "�޸ĳɹ�");
			request.getRequestDispatcher("/web/success.jsp").forward(request, response);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void modifyHouseFirst(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * �޸ĳ����磬ת�����޸Ľ���
		 */
		String id=request.getParameter("id");
		String bookSql="select * from PublishHouseInfo where id=?";
		Object[] params={id};
		try {
			List<Magazine> magList=md.getData(bookSql, params);
			request.setAttribute("request_book", magList.get(0));
			request.getRequestDispatcher("/web/modifyHouse.jsp").forward(request, response);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void modifyHouseSecond(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * �޸ĳ����磬�õ�Magazine��javabean�ٽ����޸�
		 */
		Magazine user = CommonsUtils.toBean(request.getParameterMap(),
				Magazine.class);
		String sql = "update  PublishHouseInfo set name=? where uuid=?";
		Object[] params = { user.getName(),user.getUuid() };
		try {
			md.modifyData(sql, params);
			request.setAttribute("msg", "�޸ĳɹ�");
			request.getRequestDispatcher("/web/success.jsp").forward(request, response);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void modifyTeacherFirst(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * �޸ĳ����磬ת�����޸Ľ���
		 */
		String id=request.getParameter("uuid");
		String sql="select * from TeacherInfo where uuid=?";
		Object[] params={id};
		try {
			ManagerTeacher mag= mtd.getData(sql, params);
			request.setAttribute("request_teacher", mag);
			request.getRequestDispatcher("/web/modifyTeacher.jsp").forward(request, response);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void modifyTeacherSecond(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * �޸Ľ�ʦ��Ϣ���õ�ManagerTeacher��javabean�ٽ����޸�
		 */
		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload sfu=new ServletFileUpload(factory);
		ManagerTeacher mt=new ManagerTeacher();
		try {
			List<FileItem> fileItemList=sfu.parseRequest(request);
			
			mt.setUuid(fileItemList.get(0).getString("utf-8"));
			mt.setId(fileItemList.get(1).getString("utf-8"));
			mt.setPassword(fileItemList.get(2).getString("utf-8"));
			mt.setRealName(fileItemList.get(4).getString("utf-8"));
			String birthday=fileItemList.get(5).getString("utf-8")+"-"+fileItemList.get(6).getString("utf-8")+"-"+fileItemList.get(7).getString("utf-8");
			DateFormat format=DateFormat.getDateInstance();
			mt.setBirthday(format.parse(birthday));
			mt.setTelnumber(fileItemList.get(8).getString("utf-8"));
			String isModPic=fileItemList.get(9).getString();
			if("no".equals(isModPic)){
				mt.setPicture("");
			}else if("yes".equals(isModPic)){
				FileItem pic=fileItemList.get(10);
				savePic(pic,request,mt);
			}
			mt.setEmail(fileItemList.get(11).getString());
			
			String sql = "update  TeacherInfo set id=?,password=?,realName=?,birthday=?,telnumber=?,email=?,picture=? where uuid=?";
			Object[] params = {mt.getId(),mt.getPassword(),mt.getRealName(),new java.sql.Date(mt.getBirthday().getTime()),mt.getTelnumber(),
					mt.getEmail(),mt.getPicture(),mt.getUuid()};
			mtd.modifyData(sql, params);
			request.setAttribute("msg", "�޸ĳɹ�");
			request.getRequestDispatcher("/web/success.jsp").forward(request, response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void savePic(FileItem file,HttpServletRequest request,ManagerTeacher mt){
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
