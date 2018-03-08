package com.qmh.sle.ui.fragments;

import android.os.Bundle;

import com.qmh.sle.R;
import com.qmh.sle.adapter.CommitCommentdapter;
import com.qmh.sle.adapter.CommonAdapter;
import com.qmh.sle.api.SleApi;
import com.qmh.sle.bean.Comment;
import com.qmh.sle.bean.Commit;
import com.qmh.sle.bean.Project;
import com.qmh.sle.common.Contanst;
import com.qmh.sle.ui.basefragment.BaseSwipeRefreshFragment;
import com.qmh.sle.utils.JsonUtils;

import java.util.List;

/**
 * commit评论列表
 * @created 2014-05-14 下午16:57
 * @author 火蚁（http://my.oschina.net/LittleDY）
 * 
 * 最后更新
 * 更新者
 */
public class CommitCommentFragment extends BaseSwipeRefreshFragment<Comment> {
	
	private Project mProject;
	
	private Commit mCommit;
	
	public static CommitCommentFragment newInstance(Project project, Commit commit) {
		CommitCommentFragment fragment = new CommitCommentFragment();
		Bundle args = new Bundle();
		args.putSerializable(Contanst.PROJECT, project);
		args.putSerializable(Contanst.COMMIT, commit);
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle args = getArguments();
		if (args != null) {
			mProject = (Project) args.getSerializable(Contanst.PROJECT);
			mCommit = (Commit) args.getSerializable(Contanst.COMMIT);
		}
	}

    @Override
    public CommonAdapter<Comment> getAdapter() {
        return new CommitCommentdapter(getActivity(), R.layout.list_item_commit_comment);
    }

    @Override
    public List<Comment> getDatas(byte[] responeString) {
        return JsonUtils.getList(Comment[].class, responeString);
    }

    @Override
    public void requestData() {
        SleApi.getCommitCommentList(mProject.getId(), mCommit.getId(), mHandler);
    }

    @Override
    public void onItemClick(int position, Comment data) {

    }

    @Override
    protected String getEmptyTip() {
        return "暂无评论";
    }
}
