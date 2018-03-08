package com.qmh.sle.ui.fragments;

import android.os.Bundle;

import com.qmh.sle.R;
import com.qmh.sle.adapter.CommonAdapter;
import com.qmh.sle.adapter.ProjectAdapter;
import com.qmh.sle.api.SleApi;
import com.qmh.sle.bean.Project;
import com.qmh.sle.bean.User;
import com.qmh.sle.common.Contanst;
import com.qmh.sle.common.UIHelper;
import com.qmh.sle.ui.basefragment.BaseSwipeRefreshFragment;
import com.qmh.sle.utils.JsonUtils;

import java.util.List;

/**
 * 发现页面推荐项目列表Fragment
 * @created 2014-05-19 上午10：43
 * @author 火蚁（http://my.oschina.net/LittleDY）
 * 
 * 最后更新
 * 更新者
 */
public class UserProjectsFragment extends BaseSwipeRefreshFragment<Project> {
	
	private User mUser;
	
	public static UserProjectsFragment newInstance(User user) {
		UserProjectsFragment fragment = new UserProjectsFragment();
		Bundle args = new Bundle();
		args.putSerializable(Contanst.USER, user);
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle args = getArguments();
		if (args != null) {
			mUser = (User) args.getSerializable(Contanst.USER);
		}
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
        SleApi.getUserProjects(mUser.getId(), mCurrentPage, mHandler);
    }

	@Override
	public void onItemClick(int position, Project project) {
		UIHelper.showProjectDetail(getActivity(), null, project.getId());
	}

    @Override
    protected String getEmptyTip() {
        return "暂无项目";
    }
}
