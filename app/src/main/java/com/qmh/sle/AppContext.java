package com.qmh.sle;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.media.AudioManager;

import com.kymjs.okhttp.OkHttpStack;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.http.RequestQueue;
import com.qmh.sle.bean.SPatient;
import com.qmh.sle.bean.SPatientD;
import com.qmh.sle.ui.MainActivity;
import com.squareup.okhttp.OkHttpClient;

import com.qmh.sle.api.AsyncHttpHelp;
import com.qmh.sle.bean.Follow;
import com.qmh.sle.bean.User;
import com.qmh.sle.common.BroadcastController;
import com.qmh.sle.common.CyptoUtils;
import com.qmh.sle.common.MethodsCompat;
import com.qmh.sle.common.StringUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.UUID;

import static com.qmh.sle.common.Contanst.ACCOUNT_EMAIL;
import static com.qmh.sle.common.Contanst.ACCOUNT_PWD;
import static com.qmh.sle.common.Contanst.PROP_KEY_BIO;
import static com.qmh.sle.common.Contanst.PROP_KEY_BIRTHDAY;
import static com.qmh.sle.common.Contanst.PROP_KEY_BLOG;
import static com.qmh.sle.common.Contanst.PROP_KEY_CAN_CREATE_GROUP;
import static com.qmh.sle.common.Contanst.PROP_KEY_CAN_CREATE_PROJECT;
import static com.qmh.sle.common.Contanst.PROP_KEY_CAN_CREATE_TEAM;
import static com.qmh.sle.common.Contanst.PROP_KEY_COMPLEMENT;
import static com.qmh.sle.common.Contanst.PROP_KEY_COUNTRYID;
import static com.qmh.sle.common.Contanst.PROP_KEY_CREATED_AT;
import static com.qmh.sle.common.Contanst.PROP_KEY_EMAIL;
import static com.qmh.sle.common.Contanst.PROP_KEY_IS_ADMIN;
import static com.qmh.sle.common.Contanst.PROP_KEY_NAME;
import static com.qmh.sle.common.Contanst.PROP_KEY_NATIONALITYID;
import static com.qmh.sle.common.Contanst.PROP_KEY_NEWPORTRAIT;
import static com.qmh.sle.common.Contanst.PROP_KEY_NEWPTID;
import static com.qmh.sle.common.Contanst.PROP_KEY_PALOPECIA;
import static com.qmh.sle.common.Contanst.PROP_KEY_PANTIDSDNAAB;
import static com.qmh.sle.common.Contanst.PROP_KEY_PANTIDSDNAAB_VALUE;
import static com.qmh.sle.common.Contanst.PROP_KEY_PARTHRITIS;
import static com.qmh.sle.common.Contanst.PROP_KEY_PC3;
import static com.qmh.sle.common.Contanst.PROP_KEY_PC4;
import static com.qmh.sle.common.Contanst.PROP_KEY_PCAST;
import static com.qmh.sle.common.Contanst.PROP_KEY_PCBP;
import static com.qmh.sle.common.Contanst.PROP_KEY_PCRANIALNERVEDISORDER;
import static com.qmh.sle.common.Contanst.PROP_KEY_PCREATEBY;
import static com.qmh.sle.common.Contanst.PROP_KEY_PCREATEDATE;
import static com.qmh.sle.common.Contanst.PROP_KEY_PCREATENAME;
import static com.qmh.sle.common.Contanst.PROP_KEY_PCVA;
import static com.qmh.sle.common.Contanst.PROP_KEY_PDCREATEBY;
import static com.qmh.sle.common.Contanst.PROP_KEY_PDCREATEDATE;
import static com.qmh.sle.common.Contanst.PROP_KEY_PDCREATENAME;
import static com.qmh.sle.common.Contanst.PROP_KEY_PDEPARTID;
import static com.qmh.sle.common.Contanst.PROP_KEY_PDID;
import static com.qmh.sle.common.Contanst.PROP_KEY_PDLIST;
import static com.qmh.sle.common.Contanst.PROP_KEY_PDUPDATEDATE;
import static com.qmh.sle.common.Contanst.PROP_KEY_PDUPDATENAME;
import static com.qmh.sle.common.Contanst.PROP_KEY_PFEVER;
import static com.qmh.sle.common.Contanst.PROP_KEY_PHB;
import static com.qmh.sle.common.Contanst.PROP_KEY_PHEMATURIA;
import static com.qmh.sle.common.Contanst.PROP_KEY_PID;
import static com.qmh.sle.common.Contanst.PROP_KEY_PLUPUSHEADACHE;
import static com.qmh.sle.common.Contanst.PROP_KEY_PMUCOSALULCERS;
import static com.qmh.sle.common.Contanst.PROP_KEY_PMYOSITIS;
import static com.qmh.sle.common.Contanst.PROP_KEY_PORGANICBRAINSYNDROME;
import static com.qmh.sle.common.Contanst.PROP_KEY_PORTRAIT;
import static com.qmh.sle.common.Contanst.PROP_KEY_PPERICARDITIS;
import static com.qmh.sle.common.Contanst.PROP_KEY_PPLEURISY;
import static com.qmh.sle.common.Contanst.PROP_KEY_PPLT;
import static com.qmh.sle.common.Contanst.PROP_KEY_PPROTEINURIA;
import static com.qmh.sle.common.Contanst.PROP_KEY_PPSYCHOSIS;
import static com.qmh.sle.common.Contanst.PROP_KEY_PPVASCULITIS;
import static com.qmh.sle.common.Contanst.PROP_KEY_PPYURIA;
import static com.qmh.sle.common.Contanst.PROP_KEY_PRASH;
import static com.qmh.sle.common.Contanst.PROP_KEY_PRIVATE_TOKEN;
import static com.qmh.sle.common.Contanst.PROP_KEY_PSCORE;
import static com.qmh.sle.common.Contanst.PROP_KEY_PSEIZURE;
import static com.qmh.sle.common.Contanst.PROP_KEY_PSTPTID;
import static com.qmh.sle.common.Contanst.PROP_KEY_PUPDATEBY;
import static com.qmh.sle.common.Contanst.PROP_KEY_PUPDATEDATE;
import static com.qmh.sle.common.Contanst.PROP_KEY_PUPDATENAME;
import static com.qmh.sle.common.Contanst.PROP_KEY_PURINEMICROSCOPY;
import static com.qmh.sle.common.Contanst.PROP_KEY_PVISUALDISTURBANCE;
import static com.qmh.sle.common.Contanst.PROP_KEY_PWBC;
import static com.qmh.sle.common.Contanst.PROP_KEY_SEXID;
import static com.qmh.sle.common.Contanst.PROP_KEY_SPTID;
import static com.qmh.sle.common.Contanst.PROP_KEY_STATE;
import static com.qmh.sle.common.Contanst.PROP_KEY_THEME_ID;
import static com.qmh.sle.common.Contanst.PROP_KEY_UDEPARTID;
import static com.qmh.sle.common.Contanst.PROP_KEY_UID;
import static com.qmh.sle.common.Contanst.PROP_KEY_USERNAME;
import static com.qmh.sle.common.Contanst.PROP_KEY_WEIBO;
import static com.qmh.sle.common.Contanst.ROP_KEY_FOLLOWERS;
import static com.qmh.sle.common.Contanst.ROP_KEY_FOLLOWING;
import static com.qmh.sle.common.Contanst.ROP_KEY_STARRED;
import static com.qmh.sle.common.Contanst.ROP_KEY_WATCHED;

