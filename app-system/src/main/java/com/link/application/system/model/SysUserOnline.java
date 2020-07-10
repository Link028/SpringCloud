package com.link.application.system.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.link.common.persist.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 在线用户信息表
 * </p>
 *
 * @author Link
 * @since 2018-05-21
 */
@Getter
@Setter
@Entity
@Table(name = "sys_user_online", schema = "")
public class SysUserOnline extends BaseEntity implements Serializable {

	/**
	 * 会话ID
	 */
	@Id
	@GeneratedValue(generator = "assigned")
	@GenericGenerator(name = "assigned", strategy = "assigned")
	@Column(name = "session_id", nullable = false, length = 48)
	private String sessionId;

	@Column(name = "user_id", nullable = true, length = 48)
	private String userId;

	/**
	 * 用户名称(用户姓名、中文名、英文名)
	 */
	@Column(name = "user_name", nullable = true, length = 48)
	private String userName;

	/**
	 * 用户主机地址
	 */
	@Column(name = "host", nullable = true, length = 48)
	private String host;

	/**
	 * 用户登录时系统IP
	 */
	@Column(name = "system_host", nullable = true, length = 48)
	private String systemHost;

	/**
	 * 用户浏览器类型
	 */
	@Column(name = "user_agent", nullable = true, length = 120)
	private String userAgent;

	 /**
	 * 状态 ( 1:在线 ，2：隐身，3：退出 ，4：强制退出 )
	 */
	 @Column(name = "status", nullable = true, length = 1)
	 private String status;
	

	/**
	 * session创建时间
	 */
	@Column(name = "start_time", nullable = true, length = 24)
	private String startTime;

	/**
	 * 最后操作时间
	 */
	@Column(name = "last_acc_time", nullable = true, length = 24)
	private String lastAccTime;

	/**
	 * 超时时间
	 */
	@Column(name = "time_out", nullable = true, length = 12)
	private long timeOut;
 
	private static final long serialVersionUID = -3404426725416344767L;

}
