package com.link.common.persist.domain;

public enum EntityStatus 
{
	REMOVED(     "0" ),  
	ENABLED(     "1" ),  
	DISABLED(     "2" ),  
	LOCKED(     "3" );
	 
	private String value;
	
	private EntityStatus(String value ) 
	{
		this.value = value;
	}

	public String getValue() {
		return value;
	} 
}
