package com.jiupin.jiupinhui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.activity.IdeaBackActivity;
import com.jiupin.jiupinhui.activity.LoginActivity;
import com.jiupin.jiupinhui.activity.ManageAddressActivity;
import com.jiupin.jiupinhui.activity.MemberClubActivity;
import com.jiupin.jiupinhui.activity.MyFormActivity;
import com.jiupin.jiupinhui.activity.PersonInfoActivity;
import com.jiupin.jiupinhui.activity.VersionActivity;
import com.jiupin.jiupinhui.entity.MyFormEntity;
import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.entity.UserEntity;
import com.jiupin.jiupinhui.presenter.IMyFragmentPresenter;
import com.jiupin.jiupinhui.presenter.impl.MyFragmentPresenterImpl;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.SPUtils;
import com.jiupin.jiupinhui.view.IMyFragmentView;
import com.jiupin.jiupinhui.widget.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by czb on 2017/3/15.
 */
public class MyFragment extends Fragment implements IMyFragmentView {
    private static final String TAG = "MyFragment";
    @BindView(R.id.tv_member_club)
    TextView tvMemberClub;
    @BindView(R.id.rl_member_service)
    RelativeLayout rlMemberService;
    @BindView(R.id.tv_look_form)
    TextView tvLookForm;
    @BindView(R.id.rl_my_idea_back)
    RelativeLayout rlMyIdeaBack;
    @BindView(R.id.rl_my_indent)
    RelativeLayout rlMyIndent;
    Unbinder unbinder;
    @BindView(R.id.tv_my_login)
    TextView tvMyLogin;
    @BindView(R.id.civ_head)
    CircleImageView civHead;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_vip_grade)
    TextView tvVipGrade;
    @BindView(R.id.tv_wait_pay)
    TextView tvWaitPay;
    @BindView(R.id.tv_wait_send_goods)
    TextView tvWaitSendGoods;
    @BindView(R.id.tv_wait_gain_goods)
    TextView tvWaitGainGoods;
    @BindView(R.id.tv_wait_appraise)
    TextView tvWaitAppraise;
    @BindView(R.id.tv_refund_and_after_sale)
    TextView tvRefundAndAfterSale;
    private View view;

    private IMyFragmentPresenter presenter;
    private String token;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);

        presenter = new MyFragmentPresenterImpl(this);

        token = (String) SPUtils.get(getContext(), SPUtils.LOGIN_TOKEN, "");
        LogUtils.d("token = " + token);
        if (token == "") {
            tvMyLogin.setVisibility(View.VISIBLE);
            civHead.setVisibility(View.GONE);
            tvUserName.setVisibility(View.GONE);
            tvVipGrade.setVisibility(View.GONE);
        } else {
            LogUtils.d("------getTokenStatus");
            //查看token是否可用
            presenter.getTokenStatus(token);
        }

        LogUtils.d(TAG + "    oncreateView");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_member_club, R.id.rl_member_service, R.id.tv_look_form,
            R.id.rl_my_idea_back, R.id.rl_my_indent, R.id.rl_versions_info,
            R.id.civ_head, R.id.tv_my_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_member_club:
                Intent intent1 = new Intent(getActivity(), MemberClubActivity.class);//该功能暂时隐藏
                getActivity().startActivity(intent1);
                break;
            case R.id.rl_member_service:
                Intent intent2 = new Intent(getActivity(), MemberClubActivity.class);//该功能暂时隐藏
                getActivity().startActivity(intent2);
                break;
            case R.id.tv_look_form://进入我的订单
                Intent intent3 = new Intent(getActivity(), MyFormActivity.class);
                getActivity().startActivity(intent3);
                break;
            case R.id.rl_my_idea_back:
                Intent intent4 = new Intent(getActivity(), IdeaBackActivity.class);
                getActivity().startActivity(intent4);
                break;
            case R.id.rl_my_indent:
                Intent intent5 = new Intent(getActivity(), ManageAddressActivity.class);
                getActivity().startActivity(intent5);
                break;
            case R.id.rl_versions_info:
                Intent intent6 = new Intent(getActivity(), VersionActivity.class);
                getActivity().startActivity(intent6);
                break;
            case R.id.civ_head:
                Intent intent7 = new Intent(getActivity(), PersonInfoActivity.class);
                getActivity().startActivity(intent7);
                break;
            case R.id.tv_my_login:
                Intent intent8 = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivityForResult(intent8, 1);
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.d("onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d("onResume");
    }



    @Override
    public void checkTokenBack(ResponseBase responseBase) {
        LogUtils.d("checkTokenBack = "+responseBase.getMsg());
        boolean status = "1".equals((String) responseBase.getData()) ? true : false;
        LogUtils.d("status = " + status + "    data = " + (String) responseBase.getData());
        if (status) {//登录状态
            tvMyLogin.setVisibility(View.GONE);
            civHead.setVisibility(View.VISIBLE);
            tvUserName.setVisibility(View.VISIBLE);
            tvVipGrade.setVisibility(View.VISIBLE);
            //获取用户数据
            presenter.getUserInfoByToken(token);
            presenter.getformInfoByToken(token);
        } else {//未登录状态
            tvMyLogin.setVisibility(View.VISIBLE);
            civHead.setVisibility(View.GONE);
            tvUserName.setVisibility(View.GONE);
            tvVipGrade.setVisibility(View.GONE);
        }
    }

    @Override
    public void setUserInfo(UserEntity userEntity) {
        LogUtils.d("setUserInfo"+userEntity.getData().getImageUrl());

        Glide.with(this)
                .load(userEntity.getData().getImageUrl())
                .crossFade()
                .into(civHead);

        tvUserName.setText(userEntity.getData().getUserName());
    }

    @Override
    public void setFormNumber(MyFormEntity myFormEntity) {
        LogUtils.d("setFormNumber"+myFormEntity.toString());
        if(myFormEntity.getData().getUnpay()==0){
            tvWaitPay.setVisibility(View.GONE);
        }else{
            tvWaitPay.setVisibility(View.VISIBLE);
        }
        if(myFormEntity.getData().getWaitDelivery()==0){
            tvWaitSendGoods.setVisibility(View.GONE);
        }else{
            tvWaitSendGoods.setVisibility(View.VISIBLE);
        }
        if(myFormEntity.getData().getWaitPickup()==0){
            tvWaitGainGoods.setVisibility(View.GONE);
        }else{
            tvWaitGainGoods.setVisibility(View.VISIBLE);
        }
        if(myFormEntity.getData().getWaitComment()==0){
            tvWaitAppraise.setVisibility(View.GONE);
        }else{
            tvWaitAppraise.setVisibility(View.VISIBLE);
        }
        if(myFormEntity.getData().getAfterSale()==0){
            tvRefundAndAfterSale.setVisibility(View.GONE);
        }else{
            tvRefundAndAfterSale.setVisibility(View.VISIBLE);
        }
        tvWaitPay.setText(myFormEntity.getData().getUnpay()+"");
        tvWaitSendGoods.setText(myFormEntity.getData().getWaitDelivery()+"");
        tvWaitGainGoods.setText(myFormEntity.getData().getWaitPickup()+"");
        tvWaitAppraise.setText(myFormEntity.getData().getWaitComment()+"");
        tvRefundAndAfterSale.setText(myFormEntity.getData().getAfterSale()+"");
    }
}
