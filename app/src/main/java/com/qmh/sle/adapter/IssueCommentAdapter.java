package com.qmh.sle.adapter;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

import com.qmh.sle.R;
import com.qmh.sle.bean.GitNote;
import com.qmh.sle.bean.User;
import com.qmh.sle.common.HtmlRegexpUtils;
import com.qmh.sle.common.StringUtils;
import com.qmh.sle.common.UIHelper;

/**
 * issue的评论列表适配器
 * @created 2014-06-16
 * @author 火蚁（http://my.oschina.net/LittleDY）
 * 
 * 最后更新：
 * 更新者：
 */
public class IssueCommentAdapter extends CommonAdapter<GitNote> {
	
	public IssueCommentAdapter(Context context, int resource) {
		super(context, resource);
	}

    @Override
    public void convert(ViewHolder vh, final GitNote note) {
        // 1.加载头像
//        String portraitURL = note.getAuthor().getNew_portrait();
//        if (portraitURL.endsWith("portrait.gif")) {
//            vh.setImageResource(R.id.iv_portrait, R.drawable.mini_avatar);
//        } else {
//            vh.setImageForUrl(R.id.iv_portrait, portraitURL);
//        }
//        vh.getView(R.id.iv_portrait).setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                User user = note.getAuthor();
//                if (user == null) {
//                    return;
//                }
//                UIHelper.showUserInfoDetail(mContext, user, null);
//            }
//        });

        // 2.显示相关信息
        vh.setText(R.id.tv_name, note.getAuthor().getRealname());
        vh.setText(R.id.tv_content, HtmlRegexpUtils.filterHtml(note.getBody()));
        vh.setTextWithSemantic(R.id.tv_date, StringUtils.friendly_time(note.getCreated_at()), R.string.sem_wait);
    }
}
