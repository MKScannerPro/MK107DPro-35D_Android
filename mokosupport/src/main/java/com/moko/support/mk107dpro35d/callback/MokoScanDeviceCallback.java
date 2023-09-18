package com.moko.support.mk107dpro35d.callback;

import com.moko.support.mk107dpro35d.entity.DeviceInfo;

public interface MokoScanDeviceCallback {
    void onStartScan();

    void onScanDevice(DeviceInfo device);

    void onStopScan();
}
