package com.example.noobkenneth.cody.Recommendations;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.noobkenneth.cody.R;

import java.util.List;

public class RecAdapter extends PagerAdapter {

    private List<Recommendations> generatedOutfit;
    private List<Recommendations> generatedOutfit2;
    private List<Recommendations> generatedOutfit3;
    private List<Recommendations> generatedOutfit4;
    private List<Recommendations> generatedOutfit5;


    private LayoutInflater layoutInflater;
    private Context context;
    String LogCatTag = "Recommendationslog";
    ImageView apparel;
    RecGenerateOutfit recGenerateOutfit;

    public RecAdapter(Context context, String selectedStyle) {
        Log.i(LogCatTag,"Entered RecAdapter Constructor");

        recGenerateOutfit = new RecGenerateOutfit(selectedStyle);
        generatedOutfit = recGenerateOutfit.getGeneratedOutfit();
        generatedOutfit2 = recGenerateOutfit.getAnotherOutfit();
        generatedOutfit3 = recGenerateOutfit.getAnotherOutfit();
        generatedOutfit4 = recGenerateOutfit.getAnotherOutfit();
        generatedOutfit5 = recGenerateOutfit.getAnotherOutfit();


        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public int getCount() {
        return generatedOutfit.size();
    }

    @NonNull //denotes that a parameter/field/method can never return null
    @Override
    //container View in which the page will be shown
    //position = page position to be instantiated
    public Object instantiateItem(@NonNull ViewGroup container, int position){

        Log.i(LogCatTag,"Enter RecAdapter's instantiateItem");
        View view = layoutInflater.inflate(R.layout.rec_item, container, false);

        ImageView apparel0 = view.findViewById(R.id.rec_imageView2);
        ImageView apparel1 = view.findViewById(R.id.rec_imageView3);
        ImageView apparel2 = view.findViewById(R.id.rec_imageView4);
        ImageView apparel3 = view.findViewById(R.id.rec_imageView5);
        ImageView apparel4 = view.findViewById(R.id.rec_imageView6);
        ImageView apparel5 = view.findViewById(R.id.rec_imageView7);

        int count = 0;

        while(count!=6){
            switch(count){
                case 0: apparel = apparel0; break;
                case 1: apparel = apparel1; break;
                case 2: apparel = apparel2; break;
                case 3: apparel = apparel3; break;
                case 4: apparel = apparel4; break;
                case 5: apparel = apparel5; break;
            }

            switch(position) {
                case 0:
                    apparel.setImageBitmap(generatedOutfit.get(count).getImage());
                    break;
                case 1:
                    apparel.setImageBitmap(generatedOutfit2.get(count).getImage());
                    break;
                case 2:
                    apparel.setImageBitmap(generatedOutfit3.get(count).getImage());
                    break;
                case 3:
                    apparel.setImageBitmap(generatedOutfit4.get(count).getImage());
                    break;
                case 4:
                    apparel.setImageBitmap(generatedOutfit5.get(count).getImage());
                    break;
            }

            count += 1;
        }

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container, position, object); //only works on FragmentPagerAdapter
        container.removeView((View)object);
    }
}
