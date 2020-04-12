package com.pandora.handroidsqllite.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pandora.handroidsqllite.MyApp;
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
        mDbOpenHelper = new DBOpenHelper(MyApp.getContext());
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
    protected boolean isTableExist(String tableName) {
        Cursor cursor = getDatebase().rawQuery("select name from sqlite_master where type='table';", null);
        while (cursor.moveToNext()) {
            //遍历出表名
            String name = cursor.getString(0);
            Log.d(TAG, "isTableExist ");
            if (name.equals(tableName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查询
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
     * 更新
     */
    public abstract boolean update(T t);
}
