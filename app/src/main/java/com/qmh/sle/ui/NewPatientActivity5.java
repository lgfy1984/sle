package com.qmh.sle.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.kymjs.rxvolley.client.HttpCallback;
import com.qmh.sle.AppContext;
import com.qmh.sle.R;
import com.qmh.sle.api.SleApi;
import com.qmh.sle.bean.SPatient;
import com.qmh.sle.bean.SPatientD;
import com.qmh.sle.common.Contanst;
import com.qmh.sle.common.UIHelper;
import com.qmh.sle.ui.baseactivity.BaseActivity;
import com.qmh.sle.utils.DateUtil;
import com.qmh.sle.utils.JsonUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

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
public class NewPatientActivity5 extends BaseActivity implements
        OnClickListener,CompoundButton.OnCheckedChangeListener{

    @InjectView(R.id.ptid)
    TextView ptid;
    @InjectView(R.id.sledai2k)
    TextView sledai2k;
    @InjectView(R.id.ptid_input)
    TextView ptid_input;
    @InjectView(R.id.sledai2k_input)
    TextView sledai2k_input;

    @InjectView(R.id.chart1)
    LineChart mChart;


    private List<SPatientD> datas = null;
    private AppContext mAppContext;
    private SPatientD spd;
    private SPatient sp;
    private View popView;
    private PopupWindow pop;
    private Bitmap bitmap;
    private String pt_flag="";
    public static final SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient5);
        ButterKnife.inject(this);
        mAppContext = AppContext.getInstance();
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        popView = inflater.inflate(R.layout.popup_view, null);
        sp =  mAppContext.getPatient();
        mActionBar.setTitle("INDEX CHART");

        SleApi.getPatientD(sp.getId(),"1", new HttpCallback() {
            @Override
            public void onPreStart() {
                super.onPreStart();
            }

            @Override
            public void onPreHttp() {
                super.onPreHttp();
            }

            @Override
            public void onSuccessInAsync(byte[] t) {
                super.onSuccessInAsync(t);
                datas = getDatas(t);

            }

            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
            }

            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                mAppContext.savePtlist(t);

                if(datas!=null && datas.size()>0) {
                    ptid_input.setText(sp.getPtid());

                    int num = 0;
                    try {
                        num = DateUtil.getOffsetDays(sdft.parse(sdft.format(datas.get(0).getCreateDate())),sdft.parse(sdft.format(datas.get(datas.size()-1).getCreateDate())));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    initView();
                    Description description =new Description();
                    description.setText("SLEDAI-2K");
                    description.setTextSize(12);
                    description.setEnabled(true);
                    description.setPosition(300,40);
                    mChart.setDescription(description);

                    // enable touch gestures
                    mChart.setTouchEnabled(true);

                    mChart.setDragDecelerationFrictionCoef(0.9f);

                    // enable scaling and dragging
                    mChart.setDragEnabled(true);
                    mChart.setScaleEnabled(true);
                    mChart.setDrawGridBackground(false);
                    mChart.setHighlightPerDragEnabled(true);

                    // if disabled, scaling can be done on x- and y-axis separately
                    mChart.setPinchZoom(true);

                    // set an alternative background color
                    //mChart.setBackgroundColor(Color.LTGRAY);

                    // add data

                    setData(num, datas);

                    mChart.animateX(200);

                    // get the legend (only possible after setting data)
                    Legend l = mChart.getLegend();

                    // modify the legend ...
                    l.setForm(Legend.LegendForm.LINE);
                    l.setTypeface(mTfLight);
                    l.setTextSize(11f);
                    l.setTextColor(Color.BLACK);
                    l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
                    l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
                    l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
                    l.setDrawInside(false);
//        l.setYOffset(11f);

                    XAxis xAxis = mChart.getXAxis();
                    xAxis.setTypeface(mTfLight);
                    xAxis.setTextSize(11f);
//                    xAxis.setGranularity(1);
                    xAxis.setTextColor(Color.BLACK);
                    xAxis.setDrawGridLines(true);
                    xAxis.enableGridDashedLine(4,4,0);
                    xAxis.setDrawAxisLine(false);
                    xAxis.setAxisMinimum(1);
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
                    xAxis.setLabelRotationAngle(-75);//设置x轴字体显示角度
                    xAxis.setCenterAxisLabels(false);
                    xAxis.setGranularity(1f); // one hour
                    xAxis.setLabelCount(num);
//                    xAxis.setValueFormatter(new IAxisValueFormatter() {
//
//                        private SimpleDateFormat mFormat = new SimpleDateFormat("dd/MM");
//
//                        @Override
//                        public String getFormattedValue(float value, AxisBase axis) {
//
//                            long millis = TimeUnit.DAYS.toDays((long) value);
//                            return mFormat.format(new Date(millis));
//                        }
//                    });
//        xAxis.setAxisLineWidth(100f);

                    YAxis leftAxis = mChart.getAxisLeft();
                    leftAxis.setTypeface(mTfLight);
                    leftAxis.setTextColor(Color.BLACK);
                    leftAxis.setAxisMaximum(100f);
                    leftAxis.setAxisMinimum(0f);
                    leftAxis.enableGridDashedLine(4,4,0);
                    leftAxis.setDrawGridLines(true);
                    leftAxis.setGranularityEnabled(true);


                    mChart.getAxisRight().setEnabled(false);
                }
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }

            @Override
            public void onSuccess(Map<String, String> headers, Bitmap bitmap) {
                super.onSuccess(headers, bitmap);
            }
        });



