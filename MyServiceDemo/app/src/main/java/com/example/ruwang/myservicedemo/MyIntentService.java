package com.example.ruwang.myservicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * <b>Create Date:</b> 17/3/24<br>
 * <b>Author:</b> Zhanglei<br>
 * <b>Description:</b> 线程id和主线程不一样，说明网络服务：集开启线程和自定停止于一身<br>
 */

public class MyIntentService extends IntentService {
    /**
     * 提供一个构造函数，并且必须调用父类的有参构造函数，否则在清单文件中定义不了
     */
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("TAG", "onHandleIntent: " + Thread.currentThread().getId());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "onDestroy: ");
    }
}
