package com.bytedance.tiktok.demo.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.util.Log
import com.bytedance.tiktok.demo.util.Constant


class TikTokDemoProvider: ContentProvider() {

    val AUTOHORITY = "com.bytedance.tiktok.provider"
    val User_Code = 1
    val Aweme_Code = 2
    lateinit var db: SQLiteDatabase
    private var mMatcher: UriMatcher? = null

    init {
        mMatcher = UriMatcher(UriMatcher.NO_MATCH)
        mMatcher?.addURI(AUTOHORITY, "user", User_Code)
        mMatcher?.addURI(AUTOHORITY, "aweme", Aweme_Code)
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val table: String = getTableName(uri)
        db.insert(table, null, values)
        context!!.contentResolver.notifyChange(uri, null) // 当该URI的ContentProvider数据发生变化时，通知外界
        return uri
    }

    override fun onCreate(): Boolean {
        //应用每次启动都会调用
        Log.d(Constant.TAG, "$this onCreate")
        // 运行在主线程，不能做耗时操作, 此处仅作展示
        val mDbHelper = DBHelper(context!!)
        db = mDbHelper.writableDatabase

        db.execSQL("delete from user")
        db.execSQL("insert into user values(1,'Key');")
        db.execSQL("insert into user values(2,'Handsome');")

        db.execSQL("delete from aweme")
        db.execSQL("insert into aweme values(1,'aweme_init_1');")
        db.execSQL("insert into aweme values(2,'aweme_init_2');")

        return true
    }

    override fun query(
        uri: Uri, projection: Array<String?>?, selection: String?,
        selectionArgs: Array<String?>?, sortOrder: String?
    ): Cursor? {
        val table = getTableName(uri)
        return db.query(table, projection, selection, selectionArgs, null, null, sortOrder, null)
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String {
        when (mMatcher!!.match(uri)) {
            User_Code -> return "type_user"
            Aweme_Code -> return "type_aweme"
        }
        return ""
    }

    private fun getTableName(uri: Uri): String {
        var tableName = ""
        when (mMatcher!!.match(uri)) {
            User_Code -> tableName = DBHelper.USER_TABLE_NAME
            Aweme_Code -> tableName = DBHelper.AWEME_TABLE_NAME
        }
        return tableName
    }

}