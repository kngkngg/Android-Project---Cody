//package com.example.noobkenneth.cody.database;
//
//import android.content.ContentResolver;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.util.Log;
//
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//
//public class TestCharaDbHelper {
//
//    public static final String TAG = "Logcat";
//
//    static void testQueryOneRowRandom(CharaDbHelper charaDbHelper){
//        Log.i(TAG, "testQueryOneRowRandom");
//        CharaDbHelper.CharaData charaData = charaDbHelper.queryOneRowRandom();
//        printToLogCat(charaData);
//    }
//
//    static void testTable(CharaDbHelper charaDbHelper){
//
//        Log.i(TAG,"test: check contents of table");
//        int numRows = (int) charaDbHelper.queryNumRows();
//        for (int i = 0 ; i < numRows; i++){
//            Log.i(TAG,"position " + i);
//            CharaDbHelper.CharaData charaData = charaDbHelper.queryOneRow(i);
//            printToLogCat(charaData);
//        }
//
//    }
//
//    static void testInsertOneRow(CharaDbHelper charaDbHelper, String name, String description, int resID){
//
//        Log.i(TAG, "test: Insert One Row");
//        Context context = charaDbHelper.getContext();
//        Uri uri = getUriToDrawable(context, resID);
//
//        try{
//            InputStream inputStream = context.getContentResolver().openInputStream(uri);
//            Bitmap bitmap = Utils.convertStreamToBitmap(inputStream);
//            CharaDbHelper.CharaData charaData = new CharaDbHelper.CharaData( name, description, bitmap);
//            charaDbHelper.insertOneRow(charaData);
//            testTable(charaDbHelper);
//            printToLogCat(charaData);
//            Log.i(TAG,"Rows: " + charaDbHelper.queryNumRows());
//        }catch(FileNotFoundException ex){
//            Log.i(TAG, "null bitmap");
//        }
//
//    }
//
//    static void testDeleteOneRow(CharaDbHelper charaDbHelper, String name){
//
//        Log.i(TAG, "Test: delete one Row");
//        int rowsDeleted = charaDbHelper.deleteOneRow(name);
//        Log.i(TAG,"Rows deleted: " + rowsDeleted);
//        testTable(charaDbHelper);
//    }
//
//
//    static void printToLogCat(CharaDbHelper.CharaData charaData){
//        Log.i(TAG, "Name: " + charaData.getName()
//                + " Description:" + charaData.getDescription()
//                + " FileName:" + charaData.getFile()
//                + " Bitmap" + charaData.getBitmap().toString());
//    }
//
//    static final Uri getUriToDrawable( Context context,
//                                       int drawableId) {
//        Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
//                "://" + context.getResources().getResourcePackageName(drawableId)
//                + '/' + context.getResources().getResourceTypeName(drawableId)
//                + '/' + context.getResources().getResourceEntryName(drawableId) );
//        return imageUri;
//    }
//
//
//}
