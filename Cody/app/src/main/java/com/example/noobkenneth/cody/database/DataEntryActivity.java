package com.example.noobkenneth.cody.database;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.example.noobkenneth.cody.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

public class DataEntryActivity extends AppCompatActivity implements OnItemSelectedListener {

    ImageView imageViewSelected;
    CharaDbHelper charaDbHelper;
    Bitmap bitmapSelected = null;
    SQLiteDatabase db;
    int REQUEST_CODE_IMAGE = 2000;
    String category = null;
    String formality = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        //Get a reference to the CharaDbHelper
        charaDbHelper = CharaDbHelper.createCharaDbHelper(this);


        //creates references to the button and imageView widgets
        Button buttonSelectImage = findViewById(R.id.buttonSelectImage);
        imageViewSelected = findViewById(R.id.imageViewSelected);

        //Category and Formality spinner widgets
        Spinner spinnerCategory = findViewById(R.id.spinnerCategory);
        Spinner spinnerFormality = findViewById(R.id.spinnerFormality);
        spinnerCategory.setOnItemSelectedListener(this);
        spinnerFormality.setOnItemSelectedListener(this);

        //Adds the appropriate entries into the spinners
        List<String> categories = new ArrayList<String>(Arrays.asList("Tops", "Bottoms",
                "One-piece", "Shoes", "Bags", "Accessories")){};
        List<String> formalities = new ArrayList<String>(Arrays.asList("Casual", "Smart Casual",
                "Business Formal", "Formal")){};

        //Sets the adapter to the based on the list of strings
        ArrayAdapter<String> dataAdapterCategories = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categories);
        ArrayAdapter<String> dataAdapterFormality = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, formalities);

        //fills the spinners with the categories
        dataAdapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterFormality.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(dataAdapterCategories);
        spinnerFormality.setAdapter(dataAdapterFormality);

        //When the selectImage button is clicked, set up an Implicit Intent to the gallery
        buttonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });

        //Then the OK button is clicked, add the data to the Database
        Button buttonOK = findViewById(R.id.buttonOK);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String last_used = formatter.format(date); //populates the database with default date
                boolean ootd = false; // when adding clothing into the database, it is not an ootd

                if( bitmapSelected == null){
                    Toast.makeText(DataEntryActivity.this,
                            "no image selected",
                            Toast.LENGTH_LONG).show();
                }else{
                    CharaDbHelper.CharaData charaData = new CharaDbHelper.CharaData(category,
                            formality, last_used, ootd, bitmapSelected);
                    charaDbHelper.insertOneRow( charaData );
                    Toast.makeText(DataEntryActivity.this,
                            "inserting to database",
                            Toast.LENGTH_LONG).show();

                    //In MainActivity we started DataEntryActivity using startActivityForResult
                    //Hence,DataEntryActivity must return a result and hence this set of code
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        // Toast selected category

        switch (parent.getId()) {
            case R.id.spinnerCategory:
                category = item;
                Log.i("Logcat", "Category: " + item);
                break;
            case R.id.spinnerFormality:
                formality = item;
                Log.i("Logcat", "Formality: " + item);
                break;
        }
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        //Auto-generated method stub
    }

    //OnActivityResult so that the selected image is displayed in the imageView
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == REQUEST_CODE_IMAGE && resultCode == Activity.RESULT_OK){
            try{
                Uri uri = data.getData();
                InputStream inputStream = this.getContentResolver()
                        .openInputStream(uri);
                bitmapSelected = Utils.convertStreamToBitmap(inputStream);
                imageViewSelected.setImageBitmap(bitmapSelected);
            }catch(FileNotFoundException ex){
                ex.printStackTrace();
            }

        }
    }
}
