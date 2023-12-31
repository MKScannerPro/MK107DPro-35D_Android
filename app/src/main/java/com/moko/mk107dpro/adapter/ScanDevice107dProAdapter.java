package com.moko.mk107dpro.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.moko.mk107dpro.R;

public class ScanDevice107dProAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ScanDevice107dProAdapter() {
        super(R.layout.item_scan_device);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_scan_device_info, item);
    }
}
