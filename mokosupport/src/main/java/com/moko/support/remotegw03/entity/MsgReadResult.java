package com.moko.support.remotegw03.entity;

public class MsgReadResult<T> {
    public int msg_id;
    public MsgDeviceInfo device_info;
    public T data;
}
