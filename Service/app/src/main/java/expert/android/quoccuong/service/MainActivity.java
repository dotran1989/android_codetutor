package expert.android.quoccuong.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnStartService;
    private Button btnStopService;
    private Button btnBindService;
    private Button btnUnbindService;
    private Button btnSetRandomNumber;
    private TextView txtRandomNumber;

    private MyService myService;
    private boolean isServiceBound;
    private ServiceConnection serviceConnection;

    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("CuongDNQ", "MainActivity, thread id: " + Thread.currentThread().getId());

        btnStartService = findViewById(R.id.btn_start_service);
        btnStopService = findViewById(R.id.btn_stop_service);
        btnBindService = findViewById(R.id.btn_bind_service);
        btnUnbindService = findViewById(R.id.btn_unbind_service);
        btnSetRandomNumber = findViewById(R.id.btn_get_random_number);
        txtRandomNumber = findViewById(R.id.txt_random_number);

        // explicit intent
        serviceIntent = new Intent(getApplicationContext(), MyService.class);

        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnBindService.setOnClickListener(this);
        btnUnbindService.setOnClickListener(this);
        btnSetRandomNumber.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_service:
                startService(serviceIntent);
                break;
            case R.id.btn_stop_service:
                stopService(serviceIntent);
                break;
            case R.id.btn_bind_service:
                bindService();
                break;
            case R.id.btn_unbind_service:
                unBindService();
                break;
            case R.id.btn_get_random_number:
                setRandomNumber();
                break;
        }
    }

    private void bindService() {
        if (serviceConnection == null) {
            serviceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    MyService.MyServiceBinder myServiceBinder = (MyService.MyServiceBinder) iBinder;
                    myService = myServiceBinder.getService();
                    isServiceBound = true;
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    isServiceBound = false;
                }
            };
        }
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void unBindService() {
        if (isServiceBound) {
            unbindService(serviceConnection);;
            isServiceBound = false;
        }
    }

    private void setRandomNumber() {
        if (isServiceBound) {
            txtRandomNumber.setText("Random number: " + myService.getRandomNumber());
        } else {
            txtRandomNumber.setText("Service not bound");
        }
    }


}
