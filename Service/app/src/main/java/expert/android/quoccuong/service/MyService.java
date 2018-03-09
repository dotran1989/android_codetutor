package expert.android.quoccuong.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

/**
 * Created by QUOC CUONG on 09/03/2018.
 */

public class MyService extends Service {

    private int mRandomNumber;
    private boolean mIsRandomGeneratorOn;

    private final int MIN = 0;
    private final int MAX = 100;

    class MyServiceBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    private IBinder mBinder = new MyServiceBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("CuongDNQ", "In onDestroy, thread id: " + Thread.currentThread().getId());
        stopRandomNumberGenerator();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("CuongDNQ", "In onStartCommand, thread id: " + Thread.currentThread().getId());
        mIsRandomGeneratorOn = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                startRandomNumberGenerator();
            }
        }).start();
        return START_STICKY;
    }

    private void startRandomNumberGenerator() {
        while (mIsRandomGeneratorOn) {
            try {
                Thread.sleep(1000);
                if (mIsRandomGeneratorOn) {
                    mRandomNumber = new Random().nextInt(MAX) + MIN;
                    Log.i("CuongDNQ", "Thread id: " + Thread.currentThread().getId() + ", Random number: " + mRandomNumber);
                }
            } catch (InterruptedException e) {
                Log.i("CuongDNQ", "Thread interrupted");
            }
        }
    }

    private void stopRandomNumberGenerator() {
        mIsRandomGeneratorOn = false;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("CuongDNQ", "onUnbind");
        return super.onUnbind(intent);
    }

    public int getRandomNumber() {
        return mRandomNumber;
    }
}
