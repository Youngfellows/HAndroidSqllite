package com.pandora.handroidsqllite.db.helper;

import android.util.Log;

import com.pandora.handroidsqllite.bean.Passenger;
import com.pandora.handroidsqllite.db.DBHelper;

import java.util.List;


/**
 * passenger表操作
 */
public class PassengerDBHelper extends DBHelper<Passenger> {

    private PassengerDBHelper() {
        Log.d(TAG, "PassengerDBHelper ");
    }

    private static class Holder {
        private static PassengerDBHelper INSTANCE = new PassengerDBHelper();
    }

    public static PassengerDBHelper getInstance() {
        return PassengerDBHelper.Holder.INSTANCE;
    }

    @Override
    public List<Passenger> queryAll() {
        return null;
    }

    @Override
    public boolean insert(Passenger passenger) {
        return false;
    }

    @Override
    public boolean delete(Passenger passenger) {
        return false;
    }

    @Override
    public boolean update(Passenger passenger) {
        return false;
    }
}
