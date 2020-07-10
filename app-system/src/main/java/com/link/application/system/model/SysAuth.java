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
 * 授权信息表
 * </p>
 *
 * @author Link
 * @since 2018-05-21
 */
@Getter
@Setter
@Entity(name = "SysAuth")
@Table(name = "sys_auth", schema = "", uniqueConstraints = { @UniqueConstraint(columnNames = {"res_id","entity_id"}) })
public class SysAuth extends BaseEntity implements Serializable
{ 
	/**
	 * 授权ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "assigned")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "auth_id", nullable = false, length = 48)
	private String authId ;

	/**
	 *  授权的资源类型: 
	 * 1、=> 菜单 URL 授权
	 * 2、=> Button action 授权
	 * 3、=> Tab URL 授权
	 * 4、=> Table name和column 授权
	 * 5、=> File path和name 授权
	 */
	@Column(name = "auth_type", nullable = false , length = 1)
	private String authType;
	
	/**
	 * 资源ID
	 */
	@Column(name = "res_id", nullable = false, length = 48)
	private String resId;
	
	/**
	 * 被授权的实体类型，1：用户，2：角色，3：组，4：机构，5：职位
	 */
	@Column(name = "entity_type", nullable = false, length = 1 )
	private String  entityType;
	
	/**
	 * 被授权的实体ID，有可能是用户、角色、组、机构
	 */
	@Column(name = "entity_id", nullable = false, length = 48)
	private String entityId;
	  
	/**
	 *  授权实体关系: 
	 *  1、=> 当前实体
	 *  2、=> 当前实体及下级实体(机构)
	 *  3、=> 当前用户
	 */
	@Column(name = "entity_rel", nullable = false, length = 1)
	private String entityRel;
	
	private static final long serialVersionUID = 6453941980622757343L;

  
}
