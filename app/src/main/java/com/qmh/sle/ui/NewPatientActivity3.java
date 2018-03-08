package com.qmh.sle.ui;

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
import android.widget.ImageView;
import android.widget.PopupWindow;
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
public class NewPatientActivity3 extends BaseActivity implements
        OnClickListener,CompoundButton.OnCheckedChangeListener{

    @InjectView(R.id.fever)
    TextView fever;
    @InjectView(R.id.rash)
    TextView rash;
    @InjectView(R.id.alopecia)
    TextView alopecia;
    @InjectView(R.id.mucosal_mlcers)
    TextView mucosal_mlcers;
    @InjectView(R.id.arthritis)
    TextView arthritis;
    @InjectView(R.id.myositis)
    TextView myositis;
    @InjectView(R.id.vasculitis)
    TextView vasculitis;
    @InjectView(R.id.pleurisy)
    TextView pleurisy;
    @InjectView(R.id.pericarditis)
    TextView pericarditis;

    @InjectView(R.id.fever_img)
    ImageView fever_img;
    @InjectView(R.id.rash_img)
    ImageView rash_img;
    @InjectView(R.id.alopecia_img)
    ImageView alopecia_img;
    @InjectView(R.id.mucosal_mlcers_img)
    ImageView mucosal_mlcers_img;
    @InjectView(R.id.arthritis_img)
    ImageView arthritis_img;
    @InjectView(R.id.myositis_img)
    ImageView myositis_img;
    @InjectView(R.id.vasculitis_img)
    ImageView vasculitis_img;
    @InjectView(R.id.pleurisy_img)
    ImageView pleurisy_img;
    @InjectView(R.id.pericarditis_img)
    ImageView pericarditis_img;

    @InjectView(R.id.swh_fever)
    Switch swh_fever;
    @InjectView(R.id.swh_rash)
    Switch swh_rash;
    @InjectView(R.id.swh_alopecia)
    Switch swh_alopecia;
    @InjectView(R.id.swh_mucosal_mlcers)
    Switch swh_mucosal_mlcers;
    @InjectView(R.id.swh_arthritis)
    Switch swh_arthritis;
    @InjectView(R.id.swh_myositis)
    Switch swh_myositis;
    @InjectView(R.id.swh_vasculitis)
    Switch swh_vasculitis;
    @InjectView(R.id.swh_pleurisy)
    Switch swh_pleurisy;
    @InjectView(R.id.swh_pericarditis)
    Switch swh_pericarditis;


    String value_fever="0";
    String value_rash="0";
    String value_alopecia="0";
    String value_mucosal_mlcers="0";
    String value_arthritis="0";
    String value_myositis="0";
    String value_vasculitis="0";
    String value_pleurisy="0";
    String value_pericarditis="0";
    private AppContext mAppContext;
    private View popView;
    private PopupWindow pop;
    private Bitmap bitmap;
    private String pt_flag="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient3);
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
                if(spd.getFever().equals("1")){
                    swh_fever.setChecked(true);
                    value_fever="1";
                }
                if(spd.getRash().equals("1")){
                    swh_rash.setChecked(true);
                    value_rash="1";
                }
                if(spd.getAlopecia().equals("1")){
                    swh_alopecia.setChecked(true);
                    value_alopecia="1";
                }
                if(spd.getMucosalUlcers().equals("1")){
                    swh_mucosal_mlcers.setChecked(true);
                    value_mucosal_mlcers="1";
                }
                if(spd.getArthritis().equals("1")){
                    swh_arthritis.setChecked(true);
                    value_arthritis="1";
                }
                if(spd.getMyositis().equals("1")){
                    swh_myositis.setChecked(true);
                    value_myositis="1";
                }
                if(spd.getVasculitis().equals("1")){
                    swh_vasculitis.setChecked(true);
                    value_vasculitis="1";
                }
                if(spd.getPleurisy().equals("1")){
                    swh_pleurisy.setChecked(true);
                    value_pleurisy="1";
                }
                if(spd.getPericarditis().equals("1")){
                    swh_pericarditis.setChecked(true);
                    value_pericarditis="1";
                }
            }
        }


        // set font icon


    }

    private void initData() {
        mActionBar.setTitle("");


        // 记录项目的地址链接：

        // 截取屏幕
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (bitmap == null) {
                    bitmap = UIHelper.takeScreenShot(NewPatientActivity3.this);
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
                    spd.setFever(value_fever);
                    spd.setRash(value_rash);
                    spd.setAlopecia(value_alopecia);
                    spd.setMucosalUlcers(value_mucosal_mlcers);
                    spd.setArthritis(value_arthritis);
                    spd.setMyositis(value_myositis);
                    spd.setVasculitis(value_vasculitis);
                    spd.setPleurisy(value_pleurisy);
                    spd.setPericarditis(value_pericarditis);
                    mAppContext.savePatientD(spd);
                //finish();
                UIHelper.showNewPatient4(NewPatientActivity3.this,pt_flag);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    @OnClick({R.id.fever,R.id.rash,R.id.alopecia,R.id.mucosal_mlcers,
            R.id.arthritis,R.id.myositis,R.id.vasculitis,R.id.pleurisy,R.id.pericarditis,
            R.id.fever_img,R.id.rash_img,R.id.alopecia_img,R.id.mucosal_mlcers_img,
            R.id.arthritis_img,R.id.myositis_img,R.id.vasculitis_img,R.id.pleurisy_img,R.id.pericarditis_img})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fever:
                setPopMessage(popView, getResources().getString(R.string.fever_help),fever.getText().toString());
                break;
            case R.id.fever_img:
                setPopMessage(popView, getResources().getString(R.string.fever_help),fever.getText().toString());
                break;
            case R.id.rash:
                setPopMessage(popView, getResources().getString(R.string.rash_help),rash.getText().toString());
                break;
            case R.id.rash_img:
                setPopMessage(popView, getResources().getString(R.string.rash_help),rash.getText().toString());
                break;
            case R.id.alopecia:
                setPopMessage(popView, getResources().getString(R.string.alopecia_help),alopecia.getText().toString());
                break;
            case R.id.alopecia_img:
                setPopMessage(popView, getResources().getString(R.string.alopecia_help),alopecia.getText().toString());
                break;
            case R.id.mucosal_mlcers:
                setPopMessage(popView, getResources().getString(R.string.mucosal_mlcers_help),mucosal_mlcers.getText().toString());
                break;
            case R.id.mucosal_mlcers_img:
                setPopMessage(popView, getResources().getString(R.string.mucosal_mlcers_help),mucosal_mlcers.getText().toString());
                break;
            case R.id.arthritis:
                setPopMessage(popView, getResources().getString(R.string.arthritis_help),arthritis.getText().toString());
                break;
            case R.id.arthritis_img:
                setPopMessage(popView, getResources().getString(R.string.arthritis_help),arthritis.getText().toString());
                break;
            case R.id.myositis:
                setPopMessage(popView, getResources().getString(R.string.myositis_help),myositis.getText().toString());
                break;
            case R.id.myositis_img:
                setPopMessage(popView, getResources().getString(R.string.myositis_help),myositis.getText().toString());
                break;
            case R.id.vasculitis:
                setPopMessage(popView, getResources().getString(R.string.vasculitis_help),vasculitis.getText().toString());
                break;
            case R.id.vasculitis_img:
                setPopMessage(popView, getResources().getString(R.string.vasculitis_help),vasculitis.getText().toString());
                break;
            case R.id.pleurisy:
                setPopMessage(popView, getResources().getString(R.string.pleurisy_help),pleurisy.getText().toString());
                break;
            case R.id.pleurisy_img:
                setPopMessage(popView, getResources().getString(R.string.pleurisy_help),pleurisy.getText().toString());
                break;
            case R.id.pericarditis:
                setPopMessage(popView, getResources().getString(R.string.pericarditis_help),pericarditis.getText().toString());
                break;
            case R.id.pericarditis_img:
                setPopMessage(popView, getResources().getString(R.string.pericarditis_help),pericarditis.getText().toString());
                break;
            default:
                break;
        }
    }
    private void setPopMessage(View view, String message,String tile) {

        pop = T.getListPopupWindow(popView, NewPatientActivity3.this, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,true);
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
    @OnCheckedChanged({R.id.swh_fever,R.id.swh_rash,R.id.swh_alopecia,R.id.swh_mucosal_mlcers,
            R.id.swh_arthritis,R.id.swh_myositis,R.id.swh_vasculitis,R.id.swh_pleurisy,R.id.swh_pericarditis})
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.swh_fever:
                if(buttonView.isChecked()){
                    value_fever="1";
                    //UIHelper.toastMessage(this, "on");
                }
                else {
                    value_fever="0";
                    //UIHelper.toastMessage(this, "off");
                }
                break;
            case R.id.swh_rash:
                if(buttonView.isChecked()){
                    value_rash="1";
                    //UIHelper.toastMessage(this, "on");
                }
                else {
                    value_rash="0";
                    //UIHelper.toastMessage(this, "off");
                }
                break;
            case R.id.swh_alopecia:
                if(buttonView.isChecked()){
                    value_alopecia="1";
                    //UIHelper.toastMessage(this, "on");
                }
                else {
                    value_alopecia="0";
                    //UIHelper.toastMessage(this, "off");
                }
                break;
            case R.id.swh_mucosal_mlcers:
                if(buttonView.isChecked()){
                    value_mucosal_mlcers="1";
                    //UIHelper.toastMessage(this, "on");
                }
                else {
                    value_mucosal_mlcers="0";
                    //UIHelper.toastMessage(this, "off");
                }
                break;
            case R.id.swh_arthritis:
                if(buttonView.isChecked()){
                    value_arthritis="1";
                    //UIHelper.toastMessage(this, "on");
                }
                else {
                    value_arthritis="0";
                    //UIHelper.toastMessage(this, "off");
                }
                break;
            case R.id.swh_myositis:
                if(buttonView.isChecked()){
                    value_myositis="1";
                    //UIHelper.toastMessage(this, "on");
                }
                else {
                    value_myositis="0";
                    //UIHelper.toastMessage(this, "off");
                }
                break;
            case R.id.swh_vasculitis:
                if(buttonView.isChecked()){
                    value_vasculitis="1";
                    //UIHelper.toastMessage(this, "on");
                }
                else {
                    value_vasculitis="0";
                    //UIHelper.toastMessage(this, "off");
                }
                break;
            case R.id.swh_pleurisy:
                if(buttonView.isChecked()){
                    value_pleurisy="1";
                    //UIHelper.toastMessage(this, "on");
                }
                else {
                    value_pleurisy="0";
                    //UIHelper.toastMessage(this, "off");
                }
                break;
            case R.id.swh_pericarditis:
                if(buttonView.isChecked()){
                    value_pericarditis="1";
                    //UIHelper.toastMessage(this, "on");
                }
                else {
                    value_pericarditis="0";
                    //UIHelper.toastMessage(this, "off");
                }
                break;
        }
    }


}