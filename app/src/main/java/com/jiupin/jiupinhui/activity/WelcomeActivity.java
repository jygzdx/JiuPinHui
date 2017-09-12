package com.jiupin.jiupinhui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.vp_welcome)
    ViewPager vpWelcome;
    @BindView(R.id.btn_start)
    Button btnStart;
    private int[] gifUrl;
    private MyPagerAdapter pagerAdapter;
    private List<ImageView> imgList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);


        //让状态栏透明
        if(Build.VERSION.SDK_INT >= 21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        gifUrl = new int[]{
                R.drawable.welcome_1,
                R.drawable.welcome_2,
                R.drawable.welcome_3,
                R.drawable.welcome_4,
        };

        pagerAdapter = new MyPagerAdapter();
        vpWelcome.setAdapter(pagerAdapter);
        vpWelcome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (gifUrl.length-1==position){
                    btnStart.setVisibility(View.VISIBLE);
                }else{
                    btnStart.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMainActivity();
                finish();
            }
        });
    }
    /**
     * 进去启动页
     */
    private void gotoLauchActivity() {
        SPUtils.put(mContext,SPUtils.IS_FIRST_WELCOME,false);
        Intent intent = new Intent(WelcomeActivity.this,LaunchActivity.class);
        startActivity(intent);
    }

    /**
     * 进去主页
     */
    private void gotoMainActivity() {
        SPUtils.put(mContext,SPUtils.IS_FIRST_WELCOME,false);
        Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public class MyPagerAdapter extends PagerAdapter{

        public MyPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if(imgList.size()-1>position){
                return imgList.get(position);
            }else{
                ImageView imageView = new ImageView(mContext);
                Glide.with(mContext)
                        .load(gifUrl[position])
                        .asGif()
                        .placeholder(gifUrl[position])
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(imageView);
                container.addView(imageView);
                imgList.add(imageView);
                return imageView;
            }
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            View view = (View)object;
//            container.removeView(view);
        }

        @Override
        public int getCount() {
            return gifUrl.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }
}
