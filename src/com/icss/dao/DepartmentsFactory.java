package com.icss.dao;

public class DepartmentsFactory {

	private DepartmentsFactory(){     //私有化,不让在类以外创建对象
		
	}
	
	public static DepartmentsDao getInstance(){
		return new DepartmentsDaoImpl();
	}
}
