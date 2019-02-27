package it.cast.tools;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	
	private static ComboPooledDataSource dataSource=new ComboPooledDataSource();
	
	
	private static ThreadLocal<Connection> tl=new ThreadLocal<Connection>();			//����ר������
	
	public static Connection getConnection() throws SQLException{
		Connection con=tl.get();
		if(con!=null) return con;
		return dataSource.getConnection();
	}
	
	public static DataSource getDataSource(){
		return dataSource;
	}
	
	public static void beginTransaction() throws SQLException{
		Connection con=tl.get();
		if(con!=null)
			throw new SQLException("�Ѿ�����������");
		con=getConnection();
		con.setAutoCommit(false);
		
		tl.set(con); 
	}
	
	public static void commitTransaction() throws SQLException{
		Connection con=tl.get();
		if(con==null)
			throw new SQLException("��û�п������񣬲����ύ");
		con.commit();
		con.close();
		tl.remove();
	}
	
	public static void rollbackTransaction() throws SQLException{
		Connection con=tl.get();
		if(con==null)
			throw new SQLException("��û�п������񣬲��ܻع�");
		con.rollback();
		con.close();
		tl.remove();
	}
	
	public static void releaseConnection(Connection connection) throws SQLException{
		/*
		 * Dao�е���
		 * �ж����ǲ�������ר�ã�����ǣ��Ͳ��ر�
		 * �����������ר�ã��͹ر�
		 */
		Connection con=tl.get();
		//���con==null��˵������û������
		if(con==null) connection.close();
		//���con!=null,˵����������ô��Ҫ�жϲ��������Ƿ���con��ȣ������ȣ�˵���������Ӳ�������רҵ����
		if(con!=connection) connection.close();
		
	}
	
}
