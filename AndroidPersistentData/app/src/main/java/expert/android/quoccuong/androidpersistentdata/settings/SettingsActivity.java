package expert.android.quoccuong.androidpersistentdata.settings;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

import expert.android.quoccuong.androidpersistentdata.R;

/**
 * Created by CuongDuong on 1/17/2018.
 */

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.user_pref_settings);
    }
}
