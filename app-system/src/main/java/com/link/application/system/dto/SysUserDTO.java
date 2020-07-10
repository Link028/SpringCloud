package com.link.application.system.dto;

import java.io.Serializable;

import com.link.application.system.model.SysOrg;
import com.link.common.persist.dto.BaseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysUserDTO extends BaseDTO implements Serializable 
{
	private String userId;

	private String userNum;

	private String userName;

    private  String firstName;

    private  String lastName;

	private String userNickName;

	private String gender;

	private String email;

	private String phone;

	private String tel;

	private String address;

	private String salt;

	private String password;

	private String lastLoginTime;

	private String lastLoginIp;

	private long lastPasswordResetDate;
	
	private String userStatus;
 
	private String orgId;
	
	private String  orgName ;
	
	private String remark;
	
	private int version;

    private String status;
	
    private SysOrg org ;
    
	private static final long serialVersionUID = 4042459441219144516L;

}
