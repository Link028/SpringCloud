package com.link.application.system;

import java.util.HashMap;
import java.util.Map;

import com.link.application.system.model.SysParam;
import com.link.application.system.service.SysParamService;
import com.link.common.configration.spring.SpringContextHolder;

public class SystemContext
{
   public static Map<String,SysParam> sysparams = new HashMap<String,SysParam>();
   
   public static void loadContext()
   {
	   loadSysParam();
   }
   
   public static void loadSysParam()
   {
	   SysParamService sysParamService = SpringContextHolder.getBean(SysParamService.class);
	   Map<String,SysParam> map = sysParamService.findSysParam()  ;

	   sysparams.putAll( map );
   }
   
   public static String getParam(String key)
   {
	   SysParam param = sysparams.get(key) ;
	   return param == null ? "" : param.getParamVal() ;
   }
   
   public static boolean getParamBoolean(String key)
   {
	   return getParamBoolean(key,false) ;
   }
   
   public static boolean getParamBoolean(String key,boolean defVal)
   {
	   SysParam param = sysparams.get(key) ;
	   if( param != null && param.getParamVal() != null )
	   {
		   return  Boolean.valueOf(  param.getParamVal()) ;
	   }
	   
	   return defVal ;
   }
}
