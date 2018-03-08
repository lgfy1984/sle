package com.qmh.sle.ui.fragments;

import com.qmh.sle.R;
import com.qmh.sle.adapter.CommonAdapter;
import com.qmh.sle.adapter.EventAdapter;
import com.qmh.sle.api.SleApi;
import com.qmh.sle.bean.Event;
import com.qmh.sle.common.UIHelper;
import com.qmh.sle.ui.basefragment.BaseSwipeRefreshFragment;
import com.qmh.sle.utils.JsonUtils;

import java.util.List;

/**
 * 我的最新动态列表
 * @created 2014-05-20 下午15:47
 * @author 火蚁（http://my.oschina.net/LittleDY）
 * 
 * 最后更新
 * 更新者
 */
public class MySelfEventsFragment extends BaseSwipeRefreshFragment<Event> {
	
	public static MySelfEventsFragment newInstance() {
		return new MySelfEventsFragment();
	}

    @Override
    public CommonAdapter<Event> getAdapter() {
        return new EventAdapter(getActivity(), R.layout.list_item_event);
    }

    @Override
    public List<Event> getDatas(byte[] responeString) {
        return JsonUtils.getList(Event[].class, responeString);
    }

    @Override
    public void requestData() {
        SleApi.getMyEvents(mCurrentPage, mHandler);
    }

	@Override
	public void onItemClick(int position, Event event) {
		UIHelper.showEventDetail(getActivity(), event);
	}
}
