package com.link.application.system.model;

public enum SysUserStatus {

	INVALID ("0"),
	VALID ( "1"),
	DELETED ( "2"),
	LOCKED ( "3"),
	NOACTIVE ( "4");
	
	private String val ;
	
	private SysUserStatus(String val)
	{
		this.val = val ;
	}

	public String getValue()
	{
		return val;
	}
}
