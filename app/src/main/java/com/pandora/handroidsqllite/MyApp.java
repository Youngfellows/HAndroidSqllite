package com.pandora.handroidsqllite;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.pandora.handroidsqllite.db.DBManager;
import com.pandora.handroidsqllite.db.helper.CarDBHelper;
import com.pandora.handroidsqllite.db.helper.DriverDBHelper;
import com.pandora.handroidsqllite.db.helper.PassengerDBHelper;
import com.pandora.handroidsqllite.db.helper.PhoneDBHelper;
import com.pandora.handroidsqllite.db.helper.PoiDBHelper;
import com.pandora.handroidsqllite.parser.JSONParser;
import com.pandora.handroidsqllite.service.KeepAliveService;


public class MyApp extends Application {

    private String TAG = this.getClass().getSimpleName();

    private static Context mContext;


    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            //Manifest.permission.ANSWER_PHONE_CALLS,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
    };


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate ");
        mContext = this;
        init();
    }

    private void init() {
        startService();
        if (checkPermissions(needPermissions)) {
            initCarRobotData();
        }
    }

    /**
     * 初始化Driver服务
     */
    private void startService() {
        Intent intent = new Intent();
        intent.setClass(this, KeepAliveService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.i(TAG, "> Android 8.0 ");
            startForegroundService(intent);
        } else {
            startService(intent);
        }
    }

    /**
     * 初始化SQLLite数据库
     */
    private void initCarRobotData() {
        //数据库是否存在
        boolean isCarTableExist = CarDBHelper.getInstance().isTableExist("car");
        boolean isEmptyCarTable = CarDBHelper.getInstance().isEmptyTable();
        boolean isDriverTableExist = DriverDBHelper.getInstance().isTableExist("driver");
        boolean isEmptyDriverTable = DriverDBHelper.getInstance().isEmptyTable();
        boolean isPassengerTableExist = PassengerDBHelper.getInstance().isTableExist("passenger");
        boolean isEmptyPassengerTable = PassengerDBHelper.getInstance().isEmptyTable();
        boolean isPhoneTableExist = PhoneDBHelper.getInstance().isTableExist("phone");
        boolean isEmptyPhoneTable = PhoneDBHelper.getInstance().isEmptyTable();
        boolean isPoiTableExist = PoiDBHelper.getInstance().isTableExist("POI_DATA");
        boolean isEmptyPoiTable = PoiDBHelper.getInstance().isEmptyTable();
        Log.d(TAG, "initCarRobotData: isCarTableExist: " + isCarTableExist + ",isEmptyCarTable: " + isEmptyCarTable);
        Log.d(TAG, "initCarRobotData: isDriverTableExist: " + isDriverTableExist + ",isEmptyDriverTable: " + isEmptyDriverTable);
        Log.d(TAG, "initCarRobotData: isPassengerTableExist: " + isPassengerTableExist + ",isEmptyPassengerTable: " + isEmptyPassengerTable);
        Log.d(TAG, "initCarRobotData: isPhoneTableExist: " + isPhoneTableExist + ",isEmptyPhoneTable: " + isEmptyPhoneTable);
        Log.d(TAG, "initCarRobotData: isPoiTableExist: " + isPoiTableExist + ",isEmptyPoiTable: " + isEmptyPoiTable);

        if (isCarTableExist && isEmptyCarTable
                && isDriverTableExist && isEmptyDriverTable
                && isPassengerTableExist && isEmptyPassengerTable
                && isPhoneTableExist && isEmptyPhoneTable) {
            DBManager.getInstance();//拷贝POI数据库
            JSONParser.getInstance(this).carParser(null);
            JSONParser.getInstance(this).driverParser(null);
            JSONParser.getInstance(this).passengerParser(null);
            JSONParser.getInstance(this).phoneParser(null);
        }

    }

    public static Context getContext() {
        return mContext;
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private boolean checkPermissions(String[] permissions) {
        if (Build.VERSION.SDK_INT >= 23 && getApplicationInfo().targetSdkVersion >= 23) {
            Log.d(TAG, "check permission ... ");
            for (String perm : permissions) {
                if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "checkPermissions fail: " + perm);
                    return false;
                }
            }
        }
        return true;
    }
}
