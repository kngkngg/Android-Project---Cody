package com.example.noobkenneth.cody.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.noobkenneth.cody.Customise.CustomiseActivity;
import com.example.noobkenneth.cody.Home.MainActivity;
import com.example.noobkenneth.cody.R;

public class RecyclerViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CharaAdapter charaAdapter;
    CharaDbHelper charaDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyler_view);

        //receives the intent from Wardrobe activity
        Intent intent_fromWardrobe = getIntent();
        CharaAdapter.category_query = intent_fromWardrobe.getStringExtra("CATEGORY");

        //TODO 9.7 The standard code to fill the recyclerview with data
        recyclerView = findViewById(R.id.charaRecyclerView);
        charaDbHelper = CharaDbHelper.createCharaDbHelper(this);
        charaAdapter = new CharaAdapter(this, charaDbHelper);
        recyclerView.setAdapter(charaAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        //TODO 8.3 When the fab is clicked, launch DataEntryActivity and invoke startActivityForResult
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_DataEntry = new Intent(RecyclerViewActivity.this, DataEntryActivity.class);
                intent_DataEntry.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent_DataEntry);
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
                        Log.i("Logcat", "home pressed from RecyclerViewActivity");
                        Intent intent_wardrobe = new Intent(RecyclerViewActivity.this, MainActivity.class);
                        intent_wardrobe.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_wardrobe);
                        break;
                    case R.id.navigation_wardrobe:
                        Log.i("Logcat", "wardrobe pressed from RecyclerViewActivity");
                        break;
                    case R.id.navigation_ootds:
                        Log.i("Logcat", "ootds pressed from RecyclerViewActivity");
                        Intent intent_ootds = new Intent(RecyclerViewActivity.this, CalendarActivity.class);
                        intent_ootds.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_ootds);
                        break;
                    case R.id.navigation_customise:
                        Log.i("Logcat", "customise pressed from RecyclerViewActivity");
                        Intent intent_customise = new Intent(RecyclerViewActivity.this, CustomiseActivity.class);
                        intent_customise.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_customise);
                        break;
                    case R.id.navigation_generate:
                        Log.i("Logcat", "profile pressed from RecyclerViewActivity");
                        break;
                }
                return false;
            }
        });

        //TODO 9.8 Put in code to allow each recyclerview item to be deleted when swiped
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback( 0 ,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                //TODO ATTENTION SVP - to write this code we had to make the CharaViewHolder class static
                CharaAdapter.CharaViewHolder charaViewHolder
                        = (CharaAdapter.CharaViewHolder) viewHolder;

                String id = charaViewHolder.textViewId.getText().toString();
                charaDbHelper.deleteOneRow(id);
                Toast.makeText(RecyclerViewActivity.this, "Deleting Row", Toast.LENGTH_LONG).show();
                charaAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

            }
        };


        //TODO 9.9 attach the recyclerView to helper
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper( simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


    }
}
