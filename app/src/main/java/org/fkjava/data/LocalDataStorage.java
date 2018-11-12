package org.fkjava.data;

import android.content.Context;
import android.content.SharedPreferences;

import org.fkjava.MainActivity;
import org.fkjava.TaskCenter;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class LocalDataStorage {

    private static final LocalDataStorage localDataStorage;

    static {
        localDataStorage = new LocalDataStorage();
    }

    private boolean inited = false;
    private Future<Boolean> initFuture;
    /**
     * 默认共享属性文件
     */
    private SharedPreferences defaultSharedPreferences;

    public static LocalDataStorage getLocalDataStorage(Context context) {
        localDataStorage.init(context);
        return localDataStorage;
    }

    private void init(final Context context) {
        synchronized (context) {
            if (!inited) {
                inited = true;
                // 启动一个线程来加载数据
                initFuture = TaskCenter.commit(new Callable<Boolean>() {
                    @Override
                    public Boolean call() {
                        defaultSharedPreferences = //
                                context.getSharedPreferences("fk-show", Context.MODE_PRIVATE);
                        return null;
                    }
                });
            }
        }
    }

    /**
     * 等待初始化完成
     */
    private void await() {
        try {
            initFuture.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void put(final String key, final String value) {
        TaskCenter.commit(new Runnable() {
            @Override
            public void run() {
                await();
                // 创建共享属性文件的编辑器
                SharedPreferences.Editor editor = defaultSharedPreferences.edit();
                // 存储键值对到属性文件中
                editor.putString(key, value);
                // 提交修改

                // 如果有两个线程调用commit，后面线程提交的数据为准
                //editor.commit();

                // apply和commit的区别在于：apply是在另外一个线程提交（异步提交），commit则是在当前线程提交（同步提交）
                editor.apply();
            }
        });
    }

    public String get(String key) {
        return this.get(key, "");
    }

    public String get(String key, String defaultValue) {

        await();
        // 从共享属性文件中获取key对应的值，如果没有则返回默认值
        return defaultSharedPreferences.getString(key, defaultValue);
    }
}
