package com.qmh.sle.ui.fragments;

import android.os.Bundle;

import com.qmh.sle.AppContext;
import com.qmh.sle.adapter.CommonAdapter;
import com.qmh.sle.adapter.PatientDAdapter;
import com.qmh.sle.api.SleApi;
import com.qmh.sle.bean.Project;
import com.qmh.sle.bean.SPatient;
import com.qmh.sle.bean.SPatientD;
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
public class PatientDFragment extends BaseSwipeRefreshFragment<SPatientD> {

    private SPatient sPatient;
    private AppContext mAppContext;

    public static PatientDFragment newInstance(SPatient sPatient) {
        PatientDFragment fragment = new PatientDFragment();
        Bundle args = new Bundle();
        args.putSerializable(Contanst.PATIENT, sPatient);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppContext = AppContext.getInstance();
        Bundle args = getArguments();
        if (args != null) {
            sPatient = (SPatient) args.getSerializable(Contanst.PATIENT);
            requestData();
        }
    }

    @Override
    public CommonAdapter<SPatientD> getAdapter() {
        return new PatientDAdapter(getActivity());
    }

    @Override
    public List<SPatientD> getDatas(byte[] responeString) {
        mAppContext.savePtlist(responeString);
        return JsonUtils.getList(SPatientD[].class, responeString);
    }

    @Override
    public void requestData() {
        SleApi.getPatientD(sPatient.getId(),"0", mHandler);
    }

    @Override
    public void onItemClick(int position, SPatientD sPatientD) {
        mAppContext.savePatientD(sPatientD);
        UIHelper.showIndexDetail(getActivity(), sPatientD,sPatient);
    }

    @Override
    protected String getEmptyTip() {
        return "no data";
    }

}
