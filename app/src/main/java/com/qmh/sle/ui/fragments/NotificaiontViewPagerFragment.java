package com.qmh.sle.ui.fragments;

import android.os.Bundle;

import com.qmh.sle.R;
import com.qmh.sle.adapter.ViewPageFragmentAdapter;
import com.qmh.sle.ui.basefragment.BaseViewPagerFragment;

/**
 * 通知
 * 
 * @author 火蚁（http://my.oschina.net/LittleDY）
 * @created 2014-04-29
 */
public class NotificaiontViewPagerFragment extends BaseViewPagerFragment {
	
    public static NotificaiontViewPagerFragment newInstance() {
        return new NotificaiontViewPagerFragment();
    }

	@Override
	protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter) {
		String[] title = getResources().getStringArray(R.array.notification_title_array);
		Bundle unRead = new Bundle();
		unRead.putInt(NotificationFragment.NOTIFICATION_ACTION_KEY, NotificationFragment.ACTION_UNREAD);
		adapter.addTab(title[0], "unread", NotificationFragment.class, unRead);
		Bundle readed = new Bundle();
		readed.putInt(NotificationFragment.NOTIFICATION_ACTION_KEY, NotificationFragment.ACTION_READED);
		adapter.addTab(title[1], "readed", NotificationFragment.class, readed);
	}
}
