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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.kymjs.rxvolley.client.HttpCallback;
import com.qmh.sle.AppContext;
import com.qmh.sle.R;
import com.qmh.sle.api.SleApi;
import com.qmh.sle.bean.SPatient;
import com.qmh.sle.bean.SPatientD;
import com.qmh.sle.bean.User;
import com.qmh.sle.common.Contanst;
import com.qmh.sle.common.CyptoUtils;
import com.qmh.sle.common.StringUtils;
import com.qmh.sle.common.UIHelper;
import com.qmh.sle.ui.baseactivity.BaseActivity;
import com.qmh.sle.utils.JsonUtils;
import com.qmh.sle.utils.Store;
import com.qmh.sle.utils.T;
import com.qmh.sle.utils.Tools.CountrySortModel;
import com.qmh.sle.utils.TypefaceUtils;
import com.qmh.sle.widget.TipInfoLayout;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class NewPatientActivity extends BaseActivity implements
        OnClickListener{

    @InjectView(R.id.birthday)
    EditText birthday;
    @InjectView(R.id.country)
    EditText country;
    @InjectView(R.id.ptid)
    TextView ptid;
//    @InjectView(R.id.newptid)
//    TextView newptid;
    @InjectView(R.id.sexinput)
    TextView sexinput;
    @InjectView(R.id.rg)
    RadioGroup rg;
    @InjectView(R.id.male)
    RadioButton male;
    @InjectView(R.id.femle)
    RadioButton femle;
//    @InjectView(R.id.newptidtext)
//    TextView newptidtext;
    @InjectView(R.id.ptidtext)
    TextView ptidtext;
//    @InjectView(R.id.newptid_img)
//    ImageView newptid_img;
    @InjectView(R.id.ptid_img)
    ImageView ptid_img;

    private PopupWindow pop;
    private View popView;
    private AppContext mAppContext;
    private int mYear;
    private int mMonth;
    private int mDay;
    private String sex;
    private String countryId="+852";
    private Bitmap bitmap;
    private String pt_flag="";
    private SPatient sPatient;
    private SPatientD sPatientD;
    private User user;

    public static final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    public static final SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient);
        ButterKnife.inject(this);
        mAppContext = AppContext.getInstance();
        user=mAppContext.getLoginInfo();

        mActionBar.setTitle("ADD NEW PT");

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        popView = inflater.inflate(R.layout.popup_view, null);

        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        //只要对RadioGroup进行监听
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if(R.id.male == checkedId){
                    sex="1";
                    //UIHelper.toastMessage(mAppContext, "男");
                }
                else if(R.id.femle == checkedId){
                    sex="2";
                    //UIHelper.toastMessage(mAppContext, "女");
                }
            }
        });

        initView();
    }

    private void initView() {
        // 拿到传过来的对象
        Intent intent = getIntent();
        if(intent!=null){
            pt_flag  = intent.getStringExtra(Contanst.PATIENTFlAG);
            if(pt_flag.equals("1")) {
                sPatient = (SPatient) intent.getSerializableExtra(Contanst.PATIENT);
                countryId=sPatient.getCountryId();


                ptid.setText(sPatient.getPtid());
//                newptid.setText(sPatient.getNewptid());
                if(sPatient.getBirthday()!=null) {
                    birthday.setText(sdft.format(sPatient.getBirthday()));
                }
                //rg.setVisibility(View.GONE);
                if(!sPatient.getSexId().equals("") && !sPatient.getSexId().equals("0")){
                    if(sPatient.getSexId().equals("1")){
                        sex="1";
                        male.setChecked(true);
                    }
                    if(sPatient.getSexId().equals("2")){
                        sex="2";
                        femle.setChecked(true);
                    }
//                    sexinput.setVisibility(View.VISIBLE);
                }
                mAppContext.savePatient(sPatient);
                sPatientD = (SPatientD) intent.getSerializableExtra(Contanst.PATIENTD);
                mAppContext.clearPatientD();
                mAppContext.savePatientD(sPatientD);
            }

        }

        String sta = Store.getLanguageLocal(mAppContext);
        String[] countryList = null;
        if(sta.equals("zh_CN")) {
            countryList = getResources().getStringArray(R.array.country_code_list_ch);
        }else{
            countryList = getResources().getStringArray(R.array.country_code_list_en);
        }
        for (int i = 0, length = countryList.length; i < length; i++) {
            String[] countrys = countryList[i].split("\\*");
            String countryName = countrys[0];
            String countryNumber = countrys[1];
            if(countryId.equals(countryNumber)){
                country.setText(countryName);
            }

        }


    }

    private void initData() {


        // 记录项目的地址链接：

        // 截取屏幕
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (bitmap == null) {
                    bitmap = UIHelper.takeScreenShot(NewPatientActivity.this);
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
                boolean flag = checkSubmit();
                if(flag) {
                    if(pt_flag.equals("0")) {
                        SPatient sp = new SPatient();
                        sp.setPtid(ptid.getText().toString());
//                        sp.setNewptid(newptid.getText().toString());
                        sp.setCountryId(countryId);
                        if (country.getText() != null)
                            sp.setCountryId(country.getText().toString());
                        if (sex != null) sp.setSexId(sex);
                        try {
                            sp.setCreateDate(fmt.parse(fmt.format(new Date())));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        sp.setDepartid(user.getDepartid());
                        final String[] temp = {""};
                        SleApi.putPatient(sp, new HttpCallback() {
                            @Override
                            public void onSuccess(String t) {
                                super.onSuccess(t);

                                try {
                                    t = t.replaceAll("\r\n", "");
                                    SPatient sptt= JsonUtils.json2Bean(t, SPatient.class);
                                    temp[0] = sptt.getId();

                                    mAppContext.savePatient(sptt);
                                    Toast.makeText(AppContext.getInstance(), "保存成功" + temp[0], Toast.LENGTH_SHORT).show();


                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                SPatientD spd = new SPatientD();
                                spd.setStptid(temp[0]);
                                try {
                                    spd.setCreateDate(fmt.parse(fmt.format(new Date())));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                mAppContext.savePatientD(spd);
                            }

                            @Override
                            public void onFailure(int errorNo, String strMsg) {
                                super.onFailure(errorNo, strMsg);
                                Toast.makeText(AppContext.getInstance(), "保存失败~", Toast.LENGTH_SHORT).show();
                            }

                        });
                    }else{
                        sPatient.setSexId(sex);
                        if (country.getText() != null)
                            sPatient.setCountryId(country.getText().toString());
                        if (ptid.getText() != null)
                            sPatient.setPtid(ptid.getText().toString());
//                        if (newptid.getText() != null)
//                            sPatient.setNewptid(newptid.getText().toString());
                        if (country.getText() != null)
                            sPatient.setCountryId(country.getText().toString());
                        try {
                            if(birthday.getText()!=null)
                            sPatient.setBirthday(sdft.parse(birthday.getText().toString()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        sPatient.setCountryId(countryId);
                        mAppContext.savePatient(sPatient);
                        SleApi.updatePatient(sPatient, new HttpCallback() {
                            @Override
                            public void onSuccess(String t) {
                                super.onSuccess(t);
                                String temp = "";
                                Toast.makeText(AppContext.getInstance(), "更新成功" + temp, Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onFailure(int errorNo, String strMsg) {
                                super.onFailure(errorNo, strMsg);
                                Toast.makeText(AppContext.getInstance(), "更新失败~", Toast.LENGTH_SHORT).show();
                            }

                        });
                    }
                    sPatient=mAppContext.getPatient();
                    //finish();
                    UIHelper.showNewPatient2(NewPatientActivity.this,sPatient.getId(),pt_flag);
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean checkSubmit() {
        String ptidv = ptid.getText().toString();
//        String newptidv = newptid.getText().toString();

        //检查用户输入的参数
        if (StringUtils.isEmpty(ptidv)) {
            UIHelper.toastMessage(this, getString(R.string.msg_input_pid));
            return false;
        }
//        if (StringUtils.isEmpty(newptidv)) {
//            UIHelper.toastMessage(this, getString(R.string.msg_input_newptid));
//            return false;
//        }
        return true;

    }



    @Override
    @OnClick({R.id.birthday,R.id.country,R.id.ptidtext,R.id.ptid_img})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.birthday:
                new DatePickerDialog(NewPatientActivity.this, onDateSetListener, mYear, mMonth, mDay).show();
                break;
            case R.id.country:
                //UIHelper.showCountry(NewPatientActivity.this);
                Intent intent =  new Intent();
                intent.setClass(NewPatientActivity.this, CountryActivity.class);
                startActivityForResult(intent, 12);
                break;
            case R.id.ptid_img:
                setPopMessage(popView, getResources().getString(R.string.ptid_help),ptidtext.getText().toString());
                break;
            case R.id.ptidtext:
                setPopMessage(popView, getResources().getString(R.string.ptid_help),ptidtext.getText().toString());
                break;
            default:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // TODO Auto-generated method stub
        switch (requestCode)
        {
            case 12:
                if (resultCode == RESULT_OK)
                {
                    Bundle bundle = data.getExtras();
                    String countryName = bundle.getString("countryName");
                    countryId = bundle.getString("countryNumber");

                    country.setText(countryName);
                    //tv1.setText(countryName);
                }
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
//    @OnCheckedChanged({R.id.swh_status})
//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        switch (buttonView.getId()){
//            case R.id.swh_status:
//                if(buttonView.isChecked())  UIHelper.toastMessage(this, "on");
//                else  UIHelper.toastMessage(this, "off");;
//                break;
//        }
//    }


    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

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
            birthday.setText(days);
        }
    };

    private void setPopMessage(View view, String message,String tile) {

        pop = T.getListPopupWindow(popView, NewPatientActivity.this, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,true);
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

    private void setPopMessage(View view,String tile) {

        pop = T.getListPopupWindow(popView, NewPatientActivity.this, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,true);
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
        WebView webview = ((WebView)view.findViewById(R.id.pop_webview));
        webview.loadUrl("file:///android_asset/hospital.html");
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        try {
            int version = android.os.Build.VERSION.SDK_INT;
            if (version >= 11) {
                // 这个方法在API level 11 以上才可以调用，不然会发生异常
                settings.setDisplayZoomControls(false);
            }
        } catch (NumberFormatException e) {

        }
        settings.setUseWideViewPort(true);

    }

}