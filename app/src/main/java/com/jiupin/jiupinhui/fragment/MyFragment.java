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
import com.jiupin.jiupinhui.activity.IdeaBackActivity;
import com.jiupin.jiupinhui.activity.ManageAddressActivity;
import com.jiupin.jiupinhui.activity.MemberClubActivity;
import com.jiupin.jiupinhui.activity.MyFormActivity;
import com.jiupin.jiupinhui.activity.PersonInfoActivity;
import com.jiupin.jiupinhui.activity.VersionActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by czb on 2017/3/15.
 */
public class MyFragment extends Fragment {
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

    @OnClick({R.id.tv_member_club, R.id.rl_member_service, R.id.tv_look_form,R.id.rl_my_idea_back,R.id.rl_my_indent,R.id.rl_versions_info,R.id.civ_head})
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
            case R.id.tv_look_form:
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
        }
    }
}
