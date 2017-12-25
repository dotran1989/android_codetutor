package com.example.quoccuong.mainthread;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Cuong";

    private Button btnStartThread, btnStopThread;
    private TextView txtThreadCount;

    private boolean mStopLoop;
    int count = 0;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "Thread id: " + Thread.currentThread().getId());

        btnStartThread = findViewById(R.id.btnStartThread);
        btnStopThread = findViewById(R.id.btnStopThread);
        txtThreadCount = findViewById(R.id.textThreadCount);

        btnStartThread.setOnClickListener(this);
        btnStopThread.setOnClickListener(this);

        // Initialize Handler with reference to Looper
        handler = new Handler(getApplicationContext().getMainLooper());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStartThread:
                mStopLoop = true;
                // create a new Thread for loop
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (mStopLoop) {
                            try {
                                Thread.sleep(1000);
                                count++;
                            }catch (Exception e) {
                                Log.i(TAG, e.getMessage());
                            }
                            Log.i(TAG, "Thread id in while loop: " + Thread.currentThread().getId());
                            /*handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    txtThreadCount.setText(" " + count );
                                }
                            });*/
                            txtThreadCount.post(new Runnable() {
                                @Override
                                public void run() {
                                    txtThreadCount.setText(" " + count);
                                }
                            });
                        }
                    }
                }).start();
                break;
            case R.id.btnStopThread:
                mStopLoop = false;
                break;
        }
    }
}
