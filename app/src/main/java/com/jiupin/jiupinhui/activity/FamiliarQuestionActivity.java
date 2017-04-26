package com.jiupin.jiupinhui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.jiupin.jiupinhui.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 常见问题界面
 */
public class FamiliarQuestionActivity extends AppCompatActivity {

    @BindView(R.id.tv_contact_service)
    TextView tvContactService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_familiar_question);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_contact_service)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_contact_service:

                break;
        }
    }
}
