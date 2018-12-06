package com.example.noobkenneth.cody.Wardrobe;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.noobkenneth.cody.R;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CroppingActivity extends AppCompatActivity {
    Button Crop;
    private Uri mCropImageUri;
    Button Load;
    CropImageView cropped;
    private ImageView preview;
    public final String TAG="Logcat";
    public final static String PREVIMAGE = "Preview Image";
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cropper);
        Crop = findViewById(R.id.cropper);
        Load = findViewById(R.id.loader);
        preview=findViewById(R.id.shittyimage);
        cropped = findViewById(R.id.cropImageView);
        Load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(getPickImageChooserIntent(), 200);
            }
        });
        Crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cropped.getCroppedImage() != null) {
                    Bitmap cropped2 = cropped.getCroppedImage(1000,1000);
                    Bitmap bm=Bitmap.createScaledBitmap(cropped2, 300, 300, true);
                    Intent intent = new Intent(CroppingActivity.this, TaggingActivity.class);
                    intent.putExtra(PREVIMAGE, bm);
                    startActivity(intent);
                } else {
                    Toast.makeText(CroppingActivity.this, "no image", Toast.LENGTH_LONG).show();
                }
            }


        });
    }
    protected void onActivityResult(int  requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Uri imageUri =  getPickImageResultUri(data);

            //check permissions
            boolean requirePermissions = false;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                    checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    isUriRequiresPermissions(imageUri)) {

                // request permissions and handle the result in onRequestPermissionsResult()
                requirePermissions = true;
                mCropImageUri = imageUri;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            }

            if (!requirePermissions) {
                cropped.setImageUriAsync(imageUri);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (mCropImageUri != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            cropped.setImageUriAsync(mCropImageUri);
        } else {
            Toast.makeText(this, "Required permissions are not granted", Toast.LENGTH_LONG).show();
        }
    }
        public Intent getPickImageChooserIntent() {
// Determine Uri of camera image to  save.
            Uri outputFileUri =  getCaptureImageOutputUri();

            List<Intent> allIntents = new ArrayList<>();
            PackageManager packageManager =  getPackageManager();

// collect all camera intents
            Intent captureIntent = new  Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            List<ResolveInfo> listCam =  packageManager.queryIntentActivities(captureIntent, 0);
            for (ResolveInfo res : listCam) {
                Intent intent = new  Intent(captureIntent);
                intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
                intent.setPackage(res.activityInfo.packageName);
                if (outputFileUri != null) {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                }
                allIntents.add(intent);
            }

// collect all gallery intents
            Intent galleryIntent = new  Intent(Intent.ACTION_GET_CONTENT);
            galleryIntent.setType("image/*");
            List<ResolveInfo> listGallery =  packageManager.queryIntentActivities(galleryIntent, 0);
            for (ResolveInfo res : listGallery) {
                Intent intent = new  Intent(galleryIntent);
                intent.setComponent(new  ComponentName(res.activityInfo.packageName, res.activityInfo.name));
                intent.setPackage(res.activityInfo.packageName);
                allIntents.add(intent);
            }

            Intent mainIntent =  allIntents.get(allIntents.size() - 1);
            for (Intent intent : allIntents) {
                if  (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity"))  {
                    mainIntent = intent;
                    break;
                }
            }
            allIntents.remove(mainIntent);
// Create a chooser from the main  intent
            Intent chooserIntent =  Intent.createChooser(mainIntent, "Select source");
// Add all other intents
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,  allIntents.toArray(new Parcelable[allIntents.size()]));

            return chooserIntent;
        }
    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new  File(getImage.getPath(), "pickImageResult.jpeg"));
        }
        return outputFileUri;
    }

    public Uri getPickImageResultUri(Intent  data) {
        boolean isCamera = true;
        if (data != null && data.getData() != null) {
            String action = data.getAction();
            isCamera = action != null  && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ?  getCaptureImageOutputUri() : data.getData();
    }

    public boolean isUriRequiresPermissions(Uri uri) {
        try {
            ContentResolver resolver = getContentResolver();
            InputStream stream = resolver.openInputStream(uri);
            stream.close();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
