package expert.android.quoccuong.fragmentcountry;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements FragmentActionListener{

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("CuongDNQ", "onCreate");
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            addCountriesFragment();
        }
    }

    private void addCountriesFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();

        CountriesFragment countriesFragment = new CountriesFragment();
        countriesFragment.setFragmentActionListener(this); // 'this' activity is no more valid -> callback reference is lost

        fragmentTransaction.add(R.id.fragmentContainer, countriesFragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

//    @Override
//    public void onCountrySelected(String country) {
//        addCountryDescriptionFragment(country);
//    }

    private void addCountryDescriptionFragment(Bundle bundle) {
        fragmentTransaction = fragmentManager.beginTransaction();

        CountryDescriptionFragment countryDescriptionFragment = new CountryDescriptionFragment();

        countryDescriptionFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.fragmentContainer, countryDescriptionFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onActionPerfomred(Bundle bundle) {
        int actionPerformed = bundle.getInt(FragmentActionListener.ACTION_KEY);
        switch (actionPerformed) {
            case FragmentActionListener.ACTION_VALUE_COUNTRY_SELECTED:
                addCountryDescriptionFragment(bundle);
                break;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d("CuongDNQ", "landscape");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.d("CuongDNQ", "portrait");
        }
    }
}
