package com.example.quoccuong.mainthread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

/**
 * Created by QUOC CUONG on 1/7/2018.
 */

public class CustomHandlerThread extends HandlerThread {

    private static final String TAG = "Cuong";

    public Handler mHandler;

    public CustomHandlerThread(String name) {
        super(name);
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.i(TAG, "Thread id when message is posted: " + Thread.currentThread().getId() + ", Count: " + msg.obj);
            }
        };
    }
}
