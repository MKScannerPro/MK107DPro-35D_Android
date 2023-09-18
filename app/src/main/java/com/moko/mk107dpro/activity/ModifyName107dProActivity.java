package com.moko.mk107dpro.activity;

import android.content.Context;
import android.content.Intent;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.moko.mk107dpro.AppConstants;
import com.moko.mk107dpro.R;
import com.moko.mk107dpro.base.BaseActivity;
import com.moko.mk107dpro.databinding.ActivityModifyName107dproBinding;
import com.moko.mk107dpro.db.DBTools107dPro;
import com.moko.mk107dpro.entity.MokoDevice;
import com.moko.mk107dpro.utils.ToastUtils;
import com.moko.support.mk107dpro35d.event.MQTTConnectionCompleteEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class ModifyName107dProActivity extends BaseActivity<ActivityModifyName107dproBinding> {
    private final String FILTER_ASCII = "[ -~]*";
    public static String TAG = ModifyName107dProActivity.class.getSimpleName();
    private MokoDevice device;

    @Override
    protected void onCreate() {
        device = (MokoDevice) getIntent().getSerializableExtra(AppConstants.EXTRA_KEY_DEVICE);
        InputFilter filter = (source, start, end, dest, dstart, dend) -> {
            if (!(source + "").matches(FILTER_ASCII)) {
                return "";
            }
            return null;
        };
        mBind.etNickName.setText(device.name);
        mBind.etNickName.setSelection(mBind.etNickName.getText().toString().length());
        mBind.etNickName.setFilters(new InputFilter[]{filter, new InputFilter.LengthFilter(20)});
        mBind.etNickName.postDelayed(() -> {
            InputMethodManager inputManager = (InputMethodManager) mBind.etNickName.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(mBind.etNickName, 0);
        }, 300);
    }

    @Override
    protected ActivityModifyName107dproBinding getViewBinding() {
        return ActivityModifyName107dproBinding.inflate(getLayoutInflater());
    }


    public void modifyDone(View view) {
        String name = mBind.etNickName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            ToastUtils.showToast(this, R.string.modify_device_name_empty);
            return;
        }
        device.name = name;
        DBTools107dPro.getInstance(this).updateDevice(device);
        // 跳转首页，刷新数据
        Intent intent = new Intent(this, Main107dProActivity.class);
        intent.putExtra(AppConstants.EXTRA_KEY_FROM_ACTIVITY, TAG);
        intent.putExtra(AppConstants.EXTRA_KEY_MAC, device.mac);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMQTTConnectionCompleteEvent(MQTTConnectionCompleteEvent event) {
    }
}
