package com.qmh.sle.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kymjs.core.bitmap.client.BitmapCore;

import com.qmh.sle.AppContext;
import com.qmh.sle.R;
import com.qmh.sle.bean.User;
import com.qmh.sle.common.BroadcastController;
import com.qmh.sle.common.StringUtils;
import com.qmh.sle.interfaces.DrawerMenuCallBack;
import com.qmh.sle.widget.CircleImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 导航菜单栏
 *
 * @author 火蚁（http://my.oschina.net/LittleDY）
 * @created 2014-04-29
 */
public class DrawerNavigationMenu extends Fragment implements OnClickListener {

    @InjectView(R.id.iv_portrait)
    CircleImageView ivPortrait;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.menu_user_info_layout)
    LinearLayout menuUserInfoLayout;
    @InjectView(R.id.menu_user_info_login_tips_layout)
    LinearLayout menuUserInfoLoginTipsLayout;

//    @InjectView(R.id.menu_item_myself)
//    LinearLayout menuItemMyself;
    @InjectView(R.id.menu_item_language)
    LinearLayout menuItemLanguage;
    @InjectView(R.id.menu_item_password)
    LinearLayout menuItemPassword;
    @InjectView(R.id.menu_item_setting)
    LinearLayout menuItemSetting;

    public static DrawerNavigationMenu newInstance() {
        return new DrawerNavigationMenu();
    }

    private View mSavedView;// 当前操作的菜单项

    private DrawerMenuCallBack mCallBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof DrawerMenuCallBack) {

            mCallBack = (DrawerMenuCallBack) activity;
        }
        // 注册一个用户发生变化的广播
        BroadcastController.registerUserChangeReceiver(activity, mUserChangeReceiver);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallBack = null;
        // 注销接收用户信息变更的广播
        BroadcastController.unregisterReceiver(getActivity(), mUserChangeReceiver);
    }

    private BroadcastReceiver mUserChangeReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            //接收到变化后，更新用户资料
            setupUserView(true);
        }
    };

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setupUserView(AppContext.getInstance().isLogin());
    }

    private void setupUserView(final boolean reflash) {
        //判断是否已经登录，如果已登录则显示用户的头像与信息
//        if (!AppContext.getInstance().isLogin()) {
//            ivPortrait.setImageResource(R.drawable.mini_avatar);
//            tvName.setText("");
//            menuUserInfoLayout.setVisibility(View.GONE);
//            menuUserInfoLoginTipsLayout.setVisibility(View.VISIBLE);
//            return;
//        }
        ivPortrait.setImageResource(R.drawable.mini_avatar);
            tvName.setText("");
            menuUserInfoLayout.setVisibility(View.GONE);
            menuUserInfoLoginTipsLayout.setVisibility(View.VISIBLE);
        menuUserInfoLayout.setVisibility(View.VISIBLE);
        menuUserInfoLoginTipsLayout.setVisibility(View.GONE);
        tvName.setText("");

        User user = AppContext.getInstance().getLoginInfo();

//        // 加载用户头像
//        String portrait = user.getNew_portrait() == null || user.getNew_portrait().equals("null")
//                ? "" : user.getNew_portrait();
//        if (portrait.endsWith("portrait.gif") || StringUtils.isEmpty(portrait)) {
//            ivPortrait.setImageResource(R.drawable.mini_avatar);
//        } else {
//            new BitmapCore.Builder().url(user.getNew_portrait()).view(ivPortrait).doTask();
//        }
//        // 其他资料
        tvName.setText(user.getRealname());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_drawer_menu, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initBadgeView(View view) {
    }

    // 初始化界面控件
    private void initView(View view) {
        // 高亮发现菜单栏
        highlightSelectedItem(menuItemSetting);
        initBadgeView(view);
    }

    private void highlightSelectedItem(View v) {
        setSelected(null, false);
        setSelected(v, true);
    }

    public void highlightExplore() {
        highlightSelectedItem(menuItemSetting);
    }

    private void setSelected(View v, boolean selected) {
        View view;

        if (v == null && mSavedView == null) {
            return;
        }

        if (v != null) {
            mSavedView = v;
            view = mSavedView;

        } else {
            view = mSavedView;
        }

        if (selected) {
            ViewCompat.setHasTransientState(view, true);
            view.setBackgroundColor(getResources()
                    .getColor(R.color.menu_layout_item_pressed_color));

        } else {
            ViewCompat.setHasTransientState(view, false);
            view.setBackgroundResource(R.drawable.menu_layout_item_selector);
        }
    }

    @Override
    @OnClick({R.id.menu_user_layout, R.id.menu_item_language,
            R.id.menu_item_feedback,R.id.menu_item_password, R.id.menu_item_setting})
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.menu_user_layout:
                onClickLogin();
                break;
//            case R.id.menu_item_myself:
//                onClickMySelf();
//                if (AppContext.getInstance().isLogin()) {
//                    highlightSelectedItem(v);
//                }
//                break;
            case R.id.menu_item_language:
                onClickLanguage();
                highlightSelectedItem(v);
                break;
            case R.id.menu_item_password:
                onClickPassWord();
                highlightSelectedItem(v);
                break;
            case R.id.menu_item_setting:
                onClickSetting();
                highlightSelectedItem(v);
                break;
            case R.id.menu_item_feedback:
                onClickFeedback();
                highlightSelectedItem(v);
            default:
                break;
        }
    }

    private void onClickFeedback() {
        if (mCallBack!=null){
            mCallBack.onClickFeedback();
        }
    }

    private void onClickLogin() {
        if (mCallBack != null) {
            mCallBack.onClickLogin();
        }
    }

    private void onClickSetting() {
        if (mCallBack != null) {
            mCallBack.onClickSetting();
        }
    }

    private void onClickExplore() {
        if (mCallBack != null) {
            mCallBack.onClickExplore();
        }
    }

    private void onClickMySelf() {
        if (mCallBack != null) {
            mCallBack.onClickMySelf();
        }
    }

    private void onClickLanguage() {
        if (mCallBack != null) {
            mCallBack.onClickLanguage();
        }
    }

    private void onClickShake() {
        if (mCallBack != null) {
            mCallBack.onClickShake();
        }
    }

    private void onClickPassWord() {
        if (mCallBack != null) {
            mCallBack.onClickPassWord();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
