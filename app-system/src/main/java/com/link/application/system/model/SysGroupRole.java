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
 * 机构用户表
 * </p>
 *
 * @author Link
 * @since 2018-05-21
 */
@Getter
@Setter
@Entity
@Table(name = "sys_group_role", schema = "")
@IdClass(value = SysGroupRoleID.class)
public class SysGroupRole extends BaseEntity implements Serializable {

	/**
	 * 用户ID
	 */
	@Id
	@Column(name = "role_id", nullable = false, length = 48)
	private String roleId;

	/**
	 * 用户ID
	 */
	@Id
	@Column(name = "group_id", nullable = false, length = 48)
	private String groupId;
	
	private static final long serialVersionUID = 3368822509817014236L;

}
