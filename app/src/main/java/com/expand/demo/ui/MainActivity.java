package com.expand.demo.ui;

import android.view.View;

import com.expand.demo.Navigation;
import com.expand.demo.base.BaseAppCatActivity;
import com.expand.demo.expandlistviewdemo.R;

public class MainActivity extends BaseAppCatActivity implements View.OnClickListener {

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        setOnClick(R.id.tv_demo_0, R.id.tv_demo_1, R.id.tv_demo_2, R.id.tv_demo_3);
        initToolBar();

    }

    @Override
    protected void initData() {
        setActionTitle("Demo");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_demo_0:
                Navigation.goPage(this, NormalExpandActivity.class);
                break;
            case R.id.tv_demo_1:
                Navigation.goPage(this, CustomExpandActivity.class);
                break;
            case R.id.tv_demo_2:
                Navigation.goPage(this, CustomizeIndicatorActivity.class);
                break;
            case R.id.tv_demo_3:
                Navigation.goPage(this,ExpandOnceActivity.class);
                break;
        }
    }
}
