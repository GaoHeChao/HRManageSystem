package com.icss.bean;

public class DepartmentsBean {

	private int department_id;
	private String department_name;
	private String location_name;

	public DepartmentsBean() {
		super();
	}
	
	public DepartmentsBean(int department_id, String department_name, String location_name) {
		super();
		this.department_id = department_id;
		this.department_name = department_name;
		this.location_name = location_name;
	}

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	
}
