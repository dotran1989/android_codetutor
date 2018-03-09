package expert.android.quoccuong.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by QUOC CUONG on 09/03/2018.
 */

public class MyService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("CuongDNQ", "In onDestroy, thread id: " + Thread.currentThread().getId());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("CuongDNQ", "In onStartCommand, thread id: " + Thread.currentThread().getId());
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }
}
