package expert.android.quoccuong.fragmentcountry;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by QUOC CUONG on 16/03/2018.
 */

public class CountryDescriptionFragment extends Fragment {

    View rootView;
    TextView txtViewDescription;

    String countryName;
    String countryDescription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_country_description, container, false);
        initUI();
        return rootView;
    }

    private void initUI() {
        txtViewDescription = rootView.findViewById(R.id.txtViewCountryDescription);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("CuongDNQ", "CountryDescriptionFragment onSaveInstanceState - countryName: " + countryName);
        outState.putString("selectedCountry", countryName);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            Log.d("CuongDNQ", "CountryDescriptionFragment >>> 2nd");
            countryName = savedInstanceState.getString("selectedCountry", countryName);
            countryDescription = getString(getStringId(countryName));
        } else {
            Log.d("CuongDNQ", "CountryDescriptionFragment >>> 1st");
            Bundle bundle = getArguments();
            countryName = bundle.getString(FragmentActionListener.KEY_SELECTED_COUNTRY, "India");
            countryDescription = getString(getStringId(countryName));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(countryName);
        txtViewDescription.setText(countryDescription);
    }

    private int getStringId(String countryName) {
        switch (countryName) {
            case "India":
                return R.string.India;
            case "USA":
                return R.string.USA;
            case "Pakistan":
                return R.string.Pakistan;
            case "Bangladesh":
                return R.string.Bangladesh;
            case "Egypt":
                return R.string.Egypt;
            case "Indonesia":
                return R.string.Indonesia;
            case "UK":
                return R.string.UK;
            case "Germany":
                return R.string.Germany;
            default:
                return R.string.India;
        }
    }
}
