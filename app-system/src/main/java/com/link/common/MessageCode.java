package com.link.common;

/**
 * 描述：公共枚举常量存放接口 <br>
 * 
 * 
 */
public enum MessageCode 
{
	SystemError(     "0000" ,"系统运行时错误") ,  // 系统内部错误 
	SystemRuntimeError(     "0001" ,"系统内部错误") ,  // 系统内部错误 
	SystemBizError(         "0002" ,"业务处理错误") ,  // 业务处理错误
	MethodNotSupported(      "1000" ,"请求参数错误 "),  // 不支持的方法
	ValidateError(            "1001" ,"请求参数错误 "),  // 参数错误 
    DataBaseAccError(       "1002" ,"数据库操作出错"),   // 数据库操作异常  
    HadoopServiceErr(       "1003" ,"Hadoop服务出错") ,  // Hadoop服务错误 
	IndexServiceError(      "1004" ,"索引服务出错"),  //索引服务出错
	CacheServiceError(      "1005" ,"缓存服务异常"),  // 缓存服务异常 
	FileServiceError(       "1006" ,"文件服务出错"),  //文件服务出错  
	MessageServiceError(    "1007" ,"消息服务出错") , //消息服务出错 
	OperationFailue(        "3000" ,"操作失败") ,  // 操作失败
	OperationSuccess(       "3001" ,"操作成功") , // 操作成功 
	OperationUnAuthorized(  "3002" ,"操作未授权");    // 操作未授权 
	
	
	private String code;
	private String message;
	
	private MessageCode(String errCode,String errDesc ) 
	{
		this.code = errCode;
		this.message = errDesc;
	}

	public String getCode() 
	{
		return code;
	}

	public String getMessage()
	{
		return message;
	}

	
}
