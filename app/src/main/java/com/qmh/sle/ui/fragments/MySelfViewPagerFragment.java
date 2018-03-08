package com.qmh.sle.ui.fragments;

import android.os.Bundle;

import com.qmh.sle.AppContext;
import com.qmh.sle.R;
import com.qmh.sle.adapter.ViewPageFragmentAdapter;
import com.qmh.sle.common.Contanst;
import com.qmh.sle.ui.basefragment.BaseViewPagerFragment;

/**
 * 用户主界面
 * 
 * @author 火蚁（http://my.oschina.net/LittleDY）
 * @created 2014-04-29
 */
public class MySelfViewPagerFragment extends BaseViewPagerFragment {
	
    public static MySelfViewPagerFragment newInstance() {
        return new MySelfViewPagerFragment();
    }
    
	@Override
	protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter) {
		String[] title = getResources().getStringArray(R.array.myself_title_array);
		adapter.addTab(title[0], "event", MySelfEventsFragment.class, null);
		adapter.addTab(title[1], "project", MySelfProjectsFragment.class, null);
		Bundle bundle = new Bundle();
		bundle.putSerializable(Contanst.USER, AppContext.getInstance().getLoginInfo());
		adapter.addTab(title[2], "star_projects", UserStarProjectFragment.class, bundle);
		adapter.addTab(title[3], "watch_projects", UserWatchProjectsFragment.class, bundle);
	}
}
