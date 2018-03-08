package com.qmh.sle.ui.fragments;

import android.os.Bundle;

import com.qmh.sle.R;
import com.qmh.sle.adapter.CommonAdapter;
import com.qmh.sle.adapter.ProjectAdapter;
import com.qmh.sle.api.SleApi;
import com.qmh.sle.bean.Project;
import com.qmh.sle.common.UIHelper;
import com.qmh.sle.ui.basefragment.BaseSwipeRefreshFragment;
import com.qmh.sle.utils.JsonUtils;

import java.util.List;

/**
 * 发现页面项目列表（推荐、热门、最近推荐）
 *
 * @author 火蚁（http://my.oschina.net/LittleDY）
 *         <p/>
 *         最后更新
 *         更新者
 * @created 2014-05-19 上午10：43
 */
public class ExploreProjectsFragment extends BaseSwipeRefreshFragment<Project> {

    public final static String EXPLORE_TYPE = "explore_type";

    public final static byte TYPE_FEATURED = 0x0;

    public final static byte TYPE_POPULAR = 0x1;

    public final static byte TYPE_LATEST = 0x2;

    private byte type = 0;

    public static ExploreProjectsFragment newInstance(byte type) {
        ExploreProjectsFragment exploreFeaturedListProjectFragment = new ExploreProjectsFragment();
        Bundle bundle = new Bundle();
        bundle.putByte(EXPLORE_TYPE, type);
        exploreFeaturedListProjectFragment.setArguments(bundle);
        return exploreFeaturedListProjectFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        type = args.getByte(EXPLORE_TYPE);
    }

    @Override
    public CommonAdapter<Project> getAdapter() {
        return new ProjectAdapter(getActivity(), R.layout.list_item_project);
    }

    @Override
    public List<Project> getDatas(byte[] responeString) {
        return JsonUtils.getList(Project[].class, responeString);
    }

    @Override
    public void requestData() {
        switch (type) {
            case TYPE_FEATURED:
                SleApi.getExploreFeaturedProject(mCurrentPage, mHandler);
                break;
            case TYPE_POPULAR:
                SleApi.getExplorePopularProject(mCurrentPage, mHandler);
                break;
            case TYPE_LATEST:
                SleApi.getExploreLatestProject(mCurrentPage, mHandler);
                break;
        }
    }

    @Override
    public void onItemClick(int position, Project project) {
        UIHelper.showProjectDetail(getActivity(), null, project.getId());
    }
}
