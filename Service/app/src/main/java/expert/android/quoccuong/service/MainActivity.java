package expert.android.quoccuong.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnStartService;
    private Button btnStopService;

    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("CuongDNQ", "MainActivity, thread id: " + Thread.currentThread().getId());

        btnStartService = findViewById(R.id.btn_start_service);
        btnStopService = findViewById(R.id.btn_stop_service);

        // explicit intent
        serviceIntent = new Intent(getApplicationContext(), MyService.class);

        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_service:
                startService(serviceIntent);
                break;
            case R.id.btn_stop_service:
                break;
        }
    }
}
