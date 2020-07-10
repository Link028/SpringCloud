package com.link.application.system.dto;

import java.io.Serializable;

import com.link.application.system.model.SysOrg;
import com.link.common.persist.dto.BaseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysOrgDTO extends BaseDTO implements Serializable {

	private String orgId;

	private String orgParentId;

	private String  orgParentName ;
	
	private String orgNum;

	private String orgName;

	private String orgFullName;

	private int seq;

	private int orgLevel;

	private String orgType;

	private String conUser;

	private String conTel;

	private String conAddr;

	private String conPost;

	private String conEmail;

	private String orgIdOne;

	private String orgIdTwo;

	private String orgIdThree;

	private String orgIdFour;

	private String orgIdFive;

	private String orgIdSix;

	private String status;
	
	private String remark;
	
	private int version;
	
	private SysOrg parent;
	
	private static final long serialVersionUID = 4042459441219144516L;

}
