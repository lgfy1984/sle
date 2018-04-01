package com.qmh.sle.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.kymjs.rxvolley.client.HttpCallback;

import com.qmh.sle.AppContext;
import com.qmh.sle.AppManager;
import com.qmh.sle.R;
import com.qmh.sle.api.AsyncHttpHelp;
import com.qmh.sle.api.SleApi;
import com.qmh.sle.bean.Session;
import com.qmh.sle.common.BroadcastController;
import com.qmh.sle.common.Contanst;
import com.qmh.sle.common.CyptoUtils;
import com.qmh.sle.common.StringUtils;
import com.qmh.sle.common.UIHelper;
import com.qmh.sle.ui.baseactivity.BaseActivity;
import com.qmh.sle.utils.JsonUtils;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class LoginActivity extends BaseActivity
        implements OnClickListener, OnEditorActionListener, TextWatcher {

    @InjectView(R.id.et_account)
    AutoCompleteTextView etAccount;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.bt_login)
    Button btLogin;
    private ProgressDialog mLoginProgressDialog;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        btLogin.setOnClickListener(this);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        // 添加文本变化监听事件
        etAccount.addTextChangedListener(this);
        etPassword.addTextChangedListener(this);
        etPassword.setOnEditorActionListener(this);

        String account = CyptoUtils.decode(Contanst.ACCOUNT_EMAIL, AppContext.getInstance()
                .getProperty(Contanst.ACCOUNT_EMAIL));
        etAccount.setText(account);
        String pwd = CyptoUtils.decode(Contanst.ACCOUNT_PWD, AppContext.getInstance().getProperty
                (Contanst.ACCOUNT_PWD));
        etPassword.setText(pwd);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        //在输入法里点击了“完成”，则去登录
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            checkLogin();
            //将输入法隐藏
            InputMethodManager imm = (InputMethodManager) getSystemService(
                    INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(etPassword.getWindowToken(), 0);
            return true;
        }
        return false;
    }

    /**
     * 检查登录
     */
    private void checkLogin() {
        String email = etAccount.getText().toString();
        String passwd = etPassword.getText().toString();

        //检查用户输入的参数
        if (StringUtils.isEmpty(email)) {
            UIHelper.toastMessage(this, getString(R.string.msg_login_email_null));
            return;
        }
//        if (!StringUtils.isEmail(email)) {
//            UIHelper.toastMessage(this, getString(R.string.msg_login_email_error));
//            return;
//        }
        if (StringUtils.isEmpty(passwd)) {
            UIHelper.toastMessage(this, getString(R.string.msg_login_pwd_null));
            return;
        }

        // 保存用户名和密码
        AppContext.getInstance().saveAccountInfo(CyptoUtils.encode(Contanst.ACCOUNT_EMAIL, email)
                , CyptoUtils.encode(Contanst.ACCOUNT_PWD, passwd));

        login(email.toLowerCase(), passwd);
    }

    // 登录验证
    private void login(final String account, final String passwd) {
        if (mLoginProgressDialog == null) {
            mLoginProgressDialog = new ProgressDialog(this);
            mLoginProgressDialog.setCancelable(true);
            mLoginProgressDialog.setCanceledOnTouchOutside(false);
            mLoginProgressDialog.setMessage(getString(R.string.login_tips));
        }
        SleApi.login(account, passwd, new HttpCallback() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                Session session = JsonUtils.toBean(Session.class, t);
                if (session != null) {
                    // 保存登录用户的信息
                    AppContext.getInstance().saveLoginInfo(session);
                    // 保存用户的私有token
                    if (session.get_privateToken() != null) {
                        String token = CyptoUtils.encode(AsyncHttpHelp.GITOSC_PRIVATE_TOKEN,
                                session.get_privateToken());
                        AppContext.getInstance().setProperty(AsyncHttpHelp.PRIVATE_TOKEN, token);
                    }
                    //返回标识，成功登录
                    setResult(RESULT_OK);
                    // 发送用户登录成功的广播
                    BroadcastController.sendUserChangeBroadcase(LoginActivity.this);
                    UIHelper.goMainActivity(LoginActivity.this,session);
                    finish();
                }
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                if (errorNo == 401) {
                    UIHelper.toastMessage(LoginActivity.this, "用户未登录 或 密码错误");
                } else {
                    UIHelper.toastMessage(LoginActivity.this, "登录失败");
                }
            }

            @Override
            public void onPreStart() {
                super.onPreStart();
                if (mLoginProgressDialog != null) {
                    mLoginProgressDialog.show();
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (mLoginProgressDialog != null && mLoginProgressDialog.isShowing()) {
                    mLoginProgressDialog.dismiss();
                }
            }
        });

    }

    @Override
    @OnClick(R.id.bt_login)
    public void onClick(View v) {
        imm.hideSoftInputFromWindow(etPassword.getWindowToken(), 0);
        checkLogin();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        // 若密码和帐号都为空，则登录按钮不可操作
        String account = etAccount.getText().toString();
        String pwd = etPassword.getText().toString();
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(pwd)) {
            btLogin.setEnabled(false);
        } else {
            btLogin.setEnabled(true);
        }
    }
}
