package com.qmh.sle.adapter;

import android.content.Context;
import android.widget.TextView;

import com.qmh.sle.R;
import com.qmh.sle.bean.SPatient;
import com.qmh.sle.bean.SPatientD;
import com.qmh.sle.common.StringUtils;
import com.qmh.sle.utils.TypefaceUtils;

import java.text.SimpleDateFormat;

/**
 *
 * Created by 火蚁 on 15/4/9.
 */
public class PatientDAdapter extends CommonAdapter<SPatientD> {

    public static final SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public PatientDAdapter(Context context) {
        super(context, R.layout.list_item_patient_d);
    }

    @Override
    public void convert(ViewHolder vh, final SPatientD sPatientD) {


//        vh.setText(R.id.tv_title, sPatientD.getId());
        vh.setText(R.id.tv_date,sPatientD.getCreateDate()==null?"":sdft.format(sPatientD.getCreateDate()));
//        TextView state = vh.getView(R.id.tv_state);
//        state.setText(issue.getStateRes());
//        TypefaceUtils.setOcticons(state);
//        vh.setText(R.id.tv_issue_num, "#" + issue.getIid());
//        //vh.setText(R.id.tv_description, issue.getDescription(), "暂无描述");
//        vh.setText(R.id.tv_author, issue.getAuthor() == null ? "" : "by " + issue.getAuthor().get_realname());
//
//        vh.setTextWithSemantic(R.id.tv_date, StringUtils.friendly_time(issue.getCreatedAt()), R.string.sem_wait);
    }
}
