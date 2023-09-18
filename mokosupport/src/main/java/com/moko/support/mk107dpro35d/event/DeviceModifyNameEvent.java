package com.moko.support.mk107dpro35d.event;

public class DeviceModifyNameEvent {

    private String mac;

    public DeviceModifyNameEvent(String mac) {
        this.mac = mac;
    }

    public String getMac() {
        return mac;
    }
}
