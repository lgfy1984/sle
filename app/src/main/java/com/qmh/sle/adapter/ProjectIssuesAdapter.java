package com.qmh.sle.adapter;

import android.content.Context;
import android.widget.TextView;

import com.qmh.sle.R;
import com.qmh.sle.bean.Issue;
import com.qmh.sle.common.StringUtils;
import com.qmh.sle.utils.TypefaceUtils;

/**
 * 项目Issues列表适配器
 * @created 2014-05-28 上午11：19
 * @author 火蚁（http://my.oschina.net/LittleDY）
 * 
 * 最后更新：
 * 更新者：
 */
public class ProjectIssuesAdapter extends CommonAdapter<Issue> {
	
	public ProjectIssuesAdapter(Context context) {
		super(context, R.layout.list_item_project_issue);
	}

    @Override
    public void convert(ViewHolder vh, final Issue issue) {

        vh.setText(R.id.tv_title, issue.getTitle());
        TextView state = vh.getView(R.id.tv_state);
        state.setText(issue.getStateRes());
        TypefaceUtils.setOcticons(state);
        vh.setText(R.id.tv_issue_num, "#" + issue.getIid());
        //vh.setText(R.id.tv_description, issue.getDescription(), "暂无描述");
        vh.setText(R.id.tv_author, issue.getAuthor() == null ? "" : "by " + issue.getAuthor().getRealname());

        vh.setTextWithSemantic(R.id.tv_date, StringUtils.friendly_time(issue.getCreatedAt()), R.string.sem_wait);
    }
}
