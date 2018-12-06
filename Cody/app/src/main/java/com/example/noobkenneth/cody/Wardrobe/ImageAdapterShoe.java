package com.example.noobkenneth.cody.Wardrobe;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.noobkenneth.cody.R;

public class ImageAdapterShoe extends BaseAdapter {
    private Context mContext;

    public ImageAdapterShoe(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;

        // this if statement puts new images to imageViews that will be added upon scrolling
        if (convertView == null) {
            // properties of each imageView that will be added into the gridView
            imageView = new ImageView(mContext);
            imageView.setAdjustViewBounds(true);
            imageView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }
        else
        {
            // if there exits a view already, we return the imageView
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]); // maps images to position in mThumbIds
        return imageView;
    }

    // reference all Images from an array, index of items starts from 0
    public Integer[] mThumbIds = {
            R.drawable.shoe,
            R.drawable.shoe,
            R.drawable.shoe,
            R.drawable.shoe,
            R.drawable.shoe,
            R.drawable.shoe,

    };
}
