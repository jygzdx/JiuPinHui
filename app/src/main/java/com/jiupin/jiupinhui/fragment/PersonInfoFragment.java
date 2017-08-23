package com.jiupin.jiupinhui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.activity.CompilePersonInfoActivity;
import com.jiupin.jiupinhui.entity.UserEntity;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.IPersonInfoFragmentPresenter;
import com.jiupin.jiupinhui.presenter.impl.PersonInfoFragmentPresenterImpl;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.TimeUtils;
import com.jiupin.jiupinhui.view.IPersonInfoFragmentView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.ContentValues.TAG;

/**
 * 作者：czb on 2017/8/17 16:14
 * 用户界面的个人信息展示
 */

public class PersonInfoFragment extends Fragment implements IPersonInfoFragmentView{
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_intro)
    TextView tvIntro;
    @BindView(R.id.tv_school)
    TextView tvSchool;
    @BindView(R.id.tv_grade)
    TextView tvGrade;
    @BindView(R.id.tv_add_time)
    TextView tvAddTime;
    Unbinder unbinder;
    private View view;
    private IPersonInfoFragmentPresenter presenter;
    private UserEntity.DataBean userEntity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_info, container, false);
        unbinder = ButterKnife.bind(this, view);

        presenter = new PersonInfoFragmentPresenterImpl(this);
        String userId = UserInfoManager.getInstance().getUserId(getContext());
        presenter.getUserInfo(userId);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.ll_compile_info)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), CompilePersonInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("userEntity",userEntity);
        intent.putExtras(bundle);
        startActivityForResult(intent,1);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.d(TAG,"onActivityResult.requestCode = "+requestCode+",resultCode = "+resultCode);
        if(requestCode==1){
            if(resultCode==1){
                Bundle bundle = data.getExtras();
                UserEntity.DataBean userEntity = (UserEntity.DataBean) bundle.getSerializable("userEntity");
                this.userEntity = userEntity;
                tvNickname.setText(userEntity.getNickName());
                tvSchool.setText(userEntity.getEducation());
                tvIntro.setText(userEntity.getIntro());
                tvGrade.setText("lv."+userEntity.getLevel());
                tvSex.setText(userEntity.getSex()==1?"男":"女");
                tvAddTime.setText(TimeUtils.getTime(userEntity.getAddTime(),TimeUtils.DATE_FORMAT_DATE));
                tvArea.setText(userEntity.getLocation());
            }
        }
    }

    @Override
    public void setUserInfo(UserEntity.DataBean userEntity) {
        LogUtils.d("isHidden = "+isHidden());
        if (isHidden()) return;


this.userEntity = userEntity;
        tvNickname.setText(userEntity.getNickName());
        tvSchool.setText(userEntity.getEducation());
        tvIntro.setText(userEntity.getIntro());
        tvGrade.setText("lv."+userEntity.getLevel());
        tvSex.setText(userEntity.getSex()==1?"男":"女");
        tvAddTime.setText(TimeUtils.getTime(userEntity.getAddTime(),TimeUtils.DATE_FORMAT_DATE));
        tvArea.setText(userEntity.getLocation());
    }
}
