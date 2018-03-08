package com.qmh.sle.interfaces;

/**
 * 状态监听事件
 * @author 火蚁
 *
 */
public interface OnStatusListener {
	int STATUS_NONE = 0x0;
	int STATUS_LOADING = 0x01;
	int STATUS_LOADED = 0x11;

	void onStatus(int status);
}
