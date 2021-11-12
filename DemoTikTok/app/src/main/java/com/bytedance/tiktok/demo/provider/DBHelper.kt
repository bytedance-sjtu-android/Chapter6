package com.bytedance.tiktok.demo.provider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.bytedance.tiktok.demo.util.Constant.Companion.TAG

class DBHelper(context: Context) : SQLiteOpenHelper(context, "tiktok_demo.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        Log.d(TAG, "dbHelper onCreate")
        db.execSQL("CREATE TABLE IF NOT EXISTS $USER_TABLE_NAME(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)")
        db.execSQL("CREATE TABLE IF NOT EXISTS $AWEME_TABLE_NAME(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)")
    }

    companion object {
        const val USER_TABLE_NAME = "user"
        const val AWEME_TABLE_NAME = "aweme"
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

}

