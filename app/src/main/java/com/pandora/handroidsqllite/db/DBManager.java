package com.pandora.handroidsqllite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pandora.handroidsqllite.MyApp;
import com.pandora.handroidsqllite.utils.FileUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 将db文件中的数据拷贝到自己的sqlite中
 */
public class DBManager {

    private String TAG = this.getClass().getSimpleName();

    /**
     * 上下文
     */
    private Context mContext;

    /**
     * assets文件夹下数据库文件名
     */
    private static final String ASSETS_DB_NAME = "poi_dat.db";

    /**
     * 复制数据库相关
     * 复制到手机中的数据库文件名
     */
    private final String CREAGTE_DB_NAME = "poi_dat.db";


    private String DATABASES = "databases";

    /**
     * 数据库文件夹路径
     */
    //private final String DB_PATH = "/data/data/%s/databases/";
    private final String DB_PATH = "/sdcard/%s/" + DATABASES + "/";

    /**
     * 数据库操作对象SQLiteDatabase
     */
    private SQLiteDatabase mSQLiteDatabase;

    private DBManager() {
        this.mContext = MyApp.getContext();
        copyAssetsDb();
    }

    private static class DbHolder {
        private static DBManager instance = new DBManager();
    }

    public static DBManager getInstance() {
        return DbHolder.instance;
    }

    /**
     * 获取POI数据库操作对象
     *
     * @return
     */
    public SQLiteDatabase getSQLiteDatabase() {
        return mSQLiteDatabase;
    }

    /**
     * 拷贝数据库
     **/
    private void copyAssetsDb() {
        //new Thread(copyDbTask).start();
        String packageName = mContext.getPackageName();
        String dbDirPath = String.format(DB_PATH, packageName);
        String dbFilePath = String.format(DB_PATH + CREAGTE_DB_NAME, packageName);
        if (FileUtil.isSdcardExist()) {
            FileUtil.createDirFile(dbDirPath);//db文件夹不存在则创建
            if (!FileUtil.isFileExist(dbFilePath)) {//db文件不存在则从assets复制
                try {
                    Log.d(TAG, "copyAssetsDb: copy assets sqlite db to ... ");
                    FileOutputStream out = new FileOutputStream(dbFilePath);
                    InputStream in = MyApp.getContext().getAssets().open(ASSETS_DB_NAME);
                    byte[] buffer = new byte[1024];
                    int readBytes = 0;
                    while ((readBytes = in.read(buffer)) != -1) {
                        out.write(buffer, 0, readBytes);
                    }
                    out.flush();
                    out.close();
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d(TAG, "copyDbTask run : copy " + ASSETS_DB_NAME + " success ... ");
        mSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFilePath, null);
    }

    /**
     * 拷贝Assets目录下的数据库
     */
    private Runnable copyDbTask = new Runnable() {
        @Override
        public void run() {

        }
    };

    /**
     * 关闭sqlite数据数据库
     */
    public void closeDataBase() {
        Log.d(TAG, "closeDataBase ");
        if (mSQLiteDatabase != null) {
            mSQLiteDatabase.close();
        }
    }
}
