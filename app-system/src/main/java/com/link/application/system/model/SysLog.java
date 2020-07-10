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
 * 操作日志表
 * </p>
 *
 * @author Link
 * @since 2018-05-21
 */
@Getter
@Setter
@Entity
@Table(name = "sys_log", schema = "")
public class SysLog extends BaseEntity implements Serializable  {
  
	/**
	 * 日志ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "assigned")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "log_id", nullable = false, length = 48)
	private String logId;

	/**
	 * 日志标题
	 */
	@Column(name = "title", nullable = false, length = 120)
	private String title;

	/**
	 * 请求地址
	 */
	@Column(name = "req_addr", nullable = true, length = 480)
	private String reqAddr;

	/**
	 * 日志类型(1:接入,2:查询,3:新建,4:修改,5:删除,6:登入登出)
	 */
	@Column(name = "log_type", nullable = false, length = 1)
	private String logType;

	/**
	 * 操作用户ID
	 */
	@Column(name = "user_id", nullable = false, length = 48)
	private String userId;

	/**
	 * 操作用户ID
	 */
	@Column(name = "user_name", nullable = false, length = 48)
	private String userName;
	
	/**
	 * 业务类型
	 */
	@Column(name = "biz", nullable = true, length = 120)
	private String biz;

	/**
	 * 是否异常及日志内容
	 */
	@Column(name = "msg", nullable = true, length = 1024)
	private String msg;

	/**
	 * 操作时间
	 */
	@Column(name = "opr_time", nullable = true, length = 24)
	private String oprTime;

	/**
	 * 客户端IP
	 */
	@Column(name = "client_ip", nullable = true, length = 24)
	private String clientIp;

	/**
	 * 设备名称
	 */
	@Column(name = "device_name", nullable = true, length = 24)
	private String deviceName;

	/**
	 * 浏览器名称
	 */
	@Column(name = "browser_name", nullable = true, length = 24)
	private String browserName;

	/**
	 * 响应时间
	 */
	@Column(name = "resp_time", nullable = true, length = 24)
	private String respTime;

	private static final long serialVersionUID = -3605291431485333089L;

}
