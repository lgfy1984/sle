package com.qmh.sle.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

import com.qmh.sle.R;
import com.qmh.sle.bean.Commit;
import com.qmh.sle.bean.Project;
import com.qmh.sle.common.Contanst;
import com.qmh.sle.ui.baseactivity.BaseActivity;
import com.qmh.sle.ui.fragments.CommitDetailViewPagerFragment;

/**
 * commit详情
 *
 * @author 火蚁
 * @created 2014-06-12
 */
public class CommitDetailActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_detail);
        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        Intent intent = getIntent();
        Project mProject = (Project) intent.getSerializableExtra(Contanst.PROJECT);
        Commit mCommit = (Commit) intent.getSerializableExtra(Contanst.COMMIT);
        mActionBar.setTitle("提交" + mCommit.getId().substring(0, 9));
        mActionBar.setSubtitle(mProject.getOwner().getRealname() + "/" + mProject.getName());

        if (null == savedInstanceState) {
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.replace(R.id.commit_content, CommitDetailViewPagerFragment.newInstance(mProject,
                    mCommit)).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
