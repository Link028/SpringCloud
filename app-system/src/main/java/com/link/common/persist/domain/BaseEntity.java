package com.link.common.persist.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Base abstract class for entities which will hold definitions for created,
 * last modified by and created, last modified by date.
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable
{
	private static final long serialVersionUID = 3313939219816246474L;

	/** 年月日时分秒日期格式化类 */
	public static final DateFormat _DF_YYYYMMDDHHMMSS = new SimpleDateFormat("yyyyMMddHHmmss"); // 数据文件名称日期格式化类

	// @javax.persistence.Id
	// @org.hibernate.annotations.GenericGenerator(
	// name = "system-uuid",
	// strategy = "uuid2" ,
	// parameters = {@org.hibernate.annotations.Parameter(name = "uuid_gen_strategy_class",value =
	// "org.hibernate.id.uuid.CustomVersionOneStrategy")}
	// )
	// @javax.persistence.GeneratedValue(generator = "system-uuid")
	// @Column(name = "id", nullable = false, length = 36)
	// private String id;

//	@javax.persistence.Id
//	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO, generator = "generator")
//	@org.hibernate.annotations.GenericGenerator(name = "generator", strategy = "assigned")
//	@Column(name = "id", nullable = false, length = 36)
//	private String id;

//	@javax.persistence.Id
//    @Column(name = "id")
//    private Long id;
	
	/**
	 * 创建者
	 */
	@Column(name = "create_user", nullable = true, length = 12)
	private String createUser;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time", nullable = true, length = 24)
	private String createTime;

	/**
	 * 更新者
	 */
	@Column(name = "update_user", nullable = true, length = 12)
	private String updateUser;

	/**
	 * 更新时间
	 */
	@Column(name = "update_time", nullable = true, length = 24)
	private String updateTime;

	/**
	 * 状态 ( 0:无效 ，1：有效，2：删除 )
	 */
	@Column(name = "status", nullable = true, length = 1)
	private String status;
	
	/**
	 * 版本号
	 */
	@Version
	@Column(name = "version", nullable = true, length = 6)
	private int version;

//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
	
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@PrePersist
	public void onCreate() {
		this.createTime = _DF_YYYYMMDDHHMMSS.format(System.currentTimeMillis());
		//this.updateTime = _DF_YYYYMMDDHHMMSS.format(System.currentTimeMillis());
	}

	@PreUpdate
	public void onUpdate() {
		this.updateTime = _DF_YYYYMMDDHHMMSS.format(System.currentTimeMillis());
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                builder.append(f.getName(), f.get(this)).append("\n");
            }
        } catch (Exception e) {
            builder.append("toString builder encounter an error");
        }
        return builder.toString();
    }
}
