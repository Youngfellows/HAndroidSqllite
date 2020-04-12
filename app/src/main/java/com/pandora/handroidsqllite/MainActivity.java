package com.pandora.handroidsqllite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.pandora.handroidsqllite.activity.SQLAdvActivity;
import com.pandora.handroidsqllite.activity.SQLBaseActivity;
import com.pandora.handroidsqllite.activity.SQLProviderActivity;

public class MainActivity extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * SqlLite基本使用
     *
     * @param view
     */
    public void onSqliteBase(View view) {
        Log.d(TAG, "onSqliteBase ");
        Intent intent = new Intent();
        intent.setClass(this, SQLBaseActivity.class);
        startActivity(intent);
    }

    /**
     * SqlLite高级使用
     *
     * @param view
     */
    public void onSqlliteAdv(View view) {
        Log.d(TAG, "onSqlliteAdv ");
        Intent intent = new Intent();
        intent.setClass(this, SQLAdvActivity.class);
        startActivity(intent);
    }

    /**
     * 内容提供者访问数据库
     *
     * @param view
     */
    public void onSqlProvider(View view) {
        Log.d(TAG, "onSqlProvider ");
        Intent intent = new Intent();
        intent.setClass(this, SQLProviderActivity.class);
        startActivity(intent);
    }
}
