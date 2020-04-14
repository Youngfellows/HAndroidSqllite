package com.pandora.handroidsqllite.utils;


import android.os.Environment;

import java.io.File;

public class SDCardUtil {

    /**
     * 获取内置sdcard路径(数据会保存，应用删除的时候，数据不会被清理掉)
     *
     * @return
     */
    public static String getInnerSDCardPath() {
        //判断sd卡是否存在
        boolean isSdcardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED);
        if (isSdcardExist) {
            return Environment.getExternalStorageDirectory() + File.separator;
        }
        return null;
    }
}
