package com.pandora.handroidsqllite.db.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pandora.handroidsqllite.bean.Passenger;
import com.pandora.handroidsqllite.db.DBHelper;

import java.util.ArrayList;
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
    public boolean isEmptyTable() {
        String sql = "select * from passenger;";
        SQLiteDatabase db = getDatebase();
        Cursor cursor = db.rawQuery(sql, null);
        int counts = cursor.getCount();
        cursor.close();
        Log.d(TAG, "isEmptyTable: passenger counts " + counts);
        return counts == 0;
    }

    @Override
    public Passenger query() {
        SQLiteDatabase db = getDatebase();
        //String queryRandomSql = "select * from passenger order by RANDOM() desc limit 1;";
        String queryRandomSql = "select * from passenger where phoneNumber = (select phoneNumber from passenger order by RANDOM() limit 10);";
        Log.d(TAG, "query: random query sql: " + queryRandomSql);
        Cursor cursor = db.rawQuery(queryRandomSql, null);
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
    public Passenger besidesQuery(Passenger passenger) {
        return null;
    }

    @Override
    public List<Passenger> limitQuery(long counts) {
        String sql = "select * from passenger order by RANDOM() limit " + counts + ";";
        SQLiteDatabase db = getDatebase();
        Cursor cursor = db.rawQuery(sql, null);
        List<Passenger> list = new ArrayList<>();
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
    public boolean delete() {
        String sql = "delete from passenger;";
        SQLiteDatabase db = getDatebase();
        db.execSQL(sql);
        Log.d(TAG, "delete: delete passenger table");
        return true;
    }

    @Override
    public boolean update(Passenger passenger) {
        return false;
    }
}
