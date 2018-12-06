package com.example.noobkenneth.cody.Wardrobe;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class ShoeActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.noobkenneth.cody.R.layout.activity_shoe);


        GridView gridview = findViewById(com.example.noobkenneth.cody.R.id.gridviewShoe);
        gridview.setAdapter(new ImageAdapterShoe(this)); // Image adapter to put images into WardrobeActivity
        gridview.setOnItemClickListener(this); // Make gridView clickable by user

        // floating action button NOTE: remember to put items into onCreate! previously failed to complete a toolbar perhaps because i forgot to put it here.
        // to add a floating action button, add implementation 'com.android.support:design:28.0.0' to gradle dependencies
        FloatingActionButton addItemfab = findViewById(com.example.noobkenneth.cody.R.id.addItemfab); // add item floating action button
        addItemfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: add item to end of ImageAdapterShoe thumbID array
                /**
                 * 1. add item to drawable folder.
                 * 2. add item to array. Problem - array size is immutable.. might need to use arraylist?
                * */
                Toast.makeText(getApplicationContext(), "Add new item when clicked", Toast.LENGTH_LONG).show();
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

    //TODO: do we need to do anything when we click on items in a category?
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // detect if correct gridview is clicked, in this case, we only have 1 gridview
        if(parent.getId() == com.example.noobkenneth.cody.R.id.gridviewShoe)
        {

            // each imageView item in gridView has a position. The numbering corresponds to the numbering in the image adapter thumbnail array(in ImageAdapter.java), starting from index 0.
            switch (position)
            {
                default:
                {
                    Toast.makeText(getApplicationContext(), "No action associated with this item", Toast.LENGTH_LONG).show();
                    break;
                }
            }
        }

    }
}
