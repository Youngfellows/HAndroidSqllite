package com.pandora.handroidsqllite.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by AIJACK on 2020/4/12.
 */

public class FileUtil {

    /**
     * 判断sd卡是否存在
     *
     * @return
     */
    public static boolean isSdcardExist() {
        boolean isSdcardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED);
        return isSdcardExist;
    }

    /**
     * db文件夹不存在则创建
     *
     * @param dbDirPath
     */
    public static void createDirFile(String dbDirPath) {
        File parentFile = new File(dbDirPath);
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
    }

    /**
     * @param dbFilePath
     * @return
     */
    public static boolean isFileExist(String dbFilePath) {
        File parentFile = new File(dbFilePath);
        boolean exists = parentFile.exists();
        return exists;
    }
}
