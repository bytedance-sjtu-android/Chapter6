package com.bytedance.tiktok.demo.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class TikTokDemoProvider extends ContentProvider {

    public static final String AUTOHORITY = "com.bytedance.tiktok.provider";
    private Context mContext;
    private SQLiteDatabase db = null;
    public static final int User_Code = 1;
    public static final int Aweme_Code = 2;
    private static final UriMatcher mMatcher;

    static{
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mMatcher.addURI(AUTOHORITY,"user", User_Code);
        mMatcher.addURI(AUTOHORITY, "aweme", Aweme_Code);
    }

    @Override
    public boolean onCreate() {
        //应用每次启动都会调用
        mContext = getContext();
        // 运行在主线程，不能做耗时操作, 此处仅作展示
        DBHelper mDbHelper = new DBHelper(getContext());
        db = mDbHelper.getWritableDatabase();

        db.execSQL("delete from user");
        db.execSQL("insert into user values(1,'Key');");
        db.execSQL("insert into user values(2,'Handsome');");

        db.execSQL("delete from aweme");
        db.execSQL("insert into aweme values(1,'aweme_init_1');");
        db.execSQL("insert into aweme values(2,'aweme_init_2');");

        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String table = getTableName(uri);
        db.insert(table, null, values);
        mContext.getContentResolver().notifyChange(uri, null); // 当该URI的ContentProvider数据发生变化时，通知外界
        return uri;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        String table = getTableName(uri);
        return db.query(table,projection,selection,selectionArgs,null,null,sortOrder,null);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        switch (mMatcher.match(uri)) {
            case User_Code:
                return "type_user";
            case Aweme_Code:
                return "type_aweme";
        }
        return "";
    }

    private String getTableName(Uri uri){
        String tableName = null;
        switch (mMatcher.match(uri)) {
            case User_Code:
                tableName = DBHelper.USER_TABLE_NAME;
                break;
            case Aweme_Code:
                tableName = DBHelper.AWEME_TABLE_NAME;
                break;
        }
        return tableName;
    }
}
