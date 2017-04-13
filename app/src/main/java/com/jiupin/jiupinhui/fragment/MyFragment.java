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

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.activity.MemberClubActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/3/15.
 */

public class MyFragment extends Fragment {
    @BindView(R.id.tv_member_club)
    TextView tvMemberClub;
    @BindView(R.id.rl_member_service)
    RelativeLayout rlMemberService;
    Unbinder unbinder;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_member_club, R.id.rl_member_service})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_member_club:
                Intent intent1 = new Intent(getActivity(), MemberClubActivity.class);
                getActivity().startActivity(intent1);
                break;
            case R.id.rl_member_service:
                Intent intent2 = new Intent(getActivity(), MemberClubActivity.class);
                getActivity().startActivity(intent2);
                break;
        }
    }
}
