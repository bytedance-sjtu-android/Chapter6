package com.bytedance.tiktok.demo.provider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.bytedance.tiktok.demo.R;

public class ProviderDemoActivity extends Activity {

    Uri uri_aweme = Uri.parse("content://com.bytedance.tiktok.provider/aweme");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_demo);

        findViewById(R.id.btn_insert).setOnClickListener(v -> {
            ContentValues values = new ContentValues();
            values.put("name", "aweme_" + System.currentTimeMillis());
            getContentResolver().insert(uri_aweme,values);
        });

        findViewById(R.id.btn_query).setOnClickListener(v -> {
            Cursor cursor = getContentResolver().query(uri_aweme, new String[]{"_id","name"}, null, null, null);
            while (cursor.moveToNext()){
                Log.d("ProviderDemoActivity", "query aweme:" + cursor.getInt(0) + " ---- " + cursor.getString(1));
            }
            cursor.close();
        });
    }
}