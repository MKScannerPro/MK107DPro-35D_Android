package com.moko.mk107dpro.fragment;

import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.moko.mk107dpro.base.BaseActivity;
import com.moko.mk107dpro.databinding.FragmentUserDeviceRemote03Binding;

public class UserDevice03Fragment extends Fragment {
    private final String FILTER_ASCII = "[ -~]*";
    private static final String TAG = UserDevice03Fragment.class.getSimpleName();
    private FragmentUserDeviceRemote03Binding mBind;


    private BaseActivity activity;
    private String username;
    private String password;

    public UserDevice03Fragment() {
    }

    public static UserDevice03Fragment newInstance() {
        UserDevice03Fragment fragment = new UserDevice03Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        mBind = FragmentUserDeviceRemote03Binding.inflate(inflater, container, false);
        activity = (BaseActivity) getActivity();
        InputFilter filter = (source, start, end, dest, dstart, dend) -> {
            if (!(source + "").matches(FILTER_ASCII)) {
                return "";
            }

            return null;
        };
        mBind.etMqttUsername.setFilters(new InputFilter[]{new InputFilter.LengthFilter(256), filter});
        mBind.etMqttPassword.setFilters(new InputFilter[]{new InputFilter.LengthFilter(256), filter});
        mBind.etMqttUsername.setText(username);
        mBind.etMqttPassword.setText(password);
        return mBind.getRoot();
    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }

    public void setUserName(String username) {
        this.username = username;
        if (mBind == null)
            return;
        mBind.etMqttUsername.setText(username);
    }

    public void setPassword(String password) {
        this.password = password;
        if (mBind == null)
            return;
        mBind.etMqttPassword.setText(password);
    }

    public String getUsername() {
        String username = mBind.etMqttUsername.getText().toString();
        return username;
    }

    public String getPassword() {
        String password = mBind.etMqttPassword.getText().toString();
        return password;
    }
}
