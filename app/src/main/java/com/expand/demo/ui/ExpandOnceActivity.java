package com.expand.demo.ui;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.expand.demo.adapter.NormalExpandAdapter;
import com.expand.demo.adapter.ViewHolder;
import com.expand.demo.base.BaseAppCatActivity;
import com.expand.demo.expandlistviewdemo.R;
import com.expand.demo.model.CityModel;
import com.expand.demo.model.ProvinceModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 一次只展开一项
 * Created by hanbin on 2017/10/13.
 */

public class ExpandOnceActivity extends BaseAppCatActivity {

    private ExpandableListView mListView = null;
    private NormalExpandAdapter<ProvinceModel, CityModel> mAdapter = null;
    private List<ProvinceModel> mGroupList = new ArrayList<>();//父级
    private List<List<CityModel>> mChildList = new ArrayList<>();//子级
    private int count = 0;

    @Override
    protected int initLayout() {
        return R.layout.activity_normal;
    }

    @Override
    protected void initView() {
        initToolBar();
        setTooBarBackBtn();
        mListView = (ExpandableListView) findViewById(R.id.expandable_list_view);
        mAdapter = new NormalExpandAdapter<ProvinceModel, CityModel>(this, mGroupList, mChildList,
                R.layout.layout_item_group, R.layout.layout_item_child) {
            @Override
            public void groupConvert(ViewHolder helper, ProvinceModel item, boolean isExpanded) {
                helper.setText(R.id.tv_name, item.getName());
                helper.setText(R.id.tv_number, item.getNumber());
                ImageView indicatorImg = helper.getView(R.id.img_indicator);
                indicatorImg.setVisibility(View.VISIBLE);
                if (isExpanded) {
                    indicatorImg.setImageDrawable(getResources().getDrawable(R.mipmap.indicator_down));
                } else {
                    indicatorImg.setImageDrawable(getResources().getDrawable(R.mipmap.indicator_right));
                }
            }

            @Override
            public void childConvert(ViewHolder helper, CityModel item) {
                helper.setText(R.id.tv_name, item.getName());
                helper.setText(R.id.tv_number, item.getNumber());
            }
        };
        mListView.setGroupIndicator(null);
        mListView.setAdapter(mAdapter);
        mListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                expandOnlyOne(mListView, i, count);
            }
        });
    }

    // 每次展开一个分组后，关闭其他的分组
    private boolean expandOnlyOne(ExpandableListView view, int expandedPosition, int groupLength) {
        boolean result = true;
        for (int i = 0; i < groupLength; i++) {
            if (i != expandedPosition && view.isGroupExpanded(i)) {
                result &= view.collapseGroup(i);
            }
        }
        return result;
    }

    @Override
    protected void initData() {
        setActionTitle("一次只展开一项");
        mGroupList.add(new ProvinceModel("浙江", 1001, "1001", "省"));
        mGroupList.add(new ProvinceModel("安徽", 1002, "1002", "省"));
        mGroupList.add(new ProvinceModel("江苏", 1003, "1003", "省"));
        mGroupList.add(new ProvinceModel("四川", 1004, "1004", "省"));
        String[] citys = new String[]{"绍兴", "湖州", "嘉兴", "杭州"};
        for (int i = 0; i < 4; i++) {
            List<CityModel> itemList = new ArrayList<>();
            itemList.add(new CityModel(citys[i] + i, 2001, "2001", "市区"));
            itemList.add(new CityModel(citys[i] + i, 2002, "2002", "市区"));
            itemList.add(new CityModel(citys[i] + i, 2003, "2003", "市区"));
            itemList.add(new CityModel(citys[i] + i, 2004, "2004", "市区"));
            mChildList.add(itemList);
        }
        mAdapter.notifyDataSetChanged();
        count = mGroupList.size();
    }
}
