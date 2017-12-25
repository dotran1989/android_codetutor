package com.example.quoccuong.mainthread;

import android.os.AsyncTask;
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

    private MyAsyncTask myAsyncTask;

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
//        handler = new Handler(getApplicationContext().getMainLooper());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStartThread:
                mStopLoop = true;
                // create a new Thread for loop
                /*new Thread(new Runnable() {
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
                            *//*handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    txtThreadCount.setText(" " + count );
                                }
                            });*//*
                            txtThreadCount.post(new Runnable() {
                                @Override
                                public void run() {
                                    txtThreadCount.setText(" " + count);
                                }
                            });
                        }
                    }
                }).start();*/
                myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute(5);
                break;
            case R.id.btnStopThread:
//                mStopLoop = false;
                myAsyncTask.cancel(true);
                break;
        }
    }

    private class MyAsyncTask extends AsyncTask<Integer, Integer, Integer> {

        private int customCounter;

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute");
            super.onPreExecute();
            customCounter = 0;
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            Log.i(TAG, "doInBackground");
            customCounter = integers[0];
            Log.i(TAG, "integers[0]: " + integers[0]);
            while (mStopLoop) {
                try {
                    Thread.sleep(1000);
                    customCounter++;
                    publishProgress(customCounter); // send value to onProgressUpdate()
                } catch (Exception e) {
                    Log.i(TAG, e.getMessage());
                }
                if (isCancelled()) {
                    break;
                }
            }
            return customCounter;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.i(TAG, "onProgressUpdate");
            txtThreadCount.setText("" + values[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            Log.i(TAG, "onPostExecute");
            super.onPostExecute(integer);
            txtThreadCount.setText("" + integer);
            count = integer;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i(TAG, "onCancelled");
        }

        @Override
        protected void onCancelled(Integer integer) {
            super.onCancelled(integer);
            Log.i(TAG, "onCancelled + integer");
        }
    }
}
