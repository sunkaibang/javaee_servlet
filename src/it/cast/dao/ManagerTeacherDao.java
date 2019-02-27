package it.cast.dao;

import it.cast.domain.Magazine;
import it.cast.domain.ManagerTeacher;
import it.cast.domain.PageBean;
import it.cast.tools.TxQueryRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class ManagerTeacherDao {
	public void AddData(String sql,Object[] params) throws SQLException{
		QueryRunner qr=new TxQueryRunner();
		qr.update(sql, params);
	}
	
	public ManagerTeacher getData(String sql,Object[] params) throws SQLException{
		QueryRunner qr=new TxQueryRunner();
		ManagerTeacher getMan=qr.query(sql, new BeanHandler<ManagerTeacher>(ManagerTeacher.class),params);
		return getMan;
	}
	
	public void modifyData(String sql,Object[] params) throws SQLException{
		QueryRunner qr=new TxQueryRunner();
		qr.update(sql, params);
	}
	
	public PageBean<ManagerTeacher> findAll(int pc,int ps,String table){
		QueryRunner qr=new TxQueryRunner();
		try{
			PageBean<ManagerTeacher> pb=new PageBean<ManagerTeacher>();
			pb.setPc(pc);
			pb.setPs(ps);
			String sql="select count(*) from "+table;
			Number num=(Number)qr.query(sql, new ScalarHandler());
			int tr=num.intValue();
			pb.setTr(tr);
			
			sql="select * from "+table+" order by id limit ?,?";
			List<ManagerTeacher> beanList=qr.query(sql, new BeanListHandler<ManagerTeacher>(ManagerTeacher.class),(pc-1)*ps,ps);
			pb.setBeanList(beanList);
			return pb;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public PageBean<ManagerTeacher> criteriaQuery(ManagerTeacher mag,int pc,int ps)  {
		QueryRunner qr=new TxQueryRunner();
		PageBean<ManagerTeacher> pb=new PageBean<ManagerTeacher>();
		pb.setPc(pc);
		pb.setPs(ps);
		
		StringBuilder cntSql=new StringBuilder("select count(*) from TeacherInfo ");
		StringBuilder whereSql=new StringBuilder(" where 1=1");
		List<Object> params=new ArrayList<Object>();
		
		String id=mag.getId();
		if(id!=null&&!id.trim().isEmpty()){
			whereSql.append(" and id like ?");
			params.add("%"+id+"%");
		}
		String name=mag.getRealName();
		if(name!=null&&!name.trim().isEmpty()){
			whereSql.append(" and realName like ?");
			params.add("%"+name+"%");
		}
		
		String year=mag.getYear();
		if(year!=null&&!year.trim().isEmpty()){
			String date=mag.getYear()+"-"+mag.getMonth()+"-"+mag.getDay();
			whereSql.append(" and birthDay=?");
			params.add(date);
		}
		String telNumber=mag.getTelnumber();
		if(telNumber!=null&&!telNumber.trim().isEmpty()){
			whereSql.append(" and telNumber like ?");
			params.add("%"+telNumber+"%");
		}
		String email=mag.getEmail();
		if(email!=null&&!email.trim().isEmpty()){
			whereSql.append(" and email like ?");
			params.add("%"+email+"%");
		}
		try {
			
			Number num=(Number)qr.query(cntSql.append(whereSql).toString(), new ScalarHandler(),params.toArray());
			int tr=num.intValue();
			pb.setTr(tr);
			
			StringBuilder sql=new StringBuilder("select *from TeacherInfo  ");
			StringBuilder limitSql=new StringBuilder(" limit ?,?");
			params.add((pc-1)*ps);
			params.add(ps);
			List<ManagerTeacher> beanList= qr.query(sql.append(whereSql).append(limitSql).toString(), 
					new BeanListHandler<ManagerTeacher>(ManagerTeacher.class),params.toArray());
			pb.setBeanList(beanList);
			return pb;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
