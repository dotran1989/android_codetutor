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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnAddFragment;
    private Button btnPopBackStack;
    private Button btnRemoveFragment;
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
        btnPopBackStack = findViewById(R.id.btn_pop_back_stack);
        btnRemoveFragment = findViewById(R.id.btn_remove_fragment);

        fragmentManager = getSupportFragmentManager();

        txtViewFragmentCount.setText("Fragment count in back stack: " + fragmentManager.getBackStackEntryCount());

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                txtViewFragmentCount.setText("Fragment count in back stack: " + fragmentManager.getBackStackEntryCount());

                // log backstack status
                StringBuilder backstackEntryMessage = new StringBuilder("Current status of fragment transaction back stack: " + fragmentManager.getBackStackEntryCount() + "\n");

                for (int index=(fragmentManager.getBackStackEntryCount() - 1); index >= 0; index--) {
                    FragmentManager.BackStackEntry entry = fragmentManager.getBackStackEntryAt(index);
                    backstackEntryMessage.append(entry.getName() + "\n");
                }
                Log.i("CuongDNQ", backstackEntryMessage.toString());
            }
        });

        btnAddFragment.setOnClickListener(this);
        btnPopBackStack.setOnClickListener(this);
        btnRemoveFragment.setOnClickListener(this);

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
        /*switch (fragmentManager.getBackStackEntryCount()) {
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
        }*/

        fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if (fragment instanceof SampleFragment) {
            fragment = new FragmentTwo();
        } else if (fragment instanceof FragmentTwo) {
            fragment = new FragmentThree();
        } else if (fragment instanceof FragmentThree) {
            fragment = new SampleFragment();
        } else {
            fragment = new SampleFragment();
        }

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment, "demoFragment");
        transaction.addToBackStack("Replace " + fragment.toString()); // create a seperate stack for Fragment
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        transaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if (fragment != null) {
            transaction.remove(fragment);
            transaction.addToBackStack("Remove " + fragment.toString()); // add to transaction back stack
            transaction.commit();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_fragment:
                addFragment();
                break;
            case R.id.btn_pop_back_stack:
                fragmentManager.popBackStack("Replace SampleFragment", 0);
                break;
            case R.id.btn_remove_fragment:
                transaction = fragmentManager.beginTransaction();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
                if (fragment != null) {
                    transaction.remove(fragment);
                    transaction.addToBackStack("Remove " + fragment.toString());
                    transaction.commit();
                } else {
                    Toast.makeText(this, "No fragment to remove", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
