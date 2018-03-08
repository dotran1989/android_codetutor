package expert.android.quoccuong.memoryleak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    SomeRandomSampleClass someRandomSampleClass;

    private static MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getApplicationContext() create once Application instance by Singleton
        someRandomSampleClass = SomeRandomSampleClass.getSomeRandomSampleClass(getApplicationContext());

        activity = this;
    }
}
