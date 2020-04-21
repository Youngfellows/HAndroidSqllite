package com.pandora.handroidsqllite.parser;


import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.pandora.handroidsqllite.bean.Car;
import com.pandora.handroidsqllite.bean.Driver;
import com.pandora.handroidsqllite.bean.Passenger;
import com.pandora.handroidsqllite.bean.Phone;
import com.pandora.handroidsqllite.db.helper.CarDBHelper;
import com.pandora.handroidsqllite.db.helper.DriverDBHelper;
import com.pandora.handroidsqllite.db.helper.PassengerDBHelper;
import com.pandora.handroidsqllite.db.helper.PhoneDBHelper;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JSONParser {

    public String TAG = this.getClass().getSimpleName();

    /**
     * 上下文
     */
    private Context mContext;

    private static JSONParser instance;

    /**
     * 线程池
     */
    private ExecutorService mExecutor;

    /**
     * 是否正在插入car表
     */
    private boolean isInsertCar;

    /**
     * 是否正在插入phone表
     */
    private boolean isInsertPhone;

    /**
     * 是否正在插入driver表
     */
    private boolean isInsertDriver;

    /**
     * 是否正在插入passenger表
     */
    private boolean isInsertPassenger;

    private final Lock mCarLock = new ReentrantLock();

    private final Lock mPhoneLock = new ReentrantLock();

    private final Lock mDriverLock = new ReentrantLock();

    private final Lock mPassengerLock = new ReentrantLock();

    private String FILE_NAME_CAR = "json/car_info.json";
    private String FILE_NAME_PHONE = "json/phone_info.json";
    private String FILE_NAME_DRIVER = "json/driver_info.json";
    private String FILE_NAME_PASSENGER = "json/passenger_info.json";


    private JSONParser(Context context) {
        this.mContext = context;
        this.mExecutor = Executors.newCachedThreadPool();
    }

    public static JSONParser getInstance(Context context) {
        if (instance == null) {
            synchronized (JSONParser.class) {
                if (instance == null) {
                    instance = new JSONParser(context);
                }
            }
        }
        return instance;
    }

    /**
     * 解析car表
     */
    public void carParser(JSONParseListener JSONParseListener) {
        mExecutor.execute(new CarJSONParserTask(JSONParseListener));
    }

    /**
     * 解析phone
     *
     * @param JSONParseListener
     */
    public void phoneParser(JSONParseListener JSONParseListener) {
        mExecutor.execute(new PhoneJSONParserTask(JSONParseListener));
    }

    /**
     * 解析driver
     *
     * @param JSONParseListener
     */
    public void driverParser(JSONParseListener JSONParseListener) {
        mExecutor.execute(new DriverJSONParserTask(JSONParseListener));
    }

    /**
     * 解析passenger
     *
     * @param JSONParseListener
     */
    public void passengerParser(JSONParseListener JSONParseListener) {
        mExecutor.execute(new PassengerJSONParserTask(JSONParseListener));
    }

    private class CarJSONParserTask implements Runnable {

        private JSONParseListener mJSONParseListener;

        public CarJSONParserTask(JSONParseListener JSONParseListener) {
            this.mJSONParseListener = JSONParseListener;
        }

        @Override
        public void run() {
            Log.d(TAG, "CarJSONParserTask run: isInsertCar: " + isInsertCar);
            try {
                if (mCarLock.tryLock(200, TimeUnit.MILLISECONDS)) {
                    Log.d(TAG, "CarJSONParserTask run: get lock ... ");
                    CarDBHelper.getInstance().delete();
                    if (!isInsertCar) {
                        isInsertCar = true;
                        String json_car = FileUtils.readAssets(FILE_NAME_CAR, mContext);
                        Log.d(TAG, "CarJSONParserTask run: json_car = \n" + json_car);
                        List<Car> cars = JSON.parseArray(json_car, Car.class);
                        int size = cars.size();
                        for (int i = 0; i < size; i++) {
                            Car car = cars.get(i);
                            CarDBHelper.getInstance().insert(car);
                        }
                        if (mJSONParseListener != null) {
                            mJSONParseListener.onSuccess();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    isInsertCar = false;
                    mCarLock.unlock();
                    Log.d(TAG, "CarJSONParserTask run: insert finish, unlock ...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class PhoneJSONParserTask implements Runnable {

        private JSONParseListener mJSONParseListener;

        public PhoneJSONParserTask(JSONParseListener JSONParseListener) {
            this.mJSONParseListener = JSONParseListener;
        }

        @Override
        public void run() {
            Log.d(TAG, "PhoneJSONParserTask run: isInsertPhone: " + isInsertPhone);

            try {
                if (mPhoneLock.tryLock(200, TimeUnit.MILLISECONDS)) {
                    Log.d(TAG, "PhoneJSONParserTask run: get lock ... ");
                    PhoneDBHelper.getInstance().delete();

                    if (!isInsertPhone) {
                        isInsertPhone = true;
                        String json_phone = FileUtils.readAssets(FILE_NAME_PHONE, mContext);
                        Log.d(TAG, "PhoneJSONParserTask run: json_phone = \n" + json_phone);

                        List<Phone> phones = JSON.parseArray(json_phone, Phone.class);
                        int size = phones.size();
                        for (int i = 0; i < size; i++) {
                            Phone phone = phones.get(i);
                            PhoneDBHelper.getInstance().insert(phone);
                        }
                        if (mJSONParseListener != null) {
                            mJSONParseListener.onSuccess();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    isInsertPhone = false;
                    mPhoneLock.unlock();
                    Log.d(TAG, "CarJSONParserTask run: insert finish, unlock ...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class DriverJSONParserTask implements Runnable {

        private JSONParseListener mJSONParseListener;

        public DriverJSONParserTask(JSONParseListener JSONParseListener) {
            this.mJSONParseListener = JSONParseListener;
        }

        @Override
        public void run() {
            Log.d(TAG, "DriverJSONParserTask run: isInsertDriver: " + isInsertDriver);
            try {
                if (mDriverLock.tryLock(200, TimeUnit.MILLISECONDS)) {
                    Log.d(TAG, "DriverJSONParserTask run: get lock ... ");
                    DriverDBHelper.getInstance().delete();

                    if (!isInsertDriver) {
                        isInsertDriver = true;
                        String json_driver = FileUtils.readAssets(FILE_NAME_DRIVER, mContext);
                        Log.d(TAG, "DriverJSONParserTask run: json_driver = \n" + json_driver);

                        List<Driver> drivers = JSON.parseArray(json_driver, Driver.class);
                        int size = drivers.size();
                        for (int i = 0; i < size; i++) {
                            Driver driver = drivers.get(i);
                            DriverDBHelper.getInstance().insert(driver);
                        }
                        if (mJSONParseListener != null) {
                            mJSONParseListener.onSuccess();
                        }
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    isInsertDriver = false;
                    mDriverLock.unlock();
                    Log.d(TAG, "CarJSONParserTask run: insert finish, unlock ...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class PassengerJSONParserTask implements Runnable {

        private JSONParseListener mJSONParseListener;

        public PassengerJSONParserTask(JSONParseListener JSONParseListener) {
            this.mJSONParseListener = JSONParseListener;
        }

        @Override
        public void run() {
            Log.d(TAG, "PassengerJSONParserTask run: isInsertPassenger: " + isInsertPassenger);
            try {
                if (mPassengerLock.tryLock(200, TimeUnit.MILLISECONDS)) {
                    Log.d(TAG, "PassengerJSONParserTask run: get lock ... ");
                    PassengerDBHelper.getInstance().delete();
                    if (!isInsertPassenger) {
                        isInsertPassenger = true;
                        String json_passenger = FileUtils.readAssets(FILE_NAME_PASSENGER, mContext);
                        Log.d(TAG, "PassengerJSONParserTask run: json_passenger = \n" + json_passenger);
                        List<Passenger> passengers = JSON.parseArray(json_passenger, Passenger.class);
                        int size = passengers.size();
                        Log.d(TAG, "PassengerJSONParserTask run: size = " + size);
                        for (int i = 0; i < size; i++) {
                            Passenger passenger = passengers.get(i);
                            PassengerDBHelper.getInstance().insert(passenger);
                        }
                        if (mJSONParseListener != null) {
                            mJSONParseListener.onSuccess();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    isInsertPassenger = false;
                    mPassengerLock.unlock();
                    Log.d(TAG, "CarJSONParserTask run: insert finish, unlock ...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public interface JSONParseListener {

        void onSuccess();

        void onFailure(Exception e);
    }
}