/**
 * 全局应用程序类：用于保存和调用全局应用配置及访问网络数据
 *
 * @author 火蚁 (http://my.oschina.net/LittleDY)
 * @version 1.0
 * @created 2014-04-22
 */
public class AppContext extends Application {

    public static final int PAGE_SIZE = 20;// 默认分页大小

    private boolean login = false; // 登录状态
    private int loginUid = 0; // 登录用户的id

    private static AppContext appContext;

    final  DecimalFormat df = new DecimalFormat("0.00");
    @Override
    public void onCreate() {
        super.onCreate();
        // 注册App异常崩溃处理器
        File cacheFolder = getCacheDir();
        RxVolley.setRequestQueue(RequestQueue.newRequestQueue(cacheFolder, new
                OkHttpStack(new OkHttpClient())));
        init();
        appContext = this;
    }

    public static AppContext getInstance() {
        return appContext;
    }

    /**
     * 初始化Application
     */
    private void init() {
        // 初始化用记的登录信息
        User loginUser = getLoginInfo();
        if (null != loginUser && StringUtils.toInt(loginUser.getId()) > 0
                && !StringUtils.isEmpty(getProperty(PROP_KEY_PRIVATE_TOKEN))) {
            // 记录用户的id和状态
            this.loginUid = StringUtils.toInt(loginUser.getId());
            this.login = true;
        }
    }


