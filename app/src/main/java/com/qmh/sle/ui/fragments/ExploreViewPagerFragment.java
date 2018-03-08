package com.qmh.sle.ui.fragments;

import android.os.Bundle;

import com.qmh.sle.R;
import com.qmh.sle.adapter.ViewPageFragmentAdapter;
import com.qmh.sle.ui.basefragment.BaseViewPagerFragment;

import static com.qmh.sle.ui.fragments.ExploreProjectsFragment.EXPLORE_TYPE;
import static com.qmh.sle.ui.fragments.ExploreProjectsFragment.TYPE_FEATURED;
import static com.qmh.sle.ui.fragments.ExploreProjectsFragment.TYPE_LATEST;
import static com.qmh.sle.ui.fragments.ExploreProjectsFragment.TYPE_POPULAR;

/**
 * 发现页面
 *
 * @author 火蚁（http://my.oschina.net/LittleDY）
 * @created 2014-04-29
 */
public class ExploreViewPagerFragment extends BaseViewPagerFragment {


    public static ExploreViewPagerFragment newInstance() {
        return new ExploreViewPagerFragment();
    }

    @Override
    protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter) {
        String[] title = getResources().getStringArray(R.array.explore_title_array);
        Bundle featuredBundle = new Bundle();
        featuredBundle.putByte(EXPLORE_TYPE, TYPE_FEATURED);
        adapter.addTab(title[0], "featured", ExploreProjectsFragment.class, featuredBundle);
        Bundle popularBundle = new Bundle();
        popularBundle.putByte(EXPLORE_TYPE, TYPE_POPULAR);
        adapter.addTab(title[1], "popular", ExploreProjectsFragment.class, popularBundle);
        Bundle latestdBundle = new Bundle();
        latestdBundle.putByte(EXPLORE_TYPE, TYPE_LATEST);
        adapter.addTab(title[2], "latest", ExploreProjectsFragment.class, latestdBundle);
    }
}
