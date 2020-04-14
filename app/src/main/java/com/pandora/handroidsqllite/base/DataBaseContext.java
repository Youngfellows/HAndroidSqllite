package com.pandora.handroidsqllite.base;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pandora.handroidsqllite.utils.SDCardUtil;

import java.io.File;

/**
 * SQLite数据库创建时自定义路径
 */
public class DataBaseContext extends ContextWrapper {

    private String TAG = this.getClass().getSimpleName();

    private String DATABASES = "databases";

    public DataBaseContext(Context base) {
        super(base);
    }

    /**
     * 重写数据库路径方法
     *
     * @param name
     * @return
     */
    @Override
    public File getDatabasePath(String name) {
        String packageName = this.getPackageName();
        String dirPath = SDCardUtil.getInnerSDCardPath();

        String path = null;
        File parentFile = new File(dirPath, packageName + File.separator + DATABASES);
        dirPath = parentFile.getAbsolutePath();
        Log.d(TAG, "getDatabasePath: dirPath = " + dirPath);

        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        String parentPath = parentFile.getAbsolutePath();
        //        if (parentPath.lastIndexOf("\\/") != -1) {
        //            path = dirPath + File.separator + name;
        //        } else {
        //            path = dirPath + File.separator + name;
        //        }
        path = dirPath + File.separator + name;

        File file = new File(path);

        return file;
        //return super.getDatabasePath(name);
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name).getAbsolutePath(), factory, errorHandler);
    }
}
