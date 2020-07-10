package com.link.common;

public enum BooleanConstants
{
	NO("0"), // YES
	YES("1"); // NO

	private String value;

	private BooleanConstants(String value)
	{
		this.value = value;
	}

	public String getValue()
	{
		return value;
	}
	
	public String getLabel()
	{
		if(YES.getValue().equals( value ))
		{
			return "是" ;
		}
		else
		{
			return "否" ;
		}
	}
}
