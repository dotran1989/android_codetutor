package expert.android.quoccuong.fragmentcountry;

import android.os.Bundle;

/**
 * Created by QUOC CUONG on 16/03/2018.
 */

public interface FragmentActionListener {
    String ACTION_KEY = "action_key";
    int ACTION_VALUE_COUNTRY_SELECTED = 1;

    String KEY_SELECTED_COUNTRY = "KEY_SELECTED_COUNTRY";

//    void onCountrySelected(String country);
    void onActionPerfomred(Bundle bundle);
}
