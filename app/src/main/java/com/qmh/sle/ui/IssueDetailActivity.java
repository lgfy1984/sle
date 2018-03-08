package com.qmh.sle.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;

import com.kymjs.rxvolley.client.HttpCallback;

import com.qmh.sle.R;
import com.qmh.sle.api.SleApi;
import com.qmh.sle.bean.Issue;
import com.qmh.sle.bean.Project;
import com.qmh.sle.common.Contanst;
import com.qmh.sle.ui.baseactivity.BaseActivity;
import com.qmh.sle.ui.fragments.IssueDetailViewPagerFragment;
import com.qmh.sle.utils.JsonUtils;
import com.qmh.sle.widget.TipInfoLayout;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * issue详情activity
 *
 * @author 火蚁(http://my.oschina.net/LittleDY)
 * @created 2014-08-25
 */
public class IssueDetailActivity extends BaseActivity {

    @InjectView(R.id.tip_info)
    TipInfoLayout tipInfo;

    private FragmentManager mFragmentManager;

    private Project mProject;

    private Issue mIssue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_detail);
        ButterKnife.inject(this);
        init();
    }

    private void init() {
        mFragmentManager = getSupportFragmentManager();
        Intent intent = getIntent();
        mProject = (Project) intent.getSerializableExtra(Contanst.PROJECT);
        mIssue = (Issue) intent.getSerializableExtra(Contanst.ISSUE);
        final String projectId = intent.getStringExtra(Contanst.PROJECTID);
        final String issueId = intent.getStringExtra(Contanst.ISSUEID);
        if (mIssue == null || mProject == null) {
            loadIssueAndProject(projectId, issueId);
        } else {
            initData();
        }
        tipInfo.setOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadIssueAndProject(projectId, issueId);
            }
        });
    }

    private void initData() {
        String title = "Issue " + (mIssue.getIid() == 0 ? "" : "#" + mIssue.getIid());
        mActionBar.setTitle(title);
        mActionBar.setSubtitle(mProject.getOwner().getRealname() + "/"
                + mProject.getName());
        tipInfo.setHiden();
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.replace(R.id.issue_content, IssueDetailViewPagerFragment.newInstance(mProject, mIssue)
        ).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void loadIssueAndProject(final String projectId, final String issueId) {
        SleApi.getProject(projectId, new HttpCallback() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                Project p = JsonUtils.toBean(Project.class, t);
                if (p != null) {
                    mProject = p;
                    SleApi.getIssueDetail(projectId, issueId, new HttpCallback() {
                        @Override
                        public void onSuccess(Map<String, String> headers, byte[] t) {
                            super.onSuccess(headers, t);
                            Issue issue = JsonUtils.toBean(Issue.class, t);
                            if (issue != null) {
                                mIssue = issue;
                                initData();
                            } else {
                                setLoadError();
                            }
                        }

                        @Override
                        public void onFailure(int errorNo, String strMsg) {
                            super.onFailure(errorNo, strMsg);
                            setLoadError();
                        }

                        @Override
                        public void onFinish() {
                            super.onFinish();
                            tipInfo.setHiden();
                        }
                    });
                } else {
                    setLoadError();
                }
            }


            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                setLoadError();
            }

            @Override
            public void onPreStart() {
                super.onPreStart();
                tipInfo.setLoading();
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });
    }

    private void setLoadError() {
        tipInfo.setLoadError();
    }
}
