package com.example.noobkenneth.cody.database;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.noobkenneth.cody.R;


public class CharaAdapter extends RecyclerView.Adapter<CharaAdapter.CharaViewHolder> {

    LayoutInflater mInflater;
    Context context;
    CharaDbHelper charaDbHelper;
    static String category_query = null;
    static String ootd_query = null;


    //TODO 9.3 Constructor takes in a context object and a CharaDbHelper object
    //TODO 9.3 assign the inputs to instance variables
    public CharaAdapter(Context context, CharaDbHelper charaDbHelper) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.charaDbHelper = charaDbHelper;
    }

    //TODO 9.4 onCreateViewHolder inflates each CardView layout (no coding)
    @NonNull
    @Override
    public CharaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.layout, viewGroup, false);
        return new CharaViewHolder(itemView);
    }

    //TODO 9.5 onBindViewHolder binds the data to each card according to its position
    @Override
    public void onBindViewHolder(@NonNull CharaViewHolder charaViewHolder, int i) {
        // i is the position in the recyclerview
        charaViewHolder.textViewPosition.setText(Integer.toString(i));
//        CharaDbHelper.CharaData charaData = charaDbHelper.queryOneRow(i);
        CharaDbHelper.CharaData charaData = null;
//        charaData = charaDbHelper.queryOneRowClothes(i);
//        charaData = charaDbHelper.queryOneRowWhereCatOotd(i, null, null);
        if (category_query == null) {
            charaData = charaDbHelper.queryOneRowWhereCatOotd(i, null, null);
        } else {
            switch (category_query) {
                case "'Tops'":
                    Log.i("Logcat", "CharaAdapter querying Tops from database to RecyclerView");
                    charaData = charaDbHelper.queryOneRowWhereCatOotd(i, category_query, ootd_query);
                    break;
                case "'Bottoms'":
                    Log.i("Logcat", "CharaAdapter querying Bottoms from database to RecyclerView");
                    charaData = charaDbHelper.queryOneRowWhereCatOotd(i, category_query, ootd_query);
                    break;
                case "'One-piece'":
                    Log.i("Logcat", "CharaAdapter querying One-piece from database to RecyclerView");
                    charaData = charaDbHelper.queryOneRowWhereCatOotd(i, category_query, ootd_query);
                    break;
                case "'Shoes'":
                    Log.i("Logcat", "CharaAdapter querying Shoes from database to RecyclerView");
                    charaData = charaDbHelper.queryOneRowWhereCatOotd(i, category_query, ootd_query);
                    break;
                case "'Bags'":
                    Log.i("Logcat", "CharaAdapter querying Bags from database to RecyclerView");
                    charaData = charaDbHelper.queryOneRowWhereCatOotd(i, category_query, ootd_query);
                    break;
                case "'Accessories'":
                    Log.i("Logcat", "CharaAdapter querying Accessories from database to RecyclerView");
                    charaData = charaDbHelper.queryOneRowWhereCatOotd(i, category_query, ootd_query);
                    break;
                default:
                    Log.i("Logcat", "No valid category");
                    break;
            }
        }
        if (charaData != null) {
            charaViewHolder.textViewId.setText("" + charaData.getId());
            charaViewHolder.textViewCategory.setText(charaData.getCategory());
            charaViewHolder.textViewFormality.setText(charaData.getFormality());
            charaViewHolder.textViewLastUsed.setText(charaData.getLastUsed());
            charaViewHolder.textViewOotd.setText(String.valueOf(charaData.getOotd()));
            charaViewHolder.imageViewChara.setImageBitmap(charaData.getBitmap());
        }
    }

    //TODO 9.6 this method controls the number of cardviews in the recyclerview
    @Override
    public int getItemCount() {
        int numberOfRows = (int) charaDbHelper.queryNumRowsCatOotd(category_query, ootd_query);
//        Log.i("Logcat", "numberOfRows: " + numberOfRows);
        return numberOfRows;
    }

    //TODO 9.2 Complete the constructor to initialize the widgets
    //TODO ATTENTION SVP we had to make this class static
    static class CharaViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewId;
        public TextView textViewPosition;
        public TextView textViewCategory;
        public TextView textViewFormality;
        public TextView textViewLastUsed;
        public TextView textViewOotd;
        public ImageView imageViewChara;

        public CharaViewHolder(View view) {
            super(view);
            textViewId = view.findViewById(R.id.cardViewTextId);
            textViewPosition = view.findViewById(R.id.cardViewTextCount);
            imageViewChara = view.findViewById(R.id.cardViewImage);
            textViewCategory = view.findViewById(R.id.cardViewTextCategory);
            textViewFormality = view.findViewById(R.id.cardViewTextFormality);
            textViewLastUsed = view.findViewById(R.id.cardViewTextLastUsed);
            textViewOotd = view.findViewById(R.id.cardViewTextOotd);
        }
    }
}
