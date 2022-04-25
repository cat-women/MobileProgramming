package com.mobileprogramming.mobileprogamming3.camera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import com.mobileprogramming.mobileprogamming3.R;

import java.util.ArrayList;
import java.util.List;

public class CameraActivity extends AppCompatActivity {
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 101;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imageView = findViewById(R.id.image);
        if(checkRequestPermission(CameraActivity.this)){
            chooseImage(CameraActivity.this);
        }


    }

    public static boolean checkRequestPermission(final Activity context) {
        int extStorePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int cameraPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        List<String> persionNeededList = new ArrayList<String>();
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            persionNeededList.add(Manifest.permission.CAMERA);
        }
        if (extStorePermission != PackageManager.PERMISSION_GRANTED) {
            persionNeededList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (persionNeededList.isEmpty()) {
            ActivityCompat.requestPermissions(context, persionNeededList.toArray(
                    new String[persionNeededList.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;

    }

    private void chooseImage(Context context) {
        final CharSequence[] optionMenu = {"Take Photo", "Choose from gallery", "Exit"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(optionMenu, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (optionMenu[i].equals("Take Photo")) {
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);
                } else if (optionMenu[i].equals("Choose from gallery")) {
                    Intent choosePic = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(choosePic, 1);
                } else if (optionMenu[i].equals("exit")) {
                    dialogInterface.dismiss();
                    finish();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        Log.e("iamge fron cmaera",selectedImage.toString());
                        imageView.setImageBitmap(selectedImage);
                    }
                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();

                        imageView.setImageURI(selectedImage);

//                        String[] filepath = {MediaStore.Images.Media.DATA};
//                        if (selectedImage != null) {
//                            Cursor cursor = getContentResolver().query(selectedImage, filepath, null, null, null);
//                            if (cursor != null) {
//                                cursor.moveToFirst();
//                                int columnIndex = cursor.getColumnIndex(filepath[0]);
//                                String picpath = cursor.getString(columnIndex);
//                                Log.e("image from gallery ",picpath);
//                                imageView.setImageBitmap(BitmapFactory.decodeFile(picpath));
//                                cursor.close();
//                            }
//                        }
                    }
                    break;

            }
        }
    }
}