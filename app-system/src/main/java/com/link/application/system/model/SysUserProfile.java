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
 * 用户配置信息表
 * </p>
 *
 * @author Link
 * @since 2018-05-21
 */
@Getter
@Setter
@Entity
@Table(name = "sys_user_profile", schema = "")
public class SysUserProfile extends BaseEntity implements Serializable
{

	/**
	 * 用户ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "assigned")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "user_id", nullable = false, length = 48)
	private String userId;

	/**
	 * 菜单ID1
	 */
	@Column(name = "res_id_one", nullable = true, length = 120)
	private String resIdOne;

	/**
	 * 菜单ID2
	 */
	@Column(name = "res_id_two", nullable = true, length = 120)
	private String resIdTwo;

	/**
	 * 菜单ID3
	 */
	@Column(name = "res_id_three", nullable = true, length = 120)
	private String resIdThree;

	/**
	 * 菜单ID4
	 */
	@Column(name = "res_id_four", nullable = true, length = 120)
	private String resIdFour;

	/**
	 * 菜单ID5
	 */
	@Column(name = "res_id_five", nullable = true, length = 120)
	private String resIdFive;

	/**
	 * 菜单ID6
	 */
	@Column(name = "res_id_six", nullable = true, length = 120)
	private String resIdSix;

	/**
	 * 菜单ID7
	 */
	@Column(name = "res_id_seven", nullable = true, length = 120)
	private String resIdSeven;

	/**
	 * 菜单ID8
	 */
	@Column(name = "res_id_eight", nullable = true, length = 120)
	private String resIdEight;

	/**
	 * darkthem
	 */
	@Column(name = "darkthem", nullable = true, length = 1)
	private String darkthem;

	/**
	 * minidraw
	 */
	@Column(name = "minidraw", nullable = true, length = 1)
	private String minidraw;

	/**
	 * tag
	 */
	@Column(name = "tag", nullable = true, length = 1)
	private String tag;

	/**
	 * rtl
	 */
	@Column(name = "rtl", nullable = true, length = 1)
	private String rtl;

	/**
	 * permanent
	 */
	@Column(name = "permanent", nullable = true, length = 1)
	private String permanent;

	/**
	 * 用户皮肤
	 */
	@Column(name = "primary_color", nullable = true, length = 48)
	private String primaryColor;

	/**
	 * 用户皮肤
	 */
	@Column(name = "leftmenu_color", nullable = true, length = 48)
	private String leftmenuColor;

	/**
	 * 用户头像
	 * Blob image = Hibernate.getLobCreator(session).createBlob(input, input.available());
	 */
	@Column(name = "photo", nullable = true)
	private java.sql.Blob photo;

	/**
	 * 用户头像
	 * Blob image = Hibernate.getLobCreator(session).createBlob(input, input.available());
	 */
	@Column(name = "avatars", nullable = true)
	private java.sql.Clob avatars;

	private static final long serialVersionUID = 2398285666122130441L;

}
