package com.jiupin.jiupinhui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.adapter.CommentAdapter;
import com.jiupin.jiupinhui.entity.AppraiseEntity;
import com.jiupin.jiupinhui.manage.PopWinManager;
import com.jiupin.jiupinhui.presenter.ICommentActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.CommentActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.ICommentActivityView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品评价
 * 根据goodsId获取数据
 */
public class CommentActivity extends BaseActivity implements ICommentActivityView {
    private static final String TAG = "CommentActivity";
    @BindView(R.id.lrv_comment)
    LRecyclerView lrvComment;
    private int goodsId;
    private ICommentActivityPresenter presenter;
    private CommentAdapter adapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private int page = 1;
    private boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);

        initView();

        goodsId = getIntent().getExtras().getInt("goodsId");
        presenter = new CommentActivityPresenterImpl(this);
        presenter.getAppraise(goodsId, page);

    }

    private void initView() {
        lrvComment.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        adapter = new CommentAdapter(this);
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
                presenter.getAppraise(goodsId, page);
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.iv_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_more:
                PopWinManager popWinManager = new PopWinManager(mContext, view);
                popWinManager.createPopupWindow();
                break;
        }
    }

    @Override
    public void setUserAppraise(List<AppraiseEntity> appraiseList) {
        if (appraiseList.size() > 0) {
            adapter.addAll(appraiseList);
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
