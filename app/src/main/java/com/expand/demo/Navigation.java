package com.expand.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * 负责页面间跳转参数的封装
 */
public class Navigation {

    private Navigation() {

    }

    public static <T> void goPage(Context context, Class<T> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    public static <T> void goPage4Result(Activity activity, Class<T> cls, int requestCode) {
        Intent intent = new Intent(activity, cls);
        activity.startActivityForResult(intent, requestCode);
    }

}
