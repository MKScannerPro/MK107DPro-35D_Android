package com.moko.mk107dpro.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.moko.mk107dpro.R;
import com.moko.support.remotegw03.entity.DeviceInfo;

public class DeviceInfo107dProAdapter extends BaseQuickAdapter<DeviceInfo, BaseViewHolder> {
    public DeviceInfo107dProAdapter() {
        super(R.layout.item_devices);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceInfo item) {
        helper.setText(R.id.tv_device_name, item.name);
        helper.setText(R.id.tv_device_rssi, String.valueOf(item.rssi));
    }
}
