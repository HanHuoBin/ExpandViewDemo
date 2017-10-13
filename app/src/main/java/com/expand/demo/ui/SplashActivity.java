package com.expand.demo.ui;

import android.os.Handler;
import android.util.Log;

import com.expand.demo.Navigation;
import com.expand.demo.base.BaseAppCatActivity;
import com.expand.demo.expandlistviewdemo.R;

/**
 * Created by hb on 16/4/14.
 */
public class SplashActivity extends BaseAppCatActivity {

    @Override
    protected int initLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("splash","1111");
                Navigation.goPage(SplashActivity.this, MainActivity.class);
                finish();
            }
        }, 1500);
    }


    @Override
    protected void initData() {

    }
}