//        YAxis rightAxis = mChart.getAxisRight();
//        rightAxis.setTypeface(mTfLight);
//        rightAxis.setTextColor(Color.RED);
//        rightAxis.setAxisMaximum(900);
//        rightAxis.setAxisMinimum(-200);
//        rightAxis.setDrawGridLines(false);
//        rightAxis.setDrawZeroLine(false);
//        rightAxis.setGranularityEnabled(false);

    }

    private void initView() {
        // 拿到传过来的project对象
        Intent intent = getIntent();
        if (intent != null) {
            pt_flag = intent.getStringExtra(Contanst.PATIENTFlAG);
            if (!pt_flag.equals("2")) {
                spd =  mAppContext.getPatientD();
                sledai2k_input.setText(String.valueOf(spd.getScore()));

            }else{
                ptid_input.setVisibility(View.GONE);
                ptid.setVisibility(View.GONE);
                sledai2k_input.setVisibility(View.GONE);
                sledai2k.setVisibility(View.GONE);
            }
        }
    }

    public List<SPatientD> getDatas(byte[] responeString) {
        return JsonUtils.getList(SPatientD[].class, responeString);
    }


    private void setData(int num, List<SPatientD> datas) {

        final ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        try {
            Date firstD = sdft.parse(sdft.format(datas.get(0).getCreateDate()));
            if (num == 0 && datas.size() >= 1) {
                Date temp2 = sdft.parse(sdft.format(datas.get(0).getCreateDate()));
                float val = datas.get(0).getScore();
                yVals1.add(new Entry(1, val, sdft.format(temp2)));
                yVals1.add(new Entry(2, 0, sdft.format(DateUtil.add(temp2, 5, 1))));
            }
            if (num > 0 && datas.size() > 1) {
                for (int i = 0; i <= num; i++) {
                    boolean cif = true;
                    Date temp = DateUtil.add(firstD, 5, i);
                    for (int j = 0; j < datas.size(); j++) {
                        Date temp2 = sdft.parse(sdft.format(datas.get(j).getCreateDate()));
                        if (DateUtil.isSameInstant(temp, temp2)) {
                            float val = datas.get(j).getScore();
                            yVals1.add(new Entry(i+1, val, sdft.format(temp2)));
                            cif = false;
                            break;
                        }
                    }
                    if (cif) {
                        yVals1.add(new Entry(i+1, 0, ""));
                    }
                }

            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }



        LineDataSet set1;

        if(yVals1.size()>0) {
            if (mChart.getData() != null &&
                    mChart.getData().getDataSetCount() > 0) {
                set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
                set1.setValues(yVals1);
                mChart.getData().notifyDataChanged();
                mChart.notifyDataSetChanged();
            } else {
                // create a dataset and give it a type
                set1 = new LineDataSet(yVals1, "score");

                set1.setAxisDependency(YAxis.AxisDependency.LEFT);
                set1.setLineWidth(2f);
                set1.setColor(Color.rgb(92, 57, 147));
                set1.setCircleRadius(4.5f);
                //set1.setFillAlpha(65);
                //set1.setFillColor(ColorTemplate.getHoloBlue());
                set1.setHighLightColor(Color.rgb(244, 117, 117));
                set1.setDrawCircleHole(true);
                set1.setCircleColor(Color.rgb(92, 57, 147));// 圆球的颜色
                set1.setCircleColorHole(Color.WHITE);// 填充折线上数据点、圆球里面包裹的中心空白处的颜色。
                //set1.setFillFormatter(new MyFillFormatter(0f));
                //set1.setDrawHorizontalHighlightIndicator(false);
//                set1.removeEntryByXValue(0L);
                set1.setVisible(true);
                //set1.setCircleHoleColor(Color.WHITE);

            mChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {

                    if(value>0) {
                        return yVals1.get((int) value-1).getData() + "";
                    }else{
                        return yVals1.get((int) value).getData() + "";
                    }

                }
            });


                // create a data object with the datasets
                LineData data = new LineData(set1);
                data.setValueTextColor(Color.RED);
                data.setValueTextSize(9f);

                // set data
                mChart.setData(data);
            }
        }
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
                    bitmap = UIHelper.takeScreenShot(NewPatientActivity5.this);
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


                UIHelper.showNewPatient6(NewPatientActivity5.this,pt_flag);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
//    @OnClick({R.id.cast,R.id.hematuria,R.id.pyuria,R.id.proteinuria,
//            R.id.cast_img,R.id.hematuria_img,R.id.pyuria_img,R.id.proteinuria_img})
    public void onClick(View v) {
    }
    private void setPopMessage(View view, String message,String tile) {
    }
//    @OnCheckedChanged({R.id.swh_cast,R.id.swh_hematuria,R.id.swh_pyuria,R.id.swh_proteinuria})
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    }


}