package com.link.application.system.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import com.link.common.persist.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *   用户信息表
 * </p>
 *
 * @author Link
 * @since 2018-05-21
 */
@Getter
@Setter
@Entity
@Table(name = "sys_user", schema = "",uniqueConstraints = { @UniqueConstraint(columnNames="user_num") })
public class SysUser extends BaseEntity implements Serializable
{ 
	/**
	 * 用户ID
	 */
	@Id
	@GenericGenerator(name="generator",strategy="assigned")
	@GeneratedValue(strategy = GenerationType.AUTO , generator="generator") 
	@Column(name = "user_id", nullable = false, length = 48)
	private String userId;

	/**
	 * 用户账号(账号、编号、工号)
	 */
	@Column(name = "user_num", nullable = false, length = 48)
	private String userNum;

	/**
	 * 用户名称(用户姓名、中文名、英文名)
	 */
	@Column(name = "user_name", nullable = true, length = 48)
	private String userName;
 
	/**
	 * 用户姓
	 */
	@Column(name = "first_name", nullable = true, length = 48)
    private  String firstName;
	
	/**
	 * 用户名
	 */
	@Column(name = "last_name", nullable = true, length = 48)
    private  String lastName;
  
	/**
	 * 用户昵称
	 */
	@Column(name = "user_nick_name", nullable = true, length = 48)
	private String userNickName;

	/**
	 * 用户性别('M','F','N')
	 */
	@Column(name = "gender", nullable = true, length = 1 )
	private String gender;

	/**
	 * 电子邮件
	 */
	@Column(name = "email", nullable = false, length = 48)
	private String email;

	/**
	 * 手机号码
	 */
	@Column(name = "phone", nullable = true, length = 24)
	private String phone;

	/**
	 * 办公电话
	 */
	@Column(name = "tel", nullable = true, length = 24)
	private String tel;

	/**
	 * 联系地址
	 */
	@Column(name = "address", nullable = true, length = 480)
	private String address;

	/**
	 * 密码盐
	 */
	@Column(name = "salt", nullable = true, length = 48 )
	private String salt;
	
	/**
	 * 密码
	 */
	@Column(name = "password", nullable = false, length = 240 )
	private String password;

	/**
	 * 最后登录时间
	 */
	@Column(name = "last_login_time", nullable = true, length = 24)
	private String lastLoginTime;

	/**
	 * 最后登录IP
	 */
	@Column(name = "last_login_ip", nullable = true, length = 48)
	private String lastLoginIp;

	/**
	 * 密码重置时间
	 */
	@Column(name = "last_password_reset_date", nullable = true, length = 60 )
	private long lastPasswordResetDate;
	
	/**
	 * 备注信息
	 */
	@Column(name = "remark", nullable = true, length = 240)
	private String remark;

	/**
	 * 用户状态 ( 0：停用，1:启用 ，2：删除，3：锁定，4：未激活 )
	 */
	@Column(name = "user_status", nullable = false, length = 1)
	private String userStatus;
	
	@Transient
	private List<SysRole> roles ;
	
	@Transient
	private  List<SysOrg> orgs ;

	@Transient
	private  List<SysResource> resources ;
	
	
	private static final long serialVersionUID = -6958615673084132682L;
}
