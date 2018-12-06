package com.example.noobkenneth.cody.database;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {

    public static final String UTILS_TAG = "UtilsTag";

    public static InputStream getInputStream(URL url){

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        InputStream inputStream = null;

        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
        }catch(IOException e) {
            e.printStackTrace();
            inputStream = null;
        }

        return inputStream;

    }

    public static String getJson(URL url){

        return convertStreamToString(getInputStream(url));
    }

    public static String convertStreamToString(InputStream inputStream){

        BufferedReader reader = null;
        String outString;

        StringBuffer buffer = new StringBuffer();
        if (inputStream == null) {
            // Nothing to do.
            return null;
        }
        reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        try{
            while ((line = reader.readLine()) != null) {
   /* Since it's JSON, adding a newline isn't necessary (it won't affect
      parsing) but it does make debugging a *lot* easier if you print out the
      completed buffer for debugging. */
            buffer.append(line + "\n");
            }

        } catch( IOException e){
            e.printStackTrace();

        }
        if (buffer.length() == 0) {
            // Stream was empty.  No point in parsing.
            return null;
        }
        outString = buffer.toString();
        Log.i(UTILS_TAG,outString);
        return outString;

    }


    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean haveNetwork = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        Log.i(UTILS_TAG, "Active Network: " + haveNetwork);
        return haveNetwork;
    } 

    public static Bitmap convertStreamToBitmap (InputStream inputStream){

        return BitmapFactory.decodeStream(inputStream);

    }

    public static Bitmap getBitmap(URL url){

        return convertStreamToBitmap(getInputStream(url));

    }

    public static int getResIdFromFileName(Context context, String fname) throws Resources.NotFoundException {

        String package_name = context.getPackageName();
        int resId = context.getResources().getIdentifier(fname, "drawable", package_name);
        if (resId <= 0) {
            throw new Resources.NotFoundException("No such resource in drawable");
        }else {
            return resId;
        }

    }

    public static Bitmap getBitmapFromResId(Context context, int resId) {

        Drawable drawable = context.getResources().getDrawable(resId);
        return ((BitmapDrawable) drawable).getBitmap();

    }

    public static byte[] convertBitmapToByteArray(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }


}
