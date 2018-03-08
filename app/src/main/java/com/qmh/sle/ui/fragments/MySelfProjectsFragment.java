package com.qmh.sle.ui.fragments;

import com.qmh.sle.R;
import com.qmh.sle.adapter.CommonAdapter;
import com.qmh.sle.adapter.MySelfProjectsAdapter;
import com.qmh.sle.api.SleApi;
import com.qmh.sle.bean.Project;
import com.qmh.sle.common.UIHelper;
import com.qmh.sle.ui.basefragment.BaseSwipeRefreshFragment;
import com.qmh.sle.utils.JsonUtils;

import java.util.List;

/**
 * 我的项目列表Fragment
 *
 * @author 火蚁（http://my.oschina.net/LittleDY）
 * @created 2014-05-12 下午14：24
 */
public class MySelfProjectsFragment extends BaseSwipeRefreshFragment<Project> {

    public static MySelfProjectsFragment newInstance() {
        return new MySelfProjectsFragment();
    }

    @Override
    public CommonAdapter<Project> getAdapter() {
        return new MySelfProjectsAdapter(getActivity(), R.layout.list_item_myproject);
    }

    @Override
    public List<Project> getDatas(byte[] responeString) {
        return JsonUtils.getList(Project[].class, responeString);
    }

    @Override
    public void requestData() {
        SleApi.getMyProjects(mCurrentPage, mHandler);
    }

    @Override
    public void onItemClick(int position, Project project) {
        UIHelper.showProjectDetail(getActivity(), null, project.getId());
    }
}
