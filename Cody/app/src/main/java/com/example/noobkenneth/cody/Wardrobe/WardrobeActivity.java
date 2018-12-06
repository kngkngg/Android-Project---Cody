package com.example.noobkenneth.cody.Wardrobe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.noobkenneth.cody.database.RecommendationsActivity;
import com.example.noobkenneth.cody.database.CalendarActivity;
import com.example.noobkenneth.cody.Customise.CustomiseActivity;
import com.example.noobkenneth.cody.Home.MainActivity;
import com.example.noobkenneth.cody.R;
import com.example.noobkenneth.cody.database.RecyclerViewActivity;

public class WardrobeActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardrobe);

        GridView gridview = findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this)); // Image adapter to put images into WardrobeActivity
        gridview.setOnItemClickListener(this); // Make gridView clickable by user

        // floating action button NOTE: remember to put items into onCreate! previously failed to complete a toolbar perhaps because i forgot to put it here.
        // to add a floating action button, add implementation 'com.android.support:design:28.0.0' to gradle dependencies
        FloatingActionButton addItemfab = findViewById(R.id.addItemfab); // add item floating action button
        addItemfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Add new category when clicked?", Toast.LENGTH_LONG).show();
                Intent start_recyclerView = new Intent(WardrobeActivity.this, RecyclerViewActivity.class);
                startActivity(start_recyclerView);
            }
        });

        //This part dictates the behaviour of the bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        //sets the selected bottom bar item
        bottomNavigationView.setSelectedItemId(R.id.navigation_wardrobe);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Log.i("Logcat", "home pressed from WardrobeActivity");
                        Intent intent_home = new Intent(WardrobeActivity.this, MainActivity.class);
                        intent_home.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_home);
                        break;
                    case R.id.navigation_wardrobe:
                        Log.i("Logcat", "wardrobe pressed from WardrobeActivity");
//                        Intent intent_wardrobe = new Intent(WardrobeActivity.this, WardrobeActivity.class);
//                        intent_wardrobe.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                        startActivity(intent_wardrobe);
                        break;
                    case R.id.navigation_ootds:
                        Log.i("Logcat", "ootds pressed from WardrobeActivity");
                        Intent intent_ootds = new Intent(WardrobeActivity.this, CalendarActivity.class);
                        intent_ootds.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_ootds);
                        break;
                    case R.id.navigation_customise:
                        Log.i("Logcat", "customise pressed from WardrobeActivity");
                        Intent intent_customise = new Intent(WardrobeActivity.this, CustomiseActivity.class);
                        intent_customise.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_customise);
                        break;
                    case R.id.navigation_generate:
                        Log.i("Logcat", "generate pressed from WardrobeActivity");
                        Intent intent_recommendations = new Intent(WardrobeActivity.this, RecommendationsActivity.class);
                        intent_recommendations.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_recommendations);
                        break;
                }
                return false;
            }
        });

    }




    @Override
    public void onClick(View v) {
    }

    /*
    * This part of the code is for the image adapter needed to create the gridView
    * It actually generates and destroys imageViews as the user scrolls up and down
    * */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // detect if correct gridview is clicked, in this case, we only have 1 gridview
        if(parent.getId() == R.id.gridview)
        {

            // each imageView item in gridView has a position. The numbering corresponds to the numbering in the image adapter thumbnail array(in ImageAdapter.java), starting from index 0.
            switch (position)
            {
                // each case for a different item on gridView, where case number is item index in thumbnail array (in ImageAdapter.java)
                case 0:
                {
                    // onclick, start new intent to go to another activity
//                    Toast.makeText(getApplicationContext(), "This toast is for tops!!", Toast.LENGTH_LONG).show();
                    Log.i("Logcat", "Wardrobe activity requesting for tops from RecyclerViewActivity");
                    Intent intent = new Intent(WardrobeActivity.this, RecyclerViewActivity.class);
                    intent.putExtra("CATEGORY", "'Tops'");
                    startActivity(intent);
                    break;
                }

                case 1:
                {
                    // onclick, start new intent to go to another activity
//                    Toast.makeText(getApplicationContext(), "This toast is for bottoms!!", Toast.LENGTH_LONG).show();
                    Log.i("Logcat", "WardrobeActivity requesting for Bottoms from RecyclerViewActivity");
                    Intent intent = new Intent(WardrobeActivity.this, RecyclerViewActivity.class);
                    intent.putExtra("CATEGORY", "'Bottoms'");
                    startActivity(intent);
                    break;
                }

                case 2:
                {
                    // onclick, start new intent to go to another activity
//                    Toast.makeText(getApplicationContext(), "This toast is for onepiece!!", Toast.LENGTH_LONG).show();
                    Log.i("Logcat", "WardrobeActivity requesting for One-piece from RecyclerViewActivity");
                    Intent intent = new Intent(WardrobeActivity.this, RecyclerViewActivity.class);
                    intent.putExtra("CATEGORY", "'One-piece'");
                    startActivity(intent);
                    break;
                }

                case 3: {
                    // onclick, start new intent to go to another activity

//                    Toast.makeText(getApplicationContext(), "This toast is for shoes!!", Toast.LENGTH_LONG).show();
                    Log.i("Logcat", "WardrobeActivity requesting for Shoes from RecyclerViewActivity");
                    Intent intent = new Intent(WardrobeActivity.this, RecyclerViewActivity.class);
                    intent.putExtra("CATEGORY", "'Shoes'");
                    startActivity(intent);
                    break;
                }

                case 4:
                {
                    // onclick, start new intent to go to another activity
//                    Toast.makeText(getApplicationContext(), "This toast is for bags!!", Toast.LENGTH_LONG).show();
                    Log.i("Logcat", "WardrobeActivity requesting for Bags from RecyclerViewActivity");
                    Intent intent = new Intent(WardrobeActivity.this, RecyclerViewActivity.class);
                    intent.putExtra("CATEGORY", "'Bags'");
                    startActivity(intent);
                    break;
                }
                case 5:
                {
                    // onclick, start new intent to go to another activity
//                    Toast.makeText(getApplicationContext(), "This toast is for accessories!!", Toast.LENGTH_LONG).show();
                    Log.i("Logcat", "WardrobeActivity requesting for Accessories from RecyclerViewActivity");
                    Intent intent = new Intent(WardrobeActivity.this, RecyclerViewActivity.class);
                    intent.putExtra("CATEGORY", "'Accessories'");
                    startActivity(intent);
                    break;
                }

                default:
                {
                    Toast.makeText(getApplicationContext(), "No action associated with this item", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(WardrobeActivity.this, RecyclerViewActivity.class);
                    intent.putExtra("CATEGORY", "");
                    startActivity(intent);
                    break;
                }
            }
        }

    }
}
