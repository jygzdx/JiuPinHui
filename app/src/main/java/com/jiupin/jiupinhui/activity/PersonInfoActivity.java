package com.jiupin.jiupinhui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.entity.UserEntity;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.IPersonInfoActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.PersonInfoActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.SPUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IPersonInfoActivityView;
import com.jiupin.jiupinhui.widget.CircleImageView;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个人信息页面
 */
public class PersonInfoActivity extends BaseActivity implements IPersonInfoActivityView, TakePhoto.TakeResultListener, InvokeListener {
    private static final String TAG = "PersonInfoActivity";
    private final String IMAGE_TYPE = "image/*";
    @BindView(R.id.btn_exit_login)
    Button btnExitLogin;
    @BindView(R.id.tv_user_nickname)
    TextView tvUserNickname;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_binding_phone)
    TextView tvBindingPhone;
    @BindView(R.id.civ_head)
    CircleImageView civHead;

    private IPersonInfoActivityPresenter presenter;
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private String token;

    private static final int REVISE_NICKNAME_CODE = 1;
    private static final int BINDING_PHONE_CODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        ButterKnife.bind(this);
        presenter = new PersonInfoActivityPresenterImpl(this);
        token = UserInfoManager.getInstance().getToken(this);
        refreshData();


    }

    //刷新数据
    private void refreshData() {
        presenter.getUserInfoByToken(token);
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @OnClick({R.id.iv_back, R.id.ll_user_nickname, R.id.ll_binding_phone, R.id.btn_exit_login, R.id.ll_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:

                finish();
                break;
            case R.id.ll_user_nickname:
                Intent intent1 = new Intent(mContext, ReviseNicknameActivity.class);
                startActivityForResult(intent1,REVISE_NICKNAME_CODE);
                break;
            case R.id.ll_binding_phone:
                Intent intent2 = new Intent(mContext, BindingPhoneActivity.class);
                startActivityForResult(intent2,BINDING_PHONE_CODE);
                break;
            case R.id.btn_exit_login:
                civHead.setVisibility(View.GONE);
                tvUserName.setText("");
                tvUserNickname.setText("");
                tvBindingPhone.setText("");
                UserInfoManager.getInstance().setLogin(false);
                UserInfoManager.getInstance().setToken("");
                SPUtils.remove(this, SPUtils.LOGIN_TOKEN);
                ToastUtils.showShort(this, "退出登录成功");
                finish();
                break;
            case R.id.ll_head:
                File file = new File(Environment.getExternalStorageDirectory(), "/images/" + System.currentTimeMillis() + ".jpg");
                if (!file.getParentFile().exists())
                    file.getParentFile().mkdirs();
                Uri imageUri = Uri.fromFile(file);
                CropOptions cropOptions = new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(true).create();
                getTakePhoto().onPickFromGalleryWithCrop(imageUri, cropOptions);
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REVISE_NICKNAME_CODE){
            refreshData();
        }else if(requestCode==BINDING_PHONE_CODE){
            refreshData();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    @Override
    public void takeSuccess(TResult result) {//选择头像成功
        LogUtils.i(TAG, "takeSuccess：" + result);
        if (result != null) {

            Glide.with(this)
                    .load(new File(result.getImage().getOriginalPath()))
                    .into(civHead);
            //上传图片
            String path = result.getImage().getOriginalPath();
            String name = path.substring(path.lastIndexOf("/") + 1);
            File file = new File(Environment.getExternalStorageDirectory(), "/images/" + name);
            token = UserInfoManager.getInstance().getToken(this);
            presenter.pushPicture(file, name, token);
        }

    }

    @Override
    public void takeFail(TResult result, String msg) {
        LogUtils.i(TAG, "takeFail:" + msg);
    }

    @Override
    public void takeCancel() {
        LogUtils.i(TAG, getResources().getString(R.string.msg_operation_canceled));
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    @Override
    public void setUserInfo(UserEntity userEntity) {
        LogUtils.d(TAG, "setUserInfo.url = " + userEntity.getData().getImageUrl());
        //初始化控件
        tvUserNickname.setText(userEntity.getData().getNickName());
        Glide.with(this)
                .load(userEntity.getData().getImageUrl())
                .crossFade()
                .into(civHead);
        tvUserName.setText(userEntity.getData().getUserName());
        tvBindingPhone.setText(userEntity.getData().getMobile());

    }

    @Override
    public void pushPicture(ResponseBase responseBase) {
        LogUtils.d(TAG + responseBase.getMsg());
        //        Glide.with(this)
        //                .load((String) responseBase.getData())
        //                .crossFade()
        //                .into(civHead);
    }
}
