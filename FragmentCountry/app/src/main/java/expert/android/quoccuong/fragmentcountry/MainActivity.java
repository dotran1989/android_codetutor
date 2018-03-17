package expert.android.quoccuong.fragmentcountry;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements FragmentActionListener {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    String selectedCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("CuongDNQ", "MainActivity onCreate");
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        if (findViewById(R.id.activity_main_portrait) != null) {
            addCountriesFragment();
        } else if (findViewById(R.id.activity_main_landscape) != null) {
            addCountriesFragment();
            addCountryDescriptionFragment(R.id.fragmentContainer2, "India");
        }
    }

    @Override
    protected void onResume() {
        Log.d("CuongDNQ", "MainActivity onResume");
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("CuongDNQ", "MainActivity onSaveInstanceState - selectedCountry: " + selectedCountry);
        outState.putString("selectedCountry", selectedCountry);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("CuongDNQ", "MainActivity onRestoreInstanceState - selectedCountry: " + selectedCountry);
        selectedCountry = savedInstanceState.getString("selectedCountry", "India");
    }

    private void addCountriesFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();

        CountriesFragment countriesFragment = new CountriesFragment();
        countriesFragment.setFragmentActionListener(this); // 'this' activity is no more valid -> callback reference is lost

        fragmentTransaction.add(R.id.fragmentContainer, countriesFragment);
        fragmentTransaction.commit();
    }

    private void addCountryDescriptionFragment(String countryName) {
        fragmentTransaction = fragmentManager.beginTransaction();

        CountryDescriptionFragment countryDescriptionFragment = new CountryDescriptionFragment();

        Bundle bundle = new Bundle();
        bundle.putString(FragmentActionListener.KEY_SELECTED_COUNTRY, countryName);
        countryDescriptionFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.fragmentContainer, countryDescriptionFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void addCountryDescriptionFragment(int containerId, String countryName) {
        fragmentTransaction = fragmentManager.beginTransaction();

        CountryDescriptionFragment countryDescriptionFragment = new CountryDescriptionFragment();

        Bundle bundle = new Bundle();
        bundle.putString(FragmentActionListener.KEY_SELECTED_COUNTRY, countryName);
        countryDescriptionFragment.setArguments(bundle);

        fragmentTransaction.replace(containerId, countryDescriptionFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onCountrySelected(String country) {
        selectedCountry = country;
        if (findViewById(R.id.activity_main_landscape) == null) {
            addCountryDescriptionFragment(country);
        } else {
            addCountryDescriptionFragment(R.id.fragmentContainer2, country);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_main);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d("CuongDNQ", "landscape");
            addCountriesFragment();
            if (selectedCountry == null) {
                addCountryDescriptionFragment("India");
            } else {
                addCountryDescriptionFragment(R.id.fragmentContainer2, selectedCountry);
            }
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.d("CuongDNQ", "portrait");
            if (selectedCountry == null) {
                addCountriesFragment();
            } else {
                addCountryDescriptionFragment(selectedCountry);
            }
        }
    }
}
