package it.cast.dao;

import it.cast.domain.Magazine;
import it.cast.domain.PageBean;
import it.cast.tools.TxQueryRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class MagazineDao {
	public void AddData(String sql,Object[] params) throws SQLException{
		QueryRunner qr=new TxQueryRunner();
		qr.update(sql, params);
	}
	
	public List<Magazine> getData(String sql,Object[] params) throws SQLException{
		QueryRunner qr=new TxQueryRunner();
		List<Magazine> getMan=qr.query(sql, new BeanListHandler<Magazine>(Magazine.class),params);
		return getMan;
	}
	
	public void modifyData(String sql,Object[] params) throws SQLException{
		QueryRunner qr=new TxQueryRunner();
		qr.update(sql, params);
	}
	
	public void deleteData(String sql,Object[] params) throws SQLException{
		QueryRunner qr=new TxQueryRunner();
		qr.update(sql, params);
	}
	
	//��ѯ���еķ�ҳ��ѯ
	public PageBean<Magazine> findAll(int pc,int ps,String table){
		QueryRunner qr=new TxQueryRunner();
		try{
			//����PageBean�������õ�ǰҳ���ÿҳ��¼��
			PageBean<Magazine> pb=new PageBean<Magazine>();
			pb.setPc(pc);
			pb.setPs(ps);
			//����ܼ�¼��
			String sql="select count(*) from "+table;
			Number num=(Number)qr.query(sql, new ScalarHandler());
			int tr=num.intValue();
			pb.setTr(tr);
			//���Ҫ��ѯҳ�������
			sql="select * from "+table+" order by id limit ?,?";
			List<Magazine> beanList=qr.query(sql, new BeanListHandler<Magazine>(Magazine.class),(pc-1)*ps,ps);
			pb.setBeanList(beanList);
			return pb;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	public PageBean<Magazine> criteriaQuery(Magazine mag,int pc,int ps)  {
		QueryRunner qr=new TxQueryRunner();
		//����PageBean�������õ�ǰҳ���ÿҳ��¼��
		PageBean<Magazine> pb=new PageBean<Magazine>();
		pb.setPc(pc);
		pb.setPs(ps);
		
		//����sql���Ͳ���
		StringBuilder cntSql=new StringBuilder("select count(*) from MagazineInfo ");
		StringBuilder whereSql=new StringBuilder(" where 1=1");
		List<Object> params=new ArrayList<Object>();
		
		String id=mag.getId();
		if(id!=null&&!id.trim().isEmpty()){
			whereSql.append(" and id like ?");
			params.add("%"+id+"%");
		}
		String name=mag.getName();
		if(name!=null&&!name.trim().isEmpty()){
			whereSql.append(" and name like ?");
			params.add("%"+name+"%");
		}
		String classId=mag.getClassId();
		if(classId!=null&&!classId.trim().isEmpty()){
			whereSql.append(" and classId like ?");
			params.add("%"+classId+"%");
		}
		String publishHouseId=mag.getPublishHouseId();
		if(publishHouseId!=null&&!publishHouseId.trim().isEmpty()){
			whereSql.append(" and publishHouseId like ?");
			params.add("%"+publishHouseId+"%");
		}
		
		String year=mag.getYear();
		if(year!=null&&!year.trim().isEmpty()){
			String date=mag.getYear()+"-"+mag.getMonth()+"-"+mag.getDay();
			whereSql.append(" and date=?");
			params.add(date);
		}
		String editNumber=mag.getEditNumber();
		if(editNumber!=null&&!editNumber.trim().isEmpty()){
			whereSql.append(" and editNumber like ?");
			params.add("%"+editNumber+"%");
		}
		try {
			//�����ܼ�¼��
			Number num=(Number)qr.query(cntSql.append(whereSql).toString(), new ScalarHandler(),params.toArray());
			int tr=num.intValue();
			pb.setTr(tr);
			
			//���Ҫ��ѯҳ������
			StringBuilder sql=new StringBuilder("select *from MagazineInfo  ");
			StringBuilder limitSql=new StringBuilder(" limit ?,?");
			params.add((pc-1)*ps);
			params.add(ps);
			List<Magazine> beanList= qr.query(sql.append(whereSql).append(limitSql).toString(), 
					new BeanListHandler<Magazine>(Magazine.class),params.toArray());
			pb.setBeanList(beanList);
			return pb;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
