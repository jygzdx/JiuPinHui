package com.jiupin.jiupinhui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.utils.LogUtils;

/**
 * 作者：czb on 2017/8/7 15:39
 * 酒圈-》关注fragment
 */

public class AttentionFragment extends Fragment {
    private static final String TAG = "AttentionFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attention,container,false);
        LogUtils.d(TAG,"onCreateView");
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d(TAG,"onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG,"onDestroy");
    }
}