    public void setProperties(Properties ps) {
        AppConfig.getAppConfig(this).set(ps);
    }

    public Properties getProperties() {
        return AppConfig.getAppConfig(this).get();
    }

    public void setProperty(String key, String value) {
        AppConfig.getAppConfig(this).set(key, value);
    }

    public String getProperty(String key) {
        return AppConfig.getAppConfig(this).get(key);
    }

    public void removeProperty(String... key) {
        AppConfig.getAppConfig(this).remove(key);
    }

    /**
     * 是否是第一次启动App
     */
    public boolean isFristStart() {
        boolean res = false;
        String perf_frist = getProperty(AppConfig.CONF_FRIST_START);
        // 默认是http
        if (StringUtils.isEmpty(perf_frist)) {
            res = true;
            setProperty(AppConfig.CONF_FRIST_START, "false");
        }
        return res;
    }

    /**
     * 设置是否发出提示音
     */
    public void setConfigVoice(boolean b) {
        setProperty(AppConfig.CONF_VOICE, String.valueOf(b));
    }

    /**
     * 是否启动检查更新
     */
    public boolean isCheckUp() {
        String perf_checkup = getProperty(AppConfig.CONF_CHECKUP);
        // 默认是开启
        return StringUtils.isEmpty(perf_checkup) || StringUtils.toBool(perf_checkup);
    }

    /**
     * 设置启动检查更新
     */
    public void setConfigCheckUp(boolean b) {
        setProperty(AppConfig.CONF_CHECKUP, String.valueOf(b));
    }

    /**
     * 检测当前系统声音是否为正常模式
     */
    public boolean isAudioNormal() {
        AudioManager mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        return mAudioManager.getRingerMode() == AudioManager.RINGER_MODE_NORMAL;
    }

    /**
     * 应用程序是否发出提示音
     */
    public boolean isAppSound() {
        return isAudioNormal() && isVoice();
    }


    /**
     * 设置是否开启传感器
     */
    public void setConfigSensor(boolean openSensor) {
        setProperty(AppConfig.CONF_OPEN_SENSOR, String.valueOf(openSensor));
    }

    /**
     * 是否开启传感器
     */
    public boolean isOpenSensor() {
        String perf_open_sensor = getProperty(AppConfig.CONF_OPEN_SENSOR);
        // 默认是开启
        return StringUtils.isEmpty(perf_open_sensor) || StringUtils.toBool(perf_open_sensor);
    }

    /**
     * 是否接收通知
     */
    public boolean isReceiveNotice() {
        String perf_notice = getProperty(AppConfig.CONF_RECEIVENOTICE);
        // 默认是开启提示声音
        return StringUtils.isEmpty(perf_notice) || StringUtils.toBool(perf_notice);
    }

