package com.link.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 通用常量信息
 */
public class CommonConstants
{
	/**
	 * UTF-8 字符集
	 */
	public static final String  GLOBAL_ENCODING = "UTF-8";

	public static final String SPRING_PROFILE_TEST = "test";
	public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
	public static final String SPRING_PROFILE_PRODUCTION = "prod";
	
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

	public final static String	TOKEN				= "token";

	
    /**
     * 通用失败标识
     */
    public static final String FAIL             = "0";
	
    /**
     * 通用成功标识
     */
    public static final String SUCCESS          = "1";
 

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM         = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE        = "pageSize";

    /**
     * 排序列
     */
    public static final String SORT_COLUMN  = "sortField";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC           = "sortOrder";

    
	/** 年月日日期格式化类 */
	public static final DateFormat	_DF_YYYYMMDD			= new SimpleDateFormat("yyyyMMdd") ;			// 数据文件名称日期格式化类
	/** 年月日时分秒日期格式化类 */
	public static final DateFormat	_DF_YYYYMMDDHHMMSS		= new SimpleDateFormat("yyyyMMddHHmmss") ;		// 数据文件名称日期格式化类
	
	/** 年月日时分秒日期格式化类 */
	public static final DateFormat	_DF_YYYYMMDDHHMMSSSSS	= new SimpleDateFormat("yyyyMMddHHmmssSSS") ;	// 数据文件名称日期格式化类
	
	/** 年月日时分秒日期格式化类 */
	public static final DateFormat	_DF_YYYYMMDDHHMMSSVIEW	= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;	// 数据文件名称日期格式化类
	 


}
