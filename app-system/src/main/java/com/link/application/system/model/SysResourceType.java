package com.link.application.system.model;

public enum SysResourceType 
{
	/**
	 *  授权的资源类型: 
	 * 1、=> 菜单 URL 授权
	 * 2、=> Button action 授权
	 * 3、=> Tab URL 授权
	 * 4、=> Table name和column 授权
	 * 5、=> File path和name 授权
	 */
	 URL("1"),BUTTON("2"),TAB("3"),TABLE("4"),FILE("5") ;
    
   private String val = null ;
   
   private SysResourceType(String val)
   {
   	this.val = val ;
   }
   
   public String getVal()
   {
   	return val ;
   } 
}
