package com.pandora.handroidsqllite.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.pandora.handroidsqllite.R;
import com.pandora.handroidsqllite.bean.TimeDifference;
import com.pandora.handroidsqllite.utils.TimeUtils;

import java.text.SimpleDateFormat;

public class SQLAdvActivity extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqladv);
    }

    /**
     * 计算时间差
     *
     * @param view
     */
    public void onCalculateDateDiff(View view) {
        Log.d(TAG, "onCalculateDateDiff: ");
        String dispatchTime1 = "2020-05-22 12:40:15";
        String recentTime1 = "2020-05-22 14:49:15";

        TimeDifference timeDifference = TimeUtils.timeDifference(dispatchTime1, recentTime1);
        //int diffMinutes = TimeUtils.calMinutesTimeDiff(dispatchTime1, recentTime1);
        //int diffHours = TimeUtils.calHoursTimeDiff(dispatchTime1, recentTime1);
        long diffHours = timeDifference.getDay() * 24 + timeDifference.getHour();
        long diffMinutes = timeDifference.getDay() * 24 * 60 + timeDifference.getHour() * 60 + timeDifference.getMinute();
        Log.d(TAG, "diffHours: " + diffHours);
        Log.d(TAG, "diffMinutes: " + diffMinutes);

        String dispatchTime2 = "2020-05-22 12:40:15";
        String recentTime2 = "2020-05-22 23:49:15";
        timeDifference = TimeUtils.timeDifference(dispatchTime2, recentTime2);
        //diffHours = TimeUtils.calHoursTimeDiff(dispatchTime2, recentTime2);
        //diffMinutes = TimeUtils.calMinutesTimeDiff(dispatchTime2, recentTime2);
        diffHours = timeDifference.getDay() * 24 + timeDifference.getHour();
        diffMinutes = timeDifference.getDay() * 24 * 60 + timeDifference.getHour() * 60 + timeDifference.getMinute();
        Log.d(TAG, "diffHours: " + diffHours);
        Log.d(TAG, "diffMinutes: " + diffMinutes);

        String dispatchTime3 = "2020-05-22 13:40:15";
        String recentTime3 = "2020-05-22 18:49:15";
        timeDifference = TimeUtils.timeDifference(dispatchTime3, recentTime3);
        //diffHours = TimeUtils.calHoursTimeDiff(dispatchTime3, recentTime3);
        //diffMinutes = TimeUtils.calMinutesTimeDiff(dispatchTime3, recentTime3);
        Log.d(TAG, "diffHours: " + diffHours);
        Log.d(TAG, "diffMinutes: " + diffMinutes);

        String dispatchTime4 = "2020-05-22 01:40:15";
        String recentTime4 = "2020-05-22 23:49:15";
        timeDifference = TimeUtils.timeDifference(dispatchTime4, recentTime4);
        //diffHours = TimeUtils.calHoursTimeDiff(dispatchTime4, recentTime4);
        //diffMinutes = TimeUtils.calMinutesTimeDiff(dispatchTime4, recentTime4);
        diffHours = timeDifference.getDay() * 24 + timeDifference.getHour();
        diffMinutes = timeDifference.getDay() * 24 * 60 + timeDifference.getHour() * 60 + timeDifference.getMinute();
        Log.d(TAG, "diffHours: " + diffHours);
        Log.d(TAG, "diffMinutes: " + diffMinutes);

        String dispatchTime5 = "2020-05-21 01:40:15";
        String recentTime5 = "2020-05-22 12:49:15";
        timeDifference = TimeUtils.timeDifference(dispatchTime5, recentTime5);
        //diffHours = TimeUtils.calHoursTimeDiff(dispatchTime5, recentTime5);
        //diffMinutes = TimeUtils.calMinutesTimeDiff(dispatchTime5, recentTime5);
        diffHours = timeDifference.getDay() * 24 + timeDifference.getHour();
        diffMinutes = timeDifference.getDay() * 24 * 60 + timeDifference.getHour() * 60 + timeDifference.getMinute();
        Log.d(TAG, "diffHours: " + diffHours);
        Log.d(TAG, "diffMinutes: " + diffMinutes);

        String dispatchTime6 = "2020-05-21 01:40:15";
        String recentTime6 = "2020-05-22 14:49:15";
        timeDifference = TimeUtils.timeDifference(dispatchTime6, recentTime6);
        //diffHours = TimeUtils.calHoursTimeDiff(dispatchTime6, recentTime6);
        //diffMinutes = TimeUtils.calMinutesTimeDiff(dispatchTime6, recentTime6);
        diffHours = timeDifference.getDay() * 24 + timeDifference.getHour();
        diffMinutes = timeDifference.getDay() * 24 * 60 + timeDifference.getHour() * 60 + timeDifference.getMinute();
        Log.d(TAG, "diffHours: " + diffHours);
        Log.d(TAG, "diffMinutes: " + diffMinutes);

        String dispatchTime7 = "2020-05-21 11:40:15";
        String recentTime7 = "2020-05-22 23:49:15";
        timeDifference = TimeUtils.timeDifference(dispatchTime7, recentTime7);
        //diffHours = TimeUtils.calHoursTimeDiff(dispatchTime7, recentTime7);
        //diffMinutes = TimeUtils.calMinutesTimeDiff(dispatchTime7, recentTime7);
        diffHours = timeDifference.getDay() * 24 + timeDifference.getHour();
        diffMinutes = timeDifference.getDay() * 24 * 60 + timeDifference.getHour() * 60 + timeDifference.getMinute();
        Log.d(TAG, "diffHours: " + diffHours);
        Log.d(TAG, "diffMinutes: " + diffMinutes);

        String dispatchTime8 = "2020-05-11 02:40:15";
        String recentTime8 = "2020-05-22 23:49:15";
        timeDifference = TimeUtils.timeDifference(dispatchTime8, recentTime8);
        //diffHours = TimeUtils.calHoursTimeDiff(dispatchTime8, recentTime8);
        //diffMinutes = TimeUtils.calMinutesTimeDiff(dispatchTime8, recentTime8);
        diffHours = timeDifference.getDay() * 24 + timeDifference.getHour();
        diffMinutes = timeDifference.getDay() * 24 * 60 + timeDifference.getHour() * 60 + timeDifference.getMinute();
        Log.d(TAG, "diffHours: " + diffHours);
        Log.d(TAG, "diffMinutes: " + diffMinutes);

    }
}
