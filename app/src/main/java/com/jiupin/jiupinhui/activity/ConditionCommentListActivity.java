package com.jiupin.jiupinhui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.adapter.ConditionCommentListAdapter;
import com.jiupin.jiupinhui.adapter.ImageAdapter;
import com.jiupin.jiupinhui.entity.CommunityEntity;
import com.jiupin.jiupinhui.entity.ConditionCommentEntity;
import com.jiupin.jiupinhui.presenter.IConditionCommentListActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.ConditionCommentListActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.ActivityUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.StringUtils;
import com.jiupin.jiupinhui.utils.TimeUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IConditionCommentListActivityView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 酒圈评论列表
 */
public class ConditionCommentListActivity extends BaseActivity implements IConditionCommentListActivityView {

    @BindView(R.id.lrv_comment)
    LRecyclerView lrvComment;
    private boolean isFirst = true;
    private ConditionCommentListAdapter adapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private int page = 1;
    private IConditionCommentListActivityPresenter presenter;
    private CommunityEntity community;
    private int dynamicId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition_comment_list);
        ButterKnife.bind(this);

        community = (CommunityEntity) getIntent().getExtras().getSerializable("community");
        dynamicId = community.getId();

        initView();

        presenter = new ConditionCommentListActivityPresenterImpl(this);
        presenter.getCommentList(dynamicId + "", page + "");
    }

    private void initView() {
        lrvComment.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        adapter = new ConditionCommentListAdapter(this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        //初始化头部控件
        initHeaderView();

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
                presenter.getCommentList(dynamicId + "", page + "");
            }
        });
    }

    private void initHeaderView() {
        View headerView = LayoutInflater.from(mContext).inflate(R.layout.comment_header, (ViewGroup) lrvComment, false);
        lRecyclerViewAdapter.addHeaderView(headerView);
        ImageView civHead = (ImageView) headerView.findViewById(R.id.civ_head);
        TextView tvConditionNickname = (TextView) headerView.findViewById(R.id.tv_condition_nickname);
        TextView tvContent = (TextView) headerView.findViewById(R.id.tv_content);
        TextView tvConditionTime = (TextView) headerView.findViewById(R.id.tv_condition_time);
        RecyclerView rvTranImg = (RecyclerView) headerView.findViewById(R.id.rv_tran_img);
        RecyclerView rvUserImg = (RecyclerView) headerView.findViewById(R.id.rv_user_img);
        LinearLayout llTranspond = (LinearLayout) headerView.findViewById(R.id.ll_transpond);
        TextView tvTranspondContent = (TextView) headerView.findViewById(R.id.tv_transpond_content);
        TextView tvTranspondName = (TextView) headerView.findViewById(R.id.tv_transpond_name);
        TextView tvCommentCount = (TextView) headerView.findViewById(R.id.tv_comment_count);

        Glide.with(mContext)
                .load(community.getUser_img())
                .crossFade()
                .into(civHead);
        tvConditionNickname.setText(community.getNickName());
        tvContent.setText(community.getContent());
        tvConditionTime.setText(TimeUtils.getTime(community.getAddTime()));
        tvCommentCount.setText("评论("+community.getComment_count()+")");
        LogUtils.d("getImage_list = "+community.getImage_list());
        LogUtils.d("getTrans_img_list = "+community.getTrans_img_list());

        if(StringUtils.isEmpty(community.getImage_list())){//设置用户自己发表的图片
            rvUserImg.setVisibility(View.GONE);
        }else{
            rvUserImg.setVisibility(View.VISIBLE);
            String[] imgUrls = community.getImage_list().split(";");
            initRv(rvUserImg, imgUrls);
        }

        if (community.isIs_trans()) {//设置用户转发他人的图片
            llTranspond.setVisibility(View.VISIBLE);
            if (!StringUtils.isEmpty(community.getTrans_img_list())) {
                String[] imgUrls = community.getTrans_img_list().split(";");
                initRv(rvTranImg, imgUrls);
            }
            tvTranspondContent.setText(community.getTrans_content());
            tvTranspondName.setText("@" + community.getTrans_nickName());
        } else {
            llTranspond.setVisibility(View.GONE);
            LogUtils.d("llTranspond.visibility = gone");
        }

    }

    private void initRv(RecyclerView recyclerView, String[] imgUrls) {
        LogUtils.d("imgUrls.length = "+imgUrls.length);
        GridLayoutManager manager;
        switch (imgUrls.length) {
            case 1:
            case 2:
                manager = new GridLayoutManager(mContext, 2);
                break;
            default:
                manager = new GridLayoutManager(mContext, 4);
                break;
        }
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new ImageAdapter(mContext, imgUrls));
        recyclerView.setNestedScrollingEnabled(false);
    }

    @OnClick({R.id.tv_cancel, R.id.tv_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_comment:
                Intent intent = new Intent(mContext, SendConditionComActivity.class);
                intent.putExtra("dynamicId", dynamicId);
                mContext.startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void setCommentList(List<ConditionCommentEntity> commentList) {
        if (ActivityUtils.isFinish(mContext))
            return;
        if (commentList.size() > 0) {
            adapter.addAll(commentList);
            lrvComment.refreshComplete(10);
        } else {
            if (isFirst) {
                ToastUtils.showShort(this, "暂无评论");
            }
            lrvComment.setNoMore(true);
        }
        isFirst = false;
    }
}
