package com.icss.dao;

public class EmpsFactory {

	private EmpsFactory(){
		
	}
	
	public static EmpsDao getInstance(){
		return new EmpsDaoImpl();
	}
}
