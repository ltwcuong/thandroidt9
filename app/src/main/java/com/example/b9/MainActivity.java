package com.example.b9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getContactsPressed(View v){
        getPhoneContats();
    }
    private void getPhoneContats(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS )!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_CONTACTS},0);
        }

        ContentResolver contentresolver= getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = contentresolver.query(uri,null,null,null,null);
        Log.i("Danh bạ điện thoại ","Tổng số điện thoại trong danh bạ: " + Integer.toString(cursor.getCount()));
        if(cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                 String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                 String contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                Log.i("Danh bạ điện thoại ", "Tên người dùng: " + contactName + " Số điện thoại: " + contactNumber);
            }
        }

    }
}