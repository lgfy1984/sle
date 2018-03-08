package com.qmh.sle.common;

import java.util.Date;

/**
 * 常量类
 * @created 2014-05-28
 * @author 火蚁
 *
 */
public class Contanst {
	
	public static final String CHARSET_UTF8 = "UTF-8";
	public static final String ACCOUNT_EMAIL = "user.email";
	public static final String ACCOUNT_PWD = "user.pwd";
	
	public static final String PROP_KEY_PRIVATE_TOKEN = "private_token";
	public static final String PROP_KEY_UID = "user.uid";
	public static final String PROP_KEY_USERNAME = "user.username";
	public static final String PROP_KEY_EMAIL = "user.useremail";
	public static final String PROP_KEY_NAME = "user.name";
	public static final String PROP_KEY_DEPARTID = "user.departid";
	public static final String PROP_KEY_BIO = "user.bio";// 个人介绍
	public static final String PROP_KEY_WEIBO = "user.weibo";
	public static final String PROP_KEY_BLOG = "user.blog";
	public static final String PROP_KEY_THEME_ID = "user.theme_id";
	public static final String PROP_KEY_STATE = "user.state";
	public static final String PROP_KEY_CREATED_AT = "user.created_at";
	public static final String PROP_KEY_PORTRAIT = "user.portrait";// 用户头像-文件名
	public static final String PROP_KEY_NEWPORTRAIT = "user.new_portrait";// 用户头像-文件名
	public static final String PROP_KEY_IS_ADMIN = "user.is_admin";
	public static final String PROP_KEY_CAN_CREATE_GROUP = "user.can_create_group";
	public static final String PROP_KEY_CAN_CREATE_PROJECT = "user.can_create_project";
	public static final String PROP_KEY_CAN_CREATE_TEAM = "user.can_create_team";
	public static final String ROP_KEY_FOLLOWERS = "followers";
	public static final String ROP_KEY_STARRED = "starred";
	public static final String ROP_KEY_FOLLOWING = "following";
	public static final String ROP_KEY_WATCHED = "watched";

	public static final String PROP_KEY_PDLIST="patientd.list";
	/** ID */
	public static final String PROP_KEY_PID="patient.pid";
	/** PDID */
	public static final String PROP_KEY_PDID="patient.pdid";
	/** 病人id */
	public static final String PROP_KEY_PSTPTID="patient.stpid";

	/** seizure */
	public static final String PROP_KEY_PSEIZURE="patient.seizure";

	/** psychosis */
	public static final String PROP_KEY_PPSYCHOSIS="patient.psychosis";

	/** organicBrainSyndrome */
	public static final String PROP_KEY_PORGANICBRAINSYNDROME="patient.organicbrainsyndrome";

	/** visualDisturbance */
	public static final String PROP_KEY_PVISUALDISTURBANCE="patient.visualdisturbance";

	/** cranialNerveDisorder */
	public static final String PROP_KEY_PCRANIALNERVEDISORDER="patient.cranialnervedisorder";

	/** lupusHeadache */
	public static final String PROP_KEY_PLUPUSHEADACHE="patient.lupusheadache";

	/** cva */
	public static final String PROP_KEY_PCVA="patient.cva";

	/** fever */
	public static final String PROP_KEY_PFEVER="patient.fever";

	/** rash */
	public static final String PROP_KEY_PRASH="patient.rash";

	/** alopecia */
	public static final String PROP_KEY_PALOPECIA="patient.alopecia";

	/** mucosalUlcers */
	public static final String PROP_KEY_PMUCOSALULCERS="patient.mucosalulcers";

	/** arthritis */
	public static final String PROP_KEY_PARTHRITIS="patient.arthritis";

	/** myositis */
	public static final String PROP_KEY_PMYOSITIS="patient.myositis";

	/** vasculitis */
	public static final String PROP_KEY_PPVASCULITIS="patient.vasculitis";

	/** pleurisy */
	public static final String PROP_KEY_PPLEURISY="patient.pleurisy";

	/** pericarditis */
	public static final String PROP_KEY_PPERICARDITIS="patient.pericarditis";

	/** cbp */
	public static final String PROP_KEY_PCBP="patient.cbp";

	/** hb */
	public static final String PROP_KEY_PHB="patient.hb";

