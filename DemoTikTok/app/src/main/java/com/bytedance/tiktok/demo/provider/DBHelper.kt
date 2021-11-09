package com.bytedance.tiktok.demo.provider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "tiktok_demo.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
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

