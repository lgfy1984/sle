package com.qmh.sle.bean;
/*
 * Welcome to use the TableGo Tools.
 * 
 * http://vipbooks.iteye.com
 * http://blog.csdn.net/vipbooks
 * http://www.cnblogs.com/vipbooks
 * 
 * Author:bianj
 * Email:edinsker@163.com
 * Version:5.8.0
 */

import java.util.Date;

/**
 * T_S_PATIENT
 * 
 * @author bianj
 * @version 1.0.0 2018-01-22
 */
public class SPatient implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -808614510148465326L;

    /** ID */
    private String id;

    /** 病人代码 */
    private String ptid;

    /** 病人新代码 */
    private String newptid;

    /** 生日 */
    private Date birthday;

    /** 性别id */
    private String sexId;

    /** 国家id */
    private String countryId;

    /** 民族id */
    private String nationalityId;

    /** 创建时间 */
    private Date createDate;

    /** 创建人编号 */
    private String createBy;

    /** 创建人姓名 */
    private String createName;

    /** 更新日期 */
    private Date updateDate;

    /** 更新人编号 */
    private String updateBy;

    /** 更新人姓名 */
    private String updateName;

    /** departid */
    private String departid;

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
     * 获取病人代码
     * 
     * @return 病人代码
     */
    public String getPtid() {
        return this.ptid;
    }

    /**
     * 设置病人代码
     * 
     * @param ptid
     *          病人代码
     */
    public void setPtid(String ptid) {
        this.ptid = ptid;
    }

    /**
     * 获取病人新代码
     * 
     * @return 病人新代码
     */
    public String getNewptid() {
        return this.newptid;
    }

    /**
     * 设置病人新代码
     * 
     * @param newptid
     *          病人新代码
     */
    public void setNewptid(String newptid) {
        this.newptid = newptid;
    }

    /**
     * 获取生日
     * 
     * @return 生日
     */
    public Date getBirthday() {
        return this.birthday;
    }

    /**
     * 设置生日
     * 
     * @param birthday
     *          生日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取性别id
     * 
     * @return 性别id
     */
    public String getSexId() {
        return this.sexId;
    }

    /**
     * 设置性别id
     * 
     * @param sexId
     *          性别id
     */
    public void setSexId(String sexId) {
        this.sexId = sexId;
    }

    /**
     * 获取国家id
     * 
     * @return 国家id
     */
    public String getCountryId() {
        return this.countryId;
    }

    /**
     * 设置国家id
     * 
     * @param countryId
     *          国家id
     */
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    /**
     * 获取民族id
     * 
     * @return 民族id
     */
    public String getNationalityId() {
        return this.nationalityId;
    }

    /**
     * 设置民族id
     * 
     * @param nationalityId
     *          民族id
     */
    public void setNationalityId(String nationalityId) {
        this.nationalityId = nationalityId;
    }

    /**
     * 获取创建时间
     * 
     * @return 创建时间
     */
    public Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 设置创建时间
     * 
     * @param createDate
     *          创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取创建人编号
     * 
     * @return 创建人编号
     */
    public String getCreateBy() {
        return this.createBy;
    }

    /**
     * 设置创建人编号
     * 
     * @param createBy
     *          创建人编号
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取创建人姓名
     * 
     * @return 创建人姓名
     */
    public String getCreateName() {
        return this.createName;
    }

    /**
     * 设置创建人姓名
     * 
     * @param createName
     *          创建人姓名
     */
    public void setCreateName(String createName) {
        this.createName = createName;
    }

    /**
     * 获取更新日期
     * 
     * @return 更新日期
     */
    public Date getUpdateDate() {
        return this.updateDate;
    }

    /**
     * 设置更新日期
     * 
     * @param updateDate
     *          更新日期
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取更新人编号
     * 
     * @return 更新人编号
     */
    public String getUpdateBy() {
        return this.updateBy;
    }

    /**
     * 设置更新人编号
     * 
     * @param updateBy
     *          更新人编号
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取更新人姓名
     * 
     * @return 更新人姓名
     */
    public String getUpdateName() {
        return this.updateName;
    }

    /**
     * 设置更新人姓名
     * 
     * @param updateName
     *          更新人姓名
     */
    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }
    /**
     * 获取departid
     *
     * @return departid
     */
    public String getDepartid() {
        return this.departid;
    }

    /**
     * 设置departid
     *
     * @param departid
     */
    public void setDepartid(String departid) {
        this.departid = departid;
    }
}