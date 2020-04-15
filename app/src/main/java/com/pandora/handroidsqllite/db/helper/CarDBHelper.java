package com.pandora.handroidsqllite.db.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pandora.handroidsqllite.bean.Car;
import com.pandora.handroidsqllite.db.DBHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * car表操作
 */
public class CarDBHelper extends DBHelper<Car> {

    private CarDBHelper() {
        Log.d(TAG, "CarDBHelper ");
    }

    private static class Holder {
        private static CarDBHelper INSTANCE = new CarDBHelper();
    }

    public static CarDBHelper getInstance() {
        return CarDBHelper.Holder.INSTANCE;
    }

    @Override
    public Car query() {
        String queryIdSql = "select min(_id) min,max(_id) max from car;";
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
        String queryRandomSql = "select * from car where _id='" + randomId + "';";
        Log.d(TAG, "query: random query sql: " + queryRandomSql);
        cursor = db.rawQuery(queryRandomSql, null);
        Car car = null;
        while (cursor.moveToNext()) {
            int carNumber_index = cursor.getColumnIndex("carNumber");
            int vin_index = cursor.getColumnIndex("vin");
            int plateNumber_index = cursor.getColumnIndex("plateNumber");
            int brand_index = cursor.getColumnIndex("brand");
            int colour_index = cursor.getColumnIndex("colour");

            String carNumber = cursor.getString(carNumber_index);
            String vin = cursor.getString(vin_index);
            String plateNumber = cursor.getString(plateNumber_index);
            String brand = cursor.getString(brand_index);
            String colour = cursor.getString(colour_index);

            car = new Car(carNumber, vin, plateNumber, brand, colour);
        }
        cursor.close();
        return car;
    }

    @Override
    public List<Car> queryAll() {
        // 1.使用java提供的sql查询
        List<Car> list = new ArrayList<>();
        //COLLATE NOCASE 忽略大小写查询
        //Cursor cursor = getDateBase().rawQuery("select * from T_cpz where isqy='True' COLLATE NOCASE;", null);
        SQLiteDatabase db = getDatebase();
        Cursor cursor = db.rawQuery("select * from car", null);
        while (cursor.moveToNext()) {
            int carNumber_index = cursor.getColumnIndex("carNumber");
            int vin_index = cursor.getColumnIndex("vin");
            int plateNumber_index = cursor.getColumnIndex("plateNumber");
            int brand_index = cursor.getColumnIndex("brand");
            int colour_index = cursor.getColumnIndex("colour");

            String carNumber = cursor.getString(carNumber_index);
            String vin = cursor.getString(vin_index);
            String plateNumber = cursor.getString(plateNumber_index);
            String brand = cursor.getString(brand_index);
            String colour = cursor.getString(colour_index);

            Car car = new Car(carNumber, vin, plateNumber, brand, colour);
            list.add(car);
        }
        cursor.close();
        return list;
    }

    @Override
    public boolean insert(Car car) {
        // 1.使用java提供的sql插入
        String sql = "insert into car(carNumber,vin,plateNumber,brand,colour) values('"
                + car.getCarNumber()
                + "','" + car.getVin()
                + "','" + car.getPlateNumber()
                + "','" + car.getBrand()
                + "','" + car.getColour()
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
    public boolean delete(Car car) {
        return false;
    }

    @Override
    public boolean update(Car car) {
        return false;
    }
}
