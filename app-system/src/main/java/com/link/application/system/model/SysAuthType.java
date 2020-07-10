package com.link.application.system.model;

public enum SysAuthType 
{
	//1：用户，2：角色，3：组，4：机构，4：岗位
	 USER("1"),ROLE("2"),GROUP("3"),ORG("4"),POSITION("5") ;
    
   private String val = null ;
   
   private SysAuthType(String val)
   {
   	this.val = val ;
   }
   
   public String getVal()
   {
   	return val ;
   } 
}
