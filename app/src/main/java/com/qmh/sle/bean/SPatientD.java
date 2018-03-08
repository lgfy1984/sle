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

import java.io.Serializable;
import java.util.Date;

/**
 * T_S_PATIENT_D
 * 
 * @author bianj
 * @version 1.0.0 2018-01-22
 */
public class SPatientD implements Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 5577944215809219439L;

    /** ID */
    private String id;

    /** 病人id */
    private String stptid;

    /** seizure */
    private String seizure;

    /** psychosis */
    private String psychosis;

    /** organicBrainSyndrome */
    private String organicBrainSyndrome;

    /** visualDisturbance */
    private String visualDisturbance;

    /** cranialNerveDisorder */
    private String cranialNerveDisorder;

    /** lupusHeadache */
    private String lupusHeadache;

    /** cva */
    private String cva;

    /** fever */
    private String fever;

    /** rash */
    private String rash;

    /** alopecia */
    private String alopecia;

    /** mucosalUlcers */
    private String mucosalUlcers;

    /** arthritis */
    private String arthritis;

    /** myositis */
    private String myositis;

    /** vasculitis */
    private String vasculitis;

    /** pleurisy */
    private String pleurisy;

    /** pericarditis */
    private String pericarditis;

    /** cbp */
    private Date cbp;

    /** hb */
    private Float hb;

    /** wbc */
    private Float wbc;

    /** plt */
    private Float plt;

    /** antiDsDnaAb */
    private Date antiDsDnaAb;

    /** antiDsDnaAbValue */
    private String antiDsDnaAbValue;

    /** complement */
    private Date complement;

    /** c3 */
    private String c3;

    /** c4 */
    private String c4;

    /** urineMicroscopy */
    private Date urineMicroscopy;

    /** cast */
    private String cast;

    /** hematuria */
    private String hematuria;

    /** pyuria */
    private String pyuria;

    /** proteinuria */
    private String proteinuria;

    /** score */
    private Float score;

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
     * 获取病人id
     *
     * @return 病人id
     */
    public String getStptid() {
        return this.stptid;
    }

    /**
     * 设置病人id
     *
     * @param stptid
     *          病人id
     */
    public void setStptid(String stptid) {
        this.stptid = stptid;
    }

    /**
     * 获取seizure
     *
     * @return seizure
     */
    public String getSeizure() {
        return this.seizure;
    }

    /**
     * 设置seizure
     *
     * @param seizure
     */
    public void setSeizure(String seizure) {
        this.seizure = seizure;
    }

    /**
     * 获取psychosis
     *
     * @return psychosis
     */
    public String getPsychosis() {
        return this.psychosis;
    }

    /**
     * 设置psychosis
     *
     * @param psychosis
     */
    public void setPsychosis(String psychosis) {
        this.psychosis = psychosis;
    }

    /**
     * 获取organicBrainSyndrome
     *
     * @return organicBrainSyndrome
     */
    public String getOrganicBrainSyndrome() {
        return this.organicBrainSyndrome;
    }

    /**
     * 设置organicBrainSyndrome
     *
     * @param organicBrainSyndrome
     */
    public void setOrganicBrainSyndrome(String organicBrainSyndrome) {
        this.organicBrainSyndrome = organicBrainSyndrome;
    }

    /**
     * 获取visualDisturbance
     *
     * @return visualDisturbance
     */
    public String getVisualDisturbance() {
        return this.visualDisturbance;
    }

    /**
     * 设置visualDisturbance
     *
     * @param visualDisturbance
     */
    public void setVisualDisturbance(String visualDisturbance) {
        this.visualDisturbance = visualDisturbance;
    }

    /**
     * 获取cranialNerveDisorder
     *
     * @return cranialNerveDisorder
     */
    public String getCranialNerveDisorder() {
        return this.cranialNerveDisorder;
    }

    /**
     * 设置cranialNerveDisorder
     *
     * @param cranialNerveDisorder
     */
    public void setCranialNerveDisorder(String cranialNerveDisorder) {
        this.cranialNerveDisorder = cranialNerveDisorder;
    }

    /**
     * 获取lupusHeadache
     *
     * @return lupusHeadache
     */
    public String getLupusHeadache() {
        return this.lupusHeadache;
    }

    /**
     * 设置lupusHeadache
     *
     * @param lupusHeadache
     */
    public void setLupusHeadache(String lupusHeadache) {
        this.lupusHeadache = lupusHeadache;
    }

    /**
     * 获取cva
     *
     * @return cva
     */
    public String getCva() {
        return this.cva;
    }

    /**
     * 设置cva
     *
     * @param cva
     */
    public void setCva(String cva) {
        this.cva = cva;
    }

    /**
     * 获取fever
     *
     * @return fever
     */
    public String getFever() {
        return this.fever;
    }

    /**
     * 设置fever
     *
     * @param fever
     */
    public void setFever(String fever) {
        this.fever = fever;
    }

    /**
     * 获取rash
     *
     * @return rash
     */
    public String getRash() {
        return this.rash;
    }

    /**
     * 设置rash
     *
     * @param rash
     */
    public void setRash(String rash) {
        this.rash = rash;
    }

    /**
     * 获取alopecia
     *
     * @return alopecia
     */
    public String getAlopecia() {
        return this.alopecia;
    }

    /**
     * 设置alopecia
     *
     * @param alopecia
     */
    public void setAlopecia(String alopecia) {
        this.alopecia = alopecia;
    }

    /**
     * 获取mucosalUlcers
     *
     * @return mucosalUlcers
     */
    public String getMucosalUlcers() {
        return this.mucosalUlcers;
    }

    /**
     * 设置mucosalUlcers
     *
     * @param mucosalUlcers
     */
    public void setMucosalUlcers(String mucosalUlcers) {
        this.mucosalUlcers = mucosalUlcers;
    }

    /**
     * 获取arthritis
     *
     * @return arthritis
     */
    public String getArthritis() {
        return this.arthritis;
    }

    /**
     * 设置arthritis
     *
     * @param arthritis
     */
    public void setArthritis(String arthritis) {
        this.arthritis = arthritis;
    }

    /**
     * 获取myositis
     *
     * @return myositis
     */
    public String getMyositis() {
        return this.myositis;
    }

    /**
     * 设置myositis
     *
     * @param myositis
     */
    public void setMyositis(String myositis) {
        this.myositis = myositis;
    }

    /**
     * 获取vasculitis
     *
     * @return vasculitis
     */
    public String getVasculitis() {
        return this.vasculitis;
    }

    /**
     * 设置vasculitis
     *
     * @param vasculitis
     */
    public void setVasculitis(String vasculitis) {
        this.vasculitis = vasculitis;
    }

    /**
     * 获取pleurisy
     *
     * @return pleurisy
     */
    public String getPleurisy() {
        return this.pleurisy;
    }

    /**
     * 设置pleurisy
     *
     * @param pleurisy
     */
    public void setPleurisy(String pleurisy) {
        this.pleurisy = pleurisy;
    }

    /**
     * 获取pericarditis
     *
     * @return pericarditis
     */
    public String getPericarditis() {
        return this.pericarditis;
    }

    /**
     * 设置pericarditis
     *
     * @param pericarditis
     */
    public void setPericarditis(String pericarditis) {
        this.pericarditis = pericarditis;
    }

    /**
     * 获取cbp
     *
     * @return cbp
     */
    public Date getCbp() {
        return this.cbp;
    }

    /**
     * 设置cbp
     *
     * @param cbp
     */
    public void setCbp(Date cbp) {
        this.cbp = cbp;
    }

    /**
     * 获取hb
     *
     * @return hb
     */
    public Float getHb() {
        return this.hb;
    }

    /**
     * 设置hb
     *
     * @param hb
     */
    public void setHb(Float hb) {
        this.hb = hb;
    }

    /**
     * 获取wbc
     *
     * @return wbc
     */
    public Float getWbc() {
        return this.wbc;
    }

    /**
     * 设置wbc
     *
     * @param wbc
     */
    public void setWbc(Float wbc) {
        this.wbc = wbc;
    }

    /**
     * 获取plt
     *
     * @return plt
     */
    public Float getPlt() {
        return this.plt;
    }

    /**
     * 设置plt
     *
     * @param plt
     */
    public void setPlt(Float plt) {
        this.plt = plt;
    }

    /**
     * 获取antiDsDnaAb
     *
     * @return antiDsDnaAb
     */
    public Date getAntiDsDnaAb() {
        return this.antiDsDnaAb;
    }

    /**
     * 设置antiDsDnaAb
     *
     * @param antiDsDnaAb
     */
    public void setAntiDsDnaAb(Date antiDsDnaAb) {
        this.antiDsDnaAb = antiDsDnaAb;
    }

    /**
     * 获取antiDsDnaAbValue
     *
     * @return antiDsDnaAbValue
     */
    public String getAntiDsDnaAbValue() {
        return this.antiDsDnaAbValue;
    }

    /**
     * 设置antiDsDnaAbValue
     *
     * @param antiDsDnaAbValue
     */
    public void setAntiDsDnaAbValue(String antiDsDnaAbValue) {
        this.antiDsDnaAbValue = antiDsDnaAbValue;
    }

    /**
     * 获取complement
     *
     * @return complement
     */
    public Date getComplement() {
        return this.complement;
    }

    /**
     * 设置complement
     *
     * @param complement
     */
    public void setComplement(Date complement) {
        this.complement = complement;
    }

    /**
     * 获取c3
     *
     * @return c3
     */
    public String getC3() {
        return this.c3;
    }

    /**
     * 设置c3
     *
     * @param c3
     */
    public void setC3(String c3) {
        this.c3 = c3;
    }

    /**
     * 获取c4
     *
     * @return c4
     */
    public String getC4() {
        return this.c4;
    }

    /**
     * 设置c4
     *
     * @param c4
     */
    public void setC4(String c4) {
        this.c4 = c4;
    }

    /**
     * 获取urineMicroscopy
     *
     * @return urineMicroscopy
     */
    public Date getUrineMicroscopy() {
        return this.urineMicroscopy;
    }

    /**
     * 设置urineMicroscopy
     *
     * @param urineMicroscopy
     */
    public void setUrineMicroscopy(Date urineMicroscopy) {
        this.urineMicroscopy = urineMicroscopy;
    }

    /**
     * 获取cast
     *
     * @return cast
     */
    public String getCast() {
        return this.cast;
    }

    /**
     * 设置cast
     *
     * @param cast
     */
    public void setCast(String cast) {
        this.cast = cast;
    }

    /**
     * 获取hematuria
     *
     * @return hematuria
     */
    public String getHematuria() {
        return this.hematuria;
    }

    /**
     * 设置hematuria
     *
     * @param hematuria
     */
    public void setHematuria(String hematuria) {
        this.hematuria = hematuria;
    }

    /**
     * 获取pyuria
     *
     * @return pyuria
     */
    public String getPyuria() {
        return this.pyuria;
    }

    /**
     * 设置pyuria
     *
     * @param pyuria
     */
    public void setPyuria(String pyuria) {
        this.pyuria = pyuria;
    }

    /**
     * 获取proteinuria
     *
     * @return proteinuria
     */
    public String getProteinuria() {
        return this.proteinuria;
    }

    /**
     * 设置proteinuria
     *
     * @param proteinuria
     */
    public void setProteinuria(String proteinuria) {
        this.proteinuria = proteinuria;
    }

    /**
     * 获取score
     *
     * @return score
     */
    public Float getScore() {
        return this.score;
    }

    /**
     * 设置score
     *
     * @param score
     */
    public void setScore(Float score) {
        this.score = score;
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
}