package com.pandora.handroidsqllite.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pandora.handroidsqllite.MyApp;
import com.pandora.handroidsqllite.base.DataBaseContext;

import java.util.List;

/**
 * 数据库增删改查帮助类
 */
public abstract class DBHelper<T> {

    protected String TAG = this.getClass().getSimpleName();

    private DBOpenHelper mDbOpenHelper;

    /**
     * 获取数据库对象
     *
     * @return
     */
    protected SQLiteDatabase getDatebase() {
        if (mDbOpenHelper == null) {
            //mDbOpenHelper = new DBOpenHelper(MyApp.getContext());
            mDbOpenHelper = new DBOpenHelper(new DataBaseContext(MyApp.getContext()));
        }
        SQLiteDatabase db = mDbOpenHelper.getInstance();
        return db;
    }

    /**
     * 关闭数据库
     */
    protected void closeDB() {
        SQLiteDatabase db = getDatebase();
        if (db != null) {
            db.close();
        }
    }

    /**
     * 判断表是否存在
     *
     * @param tableName：表名
     * @return
     */
    public boolean isTableExist(String tableName) {
        Cursor cursor = getDatebase().rawQuery("select name from sqlite_master where type='table';", null);
        while (cursor.moveToNext()) {
            //遍历出表名
            String name = cursor.getString(0);
            if (name.equals(tableName)) {
                Log.d(TAG, "isTableExist: have " + name + " table");
                return true;
            }
        }
        return false;
    }

    /**
     * 是否是一张空表
     *
     * @return
     */
    public abstract boolean isEmptyTable();

    /**
     * 获取数据库路径
     *
     * @return
     */
    public String getDatabasePath() {
        if (mDbOpenHelper == null) {
            //mDbOpenHelper = new DBOpenHelper(MyApp.getContext());
            mDbOpenHelper = new DBOpenHelper(new DataBaseContext(MyApp.getContext()));
        }
        return mDbOpenHelper.getDBPath();
    }

    /**
     * 查询
     */
    public abstract T query();

    /**
     * 查询除了指定条件之外的
     *
     * @param t
     * @return
     */
    public abstract T besidesQuery(T t);

    /**
     * 随机查询多少条数据
     *
     * @param counts
     * @return
     */
    public abstract List<T> limitQuery(long counts);

    /**
     * 查询全部
     */
    public abstract List<T> queryAll();

    /**
     * 添加
     */
    public abstract boolean insert(T t);

    /**
     * 删除
     */
    public abstract boolean delete(T t);

    /**
     * 删除表
     */
    public abstract boolean delete();

    /**
     * 更新
     */
    public abstract boolean update(T t);
}
