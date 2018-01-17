package expert.android.quoccuong.androidpersistentdata.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by CuongDuong on 1/17/2018.
 */

public class UserSettingsChangeListener implements SharedPreferences.OnSharedPreferenceChangeListener {

    private Context mContext;

    public UserSettingsChangeListener(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Toast.makeText(mContext, s + " value changed: " + sharedPreferences.getString(s, "Unknow"), Toast.LENGTH_SHORT).show();
    }
}
