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
 * 岗位角色表
 * </p>
 *
 * @author Link
 * @since 2018-05-21
 */
@Getter
@Setter
@Entity
@Table(name = "sys_position_role", schema = "")
@IdClass(value = SysPositionRoleID.class)
public class SysPositionRole extends BaseEntity implements Serializable {

	/**
	 * 用户ID
	 */
	@Id
	@Column(name = "position_id", nullable = false, length = 48)
	private String positionId;
	
	/**
	 * 用户ID
	 */
	@Id
	@Column(name = "role_id", nullable = false, length = 48)
	private String roleId;

	
	private static final long serialVersionUID = 3368822509817014236L;

}
