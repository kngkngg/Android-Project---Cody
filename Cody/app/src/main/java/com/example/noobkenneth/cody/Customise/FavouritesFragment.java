package com.example.noobkenneth.cody.Customise;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noobkenneth.cody.R;

import java.util.ArrayList;

public class FavouritesFragment extends Fragment {


    private RecyclerView recyclerView;
    private OutfitDataAdapter mAdapter;
    SwipeController swipeController = null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favourites_fragment,container,false);

        recyclerView = (RecyclerView)view.findViewById(R.id.fav_recyclerview);


        setOutfitDataAdapter();
        setupRecyclerView();

        return view;
    }

    private void setOutfitDataAdapter(){
        ArrayList<Outfit> outfits = new ArrayList<>();

        Outfit o1 = new Outfit();
        Bitmap bittie = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.outfit1);
        o1.setImage(bittie);
        o1.setTextthing("hi1");
        outfits.add(o1);

        o1 = new Outfit();
        bittie = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.outfit2);
        o1.setImage(bittie);
        o1.setTextthing("hi2");
        outfits.add(o1);

        o1 = new Outfit();
        bittie = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.outfit1);
        o1.setImage(bittie);
        o1.setTextthing("hi3");
        outfits.add(o1);

        o1 = new Outfit();
        bittie = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.outfit2);
        o1.setImage(bittie);
        o1.setTextthing("hi4");
        outfits.add(o1);

        mAdapter = new OutfitDataAdapter(outfits);

    }

    private void setupRecyclerView(){

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);


        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                mAdapter.outfits.remove(position);
                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
    }
}


