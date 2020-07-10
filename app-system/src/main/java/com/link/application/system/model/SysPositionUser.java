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
 * 机构信息表
 * </p>
 *
 * @author Link
 * @since 2018-05-21
 */ 
@Getter
@Setter
@Entity
@Table(name = "sys_position_user", schema = "" )
@IdClass(value = SysPositionUserID.class)
public class SysPositionUser   extends BaseEntity implements Serializable
{  
	/**
	 * 组ID
	 */
	@Id
	@Column(name = "position_id", nullable = false, length = 48)
	private String positionId;

	/**
	 * 用户ID
	 */
	@Id
	@Column(name = "user_id", nullable = false, length = 48)
	private String userId;

	
	private static final long serialVersionUID = -2287399055209739722L;

}
