package com.qmh.sle.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;

import com.qmh.sle.AppContext;
import com.qmh.sle.R;
import com.qmh.sle.adapter.CommonAdapter;
import com.qmh.sle.adapter.PatientAdapter;
import com.qmh.sle.adapter.ProjectAdapter;
import com.qmh.sle.api.SleApi;
import com.qmh.sle.bean.Project;
import com.qmh.sle.bean.SPatient;
import com.qmh.sle.common.Contanst;
import com.qmh.sle.common.UIHelper;
import com.qmh.sle.ui.basefragment.BaseSwipeRefreshFragment;
import com.qmh.sle.utils.JsonUtils;

import java.util.List;

/**
 * 病人列表
 *
 * @author 火蚁（http://my.oschina.net/LittleDY）
 *         <p/>
 *         最后更新
 *         更新者
 * @created 2014-05-19 上午10：43
 */
public class ExplorePatientsFragment extends BaseSwipeRefreshFragment<SPatient> {

    public final static String EXPLORE_TYPE = "explore_type";

    public final static byte TYPE_LIST = 0x0;

    private byte type = 0;
    private AppContext mAppContext;
    private String departid;

    public static ExplorePatientsFragment newInstance(byte type,String departid) {
        ExplorePatientsFragment exploreListPatientFragment = new ExplorePatientsFragment();
        Bundle bundle = new Bundle();
        bundle.putByte(EXPLORE_TYPE, type);
        bundle.putString(Contanst.DEPARTID, departid);
        exploreListPatientFragment.setArguments(bundle);
        return exploreListPatientFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        type = args.getByte(EXPLORE_TYPE);
        departid = args.getString(Contanst.DEPARTID);
        mAppContext = AppContext.getInstance();
    }

    @Override
    public CommonAdapter<SPatient> getAdapter() {
        return new PatientAdapter(getActivity(), R.layout.list_item_patient);
    }

    @Override
    public List<SPatient> getDatas(byte[] responeString) {
        return JsonUtils.getList(SPatient[].class, responeString);
    }

    @Override
    public void requestData() {
        if(type==TYPE_LIST) {
            SleApi.getExplorePatient(mCurrentPage,departid, mHandler);
        }
    }

    @Override
    public void onItemClick(int position, SPatient patient) {
        mAppContext.savePatient(patient);
        UIHelper.showPatientSleList(getActivity(), patient, patient.getId());
    }
}
