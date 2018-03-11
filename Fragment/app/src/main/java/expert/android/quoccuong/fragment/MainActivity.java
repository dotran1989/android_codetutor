package expert.android.quoccuong.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnAddFragment;
    private TextView txtViewFragmentCount;

    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("CuongDNQ", "onCreate");
        setContentView(R.layout.activity_main);

        txtViewFragmentCount = findViewById(R.id.txtview_fragment_count_back_stack);
        btnAddFragment = findViewById(R.id.btn_add_fragment);

        fragmentManager = getSupportFragmentManager();

        txtViewFragmentCount.setText("Fragment count in back stack: " + fragmentManager.getBackStackEntryCount());

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                txtViewFragmentCount.setText("Fragment count in back stack: " + fragmentManager.getBackStackEntryCount());
            }
        });

        btnAddFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFragment();
            }
        });
//        transaction = fragmentManager.beginTransaction(); // Error: commit already called -> app crashed!
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
        Fragment fragment;
        switch (fragmentManager.getBackStackEntryCount()) {
            case 0:
                fragment = new SampleFragment();
                break;
            case 1:
                fragment = new FragmentTwo();
                break;
            case 2:
                fragment = new FragmentThree();
                break;
            default:
                fragment = new SampleFragment();
                break;
        }
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragmentContainer, fragment, "demoFragment");
//        transaction.addToBackStack(null); // create a seperate stack for Fragment
        transaction.commit();
    }
}
