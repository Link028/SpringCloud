package com.link.application.system.model;

import java.io.Serializable;

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
 * 行政区划表
 * </p>
 *
 * @author Link
 * @since 2018-05-21
 */
@Getter
@Setter
@Entity
@Table(name = "sys_area", schema = "", uniqueConstraints = { @UniqueConstraint(columnNames = "area_num") })
public class SysArea extends BaseEntity implements Serializable {
	/**
	 * 区域ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "assigned")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "area_id", nullable = false, length = 48)
	private String areaId;

	/**
	 * 上级区域
	 */
	@Column(name = "area_parent_id", nullable = true, length = 48)
	private String areaParentId;

	/**
	 * 区域代码
	 */
	@Column(name = "area_num", nullable = false, length = 48)
	private String areaNum;

	/**
	 * 区域简称
	 */
	@Column(name = "area_short_name", nullable = true, length = 120 )
	private String areaShortName;
	
	/**
	 * 区域名称
	 */
	@Column(name = "area_name", nullable = false, length = 240)
	private String areaName;

	/**
	 * 区域类型 ( 1:国家 ，2:省份、直辖市，3:地市，4:区县，5:乡镇 )
	 */
	@Column(name = "area_type", nullable = false, length = 1)
	private String areaType;

	/**
	 * 序号
	 */
	@Column(name = "seq", nullable = true, length = 6)
	private int seq;

	@Transient
	private String areaParentName;
 
	private static final long serialVersionUID = 8177896982139053893L;

}
