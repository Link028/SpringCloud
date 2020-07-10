package com.link.cloud.gateway;

import java.util.HashMap;
import java.util.Map;

public class RestMessage extends HashMap<String, Object> {
	//
	private static final long serialVersionUID = -8157613083634272196L;

	public RestMessage() {
		put("code", 0);
		put("msg", "success");
	}

	public static RestMessage error() {
		return error(500, "未知异常，请联系管理员");
	}

	public static RestMessage error(String msg) {
		return error(500, msg);
	}

	public static RestMessage error(int code, String msg) {
		RestMessage r = new RestMessage();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}
	
	public static RestMessage ok(Map<String, Object> map) {
		RestMessage r = new RestMessage();
		r.putAll(map);
		return r;
	}

	public static RestMessage ok() {
		return new RestMessage();
	}
	
	public static RestMessage ok(String msg) {
		RestMessage r = new RestMessage();
		r.put("msg", msg);
		return r;
	}

	public static RestMessage data(Object obj) {
		RestMessage r = new RestMessage();
		r.put("data", obj);
		return r;
	}

	@Override
	public RestMessage put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}