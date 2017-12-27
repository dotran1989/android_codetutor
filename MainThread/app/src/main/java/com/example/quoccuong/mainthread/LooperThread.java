package com.example.quoccuong.mainthread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * Created by QUOC CUONG on 12/27/2017.
 */

public class LooperThread extends Thread {

    private static final String TAG = "Cuong";

    Handler handler;

    @Override
    public void run() {
        Looper.prepare();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                // msg.obj will be initialized by Thread sends message to handler
                Log.i(TAG, "Thread id of Looper thread: " + Thread.currentThread().getId() + ", count: " + msg.obj);
            }
        };

        Looper.loop();
    }
}
