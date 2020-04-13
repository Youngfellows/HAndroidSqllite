package com.pandora.handroidsqllite.db.helper;

import android.util.Log;

import com.pandora.handroidsqllite.bean.Driver;
import com.pandora.handroidsqllite.db.DBHelper;

import java.util.List;


/**
 * driver表操作
 */
public class DriverDBHelper extends DBHelper<Driver> {

    private DriverDBHelper() {
        Log.d(TAG, "DriverDBHelper ");
    }

    private static class Holder {
        private static DriverDBHelper INSTANCE = new DriverDBHelper();
    }

    public static DriverDBHelper getInstance() {
        return DriverDBHelper.Holder.INSTANCE;
    }

    @Override
    public List<Driver> queryAll() {
        return null;
    }

    @Override
    public boolean insert(Driver driver) {
        return false;
    }

    @Override
    public boolean delete(Driver driver) {
        return false;
    }

    @Override
    public boolean update(Driver driver) {
        return false;
    }
}
