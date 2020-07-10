package com.link.common.configration.web;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.link.common.BaseException;
import com.link.common.MessageCode;
import com.link.common.web.RestUtil;
import com.link.common.web.RestData;

import lombok.extern.slf4j.Slf4j;


/**
 * 异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler
{
	/**
	 * 拦截未知的运行时异常
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public RestData<?> notFount(RuntimeException e) {
		log.error("系统运行时异常，错误信息：{}", e.getMessage(),e);
		return RestUtil.createBody(false ,MessageCode.SystemRuntimeError.getCode(), "系统运行时异常，错误信息：" + e.getMessage());
	}
	
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public RestData<?> handleException(Exception e) {
		log.error("系统内部异常，错误信息：{}", e.getMessage(),e);
		return RestUtil.createBody(false ,MessageCode.SystemError.getCode(), "系统内部异常，错误信息：" + e.getMessage());
	}

	@ExceptionHandler(value = BaseException.class)
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	@ResponseBody
	public RestData<?> handleParamsInvalidException(BaseException e) {
		log.error("执行操作发生异常，错误信息：{}", e.getMessage(),e);
		return RestUtil.createBody(false ,MessageCode.SystemBizError.getCode(), "执行操作发生异常，错误信息：" + e.getMessage());
	}
	
	/**
	 * 统一处理请求参数校验(普通传参)
	 *
	 * @param e ConstraintViolationException
	 * @return FebsResponse
	 */
	@ExceptionHandler(value = ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public RestData<?> handleConstraintViolationException(ConstraintViolationException e) {
		StringBuilder message = new StringBuilder();
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		for (ConstraintViolation<?> violation : violations)
		{
			Path path = violation.getPropertyPath();
			String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
			message.append(pathArr[1]).append(violation.getMessage()).append(";");
		}
		message = new StringBuilder(message.substring(0, message.length() - 1));
 
		log.error("执行操参数校验错误，错误信息：{}", e.getMessage(),e); 
		
		return RestUtil.createBody(false ,MessageCode.ValidateError.getCode(), message.toString());
	}

	/**
	 * 请求方式不支持
	 */
	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	public RestData<?> handleException(HttpRequestMethodNotSupportedException e) {
		
		log.error("不支持' " + e.getMethod() + "'请求",  e );
		return RestUtil.createBody(false ,MessageCode.MethodNotSupported.getCode(), "执行操作发生异常，错误信息：" + e.getMessage());
	}


}
