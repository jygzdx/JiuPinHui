package com.jiupin.jiupinhui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.utils.DensityUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品评价界面
 */
public class SendCommentActivity extends TakePhotoActivity {
    private static final String TAG = "SendCommentActivity";
    private final String IMAGE_TYPE = "image/*";
    private final int IMAGE_CODE = 0;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_issue)
    TextView ivIssue;
    @BindView(R.id.iv_goods_pic)
    ImageView ivGoodsPic;
    @BindView(R.id.iv_add_picture)
    ImageView ivAddPicture;
    @BindView(R.id.ll_images)
    LinearLayout llImages;

    private Context mContext;
    private TakePhoto takePhoto;
    private CompressConfig config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_comment);
        mContext = this;
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.iv_issue, R.id.iv_add_picture})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_issue:
                ToastUtils.showShort(mContext, "发布成功");
                finish();
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
                takePhoto.onPickMultiple(3);
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
        ArrayList<TImage> images = result.getImages();
        llImages.removeAllViews();
        llImages.setOrientation(LinearLayout.HORIZONTAL);
        ImageView image;
        for (int i = 0; i < images.size(); i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            llImages.setLayoutParams(params);
            image = new ImageView(mContext);
            ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(DensityUtils.dp2px(this,60), DensityUtils.dp2px(this,60));
            image.setLayoutParams(vlp);
            image.setPadding(DensityUtils.dp2px(this,5),0,DensityUtils.dp2px(this,5),0);
            Glide.with(this)
                    .load(new File(images.get(i).getCompressPath()))
                    .into(image);
            llImages.addView(image);
        }


    }

}
