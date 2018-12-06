package com.example.noobkenneth.cody.Recommendations;

import android.graphics.Bitmap;
import android.util.Log;

public class Recommendations {
    private Bitmap image;

    public Recommendations(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage(){
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}


