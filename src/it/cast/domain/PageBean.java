package it.cast.domain;

import java.util.List;

public class PageBean<T> {
	private int pc; //��ǰҳ��page code
//	private int tp; //��ҳ��total page
	private int tr;//�ܼ�¼��total record
	private int ps;//ÿҳ��¼��page size
	private List<T> beanList;
	private String url; //�����Ӻ������
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPc() {
		return pc;
	}
	public void setPc(int pc) {
		this.pc = pc;
	}
	public int getTp() {
		//ͨ���ܼ�¼����ÿҳ��¼������
		int tp=tr/ps;
		return tr%ps==0? tp:tp+1;
	}
	
	public int getTr() {
		return tr;
	}
	public void setTr(int tr) {
		this.tr = tr;
	}
	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	
	
}
