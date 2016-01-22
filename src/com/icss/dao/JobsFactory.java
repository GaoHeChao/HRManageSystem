package com.icss.dao;

public class JobsFactory {

	private JobsFactory(){
		
	}
	
	public static JobsDao getInstance(){
		return new JobsDaoImpl();
	}
}
