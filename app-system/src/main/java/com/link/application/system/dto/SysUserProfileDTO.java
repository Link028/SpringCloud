package com.link.application.system.dto;

import java.io.Serializable;

import com.link.common.persist.dto.BaseDTO;

import lombok.Getter;
import lombok.Setter;


/**
 * <p>
 *   用户配置信息表
 * </p>
 *
 * @author Link
 * @since 2018-05-21
 */
@Getter
@Setter
public class SysUserProfileDTO extends BaseDTO implements Serializable {
 
	private String userId;

	private String resIdOne;

	private String resIdTwo;

	private String resIdThree;

	private String resIdFour;

	private String resIdFive;

	private String resIdSix;

	private String fullscrFlag;

	private String tabFlag;

	private String themeFlag;
	
	private String drawerMiniFlag;
	
	private String drawerPermanentFlag;

	private String skin;
 
	private String leftmenu;

	private String primary;
	
	private java.sql.Blob photo;

	private int version;

    private String status;
	
	private static final long serialVersionUID = 2398285666122130441L;

}
