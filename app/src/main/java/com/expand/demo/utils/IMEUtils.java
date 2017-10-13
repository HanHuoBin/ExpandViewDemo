package com.expand.demo.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by hb on 16/4/14.
 */
public class IMEUtils {
    /**
     * 获取软键盘状态
     *
     * @param context
     * @return true 打开;false 关闭
     */
    public static boolean getIMEStatus(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();//isOpen若返回true，则表示输入法打开

        if (isOpen) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取键盘是否已经打开
     */
    public static boolean isIMEShowing(Activity context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.isAcceptingText();
    }

    /**
     * 改变软键盘状态（打开则关闭，关闭则打开）
     *
     * @param context
     */
    public static void setIMEHideOrShow(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 在某个Activity中隐藏输入法
     *
     * @param context
     */
    public static void hideIME(Activity context) {
        try {
            ((InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }

    /**
     * 隐藏键盘
     */
    public static void hideIME(Activity context, View view) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 显示键盘
     *
     * @param view
     */
    public static void showIME(Activity context, View view) {

        if (android.os.Build.VERSION.SDK_INT < 14) {

            InputMethodManager imm = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

            if (view != null) {
                imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
            }
        } else {
            showIMEOtherWay(view);
        }
    }

    /**
     * 其他方式显示键盘
     *
     * @param view
     */
    public static void showIMEOtherWay(final View view) {
        (new Handler()).postDelayed(new Runnable() {
            public void run() {
                view.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
                        SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
                view.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
                        SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));
            }
        }, 200);
    }
}
