package com.qmh.sle.ui.baseactivity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;

import com.qmh.sle.AppContext;
import com.qmh.sle.common.Contanst;
import com.qmh.sle.utils.Store;
import com.umeng.analytics.MobclickAgent;

import com.qmh.sle.AppManager;
import com.qmh.sle.common.StringUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;

public abstract class BaseActivity extends AppCompatActivity {

    // 是否可以返回
    protected static boolean isCanBack;

    private AppContext mContext;
    protected ActionBar mActionBar;

    protected String mTitle;

    protected String mSubTitle;
    private boolean isDestroy;
    protected Typeface mTfRegular;
    protected Typeface mTfLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = (AppContext) getApplicationContext();

        mTfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");

        EventBus.getDefault().register(this);
        changeAppLanguage();
        initActionBar();
        //将activity加入到AppManager堆栈中
        AppManager.getAppManager().addActivity(this);
    }

    // 关闭该Activity
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    // 初始化ActionBar
    private void initActionBar() {
        mActionBar = getSupportActionBar();
        int flags = ActionBar.DISPLAY_HOME_AS_UP;
        int change = mActionBar.getDisplayOptions() ^ flags;
        // 设置返回的图标
        mActionBar.setDisplayOptions(change, flags);
        if (mTitle != null && !StringUtils.isEmpty(mTitle)) {
            mActionBar.setTitle(mTitle);
        }
        if (mSubTitle != null && !StringUtils.isEmpty(mSubTitle)) {
            mActionBar.setSubtitle(mSubTitle);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String str) {
        switch (str) {
            case "EVENT_REFRESH_LANGUAGE":
                changeAppLanguage();
                recreate();//刷新界面
                break;
        }
    }
    public void changeAppLanguage() {
        String sta =Store.getLanguageLocal(mContext);//这是SharedPreferences工具类，用于保存设置，代码很简单，自己实现吧
        if (StringUtils.isEmpty(sta)){
             sta="en-US";
        }
        // 本地语言设置
        Locale myLocale = new Locale(sta);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

    public void setActionBarTitle(String title) {
        mActionBar.setTitle(title);
    }

    public void setActionBarSubTitle(String subTitle) {
        mActionBar.setSubtitle(subTitle);
    }

    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        //setOverflowIconVisible(featureId, menu);
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        isDestroy = true;
        AppManager.getAppManager().removeActivity(this);
    }

    protected boolean isDestroy() {
        return isDestroy;
    }
}
