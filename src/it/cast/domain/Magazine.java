package it.cast.domain;

import java.util.Date;

public class Magazine {
	private String uuid;
	private String id;
	private String name;
	private String classId;
	private String publishHouseId;
	private String year;
	private String month;
	private String day;
	private Date date;
	private String editNumber;
	private String introduce;
	private String state;
	public String getUuid() {
		return uuid;
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
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getPublishHouseId() {
		return publishHouseId;
	}
	public void setPublishHouseId(String publishHouseId) {
		this.publishHouseId = publishHouseId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getEditNumber() {
		return editNumber;
	}
	public void setEditNumber(String editNumber) {
		this.editNumber = editNumber;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Magazine [uuid=" + uuid + ", id=" + id + ", name=" + name
				+ ", classId=" + classId + ", publishHouseId=" + publishHouseId
				+ ", year=" + year + ", month=" + month + ", day=" + day
				+ ", date=" + date + ", editNumber=" + editNumber
				+ ", introduce=" + introduce + ", state=" + state + "]";
	}
	public Magazine(String uuid, String id, String name, String classId,
			String publishHouseId, String year, String month, String day,
			Date date, String editNumber, String introduce, String state) {
		super();
		this.uuid = uuid;
		this.id = id;
		this.name = name;
		this.classId = classId;
		this.publishHouseId = publishHouseId;
		this.year = year;
		this.month = month;
		this.day = day;
		this.date = date;
		this.editNumber = editNumber;
		this.introduce = introduce;
		this.state = state;
	}
	public Magazine() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
