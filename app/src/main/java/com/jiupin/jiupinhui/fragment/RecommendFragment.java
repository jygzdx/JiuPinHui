package com.jiupin.jiupinhui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.adapter.RecommentAdapter;
import com.jiupin.jiupinhui.entity.CommunityEntity;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.IRecommendFragmentPresenter;
import com.jiupin.jiupinhui.presenter.impl.RecommendFragmentPresenterImpl;
import com.jiupin.jiupinhui.view.IRecommendFragmentView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：czb on 2017/8/7 15:40
 * 酒圈-》推荐fragment
 */

public class RecommendFragment extends Fragment implements IRecommendFragmentView{
    @BindView(R.id.lrv_recommend)
    LRecyclerView lrvRecommend;
    Unbinder unbinder;
    private RecommentAdapter adapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private IRecommendFragmentPresenter presenter;
    private int page = 1;
    private int rows = 10;
    private View headerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new RecommendFragmentPresenterImpl(this);

        initRecyclerView();
        String token = UserInfoManager.getInstance().getToken(getContext());
        adapter.clear();
        presenter.getRecommendList(token,page+"",rows+"");
        return view;
    }

    private void initRecyclerView() {
        lrvRecommend.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter = new RecommentAdapter(getContext());
        lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        lrvRecommend.setAdapter(lRecyclerViewAdapter);

        //添加头部
//        CommonHeader header = new CommonHeader(getActivity(),R.layout.person_condition_item);
        headerView = LayoutInflater.from(getContext()).inflate(R.layout.person_condition_item,(ViewGroup) lrvRecommend,false);
        lRecyclerViewAdapter.addHeaderView(headerView);

        lrvRecommend.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //设置底部加载颜色
        lrvRecommend.setFooterViewColor(android.R.color.darker_gray, android.R.color.darker_gray, android.R.color.white);
        //设置底部加载文字提示
        lrvRecommend.setFooterViewHint("拼命加载中", "已经全部为你呈现了", "网络不给力啊，点击再试一次吧");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setRecommendInfo(List<CommunityEntity> communityList) {
        if(communityList!=null){
            if(communityList.size()>0){
                adapter.addAll(communityList);
            }
        }
    }
}
