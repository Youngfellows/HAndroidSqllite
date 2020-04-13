package com.pandora.handroidsqllite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pandora.handroidsqllite.base.DataBaseContext;


public class DBOpenHelper extends SQLiteOpenHelper {

    private String TAG = this.getClass().getSimpleName();

    /**
     * 上下文
     */
    //private Context mContext;
    private DataBaseContext mContext;

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

    /**
     * 创建Driver表的sql语句
     */
    private String CREATE_TABLE_DRIVER = "create table driver(" +
            "_id integer primary key autoincrement," +
            "serialNumber varchar(60) not null," +
            "name varchar(60) not null," +
            "idCard varchar(60) not null," +
            "birthday varchar(60) not null," +
            "drivingLicence varchar(60) not null," +
            "phoneNumber varchar(60) not null," +
            "contactAddress varchar(60) not null," +
            "address varchar(60) not null" +
            ")";

    /**
     * 创建Passenger表的sql语句
     */
    private String CREATE_TABLE_PASSENGER = "create table passenger(" +
            "_id integer primary key autoincrement," +
            "serialNumber varchar(20) not null," +
            "name varchar(60) not null," +
            "nickName varchar(60) not null," +
            "birthday varchar(60) not null," +
            "phoneNumber varchar(60) not null" +
            ")";

    /**
     * 创建Car表的sql语句
     */
    private String CREATE_TABLE_CAR = "create table car(" +
            "_id integer primary key autoincrement," +
            "carNumber varchar(60) not null," +
            "vin varchar(60) not null," +
            "plateNumber varchar(60) not null," +
            "brand varchar(60) not null," +
            "colour varchar(60) not null" +
            ")";

    /**
     * 创建Phone表的sql语句
     */
    private String CREATE_TABLE_PHONE = "create table phone(" +
            "_id integer primary key autoincrement," +
            "brand varchar(60) not null," +
            "androidId varchar(60) not null," +
            "imei varchar(60) not null," +
            "serialNumber varchar(60) not null," +
            "mac varchar(10) not null" +
            ")";

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        //this.mContext = context;
        this.mContext = (DataBaseContext) context;
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
        db.execSQL(CREATE_TABLE_DRIVER);
        db.execSQL(CREATE_TABLE_PASSENGER);
        db.execSQL(CREATE_TABLE_CAR);
        db.execSQL(CREATE_TABLE_PHONE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade ");
    }

    /**
     * 获取数据库路径
     *
     * @return
     */
    public String getDBPath() {
        String path = mContext.getDatabasePath(DB_NAME).getPath();
        return path;
    }
}
