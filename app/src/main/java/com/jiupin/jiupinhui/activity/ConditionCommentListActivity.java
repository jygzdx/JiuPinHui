package com.jiupin.jiupinhui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.adapter.ConditionCommentListAdapter;
import com.jiupin.jiupinhui.entity.ConditionCommentEntity;
import com.jiupin.jiupinhui.presenter.IConditionCommentListActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.ConditionCommentListActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.ActivityUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IConditionCommentListActivityView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 酒圈评论列表
 */
public class ConditionCommentListActivity extends BaseActivity implements IConditionCommentListActivityView{

    @BindView(R.id.lrv_comment)
    LRecyclerView lrvComment;
    private boolean isFirst = true;
    private ConditionCommentListAdapter adapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private int page = 1;
    private IConditionCommentListActivityPresenter presenter;
    private int dynamicId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition_comment_list);
        ButterKnife.bind(this);
        initView();

//        String imgUrl = getIntent().getExtras().getString("imgUrl");
//        String nickname = getIntent().getExtras().getString("nickname");
//        String tranContent = getIntent().getExtras().getString("tranContent");
        dynamicId = getIntent().getExtras().getInt("dynamicId");

        presenter = new ConditionCommentListActivityPresenterImpl(this);
        presenter.getCommentList(dynamicId+"",page+"");
    }

    private void initView() {
        lrvComment.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        adapter = new ConditionCommentListAdapter(this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        lrvComment.setAdapter(lRecyclerViewAdapter);

        lrvComment.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //设置底部加载颜色
        lrvComment.setFooterViewColor(android.R.color.darker_gray, android.R.color.darker_gray, android.R.color.white);
        //设置底部加载文字提示
        lrvComment.setFooterViewHint("拼命加载中", "已经全部为你呈现了", "网络不给力啊，点击再试一次吧");

        lrvComment.setLoadMoreEnabled(true);
        lrvComment.setPullRefreshEnabled(false);

        lrvComment.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                LogUtils.d("loadMore");
                page++;
                presenter.getCommentList(dynamicId+"",page+"");
            }
        });
    }

    @OnClick({R.id.tv_cancel, R.id.tv_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_comment:
                Intent intent = new Intent(mContext, SendConditionComActivity.class);
                intent.putExtra("dynamicId",dynamicId);
                mContext.startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void setCommentList(List<ConditionCommentEntity> commentList) {
        if (ActivityUtils.isFinish(mContext))return;
        if (commentList.size() > 0) {
            adapter.addAll(commentList);
            lrvComment.refreshComplete(10);
        } else {
            if(isFirst){
                ToastUtils.showShort(this,"暂无评论");
            }
            lrvComment.setNoMore(true);
        }
        isFirst = false;
    }
}
