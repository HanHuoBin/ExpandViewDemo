package com.expand.demo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 通用适配器的布局绑定类
 */
public class ViewHolder {
    private final SparseArray<View> mViews;        //在数据量不大的情况下，使用SparseArray，性能比HashMap更好
    private int                     mGroupPosition;
    private int                     mChildPosition;
    private int                     mLayoutId;
    private View                    mConvertView;

    /**
     * 适用于 一级ListVew
     * 
     * @param context
     * @param parent
     * @param layoutId
     * @param mGroupPosition
     */
    ViewHolder(Context context, ViewGroup parent, int layoutId, int mGroupPosition) {
        this.mGroupPosition = mGroupPosition;
        this.mLayoutId = layoutId;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        // setTag
        mConvertView.setTag(this);
    }

    /**
     * 适用于ExpandableListView
     * 
     * @param context
     * @param parent
     * @param layoutId
     * @param mGroupPosition
     * @param mChildPosition
     */
    ViewHolder(Context context, ViewGroup parent, int layoutId, int mGroupPosition,
               int mChildPosition) {
        this.mGroupPosition = mGroupPosition;
        this.mChildPosition = mChildPosition;
        this.mLayoutId = layoutId;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        // setTag
        mConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param mGroupPosition
     * @return
     */
    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId,
                                 int mGroupPosition) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, mGroupPosition);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mGroupPosition = mGroupPosition;
            return holder;
        }
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param mGroupPosition
     * @param mChildPosition
     * @return
     */
    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId,
                                 int mGroupPosition, int mChildPosition) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, mGroupPosition, mChildPosition);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mGroupPosition = mGroupPosition;
            holder.mChildPosition = mChildPosition;
            return holder;
        }
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, CharSequence text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /**
     * 加载小图
     *
     * @param viewId
     * @param url
     * @return
     */
    public ViewHolder setSmallImageByUrl(int viewId, String url) {
        if (!TextUtils.isEmpty(url) && url.contains("oos")) {
            url = url + "@80w_80h.jpg";
        }
        //ImageLoader.getInstance().displayImage(url, (ImageView) getView(viewId));
        return this;
    }

    public int getGroupPosition() {
        return mGroupPosition;
    }

    public int getChildPosition() {
        return mChildPosition;
    }

    public int getLayoutId() {
        return mLayoutId;
    }

}
