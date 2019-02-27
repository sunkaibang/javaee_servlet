package it.cast.domain;

public class HouseClass {
	private String uuid;
	private String id;
	private String name;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "PublishHouse [uuid=" + uuid + ", id=" + id + ", name=" + name
				+ "]";
	}
	public HouseClass(String uuid, String id, String name) {
		super();
		this.uuid = uuid;
		this.id = id;
		this.name = name;
	}
	public HouseClass() {
		super();
		// TODO Auto-generated constructor stub
	}
}
