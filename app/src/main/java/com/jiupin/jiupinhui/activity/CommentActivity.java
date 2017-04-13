package com.jiupin.jiupinhui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.manage.PopWinManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommentActivity extends AppCompatActivity {
    private static final String TAG = "CommentActivity";
    @BindView(R.id.iv_more)
    ImageView ivMore;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        mContext = this;
    }

    @OnClick({R.id.iv_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_more:
                PopWinManager popWinManager = new PopWinManager(mContext,view);
                popWinManager.createPopupWindow();
                break;
        }

    }

}
