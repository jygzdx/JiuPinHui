package com.jiupin.jiupinhui.widget;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.entity.GoodsEntity;
import com.jiupin.jiupinhui.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/3/16.
 * 商品的图片展示控件
 */

public class GoodsShowView extends LinearLayout {
    private final String TAG = "ADBannerView";

    private Context mContext;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private ArrayList<View> viewsList = new ArrayList<View>();
    private LinearLayout ll_icon; // 广告图片下标LinearLayout
    private List<View> icons = new ArrayList<View>(); //
    // banner timer 自动滚动实现
    private Timer timerBanner;
    private TimerTask timerTaskBanner;
    private Handler handlerBanner;

    // “正在加载广告”
    TextView tv_loading;

    /**
     * ViewPager是否无限滚动
     */
    private boolean unlimitedPageScrolled = false;
    /**
     * 广告位id
     */
    private String adPositionId = "61BFE28EB9AE41D0B4F728CCC5BD8A62";

    private long peroid = 3500;

    public GoodsShowView(Context context) {
        this(context, false);
    }

    /**
     * @param context
     * @param unlimitedPageScrolled 设置ViewPager是否开启无限滚动模式
     */
    public GoodsShowView(Context context, boolean unlimitedPageScrolled) {
        this(context, unlimitedPageScrolled, null);
    }

    /**
     * @param context
     * @param unlimitedPageScrolled 设置ViewPager是否开启无限滚动模式
     * @param adPositionId          广告位id
     */
    public GoodsShowView(Context context, boolean unlimitedPageScrolled, String adPositionId) {
        super(context);
        this.mContext = context;
        this.unlimitedPageScrolled = unlimitedPageScrolled;
        if (adPositionId != null)
            this.adPositionId = adPositionId;
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.view_ad_banner, null);
        viewPager = (ViewPager) view.findViewById(R.id.ad_viewpager);
        ll_icon = (LinearLayout) view.findViewById(R.id.ll_icon);
        tv_loading = (TextView) view.findViewById(R.id.tv_loading);

        addView(view);
        initViewPager();
    }

    /**
     * 请求服务端，从服务端加载广告图片
     */
    public void loadAD(GoodsEntity.DataBean data) {
        tv_loading.setVisibility(View.VISIBLE);
//        String requstUrl = Constant.CAROUSEL_URL;
//        OkHttpUtils
//                .get()
//                .url(requstUrl)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        LogUtils.i(TAG, "网络请求失败" + call.toString());
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        tv_loading.setVisibility(View.GONE);
//                        Gson gson = new Gson();
//                        Carousel carousel = gson.fromJson(response.toString(), Carousel.class);
//                        String msg = carousel.getMsg();
//                        if ("OK".equals(msg)) {
//                            createADImageView(carousel.getData());
//                        }
//                    }
//                });
        createADImageView(data);
        

    }


    /**
     * 创建广告位图片
     * @param data
     */
    public void createADImageView(GoodsEntity.DataBean data) {
        try {
            viewPager.removeAllViews();
            ll_icon.removeAllViews();
            icons.clear();
            viewsList.clear();

            for (int j = 0; j < data.getProdPhoto().size(); j++) {
                GoodsEntity.DataBean.ProdPhotoBean info = data.getProdPhoto().get(j);

                if (data.getProdPhoto().size() >= 2) {
                    ImageView ivIcon = new ImageView(mContext);
                    if (j == 0) {
                        ivIcon.setBackgroundResource(R.drawable.circle_selected);
                    } else {
                        ivIcon.setBackgroundResource(R.drawable.circle_no_selected);
                    }
                    LayoutParams lp = new LayoutParams(
                            LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT);
                    lp.setMargins(5, 2, 5, 2);
                    ll_icon.addView(ivIcon, lp);
                    icons.add(ivIcon);
                }

                // 首末添加一个View, 用来实现无限循环滑动效果，如实际3张图片的时候这样添加：(3)123(1)
                // 大于2张图片时才需要滑动
                // TODO:无限滚动
                if (unlimitedPageScrolled) {
                    if (data.getProdPhoto().size() >= 2 && j == 0) {
                        // 把末张图片添加至首位
                        addImageView(data.getProdPhoto().get(
                                data.getProdPhoto().size() - 1));
                    }
                }

                // 正常添加ImageView
                addImageView(info);

                // 首末添加一个View, 用来实现无限循环滑动效果，如实际3张图片的时候这样添加：(3)123(1)
                // 大于2张图片时才需要滑动
                // TODO:无限滚动
                if (unlimitedPageScrolled) {
                    if (data.getProdPhoto().size() >= 2
                            && j == data.getProdPhoto().size() - 1) {
                        // 把首张图片添加到末位
                        addImageView(data.getProdPhoto().get(0));
                    }
                }
            }

            pagerAdapter.notifyDataSetChanged();

            // 无限滚动时，大于2张图片时, 初始化位置为1
            // TODO:无限滚动
            if (unlimitedPageScrolled) {
                if (icons.size() >= 2) {
                    viewPager.setCurrentItem(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addImageView(final GoodsEntity.DataBean.ProdPhotoBean info) {
        final ImageView iv = new ImageView(mContext);
//        iv.setTag(info);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);

        Glide.with(mContext.getApplicationContext())
                .load(info.getPath())
                .placeholder(R.drawable.middlebanner1)
                .crossFade()
                .into(iv);

        viewsList.add(iv);

        // 点击广告图片查看广告详情 0 无任何动作  1 打开网站（用默认浏览器打开） 2 打开应用  3 下载 4打开功能 5应用打开网页
        iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.i(TAG, "点击了--" + info.getId());
            }
        });
    }


    /**
     * 初始化ViewPager
     */

    private void initViewPager() {
        pagerAdapter = new AdPagerAdapter(mContext, viewsList);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new PageChangeListener());
    }

    /**
     * 广告图片适配器
     *
     * @author morton
     */
    class AdPagerAdapter extends PagerAdapter {
        private List<View> views = null;

        public AdPagerAdapter(Context mContext, List<View> views) {
            this.views = views;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //            super.destroyItem(container, position, object);
            if (getCount() > 1) {
                ((ViewPager) container).removeView(views.get(position));
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager) container).addView(views.get(position));
            return views.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

    }

    class PageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

            if (arg0 == ViewPager.SCROLL_STATE_IDLE) {// 滚动完成处于空闲状态
                // TODO:无限滚动
                if (unlimitedPageScrolled) {
                    if (icons.size() >= 2) {
                        int currentPage = viewPager.getCurrentItem();
                        if (currentPage < 1) {// 首位之前，跳转到末尾
                            currentPage = icons.size();
                            viewPager.setCurrentItem(currentPage, false); // false:不显示跳转过程的动画
                        } else if (currentPage > icons.size()) {// 末位之后，跳转到首位
                            currentPage = 1;
                            viewPager.setCurrentItem(currentPage, false); // false:不显示跳转过程的动画
                        }
                    }
                }
            }

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            // TODO:无限滚动
            if (unlimitedPageScrolled) {
                if (icons.size() >= 2
                        && (position < 1 || position > icons.size())) {
                    position = (position % icons.size());
                }
                position = (position > 0 ? position - 1 : position);
            }

			/* 设置下标位置图片变化 */
            for (int i = 0; i < icons.size(); i++) {
                if (position == i) {
                    icons.get(i).setBackgroundResource(
                            R.drawable.circle_selected);
                } else {
                    icons.get(i).setBackgroundResource(
                            R.drawable.circle_no_selected);
                }
            }

        }
    }

}
