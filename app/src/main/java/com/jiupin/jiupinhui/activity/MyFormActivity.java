package com.jiupin.jiupinhui.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.adapter.MyFormAdapter;
import com.jiupin.jiupinhui.entity.Form;
import com.jiupin.jiupinhui.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的订单
 */
public class MyFormActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ll_title)
    LinearLayout llTitleContent;
    @BindView(R.id.ll_form_pull)
    LinearLayout llFormPull;
    @BindView(R.id.iv_more)
    ImageView ivMore;
    @BindView(R.id.iv_pull_down)
    ImageView ivPullDown;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.rv_my_form)
    RecyclerView rvMyForm;
    @BindView(R.id.id_view)
    View idView;
    @BindView(R.id.rb_all_form)
    RadioButton rbAllForm;
    @BindView(R.id.rl_form_pull)
    RelativeLayout rlFormPull;
    @BindView(R.id.rb_wait_pay)
    RadioButton rbWaitPay;
    @BindView(R.id.rb_wait_deliver_goods)
    RadioButton rbWaitDeliverGoods;
    @BindView(R.id.rb_wait_gain_goods)
    RadioButton rbWaitGainGoods;
    @BindView(R.id.rb_wait_comment)
    RadioButton rbWaitComment;
    @BindView(R.id.rg_control_top)
    RadioGroup rgControlTop;
    @BindView(R.id.rg_control_bottom)
    RadioGroup rgControlBottom;
    private MyFormAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_form);

        //测试
        List<Form> forms = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Form form = new Form();
            form.setStatus(101+i%6);
            form.setName("这是瓶美酒"+i);
            forms.add(form);
        }
        RecyclerView.LayoutManager layout = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvMyForm.setLayoutManager(layout);

        adapter = new MyFormAdapter(mContext,forms);
        rvMyForm.setAdapter(adapter);
    }

    @OnClick({R.id.iv_back, R.id.ll_title, R.id.iv_more, R.id.rl_title, R.id.rv_my_form, R.id.rb_all_form, R.id.id_view, R.id.rb_wait_pay, R.id.rb_wait_deliver_goods, R.id.rb_wait_gain_goods, R.id.rb_wait_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.ll_title:
                if (rlFormPull.getVisibility() == View.GONE) {
                    rlFormPull.setVisibility(View.VISIBLE);
                    showRotationAnimator(ivPullDown, 0f, 180f);
                    int height = DensityUtils.dp2px(this, 350f);
                    showAnimator(llFormPull, -height, 0f);
                    showAlphaAnimator(0f, 0.6f);
                } else {
                    showRotationAnimator(ivPullDown, 180f, 0f);
                    hidePopupWindow(llFormPull);
                }
                break;
            case R.id.iv_more:
                break;
            case R.id.rl_title:
                break;
            case R.id.rv_my_form:
                break;
            case R.id.id_view:
                hidePopupWindow(llFormPull);
                showRotationAnimator(ivPullDown, 180f, 0f);
                break;
            case R.id.rb_all_form:
                rgControlTop.clearCheck();
                rgControlBottom.clearCheck();
                rbAllForm.setChecked(true);
                break;
            case R.id.rb_wait_pay:
                rgControlTop.clearCheck();
                rgControlBottom.clearCheck();
                rbWaitPay.setChecked(true);
                break;
            case R.id.rb_wait_deliver_goods:
                rgControlTop.clearCheck();
                rgControlBottom.clearCheck();
                rbWaitDeliverGoods.setChecked(true);
                break;
            case R.id.rb_wait_gain_goods:
                rgControlTop.clearCheck();
                rgControlBottom.clearCheck();
                rbWaitGainGoods.setChecked(true);
                break;
            case R.id.rb_wait_comment:
                rgControlTop.clearCheck();
                rgControlBottom.clearCheck();
                rbWaitComment.setChecked(true);
                break;
        }
    }

    /**
     * 隐藏popupwindow
     */
    public void hidePopupWindow(final View view) {
        int height = DensityUtils.dp2px(this, 350f);
        ObjectAnimator animator = showAnimator(view, 0f, -height);
        showAlphaAnimator(0.6f, 0f);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                rlFormPull.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * 展示平移动画
     *
     * @param fromY 启示y轴
     * @param toY   结束y轴
     * @return ObjectAnimator
     */
    public ObjectAnimator showAnimator(View view, float fromY, float toY) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", fromY, toY);
        animator.setDuration(300)
                .start();
        return animator;
    }

    /**
     * 展示旋转动画
     *
     * @param fromY 旋转开始角度
     * @param toY   旋转结束角度
     * @return ObjectAnimator
     */
    public ObjectAnimator showRotationAnimator(View view, float fromY, float toY) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotationX", fromY, toY);
        animator.setDuration(300)
                .start();
        return animator;
    }

    /**
     * 透明度动画
     *
     * @param fromAlpha
     * @param toAlpha
     */
    public void showAlphaAnimator(float fromAlpha, float toAlpha) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(idView, "alpha", fromAlpha, toAlpha);
        animator.setDuration(300)
                .start();
    }
}
