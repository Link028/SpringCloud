package com.link.application.system.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.link.common.persist.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;
 
/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author Link
 * @since 2018-05-21
 */
@Getter
@Setter
@Entity
@Table(name = "sys_role_user", schema = "")
@IdClass(value = SysRoleUserID.class)
public class SysRoleUser extends BaseEntity implements Serializable
{ 
	/**
	 * 角色ID
	 */
	@Id
	@Column(name = "role_id", nullable = false, length = 48)
	private String roleId;

	/**
	 * 用户ID
	 */
	@Id
	@Column(name = "user_id", nullable = false, length = 48)
	private String userId;

	private static final long serialVersionUID = 2265587761631126385L;

 
}
