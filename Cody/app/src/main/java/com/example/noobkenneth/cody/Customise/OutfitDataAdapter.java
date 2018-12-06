package com.example.noobkenneth.cody.Customise;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.noobkenneth.cody.R;

import java.util.List;


class OutfitDataAdapter extends RecyclerView.Adapter<OutfitDataAdapter.OutfitViewHolder> {
    public List<Outfit> outfits;

    public class OutfitViewHolder extends RecyclerView.ViewHolder {
        private TextView textview_favtext;
        private ImageView imageview_favoutfit;

        public OutfitViewHolder(View view) {
            super(view);
            textview_favtext = (TextView) view.findViewById(R.id.textview_favtext);
            imageview_favoutfit = (ImageView) view.findViewById(R.id.imageview_favoutfit);

        }
    }

    public OutfitDataAdapter(List<Outfit> outfits) {
        this.outfits = outfits;
    }

    @Override
    public OutfitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.outfit_row, parent, false);

        return new OutfitViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OutfitViewHolder holder, int position) {
        Outfit outfit = outfits.get(position);
        holder.imageview_favoutfit.setImageBitmap(outfit.getImage());
        holder.textview_favtext.setText(outfit.getTextthing());
    }

    @Override
    public int getItemCount() {
        return outfits.size();
    }
}