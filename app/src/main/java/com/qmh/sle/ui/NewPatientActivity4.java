package com.qmh.sle.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.SuperscriptSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.kymjs.rxvolley.client.HttpCallback;
import com.qmh.sle.AppContext;
import com.qmh.sle.R;
import com.qmh.sle.api.SleApi;
import com.qmh.sle.bean.SPatientD;
import com.qmh.sle.common.Contanst;
import com.qmh.sle.common.StringUtils;
import com.qmh.sle.common.UIHelper;
import com.qmh.sle.ui.baseactivity.BaseActivity;
import com.qmh.sle.utils.T;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
public class NewPatientActivity4 extends BaseActivity implements
        OnClickListener,CompoundButton.OnCheckedChangeListener{

    @InjectView(R.id.cast)
    TextView cast;
    @InjectView(R.id.hematuria)
    TextView hematuria;
    @InjectView(R.id.pyuria)
    TextView pyuria;
    @InjectView(R.id.proteinuria)
    TextView proteinuria;
    @InjectView(R.id.wbc_unit_sb)
    TextView wbc_unit_sb;
    @InjectView(R.id.plt_unit_sb)
    TextView plt_unit_sb;


    @InjectView(R.id.cast_img)
    ImageView cast_img;
    @InjectView(R.id.hematuria_img)
    ImageView hematuria_img;
    @InjectView(R.id.pyuria_img)
    ImageView pyuria_img;
    @InjectView(R.id.proteinuria_img)
    ImageView proteinuria_img;
    @InjectView(R.id.anti_ds_dna_ab_img)
    ImageView anti_ds_dna_ab_img;
    @InjectView(R.id.complement3_img)
    ImageView complement3_img;
    @InjectView(R.id.complement4_img)
    ImageView complement4_img;


    @InjectView(R.id.swh_cast)
    Switch swh_cast;
    @InjectView(R.id.swh_hematuria)
    Switch swh_hematuria;
    @InjectView(R.id.swh_pyuria)
    Switch swh_pyuria;
    @InjectView(R.id.swh_proteinuria)
    Switch swh_proteinuria;
    @InjectView(R.id.swh_anti_ds_dna_ab)
    Switch swh_anti_ds_dna_ab;
    @InjectView(R.id.swh_complement3)
    Switch swh_complement3;
    @InjectView(R.id.swh_complement4)
    Switch swh_complement4;

    @InjectView(R.id.cbp_input)
    EditText cbp_input;
//    @InjectView(R.id.hb_input)
//    EditText hb_input;
    @InjectView(R.id.wbc_input)
    EditText wbc_input;
    @InjectView(R.id.plt_input)
    EditText plt_input;
    @InjectView(R.id.anti_ds_dna_ab_input)
    EditText anti_ds_dna_ab_input;
    @InjectView(R.id.complement_input)
    EditText complement_input;
    @InjectView(R.id.urine_microscopy_input)
    EditText urine_microscopy_input;

    String value_cast = "0";
    String value_hematuria = "0";
    String value_pyuria = "0";
    String value_proteinuria = "0";
    String value_anti_ds_dna_ab = "0";
    String value_complement3 = "0";
    String value_complement4 = "0";

    private AppContext mAppContext;
    private View popView;
    private PopupWindow pop;
    private Bitmap bitmap;
    private String pt_flag="";


    SpannableString msp = null;
    private int mYear;
    private int mMonth;
    private int mDay;
    private SPatientD spd;
    private float score=0;
    public static final SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient4);
        ButterKnife.inject(this);
        mAppContext = AppContext.getInstance();
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        popView = inflater.inflate(R.layout.popup_view, null);
        mActionBar.setTitle("ADD NEW INDEX");

        spd = mAppContext.getPatientD();
        msp = new SpannableString("x1012");
        msp.setSpan(new SuperscriptSpan(), 3, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //上标

        wbc_unit_sb.setText(msp);
        plt_unit_sb.setText(msp);
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);


        initView();
    }

    private void initView() {
        // 拿到传过来的project对象
        Intent intent = getIntent();
        if(intent!=null) {
            pt_flag  = intent.getStringExtra(Contanst.PATIENTFlAG);
            if (pt_flag.equals("1")) {
                if(spd.getCast().equals("1")){
                    swh_cast.setChecked(true);
                    value_cast="1";
                }
                if(spd.getHematuria().equals("1")){
                    swh_hematuria.setChecked(true);
                    value_hematuria="1";
                }
                if(spd.getPyuria().equals("1")){
                    swh_pyuria.setChecked(true);
                    value_pyuria="1";
                }
                if(spd.getProteinuria()!=null && spd.getProteinuria().equals("1")){
                    swh_proteinuria.setChecked(true);
                    value_proteinuria="1";
                }
                if(spd.getAntiDsDnaAbValue()!=null && spd.getAntiDsDnaAbValue().equals("1")){
                    swh_anti_ds_dna_ab.setChecked(true);
                    value_anti_ds_dna_ab="1";
                }
                if(spd.getC3()!=null && spd.getC3().equals("1")){
                    swh_complement3.setChecked(true);
                    value_complement3="1";
                }
                if(spd.getC4()!=null && spd.getC4().equals("1")){
                    swh_complement4.setChecked(true);
                    value_complement4="1";
                }
                if(spd.getCbp()!=null) {
                    cbp_input.setText(sdft.format(spd.getCbp()));
                }
                if(spd.getAntiDsDnaAb()!=null) {
                    anti_ds_dna_ab_input.setText(sdft.format(spd.getAntiDsDnaAb()));
                }
                if(spd.getComplement()!=null) {
                    complement_input.setText(sdft.format(spd.getComplement()));
                }
                if(spd.getUrineMicroscopy()!=null) {
                    urine_microscopy_input.setText(sdft.format(spd.getUrineMicroscopy()));
                }
//                if(spd.getHb()!=null)  hb_input.setText(String.valueOf(spd.getHb()));
                if(spd.getWbc()!=null)  wbc_input.setText(String.valueOf(spd.getWbc()));
                if(spd.getPlt()!=null)  plt_input.setText(String.valueOf(spd.getPlt()));

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
                    bitmap = UIHelper.takeScreenShot(NewPatientActivity4.this);
                }
            }
        }, 500);
    }


    private boolean checkSubmit() {
        String cbp_inputvalue = cbp_input.getText().toString();
        String anti_ds_dna_ab_inputvalue = anti_ds_dna_ab_input.getText().toString();
        String complement_inputvalue = complement_input.getText().toString();
        String urine_microscopy_inputvalue = urine_microscopy_input.getText().toString();

        //检查用户输入的参数
        if (StringUtils.isEmpty(cbp_inputvalue)) {
            UIHelper.toastMessage(this, getString(R.string.msg_input_cbp_date));
            return false;
        }
        if (StringUtils.isEmpty(anti_ds_dna_ab_inputvalue)) {
            UIHelper.toastMessage(this, getString(R.string.msg_input_dna_date));
            return false;
        }
        if (StringUtils.isEmpty(complement_inputvalue)) {
            UIHelper.toastMessage(this, getString(R.string.msg_input_comp_date));
            return false;
        }
        if (StringUtils.isEmpty(urine_microscopy_inputvalue)) {
            UIHelper.toastMessage(this, getString(R.string.msg_input_urine_date));
            return false;
        }
//
        return true;

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

                boolean flag = checkSubmit();
                if(flag) {
                    SPatientD spd = mAppContext.getPatientD();
                    spd.setCast(value_cast);
                    spd.setHematuria(value_hematuria);
                    spd.setPyuria(value_pyuria);
                    spd.setProteinuria(value_proteinuria);
                    spd.setAntiDsDnaAbValue(value_anti_ds_dna_ab);
                    spd.setC3(value_complement3);
                    spd.setC4(value_complement4);
//                if(hb_input.getText()!=null) spd.setHb(StringUtils.toFloat(hb_input.getText().toString()));
                    if (wbc_input.getText() != null)
                        spd.setWbc(StringUtils.toFloat(wbc_input.getText().toString()));
                    if (plt_input.getText() != null)
                        spd.setPlt(StringUtils.toFloat(plt_input.getText().toString()));
                    try {
                        if (cbp_input.getText() != null && !cbp_input.getText().equals(""))
                            spd.setCbp(sdft.parse(cbp_input.getText().toString()));
                        if (anti_ds_dna_ab_input.getText() != null && !anti_ds_dna_ab_input.getText().equals(""))
                            spd.setAntiDsDnaAb(sdft.parse(anti_ds_dna_ab_input.getText().toString()));
                        if (complement_input.getText() != null && !complement_input.getText().equals(""))
                            spd.setComplement(sdft.parse(complement_input.getText().toString()));
                        if (urine_microscopy_input.getText() != null && !urine_microscopy_input.getText().equals(""))
                            spd.setUrineMicroscopy(sdft.parse(urine_microscopy_input.getText().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    score = 0;
                    if (spd.getSeizure().equals("1")) score += 8;
                    if (spd.getPsychosis().equals("1")) score += 8;
                    if (spd.getOrganicBrainSyndrome().equals("1")) score += 8;
                    if (spd.getVisualDisturbance().equals("1")) score += 8;
                    if (spd.getCranialNerveDisorder().equals("1")) score += 8;
                    if (spd.getLupusHeadache().equals("1")) score += 8;
                    if (spd.getCva().equals("1")) score += 8;
                    if (spd.getVasculitis().equals("1")) score += 8;


                    if (spd.getArthritis().equals("1")) score += 4;
                    if (spd.getMyositis().equals("1")) score += 4;
                    if (spd.getCast().equals("1")) score += 4;
                    if (spd.getHematuria().equals("1")) score += 4;
                    if (spd.getProteinuria().equals("1")) score += 4;
                    if (spd.getPyuria().equals("1")) score += 4;
//
                    if (spd.getRash().equals("1")) score += 2;
                    if (spd.getAlopecia().equals("1")) score += 2;
                    if (spd.getMucosalUlcers().equals("1")) score += 2;
                    if (spd.getPleurisy().equals("1")) score += 2;
                    if (spd.getPericarditis().equals("1")) score += 2;
                    if (spd.getAntiDsDnaAbValue().equals("1")) score += 2;
                    //complemnent
                    //binding
                    if (spd.getC3().equals("1") && spd.getC4().equals("1")) {
                        score += 2;
                    } else if (spd.getC3().equals("1") && spd.getC4().equals("0")) {
                        score += 2;
                    } else if (spd.getC3().equals("0") && spd.getC4().equals("1")) {
                        score += 2;
                    }

                    if (spd.getFever().equals("1")) score += 1;
                    if (spd.getWbc() < 3) score += 1;
                    if (spd.getPlt() < 100) score += 1;
                    //thrombocytopenia
                    //leukopenia

                    spd.setScore(score);
                    mAppContext.savePatientD(spd);
                    if (pt_flag.equals("0") || pt_flag.equals("2")) {
                        SleApi.putPatientD(spd, new HttpCallback() {
                            @Override
                            public void onSuccess(String t) {
                                super.onSuccess(t);
                                Toast.makeText(AppContext.getInstance(), "保存成功", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(int errorNo, String strMsg) {
                                super.onFailure(errorNo, strMsg);
                                Toast.makeText(AppContext.getInstance(), "保存失败~", Toast.LENGTH_SHORT).show();
                            }

                        });
                    } else {
                        SleApi.updatePatientD(spd, new HttpCallback() {
                            @Override
                            public void onSuccess(String t) {
                                super.onSuccess(t);
                                Toast.makeText(AppContext.getInstance(), "更新成功", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(int errorNo, String strMsg) {
                                super.onFailure(errorNo, strMsg);
                                Toast.makeText(AppContext.getInstance(), "更新失败~", Toast.LENGTH_SHORT).show();
                            }

                        });
                    }

                    UIHelper.showNewPatient5(NewPatientActivity4.this,pt_flag);
                }
                //finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    @OnClick({R.id.cast,R.id.hematuria,R.id.pyuria,R.id.proteinuria,
            R.id.cast_img,R.id.hematuria_img,R.id.pyuria_img,R.id.proteinuria_img,
            R.id.cbp_input,R.id.anti_ds_dna_ab_input,R.id.complement_input,R.id.urine_microscopy_input})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cast:
                setPopMessage(popView, getResources().getString(R.string.cast_help),cast.getText().toString());
                break;
            case R.id.cast_img:
                setPopMessage(popView, getResources().getString(R.string.cast_help),cast.getText().toString());
                break;
            case R.id.hematuria:
                setPopMessage(popView, getResources().getString(R.string.hematuria_help),hematuria.getText().toString());
                break;
            case R.id.hematuria_img:
                setPopMessage(popView, getResources().getString(R.string.hematuria_help),hematuria.getText().toString());
                break;
            case R.id.pyuria:
                setPopMessage(popView, getResources().getString(R.string.pyuria_help),pyuria.getText().toString());
                break;
            case R.id.pyuria_img:
                setPopMessage(popView, getResources().getString(R.string.pyuria_help),pyuria.getText().toString());
                break;
            case R.id.proteinuria:
                setPopMessage(popView, getResources().getString(R.string.proteinuria_help),proteinuria.getText().toString());
                break;
            case R.id.proteinuria_img:
                setPopMessage(popView, getResources().getString(R.string.proteinuria_help),proteinuria.getText().toString());
                break;
            case R.id.cbp_input:
                new DatePickerDialog(NewPatientActivity4.this, onDateSetListenercbp, mYear, mMonth, mDay).show();
                break;
            case R.id.anti_ds_dna_ab_input:
                new DatePickerDialog(NewPatientActivity4.this, onDateSetListenerab, mYear, mMonth, mDay).show();
                break;
            case R.id.complement_input:
                new DatePickerDialog(NewPatientActivity4.this, onDateSetListenercomp, mYear, mMonth, mDay).show();
                break;
            case R.id.urine_microscopy_input:
                new DatePickerDialog(NewPatientActivity4.this, onDateSetListenerur, mYear, mMonth, mDay).show();
                break;

            default:
                break;
        }
    }
    private void setPopMessage(View view, String message,String tile) {

        pop = T.getListPopupWindow(popView, NewPatientActivity4.this, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,true);
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
    @OnCheckedChanged({R.id.swh_cast,R.id.swh_hematuria,R.id.swh_pyuria,R.id.swh_proteinuria,
            R.id.swh_anti_ds_dna_ab,R.id.swh_complement3,R.id.swh_complement4})
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.swh_cast:
                if(buttonView.isChecked()){
                    value_cast="1";
                    //UIHelper.toastMessage(this, "on");
                }
                else {
                    value_cast="0";
                    //UIHelper.toastMessage(this, "off");
                }
                break;
            case R.id.swh_hematuria:
                if(buttonView.isChecked()){
                    value_hematuria="1";
                    //UIHelper.toastMessage(this, "on");
                }
                else {
                    value_hematuria="0";
                    //UIHelper.toastMessage(this, "off");
                }
                break;
            case R.id.swh_pyuria:
                if(buttonView.isChecked()){
                    value_pyuria="1";
                    //UIHelper.toastMessage(this, "on");
                }
                else {
                    value_pyuria="0";
                    //UIHelper.toastMessage(this, "off");
                }
                break;
            case R.id.swh_proteinuria:
                if(buttonView.isChecked()){
                    value_proteinuria="1";
                    //UIHelper.toastMessage(this, "on");
                }
                else {
                    value_proteinuria="0";
                    //UIHelper.toastMessage(this, "off");
                }
                break;
            case R.id.swh_anti_ds_dna_ab:
                if(buttonView.isChecked()){
                    value_anti_ds_dna_ab="1";
                    //UIHelper.toastMessage(this, "on");
                }
                else {
                    value_anti_ds_dna_ab="0";
                    //UIHelper.toastMessage(this, "off");
                }
                break;
            case R.id.swh_complement3:
                if(buttonView.isChecked()){
                    value_complement3="1";
                    //UIHelper.toastMessage(this, "on");
                }
                else {
                    value_complement3="0";
                    //UIHelper.toastMessage(this, "off");
                }
                break;
            case R.id.swh_complement4:
                if(buttonView.isChecked()){
                    value_complement4="1";
                    //UIHelper.toastMessage(this, "on");
                }
                else {
                    value_complement4="0";
                    //UIHelper.toastMessage(this, "off");
                }
                break;
        }
    }

    private DatePickerDialog.OnDateSetListener onDateSetListenercbp = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            String days;
            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append("0").append(mDay).toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append(mDay).toString();
                }

            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append("0").append(mDay).toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append(mDay).toString();
                }

            }
            cbp_input.setText(days);
        }
    };
    private DatePickerDialog.OnDateSetListener onDateSetListenerab = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            String days;
            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append("0").append(mDay).toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append(mDay).toString();
                }

            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append("0").append(mDay).toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append(mDay).toString();
                }

            }
            anti_ds_dna_ab_input.setText(days);
        }
    };
    private DatePickerDialog.OnDateSetListener onDateSetListenercomp = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            String days;
            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append("0").append(mDay).toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append(mDay).toString();
                }

            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append("0").append(mDay).toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append(mDay).toString();
                }

            }
            complement_input.setText(days);
        }
    };
    private DatePickerDialog.OnDateSetListener onDateSetListenerur = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            String days;
            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append("0").append(mDay).toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append(mDay).toString();
                }

            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append("0").append(mDay).toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append(mDay).toString();
                }

            }
            urine_microscopy_input.setText(days);
        }
    };
}