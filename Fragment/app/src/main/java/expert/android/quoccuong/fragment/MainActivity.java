package expert.android.quoccuong.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnAddFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("CuongDNQ", "onCreate");
        setContentView(R.layout.activity_main);

        btnAddFragment = findViewById(R.id.btn_add_fragment);
        btnAddFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFragment();
            }
        });
    }

    @Override
    protected void onStart() {
        Log.i("CuongDNQ", "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i("CuongDNQ", "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i("CuongDNQ", "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("CuongDNQ", "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("CuongDNQ", "onDestroy");
        super.onDestroy();
    }

    private void addFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        SampleFragment sampleFragment = new SampleFragment();
        transaction.add(R.id.fragmentContainer, sampleFragment);
        transaction.commit();
    }
}
