package com.example.noobkenneth.cody.Home;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noobkenneth.cody.Customise.CustomiseActivity;
import com.example.noobkenneth.cody.R;
import com.example.noobkenneth.cody.database.RecommendationsActivity;
import com.example.noobkenneth.cody.Wardrobe.WardrobeActivity;
import com.example.noobkenneth.cody.api.BusinessApiClient;
import com.example.noobkenneth.cody.api.BusinessApiInterface;
import com.example.noobkenneth.cody.database.CalendarActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.noobkenneth.cody.models.BusinessArticle;
import com.example.noobkenneth.cody.models.FashionArticle;
import com.example.noobkenneth.cody.models.News;


public class MainActivity extends AppCompatActivity {

    public static String API_KEY = "a2f02240b01c4e25a1377b872c016b93";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private String TAG = MainActivity.class.getSimpleName();
    private TextView mTextMessage;

    private final String JSON_URL = "https://api.myjson.com/bins/1darxi";
    private List<BusinessArticle> businessArticles = new ArrayList<>();
    private List<FashionArticle> fashionArticles = new ArrayList<>();
    private BusinessNewsAdapter businessNewsAdapter;
    private FashionNewsAdapter fashionNewsAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView fashionNews;
    private TextView businessNews;
    private boolean businessNewsClicked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        //This part dictates the behaviour of the bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        //sets the selected bottom bar item
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Log.i("Logcat", "home pressed from MainActivity");
                        break;
                    case R.id.navigation_wardrobe:
                        Log.i("Logcat", "wardrobe pressed from MainActivity");
                        Intent intent_wardrobe = new Intent(MainActivity.this, WardrobeActivity.class);
                        intent_wardrobe.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_wardrobe);
                        break;
                    case R.id.navigation_ootds:
                        Log.i("Logcat", "ootds pressed from MainActivity");
                        Intent intent_ootds = new Intent(MainActivity.this, CalendarActivity.class);
                        intent_ootds.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_ootds);
                        break;
                    case R.id.navigation_customise:
                        Log.i("Logcat", "customise pressed from MainActivity");
                        Intent intent_customise = new Intent(MainActivity.this, CustomiseActivity.class);
                        intent_customise.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_customise);
                        break;
                    case R.id.navigation_generate:
                        Log.i("Logcat", "generate pressed from MainActivity");
                        Intent intent_recommendations = new Intent(MainActivity.this, RecommendationsActivity.class);
                        intent_recommendations.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_recommendations);
                        break;
                }
                return false;
            }
        });


        fashionNews = findViewById(R.id.fashion_news);
        businessNews = findViewById(R.id.business_news);

        //lstFashionArticle = new ArrayList<>();

        fashionNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                businessNewsClicked = false;
                layoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setNestedScrollingEnabled(false);
                MainActivity.LoadFashionNewsJson loadFashionNewsJson = new MainActivity.LoadFashionNewsJson();
                loadFashionNewsJson.execute(JSON_URL);
                businessNewsClicked = false;


            }
        });

        businessNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView = findViewById(R.id.recyclerView);
                layoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setNestedScrollingEnabled(false);
                businessNewsClicked = true;

                LoadBusinessNewsJson();
            }
        });


        // Implements the wardrobe layout in the homepage

        LinearLayout gallery = findViewById(R.id.gallery);

        LayoutInflater galleryInflater = LayoutInflater.from(this);

        for (int i = 0; i < 1; i++) {

            View view = galleryInflater.inflate(R.layout.item, gallery, false);

            TextView textView = view.findViewById(R.id.textView);
            textView.setText("All Clothing");

            ImageView imageView = view.findViewById(R.id.imageView);
            imageView.setImageResource(R.drawable.blue_suit);

            gallery.addView(view);
        }

        for (int i = 0; i < 1; i++) {

            View view = galleryInflater.inflate(R.layout.clothing_category, gallery, false);

            TextView textView = view.findViewById(R.id.textView2);
            textView.setText("Shirts");

            ImageView imageView0 = view.findViewById(R.id.imageView0);
            ImageView imageView1 = view.findViewById(R.id.imageView1);
            ImageView imageView2 = view.findViewById(R.id.imageView2);
            ImageView imageView3 = view.findViewById(R.id.imageView3);
            imageView0.setImageResource(R.drawable.pink_shirt);
            imageView1.setImageResource(R.drawable.blue_shirt);
            imageView2.setImageResource(R.drawable.stripe_shirt);
            imageView3.setImageResource(R.drawable.white_shirt);

            gallery.addView(view);
        }

        for (int i = 0; i < 1; i++) {

            View view = galleryInflater.inflate(R.layout.clothing_category, gallery, false);

            TextView textView = view.findViewById(R.id.textView2);
            textView.setText("Shoes");

            ImageView imageView0 = view.findViewById(R.id.imageView0);
            ImageView imageView1 = view.findViewById(R.id.imageView1);
            ImageView imageView2 = view.findViewById(R.id.imageView2);
            ImageView imageView3 = view.findViewById(R.id.imageView3);
            imageView0.setImageResource(R.drawable.white_sneakers);
            imageView1.setImageResource(R.drawable.black_formal_shoes);
            imageView2.setImageResource(R.drawable.gucci_sneakers);
            imageView3.setImageResource(R.drawable.brown_loafers);

            gallery.addView(view);
        }

        // Implements the ability to refresh news

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
//        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        // Implements the news layout

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        MainActivity.LoadFashionNewsJson loadFashionNewsJson = new MainActivity.LoadFashionNewsJson();
        loadFashionNewsJson.execute(JSON_URL);

        onLoadingSwipeRefresh("");
    }

