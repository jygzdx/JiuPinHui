package com.jiupin.jiupinhui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.adapter.ServerAdapter;
import com.jiupin.jiupinhui.entity.ChatHistotyEntity;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.IServerActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.ServerActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.ActivityUtils;
import com.jiupin.jiupinhui.view.IServerActivityView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 展示售前的咨询记录
 */
public class ServerActivity extends BaseActivity implements IServerActivityView {

    @BindView(R.id.tv_consult)
    TextView tvConsult;
    @BindView(R.id.rl_no_record)
    RelativeLayout rlNoRecord;
    @BindView(R.id.lrv_server)
    LRecyclerView lrvServer;
    private IServerActivityPresenter presenter;
    private String token;
    private ServerAdapter adapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        ButterKnife.bind(this);

        initView();
        presenter = new ServerActivityPresenterImpl(this);

        requestData();
    }

    private void requestData() {
        token = UserInfoManager.getInstance().getToken(this);
        presenter.getChatHistory(token, page + "", "10");
    }

    private void initView() {
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new ServerAdapter(this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        lrvServer.setLayoutManager(layout);
        lrvServer.setAdapter(lRecyclerViewAdapter);

        lrvServer.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
        lrvServer.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);

        lrvServer.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //设置底部加载颜色
        lrvServer.setFooterViewColor(android.R.color.darker_gray, android.R.color.darker_gray, android.R.color.white);
        //设置底部加载文字提示
        lrvServer.setFooterViewHint("拼命加载中", "已经全部为你呈现了", "网络不给力啊，点击再试一次吧");

        lrvServer.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                adapter.clear();
                lRecyclerViewAdapter.notifyDataSetChanged();
                requestData();
            }
        });

        lrvServer.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                requestData();
            }
        });
    }

    @OnClick({R.id.tv_consult, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_consult://用户咨询问题
                Intent intent = new Intent(mContext, SubmitQuestionActivity.class);
                startActivityForResult(intent,1);
                break;
            case R.id.iv_back:
                finish();
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            page=1;
            adapter.clear();
            lRecyclerViewAdapter.notifyDataSetChanged();
            requestData();
        }
    }

    @Override
    public void setChatHistoryList(List<ChatHistotyEntity> historyList) {
        if (ActivityUtils.isFinish(mContext))return;

        if (historyList != null) {
            if (historyList.size() > 0) {
                adapter.addAll(historyList);
                lrvServer.refreshComplete(10);
                lRecyclerViewAdapter.notifyDataSetChanged();
            } else {
                lrvServer.setNoMore(true);
            }
        }
    }
}
