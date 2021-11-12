package com.bytedance.tiktok.demo.provider

import android.app.Activity
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bytedance.tiktok.demo.R
import com.bytedance.tiktok.demo.util.Constant


class ProviderDemoActivity: Activity() {

    var uri_aweme: Uri = Uri.parse("content://com.bytedance.tiktok.provider/aweme")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider_demo)
        findViewById<View>(R.id.btn_insert).setOnClickListener {
            val values = ContentValues()
            values.put("name", "aweme_" + System.currentTimeMillis())
            contentResolver.insert(uri_aweme, values)
        }
        findViewById<View>(R.id.btn_query).setOnClickListener {
            val cursor: Cursor? = contentResolver.query(uri_aweme, arrayOf("_id", "name"), null, null, null)
            while (cursor!!.moveToNext()) {
                Log.d(Constant.TAG,
                    "query aweme:" + cursor.getInt(0).toString() + " ---- " + cursor.getString(1)
                )
            }
            cursor.close()
        }
    }
}