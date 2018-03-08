package expert.android.quoccuong.memoryleak;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.ref.WeakReference;

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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        textView = null;
    }
}
