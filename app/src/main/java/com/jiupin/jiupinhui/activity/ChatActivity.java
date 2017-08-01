package com.jiupin.jiupinhui.activity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.adapter.ChatAdapter;
import com.jiupin.jiupinhui.entity.ChatEntity;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.IChatActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.ChatActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.DensityUtils;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.StringUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.utils.WindowUtils;
import com.jiupin.jiupinhui.view.IChatActivityView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 咨询记录
 */
public class ChatActivity extends BaseActivity implements IChatActivityView {

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
    @BindView(R.id.lrv_chat)
    LRecyclerView lrvChat;

    private boolean isFirst = true;
    private int firstHeight;
    private int softHeight;
    private String orderNum;
    private IChatActivityPresenter presenter;
    private String token;
    private ChatAdapter adapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        showEditText();

        token = UserInfoManager.getInstance().getToken(this);
        orderNum = getIntent().getExtras().getString("orderNum");
        presenter = new ChatActivityPresenterImpl(this);

        initData();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        lrvChat.setLayoutManager(manager);
        adapter = new ChatAdapter(mContext);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        lrvChat.setAdapter(lRecyclerViewAdapter);
        lrvChat.setPullRefreshEnabled(false);

    }

    private void initData() {
        presenter.getChatInfo(token, orderNum);
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
                        if(llInputText.getVisibility()==View.GONE){
                            softHeight = firstHeight - height;
                            //显示输入框
                            llInputText.setVisibility(View.VISIBLE);
                            //屏幕的高度
                            int windowHeight = WindowUtils.getWindowHeight(ChatActivity.this);
                            //输入框的高度--写死的
                            int inputHeihgt = DensityUtils.dp2px(mContext, 35);
                            LogUtils.d("windowHeight=" + windowHeight + "inputHeihgt=" + inputHeihgt + ",softHeight = " + softHeight);
                            ObjectAnimator animator = ObjectAnimator.ofFloat(llInputText, "translationY", windowHeight, windowHeight - softHeight - inputHeihgt - rect.top);
                            animator.setDuration(10).start();
                        }
                    } else {//键盘关闭
                        llInputText.setVisibility(View.GONE);
                    }
                }
            }

        });
    }

    @OnClick({R.id.tv_reply, R.id.tv_close, R.id.btn_send, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_reply:
                etInputText.requestFocus();
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            case R.id.tv_close:
                showDialog();

                break;
            case R.id.btn_send://发送咨询信息
                String content = etInputText.getText().toString();
                if(!StringUtils.isEmpty(content)){
                    presenter.getAgainAppraise(null, token, orderNum, content);
                }else{
                    ToastUtils.showShort(this,"请输入要询问的信息");
                }

                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void showDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_content, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tvEnsure = (TextView) view.findViewById(R.id.tv_ensure);

        tvContent.setText("确定要关闭提问？");
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
                presenter.closeChat(token,orderNum);
                //确定
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void setChatList(List<ChatEntity> chatList, String hint) {
        //初始化聊天数据
        LogUtils.d(hint);
        if (chatList != null && chatList.size() > 0) {
            adapter.setData(chatList);
        }
    }

    @Override
    public void sendChatSuccess(List<ChatEntity> chatList, String hint) {
        if (chatList != null && chatList.size() > 0) {
            adapter.clear();
            adapter.addAll(chatList);
            lrvChat.refreshComplete(chatList.size());
            etInputText.setText("");
//            SoftKeyboardUtils.hideSoftKeyboard(ChatActivity.this);
        }
    }

    @Override
    public void closeChatSuccess(List<ChatEntity> chatList) {
        if(chatList != null && chatList.size() > 0){
            adapter.clear();
            adapter.addAll(chatList);
            lrvChat.refreshComplete(chatList.size());
        }
    }
}
