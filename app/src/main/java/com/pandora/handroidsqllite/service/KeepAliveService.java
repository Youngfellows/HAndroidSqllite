package com.pandora.handroidsqllite.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;

import com.pandora.handroidsqllite.utils.NotificationUtils;

/**
 * 申请亮屏锁
 */
public class KeepAliveService extends Service {

    private String TAG = this.getClass().getSimpleName();

    /**
     * 锁屏锁
     */
    private PowerManager.WakeLock wakeLock = null;

    public KeepAliveService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationUtils.startForeground(this);
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, KeepAliveService.class.getName());
        if (null != wakeLock) {
            wakeLock.acquire();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (wakeLock != null) {
            wakeLock.release();
            wakeLock = null;
        }
    }
}
