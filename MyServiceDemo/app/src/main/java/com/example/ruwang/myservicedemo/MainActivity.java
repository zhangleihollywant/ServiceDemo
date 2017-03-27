package com.example.ruwang.myservicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button start, stop, myBInd, myUnbind, mIntentService;
    //创建服务连接类，在活动中操作控制服务
    private MyService.MyDownBind mBind;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            mBind = (MyService.MyDownBind) binder;
            mBind.startDown();
            mBind.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);

        myBInd = (Button) findViewById(R.id.bind);
        myUnbind = (Button) findViewById(R.id.unbind);
        mIntentService = (Button) findViewById(R.id.intent_service);
        myBInd.setOnClickListener(this);
        myUnbind.setOnClickListener(this);
        mIntentService.setOnClickListener(this);
        //只是简单的开启服务
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //用intent启动服务
                startService(new Intent(MainActivity.this, MyService.class));
            }
        });

        //简单的关闭服务
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this, MyService.class));
                //也可以这样，在服务里面调用stopSelf()
            }
        });
    }

    /**
     * 简单的活动指挥服务做什么
     * 如果一个服务调用了startService(),同时又调用了bindService，那么要把服务的生命周期走完（destroy），就必须同时调用解绑和停止服务才可以结束
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bind:
                bindService(new Intent(this, MyService.class), mConnection, BIND_ABOVE_CLIENT);//绑定服务
                break;
            case R.id.unbind:
                unbindService(mConnection);//解绑服务
                break;
            case R.id.intent_service:
                Toast.makeText(this, "MainThread" + Thread.currentThread().getId(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MyIntentService.class);
                startService(intent);
                break;
        }
    }
}
