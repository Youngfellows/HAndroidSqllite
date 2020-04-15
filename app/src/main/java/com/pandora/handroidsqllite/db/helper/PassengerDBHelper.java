package com.pandora.handroidsqllite.db.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pandora.handroidsqllite.bean.Driver;
import com.pandora.handroidsqllite.bean.Passenger;
import com.pandora.handroidsqllite.db.DBHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
    public Passenger query() {
        String queryIdSql = "select min(_id) min,max(_id) max from passenger;";
        SQLiteDatabase db = getDatebase();
        Cursor cursor = db.rawQuery(queryIdSql, null);
        int randomId = 0;
        int min = 0;
        int max = 0;
        while (cursor.moveToNext()) {
            int min_index = cursor.getColumnIndex("min");
            int max_index = cursor.getColumnIndex("max");
            min = cursor.getInt(min_index);
            max = cursor.getInt(max_index);

        }
        randomId = new Random().nextInt(max);
        Log.d(TAG, "query: min: " + min + " ,max: " + max + " ,randomId: " + randomId);
        if (randomId < min) {
            randomId = min;
        }
        String queryRandomSql = "select * from passenger where _id='" + randomId + "';";
        Log.d(TAG, "query: random query sql: " + queryRandomSql);
        cursor = db.rawQuery(queryRandomSql, null);
        Passenger passenger = null;
        while (cursor.moveToNext()) {
            int serialNumber_index = cursor.getColumnIndex("serialNumber");
            int name_index = cursor.getColumnIndex("name");
            int nickName_index = cursor.getColumnIndex("nickName");
            int birthday_index = cursor.getColumnIndex("birthday");
            int phoneNumber_index = cursor.getColumnIndex("phoneNumber");

            String serialNumber = cursor.getString(serialNumber_index);
            String name = cursor.getString(name_index);
            String nickName = cursor.getString(nickName_index);
            String birthday = cursor.getString(birthday_index);
            String phoneNumber = cursor.getString(phoneNumber_index);
            passenger = new Passenger(serialNumber, name, nickName, birthday, phoneNumber);
        }
        cursor.close();
        return passenger;
    }

    @Override
    public List<Passenger> queryAll() {
        // 1.使用java提供的sql查询
        List<Passenger> list = new ArrayList<>();
        SQLiteDatabase db = getDatebase();
        Cursor cursor = db.rawQuery("select * from passenger", null);
        while (cursor.moveToNext()) {
            int serialNumber_index = cursor.getColumnIndex("serialNumber");
            int name_index = cursor.getColumnIndex("name");
            int nickName_index = cursor.getColumnIndex("nickName");
            int birthday_index = cursor.getColumnIndex("birthday");
            int phoneNumber_index = cursor.getColumnIndex("phoneNumber");

            String serialNumber = cursor.getString(serialNumber_index);
            String name = cursor.getString(name_index);
            String nickName = cursor.getString(nickName_index);
            String birthday = cursor.getString(birthday_index);
            String phoneNumber = cursor.getString(phoneNumber_index);
            Passenger passenger = new Passenger(serialNumber, name, nickName, birthday, phoneNumber);
            list.add(passenger);
        }
        cursor.close();
        return list;
    }

    @Override
    public boolean insert(Passenger passenger) {
        // 1.使用java提供的sql插入
        String sql = "insert into passenger(serialNumber,name,nickName,birthday,phoneNumber) values('"
                + passenger.getSerialNumber()
                + "','" + passenger.getName()
                + "','" + passenger.getNickName()
                + "','" + passenger.getBirthday()
                + "','" + passenger.getPhoneNumber()
                + "');";
        Log.d(TAG, "insert sql: " + sql);
        SQLiteDatabase db = getDatebase();
        db.execSQL(sql);

        // 2.android中提供的方法插入数据
        // 使用ContentValues进行插入操作ContentValues相当于java中的Map以键值对的形式存在
        //        SQLiteDatabase db = getDatebase();
        //        ContentValues cv = new ContentValues();
        //        cv.put("name", student.getName());
        //        cv.put("sex", student.getSex());
        //        cv.put("age", student.getAge());
        //        long rowId = db.insert("student", null, cv);
        //return rowId > 0;
        return true;
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
