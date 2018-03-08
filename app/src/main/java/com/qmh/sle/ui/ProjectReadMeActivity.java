package com.qmh.sle.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.webkit.WebView;

import com.kymjs.rxvolley.client.HttpCallback;

import com.qmh.sle.R;
import com.qmh.sle.api.SleApi;
import com.qmh.sle.bean.Project;
import com.qmh.sle.bean.ReadMe;
import com.qmh.sle.common.Contanst;
import com.qmh.sle.ui.baseactivity.BaseActivity;
import com.qmh.sle.utils.JsonUtils;
import com.qmh.sle.widget.TipInfoLayout;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 项目ReadMe文件详情
 *
 * @author 火蚁（http://my.oschina.net/LittleDY）
 * @created 2014-07-17
 */
public class ProjectReadMeActivity extends BaseActivity {

    @InjectView(R.id.tip_info)
    TipInfoLayout tipInfo;
    @InjectView(R.id.webView)
    WebView webView;
    private Project mProject;

    public String linkCss = "<link rel=\"stylesheet\" type=\"text/css\" " +
            "href=\"file:///android_asset/readme_style.css\">";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_readme);
        ButterKnife.inject(this);
        Intent intent = getIntent();
        if (intent != null) {
            mProject = (Project) intent.getSerializableExtra(Contanst.PROJECT);
            mTitle = "README.md";
            mActionBar.setTitle(mTitle);
        }
        initView();
        loadData();
    }

    private void initView() {
        tipInfo.setOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    private void loadData() {
        SleApi.getReadMeFile(mProject.getId(), new HttpCallback() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                tipInfo.setHiden();
                ReadMe readMe = JsonUtils.toBean(ReadMe.class, t);
                if (readMe != null && readMe.getContent() != null) {
                    webView.setVisibility(View.VISIBLE);
                    String body = linkCss + "<div class='markdown-body'>" + readMe.getContent() +
                            "</div>";
                    webView.loadDataWithBaseURL(null, body, "text/html", "utf-8", null);
                } else {
                    tipInfo.setEmptyData("该项目暂无README.md");
                }
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                tipInfo.setLoadError();
            }

            @Override
            public void onPreStart() {
                super.onPreStart();
                tipInfo.setLoading();
                webView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ActionBar bar = getSupportActionBar();
            if(bar != null)
                bar.hide();
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            ActionBar bar = getSupportActionBar();
            if(bar != null)
                bar.show();
        }
    }
}
