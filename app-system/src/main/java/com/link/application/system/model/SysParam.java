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
 * 系统参数信息表
 * </p>
 *
 * @author Link
 * @since 2018-05-21
 */
@Getter
@Setter 
@Entity
@Table(name = "sys_param", schema = "", uniqueConstraints = { @UniqueConstraint(columnNames = { "param_key"}) })
public class SysParam extends BaseEntity implements Serializable {

	/**
	 * 参数ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "assigned")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "param_id", nullable = false, length = 48)
	private String paramId;

	/**
	 * 参数名称
	 */
	@Column(name = "param_name", nullable = false, length = 48)
	private String paramName;

	/**
	 * 参数Key
	 */
	@Column(name = "param_key", nullable = false, length = 48)
	private String paramKey;

	/**
	 * 字典标签val
	 */
	@Column(name = "param_val", nullable = false, length = 120)
	private String paramVal;

	/**
	 * 是否系统参数 ( 1:是 ，0:否 )
	 */
	@Column(name = "param_type", nullable = false, length = 1)
	private String paramType;

	/**
	 * 序号
	 */
	@Column(name = "seq", nullable = true, length = 6)
	private int seq;

	/**
	 * 备注信息
	 */
	@Column(name = "remark", nullable = true, length = 240)
	private String remark;
	
	private static final long serialVersionUID = 4042459441219144516L;

}
