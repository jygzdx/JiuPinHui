package com.jiupin.jiupinhui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.activity.ConditionActivity;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.widget.SwitchView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/3/15.
 */

public class CommunityFragment extends Fragment {
    private static final String TAG = "CommunityFragment";
    @BindView(R.id.sv_title)
    SwitchView svTitle;
    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    private Fragment mContent;
    private AttentionFragment attentionFra;
    private RecommendFragment recommendFra;
    private String[] tags = new String[]{"AttentionFragmentTag","RecommendFragmentTag"};

    Unbinder unbinder;
    private View view;
    private FragmentManager fm;
    private FragmentTransaction transaction;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_community, container, false);
        unbinder = ButterKnife.bind(this, view);
        LogUtils.d(TAG,"onCreateView");
        LogUtils.d(TAG,"savedInstanceState"+savedInstanceState);
        initView();
        setListener();
        return view;
    }

    /**
     * 初始化控件
     */
    private void initView() {
        if(recommendFra==null){
            recommendFra = new RecommendFragment();
        }

        if(attentionFra==null){
            attentionFra = new AttentionFragment();
        }
        //设置默认fragment
        fm = getChildFragmentManager();
//        switchContent(attentionFra,recommendFra,0);
        transaction = fm.beginTransaction();
        transaction.add(R.id.fl_container, recommendFra).commit();
        mContent = recommendFra;
    }

    private void setListener() {
        svTitle.setOnClickCheckedListener(new SwitchView.onClickCheckedListener() {
            @Override
            public void onClick() {
                if(svTitle.isChecked()){//显示关注fragment
                    if(attentionFra==null){
                        attentionFra = new AttentionFragment();
                    }
                    switchContent(recommendFra,attentionFra,0);
                }else {//显示推荐fragment
                    switchContent(attentionFra,recommendFra,1);
                }
            }
        });
    }

    /**
     * fragment 切换
     *
     * @param from
     * @param to
     */
    public void switchContent(Fragment from, Fragment to, int position) {
        if (mContent != to) {
            mContent = to;
            transaction = fm.beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.hide(from)
                        .add(R.id.fl_container,to,tags[position]).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d(TAG,"onDestroyView");
        attentionFra = null;
        recommendFra = null;
        mContent = null;
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG,"onDestroy");
    }

    @OnClick(R.id.iv_editor)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(),ConditionActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //不让fragment保存记录
    }
}
