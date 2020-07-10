package com.link.application.system.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.link.common.persist.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 字典数据表
 * </p>
 *
 * @author Link
 * @since 2018-05-21
 */
@Getter
@Setter 
@Entity
@Table(name = "sys_dict_data", schema = "")
public class SysDictData extends BaseEntity implements Serializable 
{  
	/**
	 * 字典数据ID
	 */
	@Id
	@GenericGenerator(name="generator",strategy="assigned")
	@GeneratedValue(strategy = GenerationType.AUTO , generator="generator") 
	@Column(name = "dict_data_id", nullable = false, length = 48)
	private String dictDataId;

	/**
	 * 上级字典数据ID 
	*/
	@Column(name = "dict_data_parent_id", nullable = true, length = 48)
	private String dictDataParentId;
	
	/**
	 * 字典ID
	*/
	@Column(name = "dict_id", nullable = false, length = 48)
	private String dictId;
     
	/**
	 所属数据字典
	cascade属性的可能值有     
	all: 所有情况下均进行关联操作，即save-update和delete。     
	none: 所有情况下均不进行关联操作。这是默认值。      
	save-update: 在执行save/update/saveOrUpdate时进行关联操作。      
	delete: 在执行delete 时进行关联操作。      
	all-delete-orphan: 当一个节点在对象图中成为孤儿节点时，删除该节点。
	比如在一个一对多的关系中，Student包含多个book，当在对象关系中删除一个book时，
	此book即成为孤儿节点。
	可选属性optional=false,表示sysDict不能为空
	
     @ManyToOne(cascade={CascadeType.ALL},optional=false)
	 @JoinColumn(name="dict_id")//设置在sysDict表中的关联字段(外键)
	 private SysDict sysDict;
	 */
	
	/**
	 * 字典标签
	 */
	@Column(name = "dict_data_name", nullable = false, length = 48)
	private String dictDataName;

	/**
	 * 字典标签值
	 */
	@Column(name = "dict_data_val", nullable = false, length = 12)
	private String dictDataVal;

	/**
	 * 序号
	 */
	@Column(name = "seq", nullable = false, length = 6)
	private int seq;
 
	private static final long serialVersionUID = 5442134572622288938L;

}
