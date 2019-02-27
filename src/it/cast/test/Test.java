package it.cast.test;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void bigWordText(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		//压缩图片测试
//		CompressPicture imgCom = new CompressPicture("E:\\TomcatInstall\\apache-tomcat-7.0.82\\webapps\\DatabaseBigWork\\FileUpload\\1.jpg"
//				,"E:\\TomcatInstall\\apache-tomcat-7.0.82\\webapps\\DatabaseBigWork\\FileUpload");  
//        imgCom.resizeFix(400, 400);
	
//		Teacher a=new Teacher();
//		System.out.print(a instanceof Teacher);
				
//		ManagerTeacherDao mdao=new ManagerTeacherDao();
//		ManagerTeacher man=new ManagerTeacher(CommonsUtils.uuid(),"0001","1234","张强",new java.sql.Date(new Date().getTime()),
//				"12345678901","1@qq.com","casdc");
//		mdao.AddData(man);
		
//		System.out.println(new Date());
//		System.out.println(new java.sql.Date(new Date().getTime()));
		
//		ManagerTeacherDao mdao=new ManagerTeacherDao();
//		ManagerTeacher man=new ManagerTeacher(CommonsUtils.uuid(),"0003","1234","张强",new java.sql.Date(new Date().getTime()),
//				"12345678901","1@qq.com","casdc");
//		System.out.println(mdao.getData(man, "ManagerInfo"));
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
			msg.setContent("阿斯顿法撒旦阿斯顿阿斯顿阿德是", "text/html;charset=utf-8");
//			Transport.send(msg);
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
		
//		String text="";
//		for(int i=1;i<=4;i++){
//			text=text+(int)(Math.random()*10);
//		}
//		System.out.println(text);
		
		
//		for (int i=200;i<=800;i++){
//			Magazine user=new Magazine();
//			user.setUuid(CommonsUtils.uuid());
//			user.setId("0"+i);
//			user.setName(i+"类");
//			user.setClassId("067");
//			user.setPublishHouseId("002");
//			user.setEditNumber("11");
//			user.setIntroduce("很好");
//			user.setState("3/4");
//			String sql="insert into MagazineInfo values(?,?,?,?,?,?,?,?,?)";
//			Object[] params={user.getUuid(),user.getId(),user.getName(),user.getClassId(),user.getPublishHouseId(),
//					new java.sql.Date(new Date().getTime()),user.getEditNumber(),user.getIntroduce(),user.getState()};
//			MagazineDao md = new MagazineDao();
//			try {
//				md.AddData(sql, params);
//			} catch (SQLException e) {
//				throw new RuntimeException(e);
//			}
//		}
//		
//		for (int i=200;i<=800;i++){
//		ManagerTeacher mt=new ManagerTeacher();
//		mt.setUuid(CommonsUtils.uuid());
//		mt.setId("0"+i);
//		mt.setPassword("asdf"+i);
//		mt.setRealName("孙"+i);
//		mt.setTelnumber(i+"45678901");
//		mt.setEmail(i+"@1.com");
//		String sql="insert into TeacherInfo values(?,?,?,?,?,?,?,?) ";
//		Object[] params={mt.getUuid(),mt.getId(),mt.getPassword(),mt.getRealName(),new java.sql.Date(new Date().getTime()),
//				mt.getTelnumber(),mt.getEmail(),mt.getPicture()};
//		ManagerTeacherDao md = new ManagerTeacherDao();
//		try {
//			md.AddData(sql, params);
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//	}
		System.out.println("a");
		
	}
	public static void create()throws Exception{
		
	}

}
