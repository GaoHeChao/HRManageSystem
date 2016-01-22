package com.icss.bean;

public class PhotoBean {

	private int photo_id;
	private String photo_path;
	private int employee_id;
	
	public PhotoBean() {
		super();
	}

	public PhotoBean(int photo_id, String photo_path, int employee_id) {
		super();
		this.photo_id = photo_id;
		this.photo_path = photo_path;
		this.employee_id = employee_id;
	}

	public int getPhoto_id() {
		return photo_id;
	}

	public void setPhoto_id(int photo_id) {
		this.photo_id = photo_id;
	}

	public String getPhoto_path() {
		return photo_path;
	}

	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

}
