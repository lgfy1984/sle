package com.qmh.sle.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.kymjs.rxvolley.client.HttpCallback;
import com.qmh.sle.AppContext;
import com.qmh.sle.R;
import com.qmh.sle.api.SleApi;
import com.qmh.sle.bean.UpLoadFile;
import com.qmh.sle.bean.User;
import com.qmh.sle.common.ImageUtils;
import com.qmh.sle.ui.baseactivity.BaseActivity;
import com.qmh.sle.utils.JsonUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 反馈界面
 * Created by thanatosx on 3/1/16.
 */
public class PassWordActivity extends BaseActivity{

    private static final int REQUEST_CODE_PICK_IMAGE = 110;

    @InjectView(R.id.pwdold_input)
    EditText pwdold;
    @InjectView(R.id.pwdnew_input)
    EditText pwdnew;
    @InjectView(R.id.pwdnew2_input)
    EditText pwdnew2;
    @InjectView(R.id.btn_submit)
    Button btn_submit;

    private File file;

    private AppContext mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
        ButterKnife.inject(this);
        mContext = (AppContext) getApplicationContext();
    }



    /**
     * 提交反馈
     */
    @OnClick(R.id.btn_submit) void submit(){
        String pwdoldt1 = pwdold.getText().toString();
        String pwdnewt = pwdnew.getText().toString();
        String pwdnew2t = pwdnew2.getText().toString();
        if (pwdoldt1.trim().equals("") || pwdnewt.trim().equals("") || pwdnew2t.trim().equals("")){
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pwdnewt.trim().equals(pwdnew2t.trim()) ){
            Toast.makeText(this, "两次输入新密码必须一致", Toast.LENGTH_SHORT).show();
            return;
        }
        String title="";
        User user =  mContext.getLoginInfo();
        String uid = user.getId();
        String username  = user.getUsername();

                submitFeedback(uid,username,pwdoldt1, pwdnewt);


    }


    private void submitFeedback(String uid,String username, String pwdold,String pwdnew){
        SleApi.modifypwd(uid,username,pwdold, pwdnew, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                Toast.makeText(AppContext.getInstance(), "修改成功", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                Toast.makeText(AppContext.getInstance(), "修改失败~", Toast.LENGTH_SHORT).show();
            }

        });
    }

}
