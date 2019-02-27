package it.cast.domain;

import java.util.Date;

public class ManagerTeacher {
	private String uuid;
	private String id;
	private String password;
	private String realName;
	private String year;
	private String month;
	private String day;
	private Date birthday;
	private String telnumber;
	private String email;
	private String picture;
	public ManagerTeacher() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	
	
	public ManagerTeacher(String uuid, String id, String password,
			String realName, String year, String month, String dat,
			Date birthday, String telnumber, String email, String picture) {
		super();
		this.uuid = uuid;
		this.id = id;
		this.password = password;
		this.realName = realName;
		this.year = year;
		this.month = month;
		this.day = dat;
		this.birthday = birthday;
		this.telnumber = telnumber;
		this.email = email;
		this.picture = picture;
	}

	
	public String toString() {
		return "ManagerTeacher [uuid=" + uuid + ", id=" + id + ", password="
				+ password + ", realName=" + realName + ", year=" + year
				+ ", month=" + month + ", dat=" + day + ", birthday="
				+ birthday + ", telnumber=" + telnumber + ", email=" + email
				+ ", picture=" + picture + "]";
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getTelnumber() {
		return telnumber;
	}
	public void setTelnumber(String telnumber) {
		this.telnumber = telnumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
}
