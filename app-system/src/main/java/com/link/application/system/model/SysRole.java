package com.link.application.system.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import com.link.common.persist.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色信息表
 * </p>
 *
 * @author Link
 * @since 2018-05-21
 */
@Getter
@Setter
@Entity
@Table(name = "sys_role", schema = "",uniqueConstraints = { @UniqueConstraint(columnNames="role_name") })
public class SysRole extends BaseEntity implements Serializable
{
	/**
	 * 角色ID
	 */
	@Id
	@GenericGenerator(name="generator",strategy="assigned")
	@GeneratedValue(strategy = GenerationType.AUTO , generator="generator") 
	@Column(name = "role_id", nullable = false, length = 48)
	private String roleId;
 
	/**
	 * 角色名称
	 */
	@Column(name = "role_name", nullable = false, length = 48)
	private String roleName;
 
	/**
	 * 排序号
	 */
	@Column(name = "seq", nullable = true, length = 6)
	private int seq;

	/**
	 * 角色类型 ( 1:管理员 ，2: 普通用户，3: 待分配)
	 */
	@Column(name = "role_type", nullable = true, length = 1)
	private String roleType;
 
	/**
	 * 备注
	 */
	@Column(name = "remark", nullable = true, length = 240)
	private int remark;
	
	private static final long serialVersionUID = 5065733770935023870L;
}
