package com.jiupin.jiupinhui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.UserEntity;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.ICompilePersonInfoActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.CompilePersonInfoActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.ActivityUtils;
import com.jiupin.jiupinhui.utils.StringUtils;
import com.jiupin.jiupinhui.utils.TimeUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.ICompilePersonInfoActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 编辑个人资料
 */
public class CompilePersonInfoActivity extends BaseActivity implements ICompilePersonInfoActivityView {

    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.et_area)
    EditText etArea;
    @BindView(R.id.et_intro)
    EditText etIntro;
    @BindView(R.id.et_school)
    EditText etSchool;
    @BindView(R.id.tv_grade)
    TextView tvGrade;
    @BindView(R.id.tv_add_time)
    TextView tvAddTime;
    private ICompilePersonInfoActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compile_person_info);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();

        presenter = new CompilePersonInfoActivityPresenterImpl(this);

        UserEntity.DataBean userEntity = (UserEntity.DataBean) bundle.getSerializable("userEntity");
        if(userEntity!=null){
            etNickname.setText(userEntity.getNickName());
            etArea.setText(userEntity.getLocation());
            etIntro.setText(userEntity.getIntro());
            etSchool.setText(userEntity.getEducation());
            tvGrade.setText("lv."+userEntity.getLevel());
            tvAddTime.setText(TimeUtils.getTime(userEntity.getAddTime(),TimeUtils.DATE_FORMAT_DATE));
            tvSex.setText(userEntity.getSex()==1?"男":"女");
        }

    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.tv_sex})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                String nickName = etNickname.getText().toString();
                String sex = tvSex.getText().toString();
                String area = etArea.getText().toString();
                String intro = etIntro.getText().toString();
                String school = etSchool.getText().toString();
                if(StringUtils.isEmpty(nickName)){
                    ToastUtils.showShort(mContext,"昵称不能为空");
                    return;
                }
                String sexNum = "";
                if(sex.equals("男")){
                    sexNum = "1";
                }else if(sex.equals("女")){
                    sexNum = "0";
                }
                String token = UserInfoManager.getInstance().getToken(mContext);
                presenter.savePersonInfo(token,nickName,sexNum,area,intro,school);
                break;
            case R.id.tv_sex:
                showDialog();
                break;
        }
    }

    private void showDialog() {
        View view= LayoutInflater.from(mContext).inflate(R.layout.sex_dialog, null);

        AlertDialog.Builder builder= new AlertDialog.Builder(mContext);

        builder.setView(view);

        final AlertDialog dialog=builder.create();

        final RadioButton rbMan=(RadioButton)view.findViewById(R.id.rb_man);
        final RadioButton rbWoman=(RadioButton)view.findViewById(R.id.rb_woman);

        TextView tvCancel=(TextView)view.findViewById(R.id.tv_cancel);

        TextView tvEnsure=(TextView)view.findViewById(R.id.tv_ensure);

        rbMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbMan.setChecked(true);
                rbWoman.setChecked(false);
            }
        });
        rbWoman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbMan.setChecked(false);
                rbWoman.setChecked(true);
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //取消

                dialog.dismiss();

            }

        });

        tvEnsure.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                //确定
                if(rbMan.isChecked()){
                    tvSex.setText("男");
                }else if(rbWoman.isChecked()){
                    tvSex.setText("女");
                }
                dialog.dismiss();
            }

        });

        dialog.show();
    }

    @Override
    public void savePersonInfo(UserEntity.DataBean userEntity) {
        if(ActivityUtils.isFinish(mContext)){
            return;
        }
        ToastUtils.showShort(mContext,"修改成功");
        Intent intent = new Intent();
        intent.putExtra("userEntity",userEntity);
        setResult(1,intent);
        finish();
    }
}
