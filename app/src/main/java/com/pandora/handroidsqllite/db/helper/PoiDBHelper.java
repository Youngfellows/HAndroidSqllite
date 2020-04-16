package com.pandora.handroidsqllite.db.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pandora.handroidsqllite.bean.Poi;
import com.pandora.handroidsqllite.db.DBHelper;
import com.pandora.handroidsqllite.db.DBManager;

import java.util.ArrayList;
import java.util.List;

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
        SQLiteDatabase db = mDb;
        //String queryRandomSql = "select * from poi_data order by RANDOM() desc limit 1;";
        String queryRandomSql = "select * from poi_data where name = (select name from poi_data order by RANDOM() limit 10);";
        Log.d(TAG, "query: random query sql: " + queryRandomSql);
        Cursor cursor = db.rawQuery(queryRandomSql, null);
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

    @Override
    public Poi besidesQuery(Poi poi) {
        return null;
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
