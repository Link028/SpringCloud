package com.link.cloud.gateway;

/**
 * 通用常量信息
 */
public class Constants
{
	/**
	 * UTF-8 字符集
	 */
	public static final String  GLOBAL_ENCODING = "UTF-8";

	public static final String  API_URL = "/v2/api-docs";
	
	public final static String	LOGIN_URL		= "/auth/login";
	public final static String	LPGOUT_URL		= "/auth/logout";
	public final static String	REGISTER_URL	= "/auth/register";
	public final static String	FORGOTPASS_URL	= "/auth/forgetpassword"; 
	
	public final static String	FALLBACK_URL	= "/fallback";
	public final static String	CODE_URL		= "/code";
	public final static String RESOURCE_PREFIX  = "/profile";
	
	public static final String	CURRENT_ID			= "curr_id";
	public static final String	CURRENT_UN	        = "curr_un";
	public static final String  ACCESS_USERID    = "ac_userid_";
	 
	public final static String	TOKEN				= "token";
	public final static String	ACCESS_TOKEN		= "ac_token_"; 
	public final static String	DEFAULT_CODE_KEY	= "rd_code_";
	
	// 排除过滤的 uri 地址, swagger排除自行添加
	public static final String[] whiteList = { LOGIN_URL, LPGOUT_URL, REGISTER_URL, FORGOTPASS_URL };
}
