package com.jiupin.jiupinhui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.activity.PersonActivity;
import com.jiupin.jiupinhui.adapter.AttentionAdapter;
import com.jiupin.jiupinhui.entity.CommunityEntity;
import com.jiupin.jiupinhui.entity.UserEntity;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.IAttentionFragmentPresenter;
import com.jiupin.jiupinhui.presenter.impl.AttentionFragmentPresenterImpl;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IAttentionFragmentView;
import com.jiupin.jiupinhui.widget.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：czb on 2017/8/7 15:39
 * 酒圈-》关注fragment
 */

public class AttentionFragment extends Fragment implements IAttentionFragmentView {
    private static final String TAG = "AttentionFragment";
    @BindView(R.id.lrv_attention)
    LRecyclerView lrvAttention;
    Unbinder unbinder;
    private AttentionAdapter adapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private IAttentionFragmentPresenter presenter;
    private int page = 1;
    private int rows = 10;
    private View headerView;
    private UserEntity.DataBean user;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_attention, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new AttentionFragmentPresenterImpl(this);
        LogUtils.d(TAG, "onCreateView");
        //初始化recyclerview
        initRecyclerView();
        //设置头部控件数据
        setHeadViewData();

        String token = UserInfoManager.getInstance().getToken(getContext());
        adapter.clear();
        presenter.getAttentionList(token, page + "", rows + "");
        return view;
    }

    private void setHeadViewData() {
        String token = UserInfoManager.getInstance().getToken(getContext());
        presenter.getUserInfo(token);

    }


    private void initRecyclerView() {
        lrvAttention.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new AttentionAdapter(getContext());
        lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        lrvAttention.setAdapter(lRecyclerViewAdapter);
        user = UserInfoManager.getInstance().getUser();
        //添加头部
        headerView = LayoutInflater.from(getContext()).inflate(R.layout.person_condition_item, (ViewGroup) view, false);
        Button btnMyPage = (Button) headerView.findViewById(R.id.btn_my_page);
        btnMyPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PersonActivity.class);
                startActivity(intent);
            }
        });
        lRecyclerViewAdapter.addHeaderView(headerView);

        lrvAttention.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //设置底部加载颜色
        lrvAttention.setFooterViewColor(android.R.color.darker_gray, android.R.color.darker_gray, android.R.color.white);
        //设置底部加载文字提示
        lrvAttention.setFooterViewHint("拼命加载中", "已经全部为你呈现了", "网络不给力啊，点击再试一次吧");

        lrvAttention.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                lRecyclerViewAdapter.notifyDataSetChanged();
                page = 1;
                String token = UserInfoManager.getInstance().getToken(getContext());
                presenter.getAttentionList(token,page+"",rows+"");
            }
        });

        lrvAttention.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                String token = UserInfoManager.getInstance().getToken(getContext());
                presenter.getAttentionList(token,page+"",rows+"");
            }
        });

        lrvAttention.setOnNetWorkErrorListener(new OnNetWorkErrorListener() {
            @Override
            public void reload() {
                String token = UserInfoManager.getInstance().getToken(getContext());
                presenter.getAttentionList(token,page+"",rows+"");
            }
        });

        adapter.setOnThumbDynamicClickListener(new AttentionAdapter.OnThumbDynamicClickListener() {
            @Override
            public void onClick(View view, int communityId, int position) {
                setThumbDynamic(communityId,position);
            }
        });

        adapter.setOnConcernExpertClickListener(new AttentionAdapter.OnConcernExpertClickListener() {
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
    public void setAttentionInfo(List<CommunityEntity> communityList) {

        LogUtils.d("isHidden = "+isHidden());
        if (isHidden()) return;

        if (communityList != null) {
            if (communityList.size() > 0) {
                adapter.addAll(communityList);
            }else {
                lrvAttention.setNoMore(true);
            }
            lrvAttention.refreshComplete(communityList.size());
            lRecyclerViewAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void thumbDynamic(String msg,int position) {
        ToastUtils.showShort(getContext(),msg);
        adapter.notifyItemChangeOnThumbStatus(position);
    }

    @Override
    public void concernExpert(String msg,String userId) {
        ToastUtils.showShort(getContext(),msg);
        adapter.notifyItemChangeOnConcernExpert(userId);
    }

    @Override
    public void setUserInfo(UserEntity userEntity) {

        LogUtils.d("isHidden = "+isHidden());
        if (isHidden()) return;

        CircleImageView civHead = (CircleImageView) headerView.findViewById(R.id.civ_head);
        TextView tvUserNickname = (TextView) headerView.findViewById(R.id.tv_user_nickname);
        TextView tvNewCondition = (TextView) headerView.findViewById(R.id.tv_new_condition);
        if(user!=null){
            tvUserNickname.setText(user.getNickName());
            if(user.getIntro()==""||user.getIntro()==null){
                tvNewCondition.setText("暂无留下任何信息简介");
            }else{
                tvNewCondition.setText(user.getIntro().trim());
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
