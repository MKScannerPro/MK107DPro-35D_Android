package com.moko.mk107dpro.activity.filter;


import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.moko.mk107dpro.AppConstants;
import com.moko.mk107dpro.base.BaseActivity;
import com.moko.mk107dpro.databinding.ActivityFilterTlm107dproBinding;
import com.moko.mk107dpro.dialog.Bottom107dProDialog;
import com.moko.mk107dpro.entity.MQTTConfig;
import com.moko.mk107dpro.entity.MokoDevice;
import com.moko.mk107dpro.utils.SPUtiles;
import com.moko.mk107dpro.utils.ToastUtils;
import com.moko.support.mk107dpro35d.MQTTConstants;
import com.moko.support.mk107dpro35d.MQTTSupport;
import com.moko.support.mk107dpro35d.entity.MsgConfigResult;
import com.moko.support.mk107dpro35d.entity.MsgReadResult;
import com.moko.support.mk107dpro35d.event.DeviceOnlineEvent;
import com.moko.support.mk107dpro35d.event.MQTTMessageArrivedEvent;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FilterTLM107dProActivity extends BaseActivity<ActivityFilterTlm107dproBinding> {

    private MokoDevice mMokoDevice;
    private MQTTConfig appMqttConfig;
    private String mAppTopic;

    public Handler mHandler;

    private ArrayList<String> mValues;
    private int mSelected;

    @Override
    protected void onCreate() {
        mValues = new ArrayList<>();
        mValues.add("version 0");
        mValues.add("version 1");
        mValues.add("all");
        mMokoDevice = (MokoDevice) getIntent().getSerializableExtra(AppConstants.EXTRA_KEY_DEVICE);
        String mqttConfigAppStr = SPUtiles.getStringValue(this, AppConstants.SP_KEY_MQTT_CONFIG_APP, "");
        appMqttConfig = new Gson().fromJson(mqttConfigAppStr, MQTTConfig.class);
        mAppTopic = TextUtils.isEmpty(appMqttConfig.topicPublish) ? mMokoDevice.topicSubscribe : appMqttConfig.topicPublish;
        mHandler = new Handler(Looper.getMainLooper());
        mHandler.postDelayed(() -> {
            dismissLoadingProgressDialog();
            finish();
        }, 30 * 1000);
        showLoadingProgressDialog();
        getFilterTlm();
    }

    @Override
    protected ActivityFilterTlm107dproBinding getViewBinding() {
        return ActivityFilterTlm107dproBinding.inflate(getLayoutInflater());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMQTTMessageArrivedEvent(MQTTMessageArrivedEvent event) {
        // 更新所有设备的网络状态
        final String topic = event.getTopic();
        final String message = event.getMessage();
        if (TextUtils.isEmpty(message))
            return;
        int msg_id;
        try {
            JsonObject object = new Gson().fromJson(message, JsonObject.class);
            JsonElement element = object.get("msg_id");
            msg_id = element.getAsInt();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (msg_id == MQTTConstants.READ_MSG_ID_FILTER_TLM) {
            Type type = new TypeToken<MsgReadResult<JsonObject>>() {
            }.getType();
            MsgReadResult<JsonObject> result = new Gson().fromJson(message, type);
            if (!mMokoDevice.mac.equalsIgnoreCase(result.device_info.mac))
                return;
            dismissLoadingProgressDialog();
            mHandler.removeMessages(0);
            mBind.cbTlm.setChecked(result.data.get("switch_value").getAsInt() == 1);
            mSelected = result.data.get("tlm_version").getAsInt();
            mBind.tvTlmVersion.setText(mValues.get(mSelected));
        }
        if (msg_id == MQTTConstants.CONFIG_MSG_ID_FILTER_TLM) {
            Type type = new TypeToken<MsgConfigResult>() {
            }.getType();
            MsgConfigResult result = new Gson().fromJson(message, type);
            if (!mMokoDevice.mac.equalsIgnoreCase(result.device_info.mac))
                return;
            dismissLoadingProgressDialog();
            mHandler.removeMessages(0);
            if (result.result_code == 0) {
                ToastUtils.showToast(this, "Set up succeed");
            } else {
                ToastUtils.showToast(this, "Set up failed");
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDeviceOnlineEvent(DeviceOnlineEvent event) {
        super.offline(event, mMokoDevice.mac);
    }

    private void getFilterTlm() {
        int msgId = MQTTConstants.READ_MSG_ID_FILTER_TLM;
        String message = assembleReadCommon(msgId, mMokoDevice.mac);
        try {
            MQTTSupport.getInstance().publish(mAppTopic, message, msgId, appMqttConfig.qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void onBack(View view) {
        finish();
    }

    public void onSave(View view) {
        if (isWindowLocked()) return;
        mHandler.postDelayed(() -> {
            dismissLoadingProgressDialog();
            ToastUtils.showToast(this, "Set up failed");
        }, 30 * 1000);
        showLoadingProgressDialog();
        saveParams();
    }


    private void saveParams() {
        int msgId = MQTTConstants.CONFIG_MSG_ID_FILTER_TLM;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("switch_value", mBind.cbTlm.isChecked() ? 1 : 0);
        jsonObject.addProperty("tlm_version", mSelected);
        String message = assembleWriteCommonData(msgId, mMokoDevice.mac, jsonObject);
        try {
            MQTTSupport.getInstance().publish(mAppTopic, message, msgId, appMqttConfig.qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void onTLMVersion(View view) {
        if (isWindowLocked())
            return;
        Bottom107dProDialog dialog = new Bottom107dProDialog();
        dialog.setDatas(mValues, mSelected);
        dialog.setListener(value -> {
            mSelected = value;
            mBind.tvTlmVersion.setText(mValues.get(value));
        });
        dialog.show(getSupportFragmentManager());
    }
}
