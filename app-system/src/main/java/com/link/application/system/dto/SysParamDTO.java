package com.link.application.system.dto;

import java.io.Serializable;

import com.link.common.persist.dto.BaseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysParamDTO extends BaseDTO implements Serializable {

	private String paramId;

	private String paramName;

	
	private String paramKey;

	private String paramVal;

	private String paramType;

	private int seq;

	private String remark;
	
	private int version;
	
	@Override
	public String toString() {
		return "SysParamDTO [paramId=" + paramId + ", paramName=" + paramName + ", paramKey=" + paramKey + ", paramVal=" + paramVal + ", paramType=" + paramType + ", seq=" + seq + ", remark=" + remark + ", version=" + version + "]";
	}

	private static final long serialVersionUID = 4042459441219144516L;

}
