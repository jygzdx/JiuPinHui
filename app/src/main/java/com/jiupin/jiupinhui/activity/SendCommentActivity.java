package com.jiupin.jiupinhui.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.utils.ToastUtils;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendCommentActivity extends BaseActivity {
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
    @BindView(R.id.iv_picture1)
    ImageView ivPicture1;
    @BindView(R.id.iv_picture2)
    ImageView ivPicture2;
    @BindView(R.id.iv_picture3)
    ImageView ivPicture3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_comment);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.iv_issue, R.id.iv_add_picture})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.iv_issue:
                ToastUtils.showShort(mContext, "发布成功");
                finish();
                break;
            case R.id.iv_add_picture:
                setImage();
                break;
        }
    }

    private void setImage() {
        Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
        getAlbum.setType(IMAGE_TYPE);
        startActivityForResult(getAlbum, IMAGE_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap bm = null;
        ContentResolver resolver = getContentResolver();
        if (requestCode == IMAGE_CODE) {

            try {
                Uri originalUri = data.getData();
                bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);
                ivPicture1.setImageBitmap(bm);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
