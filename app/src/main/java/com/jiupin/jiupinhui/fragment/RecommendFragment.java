package com.jiupin.jiupinhui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.adapter.RecommentAdapter;
import com.jiupin.jiupinhui.entity.CommunityEntity;
import com.jiupin.jiupinhui.entity.UserEntity;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.IRecommendFragmentPresenter;
import com.jiupin.jiupinhui.presenter.impl.RecommendFragmentPresenterImpl;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IRecommendFragmentView;
import com.jiupin.jiupinhui.widget.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：czb on 2017/8/7 15:40
 * 酒圈-》推荐fragment
 */

public class RecommendFragment extends Fragment implements IRecommendFragmentView {
    private static final String TAG = "RecommendFragment";
    @BindView(R.id.lrv_recommend)
    LRecyclerView lrvRecommend;
    Unbinder unbinder;
    private RecommentAdapter adapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private IRecommendFragmentPresenter presenter;
    private int page = 1;
    private int rows = 10;
    private View headerView;
    private UserEntity.DataBean user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new RecommendFragmentPresenterImpl(this);
        LogUtils.d(TAG, "onCreateView");
        //初始化recyclerview
        initRecyclerView();
        //设置头部控件数据
        setHeadViewData();

        String token = UserInfoManager.getInstance().getToken(getContext());
        adapter.clear();
        presenter.getRecommendList(token, page + "", rows + "");
        return view;
    }

    private void setHeadViewData() {
        String token = UserInfoManager.getInstance().getToken(getContext());
        presenter.getUserInfo(token);

    }


    private void initRecyclerView() {
        lrvRecommend.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new RecommentAdapter(getContext());
        lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        lrvRecommend.setAdapter(lRecyclerViewAdapter);
        user = UserInfoManager.getInstance().getUser();
        //添加头部
        headerView = LayoutInflater.from(getContext()).inflate(R.layout.person_condition_item, (ViewGroup) lrvRecommend, false);
        lRecyclerViewAdapter.addHeaderView(headerView);

        lrvRecommend.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //设置底部加载颜色
        lrvRecommend.setFooterViewColor(android.R.color.darker_gray, android.R.color.darker_gray, android.R.color.white);
        //设置底部加载文字提示
        lrvRecommend.setFooterViewHint("拼命加载中", "已经全部为你呈现了", "网络不给力啊，点击再试一次吧");

        lrvRecommend.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                lRecyclerViewAdapter.notifyDataSetChanged();
                page = 1;
                String token = UserInfoManager.getInstance().getToken(getContext());
                presenter.getRecommendList(token,page+"",rows+"");
            }
        });

        lrvRecommend.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                String token = UserInfoManager.getInstance().getToken(getContext());
                presenter.getRecommendList(token,page+"",rows+"");
            }
        });

        lrvRecommend.setOnNetWorkErrorListener(new OnNetWorkErrorListener() {
            @Override
            public void reload() {
                String token = UserInfoManager.getInstance().getToken(getContext());
                presenter.getRecommendList(token,page+"",rows+"");
            }
        });

        adapter.setOnThumbDynamicClickListener(new RecommentAdapter.OnThumbDynamicClickListener() {
            @Override
            public void onClick(View view, int communityId, int position) {
                setThumbDynamic(communityId,position);
            }
        });

        adapter.setOnConcernExpertClickListener(new RecommentAdapter.OnConcernExpertClickListener() {
            @Override
            public void onClick(View view, int userId, boolean concernStatus, int position) {
                cancelCondition(userId,concernStatus,position);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d(TAG, "onDestroyView");
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG, "onDestroy");
    }

    public void setThumbDynamic(int communityId , int position){
        String token = UserInfoManager.getInstance().getToken(getContext());
        presenter.setThumbDynamic(token,communityId+"",position);
    }

    public void cancelCondition(int userId, boolean concernStatus, int position){
        String token = UserInfoManager.getInstance().getToken(getContext());
        presenter.cancelCondition(token,userId+"",(!concernStatus)+"",position);
    }

    @Override
    public void setRecommendInfo(List<CommunityEntity> communityList) {
        if (communityList != null) {
            if (communityList.size() > 0) {
                adapter.addAll(communityList);
                lrvRecommend.refreshComplete(communityList.size());
            }else {
                lrvRecommend.setNoMore(true);
            }
        }
    }

    @Override
    public void thumbDynamic(String msg,int position) {
        ToastUtils.showShort(getContext(),msg);
        adapter.notifyItemChangeOnThumbStatus(position);
    }

    @Override
    public void concernExpert(String msg,int position) {
        ToastUtils.showShort(getContext(),msg);
        adapter.notifyItemChangeOnConcernExpert(position);
    }

    @Override
    public void setUserInfo(UserEntity userEntity) {
        CircleImageView civHead = (CircleImageView) headerView.findViewById(R.id.civ_head);
        TextView tvUserNickname = (TextView) headerView.findViewById(R.id.tv_user_nickname);
        TextView tvNewCondition = (TextView) headerView.findViewById(R.id.tv_new_condition);
        if(user!=null){
            tvUserNickname.setText(user.getNickName());
            if(user.getSignature()==""||user.getSignature()==null){
                tvNewCondition.setText("暂无留下任何信息简介");
            }else{
                tvNewCondition.setText(user.getSignature().trim());
            }

            Glide.with(getContext())
                    .load(user.getImageUrl())
                    .crossFade()
                    .into(civHead);
        }else{
            lRecyclerViewAdapter.removeHeaderView();
        }
    }
}
