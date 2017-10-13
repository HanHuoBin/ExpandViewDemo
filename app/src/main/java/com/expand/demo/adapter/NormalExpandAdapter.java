package com.expand.demo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.List;

/**
 * Created by hanbin on 2017/10/13.
 */

public abstract class NormalExpandAdapter<T, U> extends BaseExpandableListAdapter {
    protected Context     mContext;
    private List<T>       groupList;
    private List<List<U>> childList;
    private int           groupLayoutId;
    private int           childLayoutId;

    public NormalExpandAdapter(Context context, List<T> groupList, List<List<U>> childList,
                               int groupLayoutId, int childLayoutId) {
        this.mContext = context;
        this.groupList = groupList;
        this.childList = childList;
        this.groupLayoutId = groupLayoutId;
        this.childLayoutId = childLayoutId;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childList.get(i).size();
    }

    @Override
    public T getGroup(int i) {
        return groupList.get(i);
    }

    @Override
    public U getChild(int i, int i1) {
        return childList.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder = getGroupViewHolder(i, view, viewGroup);
        groupConvert(viewHolder, getGroup(i));
        return viewHolder.getConvertView();
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder = getChildViewHolder(i, i1, view, viewGroup);
        childConvert(viewHolder, getChild(i, i1));
        return viewHolder.getConvertView();
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    public abstract void groupConvert(ViewHolder helper, T item);

    public abstract void childConvert(ViewHolder helper, U item);

    private ViewHolder getGroupViewHolder(int position, View convertView, ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, groupLayoutId, position);
    }

    private ViewHolder getChildViewHolder(int groupPosition, int childPosition, View convertView,
                                          ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, childLayoutId, groupPosition,
                childPosition);
    }
}
