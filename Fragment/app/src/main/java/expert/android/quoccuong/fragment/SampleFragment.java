package expert.android.quoccuong.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by QUOC CUONG on 11/03/2018.
 */

public class SampleFragment extends android.support.v4.app.Fragment {

    /*@Override
    public void onAttach(Context context) {
        Log.i("CuongDNQ", "SampleFragment onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("CuongDNQ", "SampleFragment onCreate");
        super.onCreate(savedInstanceState);
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.i("CuongDNQ", "SampleFragment onCreateView");
        return inflater.inflate(R.layout.fragment_sample, container, false);
    }

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i("CuongDNQ", "SampleFragment onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.i("CuongDNQ", "SampleFragment onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i("CuongDNQ", "SampleFragment onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i("CuongDNQ", "SampleFragment onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i("CuongDNQ", "SampleFragment onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.i("CuongDNQ", "SampleFragment onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.i("CuongDNQ", "SampleFragment onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.i("CuongDNQ", "SampleFragment onDetach");
        super.onDetach();
    }*/

    @Override
    public String toString() {
        return SampleFragment.class.getSimpleName();
    }
}
