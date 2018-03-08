package com.qmh.sle.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.format.bg.ICellBackgroundFormat;
import com.bin.david.form.data.format.draw.ImageResDrawFormat;
import com.bin.david.form.data.format.sequence.ISequenceFormat;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.style.LineStyle;
import com.bin.david.form.data.table.ArrayTableData;
import com.bin.david.form.utils.DensityUtils;
import com.kymjs.rxvolley.client.HttpCallback;
import com.qmh.sle.AppContext;
import com.qmh.sle.R;
import com.qmh.sle.api.SleApi;
import com.qmh.sle.bean.SPatient;
import com.qmh.sle.bean.SPatientD;
import com.qmh.sle.common.Contanst;
import com.qmh.sle.common.UIHelper;
import com.qmh.sle.ui.baseactivity.BaseActivity;
import com.qmh.sle.utils.JsonUtils;

import java.text.SimpleDateFormat;
import java.util.List;

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
public class NewPatientActivity6 extends BaseActivity implements
        OnClickListener,CompoundButton.OnCheckedChangeListener{

    @InjectView(R.id.ptid)
    TextView ptid;
    @InjectView(R.id.sledai2k)
    TextView sledai2k;
    @InjectView(R.id.ptid_input)
    TextView ptid_input;
    @InjectView(R.id.sledai2k_input)
    TextView sledai2k_input;

    @InjectView(R.id.table)
    SmartTable<Integer> table;
    float score=0;


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
        setContentView(R.layout.activity_new_patient6);
        ButterKnife.inject(this);
        mAppContext = AppContext.getInstance();
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        popView = inflater.inflate(R.layout.popup_view, null);
        spd =  mAppContext.getPatientD();
        sp =  mAppContext.getPatient();

        mActionBar.setTitle("INDEX HISOTRY");
        datas = getDatas(mAppContext.getPdlist());
//        ptid_input.setText(sp.getPtid());
//        sledai2k_input.setText(String.valueOf(spd.getScore()));

        if(datas.size()>0)   initView();



    }

    private void initView() {
        // 拿到传过来的project对象
        Intent intent = getIntent();
        if(intent!=null) {
            pt_flag  = intent.getStringExtra(Contanst.PATIENTFlAG);
            if (pt_flag.equals("1")) {

            }
        }
        FontStyle fontStyle = new FontStyle(this,10, ContextCompat.getColor(this,R.color.section_border));

        table.getConfig().setColumnTitleStyle(fontStyle);
        table.getConfig().setHorizontalPadding(10);
        table.getConfig().setYSequenceBgFormat(new ICellBackgroundFormat<Integer>() {
            @Override
            public void drawBackground(Canvas canvas, Rect rect, Integer integer, Paint paint) {

            }

            @Override
            public int getTextColor(Integer integer) {
                return ContextCompat.getColor(NewPatientActivity6.this,R.color.red);
            }
        });
        table.getConfig().setVerticalPadding(5);
        table.getConfig().setHorizontalPadding(5);
        LineStyle lineStyle = new LineStyle();
        lineStyle.setColor(ContextCompat.getColor(this,android.R.color.transparent));
        table.getConfig().setGridStyle(lineStyle);
        table.getConfig().setShowXSequence(true);
        //table.getConfig().setColumnTitleHorizontalPadding(1);
        table.getConfig().setFixedYSequence(true);//暂时有问题 ，后面修复
        table.getConfig().setFixedXSequence(true);
        table.setZoom(true,1,0.5f);


        Integer[][] data = new Integer[datas.size()][23];
        //构造假数据
        for(int i = 0;i <datas.size(); i++){
            SPatientD spdd = datas.get(i);
            //Integer[] column = new Integer[23];
            for(int j= 0;j <23; j++){
                if(j==0) data[i][j]=spdd.getSeizure().equals("1")?1:0;
                if(j==1) data[i][j]=spdd.getPsychosis().equals("1")?1:0;
                if(j==2) data[i][j]=spdd.getOrganicBrainSyndrome().equals("1")?1:0;
                if(j==3) data[i][j]=spdd.getVisualDisturbance().equals("1")?1:0;
                if(j==4) data[i][j]=spdd.getCranialNerveDisorder().equals("1")?1:0;
                if(j==5) data[i][j]=spdd.getLupusHeadache().equals("1")?1:0;
                if(j==6) data[i][j]=spdd.getCva().equals("1")?1:0;
                if(j==7) data[i][j]=spdd.getFever().equals("1")?1:0;
                if(j==8) data[i][j]=spdd.getRash().equals("1")?1:0;
                if(j==9) data[i][j]=spdd.getAlopecia().equals("1")?1:0;
                if(j==10) data[i][j]=spdd.getMucosalUlcers().equals("1")?1:0;
                if(j==11) data[i][j]=spdd.getArthritis().equals("1")?1:0;
                if(j==12) data[i][j]=spdd.getMyositis().equals("1")?1:0;
                if(j==13) data[i][j]=spdd.getVasculitis().equals("1")?1:0;
                if(j==14) data[i][j]=spdd.getPleurisy().equals("1")?1:0;
                if(j==15) data[i][j]=spdd.getPericarditis().equals("1")?1:0;
                if(j==16) data[i][j]=spdd.getAntiDsDnaAbValue().equals("1")?1:0;
                if(j==17) data[i][j]=spdd.getC3().equals("1")?1:0;
                if(j==18) data[i][j]=spdd.getC4().equals("1")?1:0;
                if(j==19) data[i][j]=spdd.getCast().equals("1")?1:0;
                if(j==20) data[i][j]=spdd.getHematuria().equals("1")?1:0;
                if(j==21) data[i][j]=spdd.getPyuria().equals("1")?1:0;
                if(j==22) data[i][j]=spdd.getProteinuria().equals("1")?1:0;

            }
        }
        int sizew = DensityUtils.dp2px(this,71);
        int sizeh = DensityUtils.dp2px(this,36);
        final ArrayTableData<Integer> tableData = ArrayTableData.create(table, "All SLE", data, new ImageResDrawFormat<Integer>(sizew,sizeh) {
            @Override
            protected Context getContext() {
                return NewPatientActivity6.this;
            }

            @Override
            protected int getResourceID(Integer status, String value, int position) {
                if(status == null){return 0;}
                switch (status){
                    case 0:
                        return R.mipmap.green;
                    case 1:
                        return R.mipmap.red;
                }
                return 0;
            }


        });
//        List<CellRange>  rangecell=new ArrayList<CellRange>();
//        CellRange cellRange = new CellRange(0,10,0,0);
//        rangecell.add(cellRange);
//        tableData.setUserCellRange(rangecell);
        tableData.setXSequenceFormat(new ISequenceFormat() {
            @Override
            public String format(Integer integer) {
                String temp="           ";
                            for(int i=integer;i<=datas.size();i++) {
                                temp = sdft.format(datas.get(integer-1).getCreateDate());
                               break;
                            }
                return temp;
            }
        });
        tableData.setYSequenceFormat(new ISequenceFormat() {
            @Override
            public String format(Integer integer) {
                String temp="                  ";
                switch (integer){
                    case 1:
                        temp = "seizure";
                        break;
                    case 2:
                        temp = "psychosis";
                        break;
                    case 3:
                        temp = "organic_brain_syndrome";
                        break;
                    case 4:
                        temp = "visual_disturbance";
                        break;
                    case 5:
                        temp = "cranial_nerve_disorder";
                        break;
                    case 6:
                        temp = "lupus_headache";
                        break;
                    case 7:
                        temp = "cva";
                        break;
                    case 8:
                        temp = "fever";
                        break;
                    case 9:
                        temp = "rash";
                        break;
                    case 10:
                        temp = "alopecia";
                        break;
                    case 11:
                        temp = "mucosal_mlcers";
                        break;
                    case 12:
                        temp = "arthritis";
                        break;
                    case 13:
                        temp = "myositis";
                        break;
                    case 14:
                        temp = "vasculitis";
                        break;
                    case 15:
                        temp = "pleurisy";
                        break;
                    case 16:
                        temp = "pericarditis";
                        break;
                    case 17:
                        temp = "anti_ds_dna_ab";
                        break;
                    case 18:
                        temp = "low c3";
                        break;
                    case 19:
                        temp = "low c4";
                        break;
                    case 20:
                        temp = "cast";
                        break;
                    case 21:
                        temp = "hematuria";
                        break;
                    case 22:
                        temp = "pyuria";
                        break;
                    case 23:
                        temp = "proteinuria";
                        break;
                }
                return temp;
            }
        });
        table.setTableData(tableData);
        // set font icon


    }
    public List<SPatientD> getDatas(byte[] responeString) {
        return JsonUtils.getList(SPatientD[].class, responeString);
    }

    private void initData() {


        // 记录项目的地址链接：

        // 截取屏幕
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (bitmap == null) {
                    bitmap = UIHelper.takeScreenShot(NewPatientActivity6.this);
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

                Intent intent = new Intent();
                intent.setClass(NewPatientActivity6.this, MainActivity.class);
                startActivity(intent);
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