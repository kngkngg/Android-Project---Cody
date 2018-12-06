package com.example.noobkenneth.cody.Wardrobe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.noobkenneth.cody.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaggingActivity extends AppCompatActivity {
    Spinner tags;
    Spinner categoryfinder;
    ImageView realimage;
    Button toPress;
    public static final String APP_TAG="CodyPics";
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tagging);
        Intent intent=getIntent();
        toPress=findViewById(R.id.PressMe);
        final SharedPreferences category;
        final Bitmap croppedbitmap=intent.getParcelableExtra(CroppingActivity.PREVIMAGE);
        tags=findViewById(R.id.editor);
        realimage=findViewById(R.id.cropped);
        realimage.setImageBitmap(croppedbitmap);
        categoryfinder=findViewById(R.id.categoriser);
        tags.setPrompt("Select Dress Code");
        categoryfinder.setPrompt("Select Category");
        ArrayAdapter<CharSequence> DCadapter=ArrayAdapter.createFromResource(this, R.array.dresscode,
                android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> Catadapter=ArrayAdapter.createFromResource(this, R.array.category,
                android.R.layout.simple_spinner_item);
        DCadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Catadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tags.setAdapter(DCadapter);
        categoryfinder.setAdapter(Catadapter);
        tags.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0) {
                    String DC = tags.getSelectedItem().toString();
                    SharedPreferences dresscode = getSharedPreferences("Dress Code", MODE_PRIVATE);
                    SharedPreferences.Editor prefDC = dresscode.edit();
                    prefDC.putString("ValueDC", DC);
                    Toast.makeText(TaggingActivity.this, dresscode.getString("ValueCat",DC),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        categoryfinder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0) {
                    String Cat = categoryfinder.getSelectedItem().toString();
                    SharedPreferences category = getSharedPreferences("Category", MODE_PRIVATE);
                    SharedPreferences.Editor prefCat = category.edit();
                    prefCat.putString("ValueCat", Cat);
                    Toast.makeText(TaggingActivity.this, category.getString("ValueCat",Cat),
                            Toast.LENGTH_LONG)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        toPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage(croppedbitmap);
                Toast.makeText(TaggingActivity.this, " uwu hehehe ", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void saveImage(Bitmap finalBitmap) {
        File file=null;
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File myDir = new File(root,APP_TAG);
        myDir.mkdirs();
        String date=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fname = "Image_" + date+ ".jpg";
        try{
            file= File.createTempFile(fname,".jpg",myDir);}
        catch (IOException e){Log.i("LogCat", "IO exception");}
        if (file.exists()) file.delete();
        Log.i("LOAD", root + fname);
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            galleryAddPic(file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void galleryAddPic(String path) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(path);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }
    public void onBackPressed() {
        finish();
    }
}
