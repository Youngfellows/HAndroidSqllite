package com.pandora.handroidsqllite.db;

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
     * assets文件夹下数据库文件名
     */
    private static final String ASSETS_DB_NAME = "poi_dat.db";

    /**
     * 复制数据库相关
     * 复制到手机中的数据库文件名
     */
    private final String CREAGTE_DB_NAME = "poi_dat_copy.db";

    /**
     * 数据库文件夹路径
     */
    //private final String DB_PATH = "/data/data/%s/databases/";
    private final String DB_PATH = "/sdcard/%s/databases/";

    private DBManager() {

    }

    private static class DbHolder {
        private static DBManager instance = new DBManager();
    }

    public static DBManager getInstance() {
        return DbHolder.instance;
    }

    /**
     * 获取sqlite数据库对象
     **/
    public SQLiteDatabase getDataBase() {
        String packageName = MyApp.getContext().getPackageName();
        String dbDirPath = String.format(DB_PATH, packageName);
        String dbFilePath = String.format(DB_PATH + CREAGTE_DB_NAME, packageName);

        if (FileUtil.isSdcardExist()) {
            FileUtil.createDirFile(dbDirPath);//db文件夹不存在则创建
            if (!FileUtil.isFileExist(dbFilePath)) {//db文件不存在则从assets复制
                try {
                    Log.d(TAG, "getDataBase: copy assets sqlite db to ... ");
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
        Log.d(TAG, "getDataBase success ...");
        return SQLiteDatabase.openOrCreateDatabase(dbFilePath, null);
    }

    /**
     * 关闭sqlite数据数据库
     */
    public void closeDataBase() {
        SQLiteDatabase dataBase = getDataBase();
        if (dataBase != null) {
            dataBase.close();
        }
    }
}
