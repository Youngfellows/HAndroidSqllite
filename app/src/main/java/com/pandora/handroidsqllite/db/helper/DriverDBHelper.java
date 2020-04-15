package com.pandora.handroidsqllite.db.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pandora.handroidsqllite.bean.Driver;
import com.pandora.handroidsqllite.db.DBHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
    public Driver query() {
        String queryIdSql = "select min(_id) min,max(_id) max from driver;";
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
        String queryRandomSql = "select * from driver where _id='" + randomId + "';";
        Log.d(TAG, "query: random query sql: " + queryRandomSql);
        cursor = db.rawQuery(queryRandomSql, null);
        Driver driver = null;
        while (cursor.moveToNext()) {
            int serialNumber_index = cursor.getColumnIndex("serialNumber");
            int name_index = cursor.getColumnIndex("name");
            int idCard_index = cursor.getColumnIndex("idCard");
            int birthday_index = cursor.getColumnIndex("birthday");
            int drivingLicence_index = cursor.getColumnIndex("drivingLicence");
            int phoneNumber_index = cursor.getColumnIndex("phoneNumber");
            int contactAddress_index = cursor.getColumnIndex("contactAddress");
            int address_index = cursor.getColumnIndex("address");

            String serialNumber = cursor.getString(serialNumber_index);
            String name = cursor.getString(name_index);
            String idCard = cursor.getString(idCard_index);
            String birthday = cursor.getString(birthday_index);
            String drivingLicence = cursor.getString(drivingLicence_index);
            String phoneNumber = cursor.getString(phoneNumber_index);
            String contactAddress = cursor.getString(contactAddress_index);
            String address = cursor.getString(address_index);
            driver = new Driver(serialNumber, name, idCard, birthday, drivingLicence, phoneNumber, contactAddress, address);
        }
        cursor.close();
        return driver;
    }

    @Override
    public List<Driver> queryAll() {
        // 1.使用java提供的sql查询
        List<Driver> list = new ArrayList<>();
        SQLiteDatabase db = getDatebase();
        Cursor cursor = db.rawQuery("select * from driver", null);
        while (cursor.moveToNext()) {
            int serialNumber_index = cursor.getColumnIndex("serialNumber");
            int name_index = cursor.getColumnIndex("name");
            int idCard_index = cursor.getColumnIndex("idCard");
            int birthday_index = cursor.getColumnIndex("birthday");
            int drivingLicence_index = cursor.getColumnIndex("drivingLicence");
            int phoneNumber_index = cursor.getColumnIndex("phoneNumber");
            int contactAddress_index = cursor.getColumnIndex("contactAddress");
            int address_index = cursor.getColumnIndex("address");

            String serialNumber = cursor.getString(serialNumber_index);
            String name = cursor.getString(name_index);
            String idCard = cursor.getString(idCard_index);
            String birthday = cursor.getString(birthday_index);
            String drivingLicence = cursor.getString(drivingLicence_index);
            String phoneNumber = cursor.getString(phoneNumber_index);
            String contactAddress = cursor.getString(contactAddress_index);
            String address = cursor.getString(address_index);

            Driver driver = new Driver(serialNumber, name, idCard, birthday, drivingLicence, phoneNumber, contactAddress, address);
            list.add(driver);
        }
        cursor.close();
        return list;
    }

    @Override
    public boolean insert(Driver driver) {
        // 1.使用java提供的sql插入
        String sql = "insert into driver(serialNumber,name,idCard,birthday,drivingLicence,phoneNumber,contactAddress,address) values('"
                + driver.getSerialNumber()
                + "','" + driver.getName()
                + "','" + driver.getIdCard()
                + "','" + driver.getBirthday()
                + "','" + driver.getDrivingLicence()
                + "','" + driver.getPhoneNumber()
                + "','" + driver.getContactAddress()
                + "','" + driver.getAddress()
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
    public boolean delete(Driver driver) {
        return false;
    }

    @Override
    public boolean update(Driver driver) {
        return false;
    }
}
