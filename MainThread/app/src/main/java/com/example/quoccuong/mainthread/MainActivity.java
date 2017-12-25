package com.example.quoccuong.mainthread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Cuong";

    private Button btnStartThread, btnStopThread;

    private boolean mStopLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "Thread id: " + Thread.currentThread().getId());

        btnStartThread = findViewById(R.id.btnStartThread);
        btnStopThread = findViewById(R.id.btnStopThread);

        btnStartThread.setOnClickListener(this);
        btnStopThread.setOnClickListener(this);
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
                            Log.i(TAG, "Thread id in while loop: " + Thread.currentThread().getId());
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
