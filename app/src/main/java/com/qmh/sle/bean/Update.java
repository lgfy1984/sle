package com.qmh.sle.bean;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

/**
 * 应用程序更新实体类
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
@SuppressWarnings("serial")
public class Update  implements java.io.Serializable {
	/** 版本号 */
	private static final long serialVersionUID = -1253066091239324379L;

	/** ID */
	private String id;

	/** 版本号 */
	private String version;

	/** 版本号数字 */
	private Integer numVersion;

	/** 版本类型 */
	private Integer versionType;

	/** 描述 */
	private String description;

	/** 下载地址 */
	private String downloadUrl;

	/** 地址 */
	private String relevanceUrl;

	/** 创建日期 */
	private Date createdAt;

	/** 更新日期 */
	private Date updatedAt;

	/**
	 * 获取ID
	 *
	 * @return ID
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置ID
	 *
	 * @param id
	 *          ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取版本号
	 *
	 * @return 版本号
	 */
	public String getVersion() {
		return this.version;
	}

	/**
	 * 设置版本号
	 *
	 * @param version
	 *          版本号
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * 获取版本号数字
	 *
	 * @return 版本号数字
	 */
	public Integer getNumVersion() {
		return this.numVersion;
	}

	/**
	 * 设置版本号数字
	 *
	 * @param numVersion
	 *          版本号数字
	 */
	public void setNumVersion(Integer numVersion) {
		this.numVersion = numVersion;
	}

	/**
	 * 获取版本类型
	 *
	 * @return 版本类型
	 */
	public Integer getVersionType() {
		return this.versionType;
	}

	/**
	 * 设置版本类型
	 *
	 * @param versionType
	 *          版本类型
	 */
	public void setVersionType(Integer versionType) {
		this.versionType = versionType;
	}

	/**
	 * 获取描述
	 *
	 * @return 描述
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * 设置描述
	 *
	 * @param description
	 *          描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取下载地址
	 *
	 * @return 下载地址
	 */
	public String getDownloadUrl() {
		return this.downloadUrl;
	}

	/**
	 * 设置下载地址
	 *
	 * @param downloadUrl
	 *          下载地址
	 */
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	/**
	 * 获取地址
	 *
	 * @return 地址
	 */
	public String getRelevanceUrl() {
		return this.relevanceUrl;
	}

	/**
	 * 设置地址
	 *
	 * @param relevanceUrl
	 *          地址
	 */
	public void setRelevanceUrl(String relevanceUrl) {
		this.relevanceUrl = relevanceUrl;
	}

	/**
	 * 获取创建日期
	 *
	 * @return 创建日期
	 */
	public Date getCreatedAt() {
		return this.createdAt;
	}

	/**
	 * 设置创建日期
	 *
	 * @param createdAt
	 *          创建日期
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * 获取更新日期
	 *
	 * @return 更新日期
	 */
	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	/**
	 * 设置更新日期
	 *
	 * @param updatedAt
	 *          更新日期
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
