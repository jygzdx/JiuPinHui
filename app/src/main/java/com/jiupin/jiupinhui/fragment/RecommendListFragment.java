package com.jiupin.jiupinhui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.adapter.RecommendListAdapter;
import com.jiupin.jiupinhui.entity.AttListEntity;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.IRecommendListFragmentPresenter;
import com.jiupin.jiupinhui.presenter.impl.RecommendListFragmentPresenterImpl;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IRecommendListFragmentView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：czb on 2017/8/22 10:37
 * 推荐列表fragment
 */

public class RecommendListFragment extends Fragment implements IRecommendListFragmentView{
    private static final String TAG = "RecommendListFragment";
    @BindView(R.id.lrv_recommend_list)
    LRecyclerView lrvRecommendList;
    Unbinder unbinder;
    private View view;
    private RecommendListAdapter adapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private IRecommendListFragmentPresenter presenter;
    private int page = 1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rec_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        presenter = new RecommendListFragmentPresenterImpl(this);
        String token = UserInfoManager.getInstance().getToken(getContext());
        presenter.getRecommendList(token,page+"",10+"");

        //初始化recyclerview
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        lrvRecommendList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new RecommendListAdapter(getContext());
        lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        lrvRecommendList.setAdapter(lRecyclerViewAdapter);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.text_header_item,null);
        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
        tvContent.setText("为您推荐小伙伴儿");
        lRecyclerViewAdapter.addHeaderView(view);

        lrvRecommendList.setNestedScrollingEnabled(false);

        lrvRecommendList.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //设置底部加载颜色
        lrvRecommendList.setFooterViewColor(android.R.color.darker_gray, android.R.color.darker_gray, android.R.color.white);
        //设置底部加载文字提示
        lrvRecommendList.setFooterViewHint("拼命加载中", "已经全部为你呈现了", "网络不给力啊，点击再试一次吧");

        lrvRecommendList.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                lRecyclerViewAdapter.notifyDataSetChanged();
                page = 1;
                String token = UserInfoManager.getInstance().getToken(getContext());
                presenter.getRecommendList(token, page + "", 10 + "");
            }
        });

        lrvRecommendList.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                String token = UserInfoManager.getInstance().getToken(getContext());
                presenter.getRecommendList(token, page + "", 10 + "");
            }
        });

        lrvRecommendList.setOnNetWorkErrorListener(new OnNetWorkErrorListener() {
            @Override
            public void reload() {
                String token = UserInfoManager.getInstance().getToken(getContext());
                presenter.getRecommendList(token, page + "", 10 + "");
            }
        });

        adapter.setOnViewClickListener(new RecommendListAdapter.OnViewClickListener() {
            @Override
            public void onClick(View view, Object data, int position) {
                switch (view.getId()) {
                    case R.id.rl_attention:
                        cancelCondition(((AttListEntity) data).getId(), ((AttListEntity) data).isConcern_status(),position);
                        break;
                }
            }
        });


    }

    public void cancelCondition(int userId, boolean concernStatus, int position){
        String token = UserInfoManager.getInstance().getToken(getContext());
        presenter.cancelCondition(token,userId+"",(!concernStatus)+"",position);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void concernExpert(String msg,int position) {
        ToastUtils.showShort(getContext(),msg);
        adapter.notifyItemChangeOnConcernExpert(position);
    }

    @Override
    public void setRecommendListInfo(List<AttListEntity> attListEntities) {
        LogUtils.d("isHidden = "+isHidden());
        if (isHidden()) return;


        if(attListEntities==null)return;
        if (attListEntities.size() > 0) {
            adapter.addAll(attListEntities);
        }else {
            lrvRecommendList.setNoMore(true);
        }
        lrvRecommendList.refreshComplete(attListEntities.size());
        lRecyclerViewAdapter.notifyDataSetChanged();

    }
}
