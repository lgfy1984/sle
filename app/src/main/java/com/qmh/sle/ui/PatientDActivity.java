package com.qmh.sle.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;

import com.qmh.sle.R;
import com.qmh.sle.bean.SPatient;
import com.qmh.sle.common.Contanst;
import com.qmh.sle.common.UIHelper;
import com.qmh.sle.ui.baseactivity.BaseActivity;
import com.qmh.sle.ui.fragments.PatientDFragment;

/**
 * 项目详情界面
 *
 * @author 火蚁（http://my.oschina.net/LittleDY）
 * @created 2014-05-26 上午10：26
 * @最后更新：
 * @更新内容：
 * @更新者：
 */
@SuppressWarnings("deprecation")
public class PatientDActivity extends BaseActivity {
    public final static int PROJECT_LIST_TYPE_ISSUES = 0;
    public final static int PROJECT_LIST_TYPE_COMMITS = 1;

    private final int main_actionbar_menu_add = 0;

    private FragmentManager mFragmentManager;

    private Bundle mSavedInstanceState;

    private SPatient sPatient;

    private int mListType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_fragment);
        this.mSavedInstanceState = savedInstanceState;
        initView();
    }

    private void initView() {
        mFragmentManager = getSupportFragmentManager();

        Intent intent = getIntent();
        if (intent != null) {
            sPatient = (SPatient) intent.getSerializableExtra(Contanst.PATIENT);
//            mListType = intent.getIntExtra("project_list_type", 0);

            mTitle = getResources().getString(R.string.sle_list);
            setActionBarTitle(mTitle);
            mSubTitle = sPatient.getPtid();
            setActionBarSubTitle(mSubTitle);
        }

        if (null == mSavedInstanceState) {
            setFragmentCommit(mListType);
        }
    }

//    private String getTitle(int type) {
//        String title = "";
//        switch (type) {
//            case PROJECT_LIST_TYPE_ISSUES:
//                title = "问题列表";
//                break;
//            case PROJECT_LIST_TYPE_COMMITS:
//                title = "提交列表";
//                break;
//        }
//        return title;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mListType == PROJECT_LIST_TYPE_ISSUES) {
            getMenuInflater().inflate(R.menu.second_actionbar_menu, menu);
            return true;
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.second_actionbar_add:
                UIHelper.showNewPatient2(this,sPatient.getId(),"2");//flag为2时代表从SLE列表新增
                return true;
            case R.id.second_actionbar_history:
                UIHelper.showNewPatient5(this,"2");//flag为2时代表从SLE列表可看报表
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setFragmentCommit(int type) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.replace(R.id.content, PatientDFragment.newInstance(sPatient)).commit();

    }
}