	/** wbc */
	public static final String PROP_KEY_PWBC="patient.wbc";

	/** plt */
	public static final String PROP_KEY_PPLT="patient.plt";

	/** antiDsDnaAb */
	public static final String PROP_KEY_PANTIDSDNAAB="patient.antidsdnaab";
	/** antiDsDnaAbValue */
	public static final String PROP_KEY_PANTIDSDNAAB_VALUE="patient.antidsdnaabvalue";


	/** complement */
	public static final String PROP_KEY_COMPLEMENT="patient.complement";

	/** c3 */
	public static final String PROP_KEY_PC3="patient.c3";

	/** c4 */
	public static final String PROP_KEY_PC4="patient.c4";

	/** urineMicroscopy */
	public static final String PROP_KEY_PURINEMICROSCOPY="patient.urinemicroscopy";

	/** cast */
	public static final String PROP_KEY_PCAST="patient.cast";

	/** hematuria */
	public static final String PROP_KEY_PHEMATURIA="patient.hematuria";

	/** pyuria */
	public static final String PROP_KEY_PPYURIA="patient.pyuria";

	/** proteinuria */
	public static final String PROP_KEY_PPROTEINURIA="patient.proteinuria";

	/** score */
	public static final String PROP_KEY_PSCORE="patient.score";


	/** PATIENTD创建时间 */
	public static final String PROP_KEY_PDCREATEDATE="patientd.createdate";

	/** PATIENTD创建人编号 */
	public static final String PROP_KEY_PDCREATEBY="patientd.createby";

	/** PATIENTD创建人姓名 */
	public static final String PROP_KEY_PDCREATENAME="patientd.createname";

	/** PATIENTD更新日期 */
	public static final String PROP_KEY_PDUPDATEDATE="patientd.updatedate";

	/** PATIENTD更新人编号 */
	public static final String PROP_KEY_PDUPDATEBY="patientd.updateby";

	/** 更新人姓名 */
	public static final String PROP_KEY_PDUPDATENAME="patientd.updatename";

	/** 创建时间 */
	public static final String PROP_KEY_PCREATEDATE="patient.createdate";

	/** 创建人编号 */
	public static final String PROP_KEY_PCREATEBY="patient.createby";

	/** 创建人姓名 */
	public static final String PROP_KEY_PCREATENAME="patient.createname";

	/** 更新日期 */
	public static final String PROP_KEY_PUPDATEDATE="patient.updatedate";

	/** 更新人编号 */
	public static final String PROP_KEY_PUPDATEBY="patient.updateby";

	/** 更新人姓名 */
	public static final String PROP_KEY_PDEPARTID="patient.departid";

	/** 更新人姓名 */
	public static final String PROP_KEY_PUPDATENAME="patient.updatename";





	/** 病人原id */
	public static final String PROP_KEY_SPTID="patient.sptid";
	/** 病人新id */
	public static final String PROP_KEY_NEWPTID="patient.newptid";
	/** 性别 */
	public static final String PROP_KEY_SEXID="patient.sexid";
	/** 国籍 */
	public static final String PROP_KEY_COUNTRYID="patient.countryid";
	/** 民族 */
	public static final String PROP_KEY_NATIONALITYID="patient.nationalityid";
	/** 生日*/
	public static final String PROP_KEY_BIRTHDAY="patient.birthday";

	public static final String PATIENTD= "patientd";
	public static final String PATIENT = "patient";
	
	public static final String PROJECT = "project";
	
	public static final String BRANCH = "branch";
	
	public static final String PATH = "path";
	
	public static final String PROJECTID = "project_id";

	public static final String PATIENTID = "patient_id";

	public static final String PATIENTFlAG = "patientflag";

	public static final String DEPARTID = "departid";

	
	public static final String COMMIT = "commit";
	
	public static final String COMMITID = "commit_id";
	
	public static final String COMMITDIFF = "commitDiff";
	
	public static final String ISSUE = "issue";
	
	public static final String ISSUEID = "issue_id";
	
	public static final String COMMENT = "comment";
	
	public static final String CURRENTITEM = "currentItem";
	
	public static final String USER = "user";
	
	public static final String USERID = "user_id";
	
	public static final int LOGIN_REQUESTCODE = 6168;
	
	public static final String IMG_URL = "img_url";
	
	public static final String CODE_FILE = "code_file";
	
}
