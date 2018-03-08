package com.qmh.sle.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.kymjs.rxvolley.client.HttpCallback;

import com.qmh.sle.R;
import com.qmh.sle.adapter.PatientAdapter;
import com.qmh.sle.adapter.ProjectAdapter;
import com.qmh.sle.api.SleApi;
import com.qmh.sle.bean.Project;
import com.qmh.sle.bean.SPatient;
import com.qmh.sle.common.Contanst;
import com.qmh.sle.common.UIHelper;
import com.qmh.sle.ui.baseactivity.BaseActivity;
import com.qmh.sle.utils.JsonUtils;
import com.qmh.sle.widget.EnhanceListView;
import com.qmh.sle.widget.TipInfoLayout;

import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 搜索项目界面
 *
 * @author 火蚁（http://my.oschina.net/LittleDY）
 * @created 2014-07-10
 */
public class SearchActivity extends BaseActivity implements
        OnQueryTextListener, OnItemClickListener {

    @InjectView(R.id.search_view)
    SearchView searchView;
    @InjectView(R.id.listView)
    EnhanceListView listView;
    @InjectView(R.id.tip_info)
    TipInfoLayout tipInfo;

    private InputMethodManager imm;

    private PatientAdapter adapter;

    private String mKey;

    private String departid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();
        if(intent!=null) {
            departid  = intent.getStringExtra(Contanst.DEPARTID);

        }
        ButterKnife.inject(this);
        initView();
        steupList();
    }

    private void initView() {
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        searchView.setOnQueryTextListener(this);
        searchView.setIconifiedByDefault(false);
        tipInfo.setHiden();
        tipInfo.setOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load(mKey,departid, 1);
            }
        });
    }

    private void steupList() {
        adapter = new PatientAdapter(this,
                R.layout.list_item_patient);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setPageSize(15);
        listView.setOnLoadMoreListener(new EnhanceListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore(int pageNum, int pageSize) {
                load(mKey,departid, pageNum);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextChange(String arg0) {
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String arg0) {
        mKey = arg0;
        adapter.clear();
        load(arg0, departid,1);
        imm.hideSoftInputFromWindow(listView.getWindowToken(), 0);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        SPatient p = adapter.getItem(position);
        if (p != null) {
            UIHelper.showPatientSleList(this, p, null);
        }
    }

    private void load(final String key,String departid, final int page) {
        SleApi.searchPatients(key, page,departid, new HttpCallback() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                List<SPatient> sPatients = JsonUtils.getList(SPatient[].class, t);
                tipInfo.setHiden();
                if (sPatients.size() > 0) {
                    adapter.addItem(sPatients);
                    listView.setVisibility(View.VISIBLE);
                } else {
                    if (page == 1 || page == 0) {
                        listView.setVisibility(View.GONE);
                        tipInfo.setEmptyData("未找到病人信息");
                    }
                }
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                tipInfo.setLoadError();
            }

            @Override
            public void onPreStart() {
                super.onPreStart();
                if (page <= 1) {
                    tipInfo.setLoading();
                    listView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });
    }
}
