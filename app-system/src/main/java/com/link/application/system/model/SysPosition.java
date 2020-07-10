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
 *  岗位信息表
 * </p>
 *
 * @author Link
 * @since 2018-05-21
 */ 
@Getter
@Setter
@Entity
@Table(name = "sys_position", schema = "", uniqueConstraints = { @UniqueConstraint(columnNames = "position_name") } )
public class SysPosition   extends BaseEntity implements Serializable
{  
	/**
	 * 岗位ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "assigned")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "position_id", nullable = false, length = 48)
	private String positionId;
 
	/**
	 * 组名称
	 */
	@Column(name = "position_name", nullable = false, length = 120)
	private String positionName;

	/**
	 * 排序号
	 */
	@Column(name = "seq", nullable = true, length = 6)
	private int seq;
  
	/**
	 * 备注信息
	 */
	@Column(name = "remark", nullable = true, length = 240)
	private String remark;
	
	private static final long serialVersionUID = -2287399055209739722L;

}
