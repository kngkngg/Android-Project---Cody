package com.example.noobkenneth.cody.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.noobkenneth.cody.R;

import org.w3c.dom.Text;

public class FashionNewsDetailActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{

    private ImageView imageView;
    private TextView appbar_title, appbar_subtitle, date, time, title;
    private boolean isHideToolBarView = false;
    private FrameLayout date_behavior;
    private LinearLayout titleAppBar;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private String mUrl, mImg, mTitle, mDate, mSource, mAuthor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");

        appBarLayout = findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(this);
        date_behavior = findViewById(R.id.date_behavior);
        titleAppBar = findViewById(R.id.title_appbar);
        imageView = findViewById(R.id.backdrop);
        appbar_title = findViewById(R.id.title_on_appbar);
        appbar_subtitle = findViewById(R.id.subtitle_on_appbar);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        title = findViewById(R.id.title);

        Intent intent = getIntent();
        mUrl = intent.getStringExtra("url").substring(1, intent.getStringExtra("url").length() - 1);
        mImg = intent.getStringExtra("img").substring(1, intent.getStringExtra("img").length() - 1);
        mTitle = intent.getStringExtra("title").substring(1, intent.getStringExtra("title").length() - 1);
        //mDate = intent.getStringExtra("date").substring(1, intent.getStringExtra("date").length() - 1);
        mSource = intent.getStringExtra("source").substring(1, intent.getStringExtra("source").length() - 1);
        //mAuthor = intent.getStringExtra("author").substring(1, intent.getStringExtra("author").length() - 1);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(Utils.getRandomDrawbleColor());

        Glide.with(this)
                .load(mImg)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);

        appbar_title.setText(mSource);
        appbar_subtitle.setText(mUrl);
        //date.setText(Utils.DateFormat(mDate));
        title.setText(mTitle);

        String author = null;
        if (mAuthor != null || mAuthor != "") {
            mAuthor = " \u2022 " + mAuthor;
        } else {
            author = "";
        }

        //time.setText(mSource + author + " \u2022 " + Utils.DateToTimeFormat(mDate));

        initWebView(mUrl);


    }

    private void initWebView(String url) {
        WebView webview = findViewById(R.id.webView);
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(false);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(url);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        if (percentage == 1f && isHideToolBarView) {
            date_behavior.setVisibility(View.GONE);
            titleAppBar.setVisibility(View.VISIBLE);
            isHideToolBarView = !isHideToolBarView;
        }

        else if (percentage < 1f && isHideToolBarView) {
            date_behavior.setVisibility(View.VISIBLE);
            titleAppBar.setVisibility(View.GONE);
            isHideToolBarView = !isHideToolBarView;
        }
    }
}
