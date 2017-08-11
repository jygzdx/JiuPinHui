package com.jiupin.jiupinhui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.ISubmitQuestionActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.SubmitQuestionActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.DensityUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.StringUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.ISubmitQuestionActivityView;
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
 * 用户提交咨询问题
 */
public class SubmitQuestionActivity extends TakePhotoActivity implements ISubmitQuestionActivityView{

    private static final String TAG = "SubmitQuestionActivity";
    @BindView(R.id.ll_picture_container)
    LinearLayout llPictureContainer;
    @BindView(R.id.et_question_content)
    EditText etQuestionContent;
    private TakePhoto takePhoto;
    private CompressConfig config;

    private List<File> files = new ArrayList<>();
    private ISubmitQuestionActivityPresenter presenter;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_question);
        ButterKnife.bind(this);

        presenter = new SubmitQuestionActivityPresenterImpl(this);
        token = UserInfoManager.getInstance().getToken(this);
    }

    @OnClick({R.id.iv_back, R.id.iv_submit_picture, R.id.iv_submit_question})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_submit_picture:
                takePhoto = getTakePhoto();
                if (null == config) {
                    config = new CompressConfig.Builder()
                            .setMaxSize(500 * 1024)
                            .setMaxPixel(800)
                            .enableReserveRaw(true)
                            .create();
                }
                takePhoto.onEnableCompress(config, true);
                takePhoto.onPickMultiple(3);
                break;
            case R.id.iv_submit_question:
                String content = etQuestionContent.getText().toString();
                if(StringUtils.isEmpty(content)){
                    return;
                }

                token = UserInfoManager.getInstance().getToken(this);
                //提交问题
                presenter.submitQuestion(token,content,files);

                break;
        }
    }


    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        ArrayList<TImage> images = result.getImages();
        llPictureContainer.removeAllViews();
        llPictureContainer.setOrientation(LinearLayout.HORIZONTAL);
        ImageView image;
        for (int i = 0; i < images.size(); i++) {
            LogUtils.d(TAG,"path = "+images.get(i).getCompressPath());
            files.add(new File(images.get(i).getCompressPath()));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            llPictureContainer.setLayoutParams(params);
            image = new ImageView(this);
            ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(DensityUtils.dp2px(this, 60), DensityUtils.dp2px(this, 60));
            image.setLayoutParams(vlp);
            image.setPadding(DensityUtils.dp2px(this, 5), 0, DensityUtils.dp2px(this, 5), 0);
            Glide.with(this)
                    .load(new File(images.get(i).getCompressPath()))
                    .into(image);
            llPictureContainer.addView(image);
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
    public void submitQuestionSuccess() {
        ToastUtils.showShort(this,"提交成功，请等待客服回复");
        finish();
    }
}
