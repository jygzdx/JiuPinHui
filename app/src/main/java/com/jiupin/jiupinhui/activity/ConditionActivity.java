package com.jiupin.jiupinhui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.adapter.ConditionAdapter;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.IConditionActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.ConditionActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.StringUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.IConditionActivityView;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 酒圈发布动态界面
 */
public class ConditionActivity extends TakePhotoActivity implements IConditionActivityView{
    private static final String TAG = "ConditionActivity";

    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_release)
    TextView tvRelease;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.rv_picture)
    RecyclerView rvPicture;

    private List<File> files = new ArrayList<>();
    private ConditionAdapter adapter;
    private TakePhoto takePhoto;
    private CompressConfig config;
    private IConditionActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition);
        ButterKnife.bind(this);
        initRecyclerView();

        presenter = new ConditionActivityPresenterImpl(this);

    }

    private void initRecyclerView() {
        rvPicture.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapter = new ConditionAdapter(this);
        rvPicture.setAdapter(adapter);
    }

    @OnClick({R.id.tv_cancel, R.id.tv_release, R.id.iv_add_picture})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_release:
                String content = etContent.getText().toString();
                if(StringUtils.isEmpty(content)){
                    ToastUtils.showShort(this,"请输入发布内容");
                    return;
                }
                String token = UserInfoManager.getInstance().getToken(this);
                presenter.sendCondition(token,content,"0",files);

                break;
            case R.id.iv_add_picture:
                takePhoto = getTakePhoto();
                if (null == config) {
                    config = new CompressConfig.Builder()
                            .setMaxSize(5 * 1024)
                            .setMaxPixel(800)
                            .enableReserveRaw(true)
                            .create();
                }
                takePhoto.onEnableCompress(config, true);
                takePhoto.onPickMultiple(8);
                break;
        }
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
        LogUtils.d(TAG, "takeCancel");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG, "onDestroy");
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        LogUtils.d(TAG, "takeFail");
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        List<TImage> images = result.getImages();
        if(images.size()>0){
            adapter.setData(images);
            for (int i = 0; i < images.size(); i++) {
                files.add(new File(images.get(i).getCompressPath()));
            }
        }
    }

    @Override
    public void sendConditionSuccess() {
        ToastUtils.showShort(this,"发布成功");
    }
}