//    @Override
    public void onRefresh() {
        if (businessNewsClicked) {
            LoadBusinessNewsJson();
        } else {
            layoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(false);

            MainActivity.LoadFashionNewsJson loadFashionNewsJson = new MainActivity.LoadFashionNewsJson();
            loadFashionNewsJson.execute(JSON_URL);

            swipeRefreshLayout.setRefreshing(false);
        }

    }

    private void onLoadingSwipeRefresh(final String keyword) {
        if (businessNewsClicked) {
            swipeRefreshLayout.post(
                    new Runnable() {
                        @Override
                        public void run() {
                            LoadBusinessNewsJson();
                        }
                    }
            );
        } else {
            swipeRefreshLayout.post(
                    new Runnable() {
                        @Override
                        public void run() {
                            layoutManager = new LinearLayoutManager(MainActivity.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setNestedScrollingEnabled(false);

                            MainActivity.LoadFashionNewsJson loadFashionNewsJson = new MainActivity.LoadFashionNewsJson();
                            loadFashionNewsJson.execute(JSON_URL);
                        }
                    }
            );
        }
    }

    private void initListener() {

        businessNewsAdapter.setOnItemClickListener(new BusinessNewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, NewsDetailActivity.class);

                BusinessArticle article = businessArticles.get(position);
                intent.putExtra("url", article.getUrl());
                intent.putExtra("title", article.getTitle());
                intent.putExtra("img", article.getUrlToImage());
                intent.putExtra("date", article.getPublishedAt());
                intent.putExtra("source", article.getSource().getName());
                intent.putExtra("author", article.getAuthor());

                startActivity(intent);
            }
        });
    }


    private void initListener2() {

        fashionNewsAdapter.setOnItemClickListener(new FashionNewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, FashionNewsDetailActivity.class);

                FashionArticle article = fashionArticles.get(position);
                intent.putExtra("url", article.getUrl());
                intent.putExtra("title", article.getTitle());
                intent.putExtra("img", article.getUrlToImage());
                //intent.putExtra("date", article.getPublishedAt());
                intent.putExtra("source", article.getSource().getName());
                //intent.putExtra("author", article.getAuthor());

                startActivity(intent);
            }
        });
    }
    //Reads the JSON file provided by the API

    public void LoadBusinessNewsJson() {

        swipeRefreshLayout.setRefreshing(true);

        BusinessApiInterface apiInterface = BusinessApiClient.getApiClient().create(BusinessApiInterface.class);

        String country = Utils.getCountry();
        //String category = "business";
        int pageSize = 100;


        Call<News> call;
        call = apiInterface.getNews(pageSize, country, API_KEY);

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() && response.body().getBusinessArticle() != null) {
                    if (!businessArticles.isEmpty()) {
                        businessArticles.clear();
                    }

                    businessArticles = response.body().getBusinessArticle();
                    List<BusinessArticle> articleWithImage = new ArrayList<>();
                    for (BusinessArticle article : businessArticles) {
                        //System.out.println("TITLE: " + article.getUrlToImage());
                        if (article.getUrlToImage() != null) {
                            articleWithImage.add(article);
                        }
                    }
                    businessNewsAdapter = new BusinessNewsAdapter(articleWithImage, MainActivity.this);
                    recyclerView.setAdapter(businessNewsAdapter);
                    businessNewsAdapter.notifyDataSetChanged();

                    initListener();

                    swipeRefreshLayout.setRefreshing(false);

                } else {
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(MainActivity.this, "No Result!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }

    class LoadFashionNewsJson extends AsyncTask<String, Void, List<FashionArticle>> {
        @Override
        protected List<FashionArticle> doInBackground(String... strings) {
            fashionArticles = Utils.getJSON(strings[0]);
            return fashionArticles;
        }

        @Override
        protected void onPostExecute(List<FashionArticle> articles) {
            super.onPostExecute(articles);
            fashionNewsAdapter = new FashionNewsAdapter(articles, MainActivity.this);
            recyclerView.setAdapter(fashionNewsAdapter);
            fashionNewsAdapter.notifyDataSetChanged();

            initListener2();
        }

    }

}
