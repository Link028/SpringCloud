package com.link.common;

/**
 * 基础异常
 */
public class BaseException extends Exception
{
	/**
	 * 错误码
	 */
	private String code;

	/**
	 * 错误消息
	 */
	private String message;

	public BaseException(String code, String message)
	{
		super();
		this.code = code;
		this.message = message;
	}

	public BaseException(String code, String message, Exception exception)
	{
		super(exception);
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private static final long serialVersionUID = 1L;

}
