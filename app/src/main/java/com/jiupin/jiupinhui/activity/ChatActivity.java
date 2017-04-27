package com.jiupin.jiupinhui.activity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.utils.DensityUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.WindowUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 咨询记录
 */
public class ChatActivity extends BaseActivity {

    @BindView(R.id.tv_reply)
    TextView tvReply;
    @BindView(R.id.tv_close)
    TextView tvClose;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.et_input_text)
    EditText etInputText;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.ll_input_text)
    LinearLayout llInputText;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    @BindView(R.id.rv_chat)
    RecyclerView rvChat;

    private boolean isFirst = true;
    private int firstHeight;
    private int softHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        showEditText();

    }

    /**
     * 展示edittext
     */
    private void showEditText() {
        etInputText.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            //当键盘弹出隐藏的时候会 调用此方法。
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                etInputText.getWindowVisibleDisplayFrame(rect);
                if (isFirst) {
                    isFirst = false;
                    firstHeight = rect.height();
                } else {
                    int height = rect.height();
                    if (height < firstHeight) {//键盘打开
                        softHeight = firstHeight - height;
                        //显示输入框
                        llInputText.setVisibility(View.VISIBLE);
                        //屏幕的高度
                        int windowHeight = WindowUtils.getWindowHeight(ChatActivity.this);
                        //输入框的高度--写死的
                        int inputHeihgt = DensityUtils.dp2px(mContext, 35);
                        LogUtils.d("windowHeight=" + windowHeight + "inputHeihgt=" + inputHeihgt+ ",softHeight = " + softHeight);
                        ObjectAnimator animator = ObjectAnimator.ofFloat(llInputText, "translationY", windowHeight, windowHeight - softHeight - inputHeihgt-rect.top);
                        animator.setDuration(10).start();
                    } else {//键盘关闭
                        llInputText.setVisibility(View.GONE);
                    }
                }
            }

        });
    }

    @OnClick({R.id.tv_reply, R.id.tv_close, R.id.btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_reply:
                etInputText.requestFocus();
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            case R.id.tv_close:
                break;
            case R.id.btn_send:
                break;
        }
    }
}
