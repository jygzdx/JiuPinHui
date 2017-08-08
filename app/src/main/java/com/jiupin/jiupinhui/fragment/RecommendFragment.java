package com.jiupin.jiupinhui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiupin.jiupinhui.R;

/**
 * 作者：czb on 2017/8/7 15:40
 * 酒圈-》推荐fragment
 */

public class RecommendFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend,container,false);
        return view;

    }
}
