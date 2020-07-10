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
 * 机构信息表
 * </p>
 *
 * @author Link
 * @since 2018-05-21
 */ 
@Getter
@Setter
@Entity
@Table(name = "sys_group", schema = "", uniqueConstraints = { @UniqueConstraint(columnNames = "group_name") } )
public class SysGroup   extends BaseEntity implements Serializable
{  
	/**
	 * 组ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "assigned")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "group_id", nullable = false, length = 48)
	private String groupId;
 
	/**
	 * 组名称
	 */
	@Column(name = "group_name", nullable = false, length = 120)
	private String groupName;

	/**
	 * 排序号
	 */
	@Column(name = "seq", nullable = true, length = 6)
	private int seq;
  
	
	private static final long serialVersionUID = -2287399055209739722L;

}
