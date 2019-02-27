package it.cast.tools;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

/**
 * ������еķ������Լ����������ӵ�����
 * ������紫��
 * ��ô������أ�
 * 		ͨ��JdbcUtils.getConnection()�õ����ӣ��������������ӣ�Ҳ��������ͨ������
 *      JdncUtils.releaseConnection()��ɶ����ӵ��ͷţ��������ͨ���ӣ��ر�
 * @author acer-pc
 *
 */

public class TxQueryRunner extends QueryRunner {

	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		/*
		 * 1.�õ�����
		 * 2.ִ�и��෽��
		 * 3.�ͷ�����
		 * 4.����ֵ
		 */
		Connection con=JdbcUtils.getConnection();
		int []result=super.batch(con, sql,params);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public <T> T query(String sql, Object param, ResultSetHandler<T> rsh)
			throws SQLException {
		// TODO Auto-generated method stub
		Connection con=JdbcUtils.getConnection();
		T result=super.query(con, sql, param, rsh);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public <T> T query(String sql, Object[] params, ResultSetHandler<T> rsh)
			throws SQLException {
		// TODO Auto-generated method stub
		Connection con=JdbcUtils.getConnection();
		T result=super.query(con, sql, params, rsh);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params)
			throws SQLException {
		// TODO Auto-generated method stub
		Connection con=JdbcUtils.getConnection();
		T result=super.query(con, sql, rsh, params);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		// TODO Auto-generated method stub
		Connection con=JdbcUtils.getConnection();
		T result=super.query(con, sql, rsh);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public int update(String sql, Object... params) throws SQLException {
		// TODO Auto-generated method stub
		Connection con=JdbcUtils.getConnection();
		int result=super.update(con, sql, params);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public int update(String sql, Object param) throws SQLException {
		// TODO Auto-generated method stub
		Connection con=JdbcUtils.getConnection();
		int result=super.update(con, sql, param);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public int update(String sql) throws SQLException {
		// TODO Auto-generated method stub
		Connection con=JdbcUtils.getConnection();
		int result=super.update(con, sql);
		JdbcUtils.releaseConnection(con);
		return result;
	}
	
}
