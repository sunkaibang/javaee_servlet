package it.cast.tools;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	
	private static ComboPooledDataSource dataSource=new ComboPooledDataSource();
	
	
	private static ThreadLocal<Connection> tl=new ThreadLocal<Connection>();			//事务专用连接
	
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
			throw new SQLException("已经开启了事务");
		con=getConnection();
		con.setAutoCommit(false);
		
		tl.set(con); 
	}
	
	public static void commitTransaction() throws SQLException{
		Connection con=tl.get();
		if(con==null)
			throw new SQLException("还没有开启事务，不能提交");
		con.commit();
		con.close();
		tl.remove();
	}
	
	public static void rollbackTransaction() throws SQLException{
		Connection con=tl.get();
		if(con==null)
			throw new SQLException("还没有开启事务，不能回滚");
		con.rollback();
		con.close();
		tl.remove();
	}
	
	public static void releaseConnection(Connection connection) throws SQLException{
		/*
		 * Dao中调用
		 * 判断它是不是事务专用，如果是，就不关闭
		 * 如果不是事物专用，就关闭
		 */
		Connection con=tl.get();
		//如果con==null，说明现在没有事务
		if(con==null) connection.close();
		//如果con!=null,说明有事务，那么需要判断参数连接是否与con相等，若不等，说明参数连接不是事务专业连接
		if(con!=connection) connection.close();
		
	}
	
}