    /**
     * 设置是否接收通知
     */
    public void setConfigReceiveNotice(boolean isReceiveNotice) {
        setProperty(AppConfig.CONF_RECEIVENOTICE, String.valueOf(isReceiveNotice));
    }

    /**
     * 是否发出提示音
     */
    public boolean isVoice() {
        String perf_voice = getProperty(AppConfig.CONF_VOICE);
        return StringUtils.isEmpty(perf_voice) || StringUtils.toBool(perf_voice);
    }

    /**
     * 判断当前版本是否兼容目标版本的方法
     */
    public static boolean isMethodsCompat(int VersionCode) {
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        return currentVersion >= VersionCode;
    }

    /**
     * 获取App唯一标识
     */
    public String getAppId() {
        String uniqueID = getProperty(AppConfig.CONF_APP_UNIQUEID);
        if (StringUtils.isEmpty(uniqueID)) {
            uniqueID = UUID.randomUUID().toString();
            setProperty(AppConfig.CONF_APP_UNIQUEID, uniqueID);
        }
        return uniqueID;
    }

    /**
     * 获取App安装包信息
     */
    public PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }

    /**
     * 获取登录信息
     */
    public User getLoginInfo() {
        User user = new User();
        user.setId(getProperty(PROP_KEY_UID));
        user.setUsername(getProperty(PROP_KEY_USERNAME));
        user.setRealname(getProperty(PROP_KEY_NAME));
        user.setDepartid(getProperty(PROP_KEY_UDEPARTID));
        user.setStatus(StringUtils.toInt(getProperty(PROP_KEY_STATE)));


        return user;
    }

    /**
     * 保存用户的email和pwd
     */
    public void saveAccountInfo(String email, String pwd) {
        setProperty(ACCOUNT_EMAIL, email);
        setProperty(ACCOUNT_PWD, pwd);
    }

    /**
     * 保存登录用户的信息
     */
    @SuppressWarnings("serial")
    public void saveLoginInfo(final User user) {
        if (null == user) {
            return;
        }
        // 保存用户的信息
        this.loginUid = StringUtils.toInt(user.getId());
        this.login = true;
        setProperties(new Properties() {
            {
                setProperty(PROP_KEY_UID, String.valueOf(user.getId()));
                setProperty(PROP_KEY_USERNAME, String.valueOf(user.getUsername()));
                setProperty(PROP_KEY_NAME, String.valueOf(user.getRealname()));
                setProperty(PROP_KEY_STATE, String.valueOf(user.getStatus()));
                setProperty(PROP_KEY_UDEPARTID, String.valueOf(user.getDepartid()));

            }
        });
    }
    public SPatientD getPatientD() {
        SPatientD sPatientD = new SPatientD();
        sPatientD.setId(getProperty(PROP_KEY_PDID));
        sPatientD.setStptid(getProperty(PROP_KEY_PSTPTID));
        sPatientD.setSeizure(getProperty(PROP_KEY_PSEIZURE));
        sPatientD.setPsychosis(getProperty(PROP_KEY_PPSYCHOSIS));
        sPatientD.setOrganicBrainSyndrome(getProperty(PROP_KEY_PORGANICBRAINSYNDROME));
        sPatientD.setVisualDisturbance(getProperty(PROP_KEY_PVISUALDISTURBANCE));
        sPatientD.setCranialNerveDisorder(getProperty(PROP_KEY_PCRANIALNERVEDISORDER));
        sPatientD.setLupusHeadache(getProperty(PROP_KEY_PLUPUSHEADACHE));
        sPatientD.setCva(getProperty(PROP_KEY_PCVA));
        sPatientD.setFever(getProperty(PROP_KEY_PFEVER));
        sPatientD.setRash(getProperty(PROP_KEY_PRASH));
        sPatientD.setAlopecia(getProperty(PROP_KEY_PALOPECIA));
        sPatientD.setMucosalUlcers(getProperty(PROP_KEY_PMUCOSALULCERS));
        sPatientD.setArthritis(getProperty(PROP_KEY_PARTHRITIS));
        sPatientD.setMyositis(getProperty(PROP_KEY_PMYOSITIS));
        sPatientD.setVasculitis(getProperty(PROP_KEY_PPVASCULITIS));
        sPatientD.setPleurisy(getProperty(PROP_KEY_PPLEURISY));
        sPatientD.setPericarditis(getProperty(PROP_KEY_PPERICARDITIS));
        sPatientD.setCbp(StringUtils.toDate(getProperty(PROP_KEY_PCBP)));
        sPatientD.setHb(StringUtils.toFloat(getProperty(PROP_KEY_PHB)));
        sPatientD.setWbc(StringUtils.toFloat(getProperty(PROP_KEY_PWBC)));
        sPatientD.setPlt(StringUtils.toFloat(getProperty(PROP_KEY_PPLT)));
        sPatientD.setAntiDsDnaAb(StringUtils.toDate(getProperty(PROP_KEY_PANTIDSDNAAB)));
        sPatientD.setAntiDsDnaAbValue(getProperty(PROP_KEY_PANTIDSDNAAB_VALUE));
        sPatientD.setComplement(StringUtils.toDate(getProperty(PROP_KEY_COMPLEMENT)));
        sPatientD.setC3(getProperty(PROP_KEY_PC3));
        sPatientD.setC4(getProperty(PROP_KEY_PC4));
        sPatientD.setUrineMicroscopy(StringUtils.toDate(getProperty(PROP_KEY_PURINEMICROSCOPY)));
        sPatientD.setCast(getProperty(PROP_KEY_PCAST));
        sPatientD.setHematuria(getProperty(PROP_KEY_PHEMATURIA));
        sPatientD.setPyuria(getProperty(PROP_KEY_PPYURIA));
        sPatientD.setProteinuria(getProperty(PROP_KEY_PPROTEINURIA));
        sPatientD.setScore(StringUtils.toFloat(getProperty(PROP_KEY_PSCORE)));
        sPatientD.setCreateDate(StringUtils.toDate(getProperty(PROP_KEY_PDCREATEDATE)));
        sPatientD.setCreateBy(getProperty(PROP_KEY_PDCREATEBY));
        sPatientD.setCreateName(getProperty(PROP_KEY_PDCREATENAME));
        sPatientD.setUpdateDate(StringUtils.toDate(getProperty(PROP_KEY_PDUPDATEDATE)));
        sPatientD.setUpdateName(getProperty(PROP_KEY_PDUPDATENAME));
        return sPatientD;
    }
    @SuppressWarnings("serial")
    public void savePatientD(final SPatientD sPatientD) {
        if (null == sPatientD) {
            return;
        }
        setProperties(new Properties() {
            {
                setProperty(PROP_KEY_PDID, String.valueOf(sPatientD.getId()));
                setProperty(PROP_KEY_PSTPTID, String.valueOf(sPatientD.getStptid()));
                setProperty(PROP_KEY_PSEIZURE, String.valueOf(sPatientD.getSeizure()));
                setProperty(PROP_KEY_PPSYCHOSIS, String.valueOf(sPatientD.getPsychosis()));
                setProperty(PROP_KEY_PORGANICBRAINSYNDROME, String.valueOf(sPatientD.getOrganicBrainSyndrome()));
                setProperty(PROP_KEY_PVISUALDISTURBANCE, String.valueOf(sPatientD.getVisualDisturbance()));
                setProperty(PROP_KEY_PCRANIALNERVEDISORDER, String.valueOf(sPatientD.getCranialNerveDisorder()));
                setProperty(PROP_KEY_PLUPUSHEADACHE, String.valueOf(sPatientD.getLupusHeadache()));
                setProperty(PROP_KEY_PCVA, String.valueOf(sPatientD.getCva()));
                setProperty(PROP_KEY_PFEVER, String.valueOf(sPatientD.getFever()));
                setProperty(PROP_KEY_PRASH, String.valueOf(sPatientD.getRash()));
                setProperty(PROP_KEY_PALOPECIA, String.valueOf(sPatientD.getAlopecia()));
                setProperty(PROP_KEY_PMUCOSALULCERS, String.valueOf(sPatientD.getMucosalUlcers()));
                setProperty(PROP_KEY_PARTHRITIS, String.valueOf(sPatientD.getArthritis()));
                setProperty(PROP_KEY_PMYOSITIS, String.valueOf(sPatientD.getMyositis()));
                setProperty(PROP_KEY_PPVASCULITIS, String.valueOf(sPatientD.getVasculitis()));
                setProperty(PROP_KEY_PPLEURISY, String.valueOf(sPatientD.getPleurisy()));
                setProperty(PROP_KEY_PPERICARDITIS, String.valueOf(sPatientD.getPericarditis()));
                setProperty(PROP_KEY_PCBP, StringUtils.datetoStringSS(sPatientD.getCbp()));
                setProperty(PROP_KEY_PHB, sPatientD.getHb()==null?"":df.format(sPatientD.getHb()));
                setProperty(PROP_KEY_PWBC, sPatientD.getWbc()==null?"":df.format(sPatientD.getWbc()));
                setProperty(PROP_KEY_PPLT, sPatientD.getPlt()==null?"":df.format(sPatientD.getPlt()));
                setProperty(PROP_KEY_PANTIDSDNAAB, StringUtils.datetoStringSS(sPatientD.getAntiDsDnaAb()));
                setProperty(PROP_KEY_PANTIDSDNAAB_VALUE, String.valueOf(sPatientD.getAntiDsDnaAbValue()));
                setProperty(PROP_KEY_COMPLEMENT, StringUtils.datetoStringSS(sPatientD.getComplement()));
                setProperty(PROP_KEY_PC3, String.valueOf(sPatientD.getC3()));
                setProperty(PROP_KEY_PC4, String.valueOf(sPatientD.getC4()));
                setProperty(PROP_KEY_PURINEMICROSCOPY, StringUtils.datetoStringSS(sPatientD.getUrineMicroscopy()));
                setProperty(PROP_KEY_PCAST, String.valueOf(sPatientD.getCast()));
                setProperty(PROP_KEY_PHEMATURIA, String.valueOf(sPatientD.getHematuria()));
                setProperty(PROP_KEY_PPYURIA, String.valueOf(sPatientD.getPyuria()));
                setProperty(PROP_KEY_PPROTEINURIA, String.valueOf(sPatientD.getProteinuria()));
                setProperty(PROP_KEY_PSCORE, String.valueOf(sPatientD.getScore()));
                setProperty(PROP_KEY_PDCREATEDATE, StringUtils.datetoStringSS(sPatientD.getCreateDate()));
                setProperty(PROP_KEY_PDCREATEBY, String.valueOf(sPatientD.getCreateBy()));
                setProperty(PROP_KEY_PDCREATENAME, String.valueOf(sPatientD.getCreateName()));
                setProperty(PROP_KEY_PDUPDATEDATE, StringUtils.datetoStringSS(sPatientD.getUpdateDate()));
                setProperty(PROP_KEY_PDUPDATENAME, String.valueOf(sPatientD.getUpdateName()));
            }
        });
    }

    public SPatient getPatient() {
        SPatient sPatient = new SPatient();
        sPatient.setId(getProperty(PROP_KEY_PID));
        sPatient.setPtid(getProperty(PROP_KEY_SPTID));
        sPatient.setNewptid(getProperty(PROP_KEY_NEWPTID));
        sPatient.setBirthday(StringUtils.toDate(getProperty(PROP_KEY_BIRTHDAY)));
        sPatient.setSexId(getProperty(PROP_KEY_SEXID));
        sPatient.setCountryId(getProperty(PROP_KEY_COUNTRYID));
        sPatient.setNationalityId(getProperty(PROP_KEY_NATIONALITYID));
        sPatient.setCreateDate(StringUtils.toDate(getProperty(PROP_KEY_PCREATEDATE)));
        sPatient.setCreateBy(getProperty(PROP_KEY_PCREATEBY));
        sPatient.setCreateName(getProperty(PROP_KEY_PCREATENAME));
        sPatient.setUpdateDate(StringUtils.toDate(getProperty(PROP_KEY_PUPDATEDATE)));
        sPatient.setDepartid(getProperty(PROP_KEY_PDEPARTID));
        return sPatient;
    }
    @SuppressWarnings("serial")
    public void savePatient(final SPatient sPatient) {
        if (null == sPatient) {
            return;
        }
        setProperties(new Properties() {
            {
                setProperty(PROP_KEY_PID, String.valueOf(sPatient.getId()));
                setProperty(PROP_KEY_SPTID, String.valueOf(sPatient.getPtid()));
                setProperty(PROP_KEY_NEWPTID, String.valueOf(sPatient.getNewptid()));
                setProperty(PROP_KEY_BIRTHDAY, StringUtils.datetoString(sPatient.getBirthday()));
                setProperty(PROP_KEY_SEXID, String.valueOf(sPatient.getSexId()));
                setProperty(PROP_KEY_NATIONALITYID, String.valueOf(sPatient.getNationalityId()));
                setProperty(PROP_KEY_COUNTRYID, String.valueOf(sPatient.getCountryId()));
                setProperty(PROP_KEY_PCREATEDATE, StringUtils.datetoStringSS(sPatient.getCreateDate()));
                setProperty(PROP_KEY_PCREATEBY, String.valueOf(sPatient.getCreateBy()));
                setProperty(PROP_KEY_PCREATENAME, String.valueOf(sPatient.getCreateName()));
                setProperty(PROP_KEY_PUPDATEDATE, String.valueOf(sPatient.getUpdateDate()));
                setProperty(PROP_KEY_PUPDATENAME, String.valueOf(sPatient.getUpdateName()));
                setProperty(PROP_KEY_PDEPARTID, String.valueOf(sPatient.getDepartid()));
            }
        });
    }
    /**
     * 清除登录信息，用户的私有token也一并清除
     */
    private void cleanLoginInfo() {
        this.loginUid = 0;
        this.login = false;
        removeProperty(PROP_KEY_PRIVATE_TOKEN, PROP_KEY_UID, PROP_KEY_USERNAME, PROP_KEY_EMAIL,
                PROP_KEY_NAME, PROP_KEY_BIO, PROP_KEY_WEIBO, PROP_KEY_BLOG,
                PROP_KEY_THEME_ID, PROP_KEY_STATE, PROP_KEY_CREATED_AT,PROP_KEY_UDEPARTID,
                PROP_KEY_PORTRAIT, PROP_KEY_IS_ADMIN,
                PROP_KEY_CAN_CREATE_GROUP, PROP_KEY_CAN_CREATE_PROJECT,
                PROP_KEY_CAN_CREATE_TEAM, ROP_KEY_FOLLOWERS, ROP_KEY_STARRED,
                ROP_KEY_FOLLOWING, ROP_KEY_WATCHED,PROP_KEY_PDLIST);

    }

    /**
     * 用户是否登录
     */
    public boolean isLogin() {
        return login;
    }

    /**
     * 获取登录用户id
     */
    public int getLoginUid() {
        return this.loginUid;
    }

    /**
     * 用户注销
     */
    public void logout() {
        // 清除已登录用户的信息
        cleanLoginInfo();
        clearPatientD();
        this.login = false;
        this.loginUid = 0;
        // 发送广播通知
        BroadcastController.sendUserChangeBroadcase(this);
    }

    public void clearPatientD() {
        // 清除PATIENTD
        removeProperty(PROP_KEY_PDID,PROP_KEY_PSTPTID,PROP_KEY_PSEIZURE,PROP_KEY_PPSYCHOSIS,PROP_KEY_PORGANICBRAINSYNDROME,PROP_KEY_PVISUALDISTURBANCE,
                PROP_KEY_PCRANIALNERVEDISORDER,PROP_KEY_PLUPUSHEADACHE,PROP_KEY_PCVA,PROP_KEY_PFEVER,PROP_KEY_PRASH,PROP_KEY_PALOPECIA,PROP_KEY_PMUCOSALULCERS,
                PROP_KEY_PARTHRITIS,PROP_KEY_PMYOSITIS,PROP_KEY_PPVASCULITIS,PROP_KEY_PPLEURISY,PROP_KEY_PPERICARDITIS,PROP_KEY_PCBP,PROP_KEY_PHB,PROP_KEY_PWBC,
                PROP_KEY_PPLT,PROP_KEY_PANTIDSDNAAB,PROP_KEY_PANTIDSDNAAB_VALUE,PROP_KEY_COMPLEMENT,PROP_KEY_PC3,PROP_KEY_PC4,PROP_KEY_PURINEMICROSCOPY,PROP_KEY_PCAST,
                PROP_KEY_PHEMATURIA,PROP_KEY_PPYURIA,PROP_KEY_PPROTEINURIA,PROP_KEY_PSCORE,PROP_KEY_PCREATEDATE,PROP_KEY_PCREATEBY,PROP_KEY_PCREATENAME,PROP_KEY_PUPDATEDATE,PROP_KEY_PUPDATENAME);
    }
    /**
     * 清除app缓存
     */
    public void clearAppCache() {
        deleteDatabase("webview.db");
        deleteDatabase("webview.db-shm");
        deleteDatabase("webview.db-wal");
        deleteDatabase("webviewCache.db");
        deleteDatabase("webviewCache.db-shm");
        deleteDatabase("webviewCache.db-wal");
        // 清除数据缓存
        clearCacheFolder(getFilesDir(), System.currentTimeMillis());
        clearCacheFolder(getCacheDir(), System.currentTimeMillis());
        // 2.2版本才有将应用缓存转移到sd卡的功能
        if (isMethodsCompat(android.os.Build.VERSION_CODES.FROYO)) {
            clearCacheFolder(MethodsCompat.getExternalCacheDir(this),
                    System.currentTimeMillis());
        }
        // 清除编辑器保存的临时内容
        Properties props = getProperties();
        for (Object key : props.keySet()) {
            String _key = key.toString();
            if (_key.startsWith("temp"))
                removeProperty(_key);
        }
    }


    public byte[] getPdlist() {
        try {
        return getProperty(PROP_KEY_PDLIST).getBytes("UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void savePtlist(final byte[] bs) {

        setProperties(new Properties() {
            {
                try {
                    setProperty(PROP_KEY_PDLIST, new String(bs,"UTF8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    /**
     * 清除缓存目录
     *
     * @param dir     目录
     * @param curTime 当前系统时间
     */
    private int clearCacheFolder(File dir, long curTime) {
        int deletedFiles = 0;
        if (dir != null && dir.isDirectory()) {
            try {
                for (File child : dir.listFiles()) {
                    if (child.isDirectory()) {
                        deletedFiles += clearCacheFolder(child, curTime);
                    }
                    if (child.lastModified() < curTime) {
                        if (child.delete()) {
                            deletedFiles++;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deletedFiles;
    }

    public static String getToken() {
        String private_token = AppContext.getInstance().getProperty(AsyncHttpHelp.PRIVATE_TOKEN);
        private_token = CyptoUtils.decode(AsyncHttpHelp.GITOSC_PRIVATE_TOKEN, private_token);
        return private_token;
    }
}
