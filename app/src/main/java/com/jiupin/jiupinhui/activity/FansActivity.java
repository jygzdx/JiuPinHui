package com.jiupin.jiupinhui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.adapter.FansAdapter;
import com.jiupin.jiupinhui.entity.AttListEntity;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.IFansActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.FansActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.ActivityUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IFansActivityView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FansActivity extends BaseActivity  implements IFansActivityView{
    private static final String TAG = "FansActivity";
    @BindView(R.id.lrv_fans)
    LRecyclerView lrvFans;
    private FansAdapter adapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private int page = 1;
    private IFansActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fans);
        ButterKnife.bind(this);

        presenter = new FansActivityPresenterImpl(this);
        String token = UserInfoManager.getInstance().getToken(mContext);
        presenter.getRecommendList(token,page+"",10+"");

        //初始化recyclerview
        initRecyclerView();
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    private void initRecyclerView() {
        lrvFans.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        adapter = new FansAdapter(mContext);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        lrvFans.setAdapter(lRecyclerViewAdapter);

        View view = LayoutInflater.from(mContext).inflate(R.layout.text_header_item,null);
        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
        tvContent.setText("您的所有粉丝");
        lRecyclerViewAdapter.addHeaderView(view);

        lrvFans.setNestedScrollingEnabled(false);

        lrvFans.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //设置底部加载颜色
        lrvFans.setFooterViewColor(android.R.color.darker_gray, android.R.color.darker_gray, android.R.color.white);
        //设置底部加载文字提示
        lrvFans.setFooterViewHint("拼命加载中", "已经全部为你呈现了", "网络不给力啊，点击再试一次吧");

        lrvFans.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                lRecyclerViewAdapter.notifyDataSetChanged();
                page = 1;
                String token = UserInfoManager.getInstance().getToken(mContext);
                presenter.getRecommendList(token, page + "", 10 + "");
            }
        });

        lrvFans.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                String token = UserInfoManager.getInstance().getToken(mContext);
                presenter.getRecommendList(token, page + "", 10 + "");
            }
        });

        lrvFans.setOnNetWorkErrorListener(new OnNetWorkErrorListener() {
            @Override
            public void reload() {
                String token = UserInfoManager.getInstance().getToken(mContext);
                presenter.getRecommendList(token, page + "", 10 + "");
            }
        });

        adapter.setOnViewClickListener(new FansAdapter.OnViewClickListener() {
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
        String token = UserInfoManager.getInstance().getToken(mContext);
        presenter.cancelCondition(token,userId+"",(!concernStatus)+"",position);
    }

    @Override
    public void concernExpert(String msg,int position) {
        ToastUtils.showShort(mContext,msg);
        adapter.notifyItemChangeOnConcernExpert(position);
    }

    @Override
    public void setRecommendListInfo(List<AttListEntity> attListEntities) {
        if(ActivityUtils.isFinish(mContext)) return;
        if(attListEntities==null)return;
        if (attListEntities.size() > 0) {
            adapter.addAll(attListEntities);
            lrvFans.refreshComplete(attListEntities.size());
        }else {
            lrvFans.setNoMore(true);
        }

    }
}
