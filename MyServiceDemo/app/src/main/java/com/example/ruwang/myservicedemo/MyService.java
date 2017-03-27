package com.example.ruwang.myservicedemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {


    private static String TAG = "MyService";
    private MyDownBind mBind = new MyDownBind();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBind;
    }

    /**
     * 服务创建的时候调用，只调用一次（如果项目要求必须使用前台服务，或者是防止服务被回收可以使用前台服务！具体做法就是结合通知使用）
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("我是前台通知")
                .setContentText("内容")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();
        startForeground(1, notification);//开启前台服务，类似于notify()
    }

    /**
     * 服务每次启动的时候调用
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 销毁服务
     */
    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
//        stopSelf();关闭服务
    }

    /**
     * 编写内部下载bind类
     */
    class MyDownBind extends Binder {
        public void startDown() {
            Toast.makeText(MyService.this, "开始下载", Toast.LENGTH_SHORT).show();
        }

        public void getProgress() {
            Toast.makeText(MyService.this, "获取到下载进度", Toast.LENGTH_SHORT).show();
        }
    }
}
