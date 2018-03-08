package com.qmh.sle.ui;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.kymjs.rxvolley.client.HttpCallback;

import com.qmh.sle.R;
import com.qmh.sle.ui.baseactivity.BaseActivity;
import com.qmh.sle.utils.Store;
import com.qmh.sle.widget.EnhanceListView;
import com.qmh.sle.widget.TipInfoLayout;

import org.greenrobot.eventbus.EventBus;


import butterknife.ButterKnife;
import butterknife.InjectView;

public class LanguageActivity extends BaseActivity implements
         OnItemClickListener {



    /**
     * The serialization (saved instance state) Bundle key representing the
     * current dropdown position.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_language);
        final String[] cities = {getString(R.string.lan_chinese), getString(R.string.lan_en), getString(R.string.lan_ja), getString(R.string.lan_de)};
        final String[] locals = {"zh_CN", "en", "ja", "de"};
        ButterKnife.inject(this);
        mActionBar.setDisplayShowTitleEnabled(false);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);

        AlertDialog.Builder builder = new AlertDialog.Builder(LanguageActivity.this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.select_language);
        builder.setItems(cities, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Store.setLanguageLocal(LanguageActivity.this, locals[which]);
                EventBus.getDefault().post("EVENT_REFRESH_LANGUAGE");
                finish();
            }
        });
        builder.show();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

    }
}
