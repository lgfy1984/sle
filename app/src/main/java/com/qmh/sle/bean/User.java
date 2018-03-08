package com.qmh.sle.bean;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 用户实体类
 * @author 火蚁(http://my.oschina.net/LittleDY)
 *
 */
@SuppressWarnings("serial")
public class User implements java.io.Serializable {
	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 4323553156891335445L;

	/**
	 * ID
	 */
	private String id;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 真实名字
	 */
	private String realname;

	/**
	 * 签名
	 */
	private Object signature;

	/**
	 * 有效状态
	 */
	private Integer status;

	/**
	 * 用户KEY
	 */
	private String userkey;

	/**
	 * 用户账号
	 */
	private String username;

	/**
	 * 部门ID
	 */
	private String departid;

	/**
	 * 删除状态
	 */
	private Integer deleteFlag;

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
	 * @param id ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取密码
	 *
	 * @return 密码
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * 设置密码
	 *
	 * @param password 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取真实名字
	 *
	 * @return 真实名字
	 */
	public String getRealname() {
		return this.realname;
	}

	/**
	 * 设置真实名字
	 *
	 * @param realname 真实名字
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}


	public Object getSignature() {
		return signature;
	}

	public void setSignature(Object signature) {
		this.signature = signature;
	}

	/**
	 * 获取有效状态
	 *
	 * @return 有效状态
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * 设置有效状态
	 *
	 * @param status 有效状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取用户KEY
	 *
	 * @return 用户KEY
	 */
	public String getUserkey() {
		return this.userkey;
	}

	/**
	 * 设置用户KEY
	 *
	 * @param userkey 用户KEY
	 */
	public void setUserkey(String userkey) {
		this.userkey = userkey;
	}

	/**
	 * 获取用户账号
	 *
	 * @return 用户账号
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * 设置用户账号
	 *
	 * @param username 用户账号
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 获取部门ID
	 *
	 * @return 部门ID
	 */
	public String getDepartid() {
		return this.departid;
	}

	/**
	 * 设置部门ID
	 *
	 * @param departid 部门ID
	 */
	public void setDepartid(String departid) {
		this.departid = departid;
	}

	/**
	 * 获取删除状态
	 *
	 * @return 删除状态
	 */
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	/**
	 * 设置删除状态
	 *
	 * @param deleteFlag 删除状态
	 */
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
