package com.example.noobkenneth.cody.Customise;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.noobkenneth.cody.database.RecommendationsActivity;
import com.example.noobkenneth.cody.database.CalendarActivity;
import com.example.noobkenneth.cody.Home.MainActivity;
import com.example.noobkenneth.cody.R;
import com.example.noobkenneth.cody.database.RecyclerViewActivity;

public class CustomiseActivity extends AppCompatActivity {
    private static final String TAG = "CustomiseActivity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customise);
        Log.d(TAG, "onCreate: Starting");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        //Set up ViewPager with sections Adapter
        mViewpager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewpager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewpager);

        //This part dictates the behaviour of the bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        //sets the selected bottom bar item
        bottomNavigationView.setSelectedItemId(R.id.navigation_customise);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Log.i("Logcat", "home pressed from CustomiseActivity");
                        Intent intent_home = new Intent(CustomiseActivity.this, MainActivity.class);
                        intent_home.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_home);
                        break;
                    case R.id.navigation_wardrobe:
                        Log.i("Logcat", "wardrobe pressed from CustomiseActivity");
                        Intent intent_wardrobe = new Intent(CustomiseActivity.this, RecyclerViewActivity.class);
                        intent_wardrobe.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_wardrobe);
                        break;
                    case R.id.navigation_ootds:
                        Log.i("Logcat", "ootds pressed from CustomiseActivity");
                        Intent intent_ootds = new Intent(CustomiseActivity.this, CalendarActivity.class);
                        intent_ootds.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_ootds);
                        break;
                    case R.id.navigation_customise:
                        Log.i("Logcat", "customise pressed from CustomiseActivity");
//                        Intent intent_customise = new Intent(CustomiseActivity.this, CustomiseActivity.class);
//                        intent_customise.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                        startActivity(intent_customise);
                        break;
                    case R.id.navigation_generate:
                        Log.i("Logcat", "generate pressed from CalendarActivity");
                        Intent intent_recommendations = new Intent(CustomiseActivity.this, RecommendationsActivity.class);
                        intent_recommendations.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_recommendations);
                        break;
                }
                return false;
            }
        });
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new CustomiseFragment(), "Customise");
        adapter.addFragment(new FavouritesFragment(), "Favourites");
        viewPager.setAdapter(adapter);
    }
}