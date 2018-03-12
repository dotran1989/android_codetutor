package expert.android.quoccuong.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by QUOC CUONG on 11/03/2018.
 */

public class FragmentTwo extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("CuongDNQ", "FragmentTwo onCreateView");
        return inflater.inflate(R.layout.fragment_sample_two, container, false);
    }

    @Override
    public String toString() {
        return FragmentTwo.class.getSimpleName();
    }
}
