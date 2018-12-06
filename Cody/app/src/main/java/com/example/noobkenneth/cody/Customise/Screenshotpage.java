package com.example.noobkenneth.cody.Customise;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.noobkenneth.cody.R;

public class Screenshotpage extends AppCompatActivity {

    Bitmap bmp;
    ImageView imageview_screenshotpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screenshotpage);

        byte[] byteArray = getIntent().getByteArrayExtra("screenshot");
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        imageview_screenshotpage = (ImageView) findViewById(R.id.imageview_screenshotpage);
        imageview_screenshotpage.setImageBitmap(bmp);
    }
}

