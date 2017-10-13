package com.expand.demo.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.expand.demo.utils.IMEUtils;
import com.expand.demo.expandlistviewdemo.R;

import java.util.Date;

/**
 * Created by hb on 16/3/29.
 */
public abstract class BaseAppCatActivity
        extends AppCompatActivity{
    private long                  mLastBackTime = 0;
    private long                  TIME_DIFF     = 2 * 1000;
    private Toolbar               mToolbar;

    protected abstract int initLayout();

    protected abstract void initView();

    protected abstract void initData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //全部禁止横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
//        ButterKnife.bind(this);
        initView();
        initData();
    }



    public void initToolBar() {
        mToolbar = findView(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void setTooBarBackBtn() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMEUtils.hideIME(BaseAppCatActivity.this);
                finish();
            }
        });
    }

    public void setTooBarBackBtn(OnClickListener listener) {
        mToolbar.setNavigationOnClickListener(listener);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 设置导航栏标题
     *
     * @param title
     */
    protected final void setActionTitle(String title) {
        TextView titleTv = findView(R.id.tv_action_title);
        if (titleTv != null && !TextUtils.isEmpty(title)) {
            titleTv.setText(title);
        }
    }

    /**
     * 设置导航栏标题
     *
     * @param resId
     */
    protected final void setActionTitle(int resId) {
        String title = getResources().getString(resId);
        setActionTitle(title);
    }

    /**
     * 通过控件的Id获取对应的控件
     *
     * @param viewId
     * @return
     */
    @SuppressWarnings("unchecked")
    protected final <U extends View> U findView(int viewId) {
        View view = findViewById(viewId);
        return (U) view;
    }

    /**
     * 给id设置监听
     *
     * @param ids
     */
    protected final void setOnClick(int... ids) {
        if (ids == null) {
            return;
        }
        for (int i : ids) {
            setOnClick(this.<View> findView(i));
        }
    }

    /**
     * 给view设置监听
     *
     * @param params
     */
    protected final void setOnClick(View... params) {
        if (params == null) {
            return;
        }

        for (View view : params) {
            if (view != null && this instanceof OnClickListener) {
                view.setOnClickListener((OnClickListener) this);
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //友盟统计
        //MobclickAgent.onPause(this);
        //极光推送统计
        //JPushInterface.onPause(this);
    }

    public void finishActivity() {
        finish();
    }

    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isTaskRoot()) {
                long now = new Date().getTime();
                if (now - mLastBackTime < TIME_DIFF) {
                    return super.onKeyDown(keyCode, event);
                } else {
                    mLastBackTime = now;
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
