package com.link.application.system.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.link.common.persist.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 资源信息表
 * </p>
 *
 * @author Link
 * @since 2018-05-21
 */
@Getter
@Setter
@Entity
@Table(name = "sys_resource", schema = "")
public class SysResource extends BaseEntity implements Serializable
{
	/**
	 * 资源ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "assigned")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "res_id", nullable = false, length = 48)
	private String resId ;

	/**
	 * 上级资源ID
	 */
	@Column(name = "res_parent_id", nullable = true, length = 48)
	private String resParentId;

	/**
	 * 资源编码
	 */
	@Column(name = "res_num", nullable = true , length = 48)
	private String resNum;

	/**
	 * 资源名称
	 */
	@Column(name = "res_name", nullable = false, length = 48)
	private String resName;

	/**
	 * 资源提示
	 */
	@Column(name = "res_title", nullable = false, length = 48)
	private String resTitle;

	/**
	 * 资源图标
	 */
	@Column(name = "res_icon", nullable = false, length = 120)
	private String resIcon;

	/**
	 * 资源URL:
	 * 1、 菜单 => URL，
	 * 2、 Button action => URL，
	 * 3、 Tab => URL，
	 * 4、 Table name和column => URL，
	 * 5、 File path和name => URL
	 */
	@Column(name = "res_url", nullable = false , length = 480)
	private String resUrl;

	/**
	 * 资源内容:Button action html ...
	 */
	@Column(name = "res_content", nullable = true, length = 480)
	private String resContent;

	/**
	 * 资源目标( 1:默认，2：新窗口 )
	 */
	@Column(name = "res_target", nullable = true , length = 24)
	private String resTarget;

	/**
	 * 序号
	 */
	@Column(name = "seq", nullable = false, length = 6)
	private int seq;
	  
	/**
	 * 是否显示
	 */
	@Column(name = "is_show", nullable = true, length = 1 )
	private String isShow;
	
	/**
	 * 权限标识
	 */
	@Column(name = "res_auth", nullable = true, length = 240)
	private String resAuth;

	/**
	 * 资源类型( 
	 * 1：菜单 URL 
	 * 2：Button action(查询、修改、删除)
	 * 3:Tab URL
	 * 4：Table name和column ，
	 * 5：File path和name ，
	 * 【如“res”表示资源的访问权限、“OPERATION”表示功能模块的操作权限、“FILE”表示文件的修改权限、“ELEMENT”表示页面元素的可见性控制等】
	 */
	@Column(name = "res_type", nullable = false, length = 1)
	private String resType;

	/**
	 * 权重( 1:超级管理员 ，2：系统管理员，3：二级管理员，4：默认 )
	 */
	@Column(name = "res_level", nullable = true, length = 1)
	private String resLevel;

	@Transient
	private String  resParentName ;
	 
	@Transient
	private List<SysResource> children = new ArrayList<SysResource>();
	 
	private static final long serialVersionUID = 6247427848464502603L;
	
}
