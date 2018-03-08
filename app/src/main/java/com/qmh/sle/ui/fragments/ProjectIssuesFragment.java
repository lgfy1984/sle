package com.qmh.sle.ui.fragments;

import android.os.Bundle;

import com.qmh.sle.adapter.CommonAdapter;
import com.qmh.sle.adapter.ProjectIssuesAdapter;
import com.qmh.sle.api.SleApi;
import com.qmh.sle.bean.Issue;
import com.qmh.sle.bean.Project;
import com.qmh.sle.common.Contanst;
import com.qmh.sle.common.UIHelper;
import com.qmh.sle.ui.basefragment.BaseSwipeRefreshFragment;
import com.qmh.sle.utils.JsonUtils;

import java.util.List;

/**
 * 项目issue列表
 *
 * @author 火蚁（http://my.oschina.net/LittleDY）
 *         <p>
 *         最后更新
 *         更新者
 * @created 2014-05-26
 */
public class ProjectIssuesFragment extends BaseSwipeRefreshFragment<Issue> {

    private Project mProject;

    public static ProjectIssuesFragment newInstance(Project project) {
        ProjectIssuesFragment fragment = new ProjectIssuesFragment();
        Bundle args = new Bundle();
        args.putSerializable(Contanst.PROJECT, project);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mProject = (Project) args.getSerializable(Contanst.PROJECT);
            requestData();
        }
    }

    @Override
    public CommonAdapter<Issue> getAdapter() {
        return new ProjectIssuesAdapter(getActivity());
    }

    @Override
    public List<Issue> getDatas(byte[] responeString) {
        return JsonUtils.getList(Issue[].class, responeString);
    }

    @Override
    public void requestData() {
        SleApi.getProjectIssues(mProject.getId(), mCurrentPage, mHandler);
    }

    @Override
    public void onItemClick(int position, Issue issue) {
        UIHelper.showIssueDetail(getActivity(), mProject, issue, null, null);
    }

    @Override
    protected String getEmptyTip() {
        return "暂无issue";
    }

}
