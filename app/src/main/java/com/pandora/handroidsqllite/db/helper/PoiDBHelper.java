package com.pandora.handroidsqllite.db.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pandora.handroidsqllite.bean.Driver;
import com.pandora.handroidsqllite.bean.Poi;
import com.pandora.handroidsqllite.db.DBHelper;
import com.pandora.handroidsqllite.db.DBManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 操作POI数据库
 */
public class PoiDBHelper extends DBHelper<Poi> {

    /**
     * POI数据库操作对象
     */
    private SQLiteDatabase mDb;

    private PoiDBHelper() {
        mDb = DBManager.getInstance().getSQLiteDatabase();
    }

    private static class Holder {
        private static PoiDBHelper INSTANCE = new PoiDBHelper();
    }

    public static PoiDBHelper getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Poi query() {
        String queryIdSql = "select min(_id) min,max(_id) max from driver;";
        SQLiteDatabase db = mDb;
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
        Poi poi = null;
        while (cursor.moveToNext()) {
            int name_index = cursor.getColumnIndex("NAME");
            int address_index = cursor.getColumnIndex("ADDRESS");
            int city_index = cursor.getColumnIndex("CITY");
            int district_index = cursor.getColumnIndex("DISTRICT");
            int latitude_index = cursor.getColumnIndex("LATITUDE");
            int longitude_index = cursor.getColumnIndex("LONGITUDE");
            int category_index = cursor.getColumnIndex("CATEGORY");

            String name = cursor.getString(name_index);
            String address = cursor.getString(address_index);
            String city = cursor.getString(city_index);
            String district = cursor.getString(district_index);
            double latitude = cursor.getDouble(latitude_index);
            double longitude = cursor.getDouble(longitude_index);
            String category = cursor.getString(category_index);

            poi = new Poi(name, address, city, district, latitude, longitude, category);
        }
        cursor.close();
        return poi;
    }

    /**
     * 查询POI列表
     *
     * @return
     */
    @Override
    public List<Poi> queryAll() {
        // 1.使用java提供的sql查询
        List<Poi> list = new ArrayList<>();
        SQLiteDatabase db = mDb;
        Log.d(TAG, "queryAll : mDb = " + mDb);
        Cursor cursor = db.rawQuery("select * from POI_DATA", null);
        while (cursor.moveToNext()) {
            int name_index = cursor.getColumnIndex("NAME");
            int address_index = cursor.getColumnIndex("ADDRESS");
            int city_index = cursor.getColumnIndex("CITY");
            int district_index = cursor.getColumnIndex("DISTRICT");
            int latitude_index = cursor.getColumnIndex("LATITUDE");
            int longitude_index = cursor.getColumnIndex("LONGITUDE");
            int category_index = cursor.getColumnIndex("CATEGORY");

            String name = cursor.getString(name_index);
            String address = cursor.getString(address_index);
            String city = cursor.getString(city_index);
            String district = cursor.getString(district_index);
            double latitude = cursor.getDouble(latitude_index);
            double longitude = cursor.getDouble(longitude_index);
            String category = cursor.getString(category_index);

            Poi poi = new Poi(name, address, city, district, latitude, longitude, category);
            list.add(poi);
        }
        cursor.close();
        Log.d(TAG, "queryAll success ... ");
        return list;
    }

    @Override
    public boolean insert(Poi poi) {
        return false;
    }

    @Override
    public boolean delete(Poi poi) {
        return false;
    }

    @Override
    public boolean update(Poi poi) {
        return false;
    }
}
