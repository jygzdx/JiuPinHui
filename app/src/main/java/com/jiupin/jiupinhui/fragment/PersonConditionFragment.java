package com.jiupin.jiupinhui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.adapter.PersonConditionAdapter;
import com.jiupin.jiupinhui.entity.CommunityEntity;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.IPersonConditionFragmentPresenter;
import com.jiupin.jiupinhui.presenter.impl.PersonConditionFragmentPresenterImpl;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IPersonConditionFragmentView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.ContentValues.TAG;

/**
 * 作者：czb on 2017/8/17 16:14
 */

public class PersonConditionFragment extends Fragment implements IPersonConditionFragmentView {
    @BindView(R.id.lrv_person)
    LRecyclerView lrvPerson;
    Unbinder unbinder;
    private View view;
    private PersonConditionAdapter adapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private IPersonConditionFragmentPresenter presenter;
    private int page = 1;
    private int rows = 10;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_condition, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new PersonConditionFragmentPresenterImpl(this);
        LogUtils.d(TAG, "onCreateView");
        //初始化recyclerview
        initRecyclerView();

        String token = UserInfoManager.getInstance().getToken(getContext());
        String userId = UserInfoManager.getInstance().getUserId(getContext());
        adapter.clear();
        presenter.getPersonConditionList(token, userId, page + "", rows + "");

        return view;
    }

    private void initRecyclerView() {
        lrvPerson.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new PersonConditionAdapter(getContext());
        lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        lrvPerson.setAdapter(lRecyclerViewAdapter);
        lrvPerson.setNestedScrollingEnabled(false);

        lrvPerson.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //设置底部加载颜色
        lrvPerson.setFooterViewColor(android.R.color.darker_gray, android.R.color.darker_gray, android.R.color.white);
        //设置底部加载文字提示
        lrvPerson.setFooterViewHint("拼命加载中", "已经全部为你呈现了", "网络不给力啊，点击再试一次吧");

        lrvPerson.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                lRecyclerViewAdapter.notifyDataSetChanged();
                page = 1;
                String token = UserInfoManager.getInstance().getToken(getContext());
                String userId = UserInfoManager.getInstance().getUserId(getContext());
                presenter.getPersonConditionList(token, userId, page + "", rows + "");
            }
        });

        lrvPerson.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                String token = UserInfoManager.getInstance().getToken(getContext());
                String userId = UserInfoManager.getInstance().getUserId(getContext());
                presenter.getPersonConditionList(token, userId, page + "", rows + "");
            }
        });

        lrvPerson.setOnNetWorkErrorListener(new OnNetWorkErrorListener() {
            @Override
            public void reload() {
                String token = UserInfoManager.getInstance().getToken(getContext());
                String userId = UserInfoManager.getInstance().getUserId(getContext());
                presenter.getPersonConditionList(token, userId, page + "", rows + "");
            }
        });

        adapter.setOnViewClickListener(new PersonConditionAdapter.OnViewClickListener() {
            @Override
            public void onClick(View view, Object data, int position) {
                switch (view.getId()) {
                    case R.id.ll_set_like:
                        setThumbDynamic(((CommunityEntity) data).getId(), position);
                        break;
                    case R.id.tv_delete:
                        setDeleteCondition(((CommunityEntity) data).getId(), position);
                        break;
                    case R.id.tv_only_youself_look:
                        changeOnlyLookStatus((CommunityEntity) data, position);
                        break;
                    case R.id.tv_move_top:
                        moveToTop((CommunityEntity) data, position);
                        break;
                }
            }
        });


    }

    /**
     * 发送置顶动态请求
     * @param data
     * @param position
     */
    private void moveToTop(CommunityEntity data, int position) {
        adapter.clear();
        lRecyclerViewAdapter.notifyDataSetChanged();
        page = 1;
        String token = UserInfoManager.getInstance().getToken(getContext());
        presenter.moveConditionToTop(token,data.getId()+"");
    }

    /**
     * 改变仅自己可见状态
     * @param data
     * @param position
     */
    private void changeOnlyLookStatus(CommunityEntity data, int position) {
        adapter.clear();
        lRecyclerViewAdapter.notifyDataSetChanged();
        page = 1;
        String token = UserInfoManager.getInstance().getToken(getContext());
        presenter.isOnlyMyselfLook(token,data.getId()+"",!data.is_visible()+"");
    }

    /**
     * 发送删除动态请求
     *
     * @param communityId
     * @param position
     */
    public void setDeleteCondition(int communityId, int position) {
        String token = UserInfoManager.getInstance().getToken(getContext());
        presenter.deleteCondition(token, communityId + "", position);
    }

    /**
     * 发送点赞请求
     *
     * @param communityId
     * @param position
     */
    public void setThumbDynamic(int communityId, int position) {
        String token = UserInfoManager.getInstance().getToken(getContext());
        presenter.setThumbDynamic(token, communityId + "", position);
    }

    @Override
    public void setPersonConditionInfo(List<CommunityEntity> communityList) {
        if (communityList != null) {
            if (communityList.size() > 0) {
                adapter.addAll(communityList);
                lrvPerson.refreshComplete(communityList.size());
            } else {
                lrvPerson.setNoMore(true);
            }
        }
    }

    @Override
    public void thumbDynamic(String msg, int position) {
        ToastUtils.showShort(getContext(), msg);
        adapter.notifyItemChangeOnThumbStatus(position);
    }

    @Override
    public void deleteCondition(String msg, int position) {
        ToastUtils.showShort(getContext(), msg);
        adapter.removeCondition(position);
    }

    @Override
    public void moveConditionToTop(List<CommunityEntity> communityList) {
        adapter.addAll(communityList);
        lrvPerson.refreshComplete(communityList.size());
    }

    @Override
    public void onlymyselflook(List<CommunityEntity> communityList) {
        adapter.addAll(communityList);
        lrvPerson.refreshComplete(communityList.size());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
