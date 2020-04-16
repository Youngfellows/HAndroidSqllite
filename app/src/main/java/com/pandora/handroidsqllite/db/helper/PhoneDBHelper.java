package com.pandora.handroidsqllite.db.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pandora.handroidsqllite.bean.Phone;
import com.pandora.handroidsqllite.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * phone表操作
 */
public class PhoneDBHelper extends DBHelper<Phone> {

    private PhoneDBHelper() {
        Log.d(TAG, "PhoneDBHelper ");
    }

    private static class Holder {
        private static PhoneDBHelper INSTANCE = new PhoneDBHelper();
    }

    public static PhoneDBHelper getInstance() {
        return PhoneDBHelper.Holder.INSTANCE;
    }

    @Override
    public Phone query() {
        SQLiteDatabase db = getDatebase();
        //String queryRandomSql = "select * from phone order by RANDOM() desc limit 1;";
        String queryRandomSql = "select * from phone where imei = (select imei from phone order by RANDOM() limit 10);";
        Log.d(TAG, "query: random query sql: " + queryRandomSql);
        Cursor cursor = db.rawQuery(queryRandomSql, null);
        Phone phone = null;
        while (cursor.moveToNext()) {
            int brand_index = cursor.getColumnIndex("brand");
            int androidId_index = cursor.getColumnIndex("androidId");
            int imei_index = cursor.getColumnIndex("imei");
            int serialNumber_index = cursor.getColumnIndex("serialNumber");
            int mac_index = cursor.getColumnIndex("mac");

            String brand = cursor.getString(brand_index);
            String androidId = cursor.getString(androidId_index);
            String imei = cursor.getString(imei_index);
            String serialNumber = cursor.getString(serialNumber_index);
            String mac = cursor.getString(mac_index);

            phone = new Phone(brand, androidId, imei, serialNumber, mac);
        }
        cursor.close();
        return phone;
    }

    @Override
    public Phone besidesQuery(Phone phone) {
        return null;
    }

    @Override
    public List<Phone> queryAll() {
        //phone(brand,androidId,imei,serialNumber,mac)
        // 1.使用java提供的sql查询
        List<Phone> list = new ArrayList<>();
        SQLiteDatabase db = getDatebase();
        Cursor cursor = db.rawQuery("select * from phone", null);
        while (cursor.moveToNext()) {
            int brand_index = cursor.getColumnIndex("brand");
            int androidId_index = cursor.getColumnIndex("androidId");
            int imei_index = cursor.getColumnIndex("imei");
            int serialNumber_index = cursor.getColumnIndex("serialNumber");
            int mac_index = cursor.getColumnIndex("mac");

            String brand = cursor.getString(brand_index);
            String androidId = cursor.getString(androidId_index);
            String imei = cursor.getString(imei_index);
            String serialNumber = cursor.getString(serialNumber_index);
            String mac = cursor.getString(mac_index);

            Phone phone = new Phone(brand, androidId, imei, serialNumber, mac);
            list.add(phone);
        }
        cursor.close();
        return list;
    }

    @Override
    public boolean insert(Phone phone) {
        // 1.使用java提供的sql插入
        String sql = "insert into phone(brand,androidId,imei,serialNumber,mac) values('"
                + phone.getBrand()
                + "','" + phone.getAndroidId()
                + "','" + phone.getImei()
                + "','" + phone.getSerialNumber()
                + "','" + phone.getMac()
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
    public boolean delete(Phone phone) {
        return false;
    }

    @Override
    public boolean update(Phone phone) {
        return false;
    }
}
