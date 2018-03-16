package expert.android.quoccuong.fragmentcountry;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by QUOC CUONG on 16/03/2018.
 */

public class CountriesFragment extends Fragment {

    View rootView;

    ListView listViewCountries;
    Context context;
    String[] countries;
    ArrayAdapter<String> countryNamesAdapter;

    FragmentActionListener fragmentActionListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_countries, container, false);
        initUI();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) { // reset callback reference
            fragmentActionListener = (MainActivity) getActivity();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Select country");
    }

    public void setFragmentActionListener(FragmentActionListener fragmentActionListener) {
        this.fragmentActionListener = fragmentActionListener;
    }

    private void initUI() {
        context = getContext();
        countries = getResources().getStringArray(R.array.countries);
        listViewCountries = rootView.findViewById(R.id.listViewCountries);

        countryNamesAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, countries);
        listViewCountries.setAdapter(countryNamesAdapter);

        listViewCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (fragmentActionListener != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(FragmentActionListener.ACTION_KEY, FragmentActionListener.ACTION_VALUE_COUNTRY_SELECTED);
                    bundle.putString(FragmentActionListener.KEY_SELECTED_COUNTRY, countries[i]);
                    fragmentActionListener.onActionPerfomred(bundle);
                }
            }
        });
    }
}
