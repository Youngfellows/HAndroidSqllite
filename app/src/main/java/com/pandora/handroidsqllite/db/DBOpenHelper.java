package com.pandora.handroidsqllite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBOpenHelper extends SQLiteOpenHelper {

    private String TAG = this.getClass().getSimpleName();

    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 数据库名称
     */
    private static final String DB_NAME = "my_pandora.db";

    /**
     * 数据库版本
     */
    private static final int VERSION = 1;

    /**
     * 数据库操作的SQLiteDatabase
     */
    private static SQLiteDatabase INSTANCE;

    /**
     * 创建student的sql语句
     * _id integer NOT NULL PRIMARY KEY AUTOINCREMENT
     */
    private String CREATE_TABLE_STUDENT = "create table student(" +
            "_id integer primary key autoincrement," +
            "name varchar(20) not null," +
            "sex varchar(10) not null," +
            "age integer" +
            ")";

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.mContext = context;
    }

    public SQLiteDatabase getInstance() {
        if (INSTANCE == null) {
            synchronized (DBOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DBOpenHelper(mContext).getWritableDatabase();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate ");
        //此方法只有在调用getWritableDatabase()
        //或getReadableDatabase()方法时才会执行
        db.execSQL(CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade ");
    }
}
