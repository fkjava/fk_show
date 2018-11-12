package org.fkjava;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.fkjava.data.LocalDataStorage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("测试", "程序开始");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("测试", "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("测试", "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("测试", "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("测试", "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("测试", "onStart");
        LocalDataStorage ld = LocalDataStorage.getLocalDataStorage(this);

        String token = ld.get("token", null);
        if (token == null) {
            // 未登录，显示登录页面
            Intent showLoginIntent = new Intent(this, LoginActivity.class);
            super.startActivity(showLoginIntent);
        } else {
            // 已经登录，显示首页
            Intent showIndexIntent = new Intent(this, IndexActivity.class);
            super.startActivity(showIndexIntent);
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i("测试", "onPostResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("测试", "onDestroy");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("测试", "onRestoreInstanceState");
    }

    @Override
    protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
        super.onApplyThemeResource(theme, resid, first);
        Log.i("测试", "onApplyThemeResource");
    }

    @Override
    protected void onChildTitleChanged(Activity childActivity, CharSequence title) {
        super.onChildTitleChanged(childActivity, title);
        Log.i("测试", "onChildTitleChanged");
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Log.i("测试", "onUserLeaveHint");
    }
}
