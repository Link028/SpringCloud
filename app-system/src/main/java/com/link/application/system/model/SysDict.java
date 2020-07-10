package com.link.application.system.model;

import java.io.Serializable;
import java.util.List;

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
 * 数据字典表
 * </p>
 *
 * @author Link
 * @since 2018-05-21
 */
@Getter
@Setter 
@Entity
@Table(name = "sys_dict", schema = "", uniqueConstraints = { @UniqueConstraint(columnNames = "dict_num") })
public class SysDict extends BaseEntity implements Serializable 
{
	/**
	 * 字典ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "assigned")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "dict_id", nullable = false, length = 48)
	private String dictId;

	/**
	 * 字典名称(中文名称)
	 */
	@Column(name = "dict_name", nullable = false, length = 120)
	private String dictName;

	/**
	 * 字典代码(字典类型、编码)
	 */
	@Column(name = "dict_num", nullable = false, length = 48)
	private String dictNum;

	/**
	 * 字典类型 ( 1:系统字典 ，2:非系统字典 )
	 */
	@Column(name = "dict_type", nullable = true, length = 1)
	private String dictType;

	/**
	 * 序号
	 */
	@Column(name = "seq", nullable = true, length = 6)
	private int seq;

	@Transient
	private  List<SysDictData> sysDictData ;
	
	private static final long serialVersionUID = -9103283311404778099L;

}
