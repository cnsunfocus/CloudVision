package com.xunda.cloudvision.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.View;

import com.xunda.cloudvision.Config;
import com.xunda.cloudvision.R;
import com.xunda.cloudvision.presenter.CorporateIntroPresenter;
import com.xunda.cloudvision.view.ICorporateIntroView;
import com.xunda.cloudvision.ui.fragment.WebViewFragment;
import com.xunda.cloudvision.ui.widget.CompanyTabItem;

/**
 * 企业详情页面
 * Created by yinglovezhuzhu@gmail.com on 2016/9/20.
 */

public class CorporateIntroActivity extends BaseActivity implements ICorporateIntroView {

    /** 企业荣誉页面 **/
    public static final int PAGE_CORPORATE_HONOR = 0;
    /** 企业文化页面 **/
    public static final int PAGE_CORPORATE_CULTURE = 1;
    /** 企业形象页面 **/
    public static final int PAGE_CORPORATE_IMAGE = 2;
    /** 企业简介页面 **/
    public static final int PAGE_CORPORATE_INTRO = 3;
    /** 企业环境页面 **/
    public static final int PAGE_CORPORATE_ENVIRONMENT = 4;

    private CorporateIntroPresenter mCorporateIntroPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_corporate_intro);

        mCorporateIntroPresenter = new CorporateIntroPresenter(this);

        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_corporate_intro_back:
                finish(RESULT_CANCELED, null);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish(RESULT_CANCELED, null);
    }

    private void initView() {
        FragmentTabHost tabHost = (FragmentTabHost) findViewById(R.id.fth_corporate_intro_tabs);
        tabHost.setup(this, getSupportFragmentManager(), R.id.fl_corporate_intro_container);
        tabHost.getTabWidget().setDividerDrawable(null);

        addTabs(tabHost);

        findViewById(R.id.btn_corporate_intro_back).setOnClickListener(this);

        Intent intent = getIntent();
        if(null != intent && intent.hasExtra(Config.EXTRA_DATA)) {
            int page = intent.getIntExtra(Config.EXTRA_DATA, PAGE_CORPORATE_CULTURE);
            tabHost.setCurrentTab(page);
        }
    }

    /**
     * 添加标签
     * @param tabHost
     */
    private void addTabs(FragmentTabHost tabHost) {
        CompanyTabItem honorTab = new CompanyTabItem(this, R.string.str_corporate_honor);
        CompanyTabItem cultureTab = new CompanyTabItem(this, R.string.str_corporate_culture);
        CompanyTabItem imageTab = new CompanyTabItem(this, R.string.str_corporate_image);
        CompanyTabItem introTab = new CompanyTabItem(this, R.string.str_corporate_intro);
        CompanyTabItem environmentTab = new CompanyTabItem(this, R.string.str_corporate_environment);

        // 企业荣誉
        final Bundle honorArgs = new Bundle();
        honorArgs.putString(Config.EXTRA_DATA, "https://www.baidu.com");
        tabHost.addTab(tabHost.newTabSpec("honorTab").setIndicator(honorTab),
                WebViewFragment.class, honorArgs);

        // 企业文化
        final Bundle cultureArgs = new Bundle();
        cultureArgs.putString(Config.EXTRA_DATA, "https://www.so.com/");
        tabHost.addTab(tabHost.newTabSpec("cultureTab").setIndicator(cultureTab),
                WebViewFragment.class, cultureArgs);

        // 企业形象
        final Bundle imageArgs = new Bundle();
        imageArgs.putString(Config.EXTRA_DATA, "http://dict.youdao.com/");
        tabHost.addTab(tabHost.newTabSpec("imageTab").setIndicator(imageTab),
                WebViewFragment.class, imageArgs);

        // 企业简介
        final Bundle introArgs = new Bundle();
        introArgs.putString(Config.EXTRA_DATA, "http://www.liantu.com/");
        tabHost.addTab(tabHost.newTabSpec("introTab").setIndicator(introTab),
                WebViewFragment.class, introArgs);

        // 企业环境
        final Bundle environmentArgs = new Bundle();
        environmentArgs.putString(Config.EXTRA_DATA, "http://www.express8.cn/");
        tabHost.addTab(tabHost.newTabSpec("environmentTab").setIndicator(environmentTab),
                WebViewFragment.class, environmentArgs);
    }
}