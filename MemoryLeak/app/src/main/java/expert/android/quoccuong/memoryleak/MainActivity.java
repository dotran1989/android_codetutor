package expert.android.quoccuong.memoryleak;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    SomeRandomSampleClass someRandomSampleClass;

    private static WeakReference<MainActivity> activity;

    private static TextView textView;

    private Object innerObject;

    // Refresh Java inner and static inner class why it works.
    private static class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

    private Thread thread;

    private static class CustomHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }

    private static class RunnableHandler implements Runnable {
        @Override
        public void run() {

        }
    }

    private static class CustomTimer extends TimerTask {

        @Override
        public void run() {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getApplicationContext() create once Application instance by Singleton
        someRandomSampleClass = SomeRandomSampleClass.getSomeRandomSampleClass(getApplicationContext());

        activity = new WeakReference<MainActivity>(this);

        textView = findViewById(R.id.textviewLabel);

        class SampleInnerClass{

        }

        innerObject = new SampleInnerClass();

        new MyAsyncTask().execute();

        // Thread might outlive Activity. Hence leaking memory
        thread = new Thread(){
            @Override
            public void run() {
                super.run();
                if (!isInterrupted()) {

                }
            }
        };
        thread.start();

        // anonymous inner class Hander and Runnable. Having the reference to the outer activity class
        new CustomHandler().postDelayed(new RunnableHandler(), Long.MAX_VALUE >> 1);

        new Timer().schedule(new CustomTimer(), Long.MAX_VALUE>>1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        textView = null;
        thread.interrupt(); // make sure thread does not running even beyond the life cycle of activity
    }
}
