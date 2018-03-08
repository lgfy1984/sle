package com.qmh.sle.adapter;

import android.content.Context;
import android.view.View;

import com.qmh.sle.R;
import com.qmh.sle.bean.SPatient;
import com.qmh.sle.bean.User;
import com.qmh.sle.common.UIHelper;
import com.qmh.sle.utils.DateUtil;

import java.text.SimpleDateFormat;

/**
 *
 * Created by 火蚁 on 15/4/9.
 */
public class PatientAdapter extends CommonAdapter<SPatient> {

    public static final SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");

    public PatientAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder vh, final SPatient patient) {

        //
        vh.setText(R.id.tv_title, patient.getPtid(), R.string.msg_project_empty_description);
        vh.setText(R.id.tv_description,patient.getSexId().equals("1")?R.string.male:R.string.female);
        try {
            if(patient.getBirthday()!=null){
                int age = DateUtil.getAge(patient.getBirthday());
                vh.setText(R.id.tv_date,""+age);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
