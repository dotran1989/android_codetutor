package expert.android.quoccuong.memoryleak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    SomeRandomSampleClass someRandomSampleClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get instance
        someRandomSampleClass = SomeRandomSampleClass.getSomeRandomSampleClass(this);
    }
}