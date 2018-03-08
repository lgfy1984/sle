package com.qmh.sle.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.qmh.sle.AppContext;
import com.qmh.sle.R;
import com.qmh.sle.bean.SPatient;
import com.qmh.sle.bean.SPatientD;
import com.qmh.sle.common.Contanst;
import com.qmh.sle.common.UIHelper;
import com.qmh.sle.ui.baseactivity.BaseActivity;
import com.qmh.sle.utils.T;

import java.util.Calendar;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * 病人新增界面
 *
 * @author 火蚁（http://my.oschina.net/LittleDY）
 * @created 2014-05-26 上午10：26
 * @最后更新：
 * @更新内容：
 * @更新者：
 */
@SuppressWarnings("deprecation")
public class NewPatientActivity2 extends BaseActivity implements
        OnClickListener,CompoundButton.OnCheckedChangeListener{

    @InjectView(R.id.seizure)
    TextView seizure;
    @InjectView(R.id.psychosis)
    TextView psychosis;
    @InjectView(R.id.organic_brain_syndrome)
    TextView organic_brain_syndrome;
    @InjectView(R.id.visual_disturbance)
    TextView visual_disturbance;
    @InjectView(R.id.cranial_nerve_disorder)
    TextView cranial_nerve_disorder;
    @InjectView(R.id.lupus_headache)
    TextView lupus_headache;
    @InjectView(R.id.cva)
    TextView cva;

    @InjectView(R.id.seizure_img)
    ImageView seizure_img;
    @InjectView(R.id.psychosis_img)
    ImageView psychosis_img;
    @InjectView(R.id.organic_brain_syndrome_img)
    ImageView organic_brain_syndrome_img;
    @InjectView(R.id.visual_disturbance_img)
    ImageView visual_disturbance_img;
    @InjectView(R.id.cranial_nerve_disorder_img)
    ImageView cranial_nerve_disorder_img;
    @InjectView(R.id.lupus_headache_img)
    ImageView lupus_headache_img;
    @InjectView(R.id.cva_img)
    ImageView cva_img;

    @InjectView(R.id.swh_seizure)
    Switch swh_seizure;
    @InjectView(R.id.swh_psychosis)
    Switch swh_psychosis;
    @InjectView(R.id.swh_organic_brain_syndrome)
    Switch swh_organic_brain_syndrome;
    @InjectView(R.id.swh_visual_disturbance)
    Switch swh_visual_disturbance;
    @InjectView(R.id.swh_cranial_nerve_disorder)
    Switch swh_cranial_nerve_disorder;
    @InjectView(R.id.swh_lupus_headache)
    Switch swh_lupus_headache;
    @InjectView(R.id.swh_cva)
    Switch swh_cva;

    String value_seizure="0";
    String value_psychosis="0";
    String value_organic_brain_syndrome="0";
    String value_visual_disturbance="0";
    String value_cranial_nerve_disorder="0";
    String value_lupus_headache="0";
    String value_cva="0";
    private AppContext mAppContext;
    private View popView;
    private PopupWindow pop;
    private Bitmap bitmap;
    private String pt_flag="";
    private String stptid="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient2);
        ButterKnife.inject(this);
        mAppContext = AppContext.getInstance();

        mActionBar.setTitle("ADD NEW INDEX");
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        popView = inflater.inflate(R.layout.popup_view, null);

        initView();
    }

    private void initView() {
        // 拿到传过来的project对象
        Intent intent = getIntent();
        if(intent!=null) {
            pt_flag  = intent.getStringExtra(Contanst.PATIENTFlAG);
            if (pt_flag.equals("1")) {
                SPatientD spd = mAppContext.getPatientD();
                if(spd!=null){
                    if(spd.getSeizure().equals("1")){
                        swh_seizure.setChecked(true);
                        value_seizure="1";
                    }
                    if(spd.getPsychosis().equals("1")){
                        swh_psychosis.setChecked(true);
                        value_psychosis="1";
                    }
                    if(spd.getOrganicBrainSyndrome().equals("1")){
                        swh_organic_brain_syndrome.setChecked(true);
                        value_organic_brain_syndrome="1";
                    }
                    if(spd.getVisualDisturbance().equals("1")){
                        swh_visual_disturbance.setChecked(true);
                        value_visual_disturbance="1";
                    }
                    if(spd.getCranialNerveDisorder().equals("1")){
                        swh_cranial_nerve_disorder.setChecked(true);
                        value_cranial_nerve_disorder="1";
                    }
                    if(spd.getLupusHeadache().equals("1")){
                        swh_lupus_headache.setChecked(true);
                        value_lupus_headache="1";
                    }
                    if(spd.getCva().equals("1")){
                        swh_cva.setChecked(true);
                        value_cva="1";
                    }
                }
            }

            if(pt_flag.equals("2")){
                stptid  = intent.getStringExtra(Contanst.PATIENTID);
            }

        }

        // set font icon


    }

    private void initData() {


        // 记录项目的地址链接：

        // 截取屏幕
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (bitmap == null) {
                    bitmap = UIHelper.takeScreenShot(NewPatientActivity2.this);
                }
            }
        }, 500);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.patient_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.next:
                SPatientD spd = mAppContext.getPatientD();
                if(pt_flag.equals("2")){
                    spd.setStptid(stptid);
                }
                spd.setCva(value_cva);
                spd.setLupusHeadache(value_lupus_headache);
                spd.setCranialNerveDisorder(value_cranial_nerve_disorder);
                spd.setVisualDisturbance(value_visual_disturbance);
                spd.setOrganicBrainSyndrome(value_organic_brain_syndrome);
                spd.setPsychosis(value_psychosis);
                spd.setSeizure(value_seizure);
                if(pt_flag.equals("0") || pt_flag.equals("2")){
                    spd.setCreateDate(new Date());
                }
                mAppContext.savePatientD(spd);
                //finish();
                UIHelper.showNewPatient3(NewPatientActivity2.this,pt_flag);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    @OnClick({R.id.seizure,R.id.psychosis,R.id.organic_brain_syndrome,R.id.visual_disturbance,
            R.id.cranial_nerve_disorder,R.id.lupus_headache,R.id.cva,R.id.seizure_img,R.id.psychosis_img,R.id.organic_brain_syndrome_img,R.id.visual_disturbance_img,
            R.id.cranial_nerve_disorder_img,R.id.lupus_headache_img,R.id.cva_img})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.seizure :
                setPopMessage(popView, getResources().getString(R.string.seizure_help),seizure.getText().toString());
                break;
            case R.id.seizure_img:
                setPopMessage(popView, getResources().getString(R.string.seizure_help),seizure.getText().toString());
                break;
            case R.id.psychosis:
                setPopMessage(popView, getResources().getString(R.string.psychosis_help),psychosis.getText().toString());
                break;
            case R.id.psychosis_img:
                setPopMessage(popView, getResources().getString(R.string.psychosis_help),psychosis.getText().toString());
                break;
            case R.id.organic_brain_syndrome:
                setPopMessage(popView, getResources().getString(R.string.organic_brain_syndrome_help),organic_brain_syndrome.getText().toString());
                break;
            case R.id.organic_brain_syndrome_img:
                setPopMessage(popView, getResources().getString(R.string.organic_brain_syndrome_help),organic_brain_syndrome.getText().toString());
                break;
            case R.id.visual_disturbance:
                setPopMessage(popView, getResources().getString(R.string.visual_disturbance_help),visual_disturbance.getText().toString());
                break;
            case R.id.visual_disturbance_img:
                setPopMessage(popView, getResources().getString(R.string.visual_disturbance_help),visual_disturbance.getText().toString());
                break;
            case R.id.cranial_nerve_disorder:
                setPopMessage(popView, getResources().getString(R.string.cranial_nerve_disorder_help),cranial_nerve_disorder.getText().toString());
                break;
            case R.id.cranial_nerve_disorder_img:
                setPopMessage(popView, getResources().getString(R.string.cranial_nerve_disorder_help),cranial_nerve_disorder.getText().toString());
                break;
            case R.id.lupus_headache:
                setPopMessage(popView, getResources().getString(R.string.lupus_headache_help),lupus_headache.getText().toString());
                break;
            case R.id.lupus_headache_img:
                setPopMessage(popView, getResources().getString(R.string.lupus_headache_help),lupus_headache.getText().toString());
                break;
            case R.id.cva:
                setPopMessage(popView, getResources().getString(R.string.cva_help),cva.getText().toString());
                break;
            case R.id.cva_img:
                setPopMessage(popView, getResources().getString(R.string.cva_help),cva.getText().toString());
                break;
            default:
                break;
        }
    }
    private void setPopMessage(View view, String message,String tile) {

        pop = T.getListPopupWindow(popView, NewPatientActivity2.this, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,true);
        pop.setBackgroundDrawable(getResources().getDrawable(R.color.white));
        pop.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        pop.showAtLocation(view, Gravity.CENTER , 0, 0);
        // 关闭按钮
        view.findViewById(R.id.closeBtn).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (pop != null) {
                    pop.dismiss();
                }
            }
        });
        ((TextView)view.findViewById(R.id.popTitle)).setText(tile);
        ((TextView)view.findViewById(R.id.popmessage)).setText(message);
    }
    @OnCheckedChanged({R.id.swh_seizure,R.id.swh_psychosis,R.id.swh_organic_brain_syndrome,R.id.swh_visual_disturbance,
            R.id.swh_cranial_nerve_disorder,R.id.swh_lupus_headache,R.id.swh_cva})
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.swh_seizure:
                if(buttonView.isChecked()){
                    value_seizure="1";
                    //UIHelper.toastMessage(this, "on");
                }
                else {
                    value_seizure="0";
                    //UIHelper.toastMessage(this, "off");
                }
                break;
            case R.id.swh_psychosis:
                if(buttonView.isChecked()){
                    value_psychosis="1";
                    //UIHelper.toastMessage(this, "on");
                }
                else {
                    value_psychosis="0";
                    //UIHelper.toastMessage(this, "off");
                }
                break;
            case R.id.swh_organic_brain_syndrome:
                if(buttonView.isChecked()){
                    value_organic_brain_syndrome="1";
                    //UIHelper.toastMessage(this, "on");
                }
                else {
                    value_organic_brain_syndrome="0";
                    //UIHelper.toastMessage(this, "off");
                }
                break;
            case R.id.swh_visual_disturbance:
                if(buttonView.isChecked()){
                    value_visual_disturbance="1";
                    //UIHelper.toastMessage(this, "on");
                }
                else {
                    value_visual_disturbance="0";
                    //UIHelper.toastMessage(this, "off");
                }
                break;
            case R.id.swh_cranial_nerve_disorder:
                if(buttonView.isChecked()){
                    value_cranial_nerve_disorder="1";
                    //UIHelper.toastMessage(this, "on");
                }
                else {
                    value_cranial_nerve_disorder="0";
                    //UIHelper.toastMessage(this, "off");
                }
                break;
            case R.id.swh_lupus_headache:
                if(buttonView.isChecked()){
                    value_lupus_headache="1";
                    //UIHelper.toastMessage(this, "on");
                }
                else {
                    value_lupus_headache="0";
                    //UIHelper.toastMessage(this, "off");
                }
                break;
            case R.id.swh_cva:
                if(buttonView.isChecked()){
                    value_cva="1";
                    //UIHelper.toastMessage(this, "on");
                }
                else {
                    value_cva="0";
                    //UIHelper.toastMessage(this, "off");
                }
                break;
        }
    }


}