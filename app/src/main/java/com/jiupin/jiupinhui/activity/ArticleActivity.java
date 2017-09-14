package com.jiupin.jiupinhui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.config.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 展示热门文章
 */
public class ArticleActivity extends AppCompatActivity {

    @BindView(R.id.wv_article)
    WebView wvArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ButterKnife.bind(this);
        initWebView();

        String url = getIntent().getExtras().getString("url");

        wvArticle.loadUrl(Constant.MAIN_URL+url);

    }

    /**
     * 初始化webview
     */
    private void initWebView() {
        WebSettings setting = wvArticle.getSettings();
        //禁止webview进行复制黏贴
        wvArticle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        setting.setJavaScriptEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            setting.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }


        wvArticle.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }
        });

    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
