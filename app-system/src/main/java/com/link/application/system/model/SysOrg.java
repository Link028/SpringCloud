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
@Table(name = "sys_org", schema = "", uniqueConstraints = { @UniqueConstraint(columnNames = "org_num"),@UniqueConstraint(columnNames = "org_name")} )
public class SysOrg  extends BaseEntity implements Serializable
{
	/**
	 * 机构ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "assigned")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "org_id", nullable = false, length = 48)
	private String orgId;

	/**
	 * 机构编码
	 */
	@Column(name = "org_num", nullable = false, length = 36)
	private String orgNum;

	/**
	 * 机构名称
	 */
	@Column(name = "org_name", nullable = false, length = 120)
	private String orgName;

	/**
	 * 机构全称
	 */
	@Column(name = "org_full_name", nullable = true, length = 240)
	private String orgFullName;

	/**
	 * 排序号
	 */
	@Column(name = "seq", nullable = true, length = 6)
	private int seq;

	/**
	 * 机构级别(1:一级，2：二级，3：三级，4：四级，5：五级)
	 */
	@Column(name = "org_level", nullable = true, length = 1 )
	private int orgLevel;
 
	/**
	 * 机构类型 ( 1:机构 ，2:部门，3:岗位，4:其它 )
	 */
	@Column(name = "org_type", nullable = true, length = 1)
	private String orgType;

	/**
	 * 负责人
	 */
	@Column(name = "con_user", nullable = true, length = 120 )
	private String conUser;

	/**
	 *  联系电话
	 */
	@Column(name = "con_tel", nullable = true, length = 16 )
	private String conTel;

	/**
	 * 联系地址
	 */
	@Column(name = "con_addr", nullable = true, length = 240 )
	private String conAddr;

	/**
	 * 邮政编码
	 */
	@Column(name = "con_post", nullable = true, length = 8 )
	private String conPost;

	/**
	 * 电子邮箱
	 */
	@Column(name = "con_email", nullable = true, length = 28 )
	private String conEmail;

	/**
	 * 一级机构ID
	 */
	@Column(name = "org_id_one", nullable = true, length = 48)
	private String orgIdOne;

	/**
	 * 二级机构ID
	 */
	@Column(name = "org_id_two", nullable = true, length = 48)
	private String orgIdTwo;

	/**
	 * 三级机构ID
	 */
	@Column(name = "org_id_three", nullable = true, length = 48)
	private String orgIdThree;

	/**
	 * 四级机构ID
	 */
	@Column(name = "org_id_four", nullable = true, length = 48)
	private String orgIdFour;

	/**
	 * 五级机构ID
	 */
	@Column(name = "org_id_five", nullable = true, length = 48)
	private String orgIdFive;

	/**
	 * 六级机构ID
	 */
	@Column(name = "org_id_six", nullable = true, length = 48)
	private String orgIdSix;
	
	/**
	 * 备注信息
	 */
	@Column(name = "remark", nullable = true, length = 240)
	private String remark;
	
	/**
	 * 上级机构ID
	 */
	@Column(name = "org_parent_id", nullable = true, length = 48)
	private String orgParentId;
	
	//---------------------------------------------------------------
	
	@javax.persistence.Transient
	private String  orgParentName ;
	
	
	private static final long serialVersionUID = -2287399055209739722L;



}
