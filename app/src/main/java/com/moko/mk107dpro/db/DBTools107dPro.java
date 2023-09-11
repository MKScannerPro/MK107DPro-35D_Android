package com.moko.mk107dpro.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.moko.mk107dpro.entity.MokoDevice;

import java.util.ArrayList;

public class DBTools107dPro {
    private DBOpenHelper107dPro myDBOpenHelper;
    private SQLiteDatabase db;
    private static DBTools107dPro dbTools;

    public static DBTools107dPro getInstance(Context context) {
        if (dbTools == null) {
            dbTools = new DBTools107dPro(context);
            return dbTools;
        }
        return dbTools;
    }

    public DBTools107dPro(Context context) {
        myDBOpenHelper = new DBOpenHelper107dPro(context);
        db = myDBOpenHelper.getWritableDatabase();
    }

    public long insertDevice(MokoDevice mokoDevice) {
        ContentValues cv = new ContentValues();
        cv.put(DBConstants107dPro.DEVICE_FIELD_NAME, mokoDevice.name);
        cv.put(DBConstants107dPro.DEVICE_FIELD_MAC, mokoDevice.mac);
        cv.put(DBConstants107dPro.DEVICE_FIELD_MQTT_INFO, mokoDevice.mqttInfo);
        cv.put(DBConstants107dPro.DEVICE_FIELD_DEVICE_TYPE, mokoDevice.deviceType);
        cv.put(DBConstants107dPro.DEVICE_FIELD_LWT_ENABLE, mokoDevice.lwtEnable);
        cv.put(DBConstants107dPro.DEVICE_FIELD_LWT_TOPIC, mokoDevice.lwtTopic);
        cv.put(DBConstants107dPro.DEVICE_FIELD_TOPIC_PUBLISH, mokoDevice.topicPublish);
        cv.put(DBConstants107dPro.DEVICE_FIELD_TOPIC_SUBSCRIBE, mokoDevice.topicSubscribe);
        long row = db.insert(DBConstants107dPro.TABLE_NAME_DEVICE, null, cv);
        return row;
    }

    @SuppressLint("Range")
    public ArrayList<MokoDevice> selectAllDevice() {
        Cursor cursor = db.query(DBConstants107dPro.TABLE_NAME_DEVICE, null, null, null,
                null, null, DBConstants107dPro.DEVICE_FIELD_ID + " DESC");
        ArrayList<MokoDevice> mokoDevices = new ArrayList<>();
        while (cursor.moveToNext()) {
            MokoDevice mokoDevice = new MokoDevice();
            mokoDevice.id = cursor.getInt(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_ID));
            mokoDevice.name = cursor.getString(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_NAME));
            mokoDevice.mac = cursor.getString(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_MAC));
            mokoDevice.mqttInfo = cursor.getString(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_MQTT_INFO));
            mokoDevice.deviceType = cursor.getInt(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_DEVICE_TYPE));
            mokoDevice.lwtEnable = cursor.getInt(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_LWT_ENABLE));
            mokoDevice.lwtTopic = cursor.getString(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_LWT_TOPIC));
            mokoDevice.topicPublish = cursor.getString(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_TOPIC_PUBLISH));
            mokoDevice.topicSubscribe = cursor.getString(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_TOPIC_SUBSCRIBE));
            mokoDevices.add(mokoDevice);
        }
        return mokoDevices;
    }

    @SuppressLint("Range")
    public MokoDevice selectDevice(String mac) {
        Cursor cursor = db.query(DBConstants107dPro.TABLE_NAME_DEVICE, null, DBConstants107dPro.DEVICE_FIELD_MAC + " = ?", new String[]{mac}, null, null, null);
        MokoDevice mokoDevice = null;
        while (cursor.moveToFirst()) {
            mokoDevice = new MokoDevice();
            mokoDevice.id = cursor.getInt(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_ID));
            mokoDevice.name = cursor.getString(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_NAME));
            mokoDevice.mac = cursor.getString(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_MAC));
            mokoDevice.mqttInfo = cursor.getString(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_MQTT_INFO));
            mokoDevice.deviceType = cursor.getInt(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_DEVICE_TYPE));
            mokoDevice.lwtEnable = cursor.getInt(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_LWT_ENABLE));
            mokoDevice.lwtTopic = cursor.getString(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_LWT_TOPIC));
            mokoDevice.topicPublish = cursor.getString(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_TOPIC_PUBLISH));
            mokoDevice.topicSubscribe = cursor.getString(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_TOPIC_SUBSCRIBE));
            break;
        }
        return mokoDevice;
    }

    @SuppressLint("Range")
    public MokoDevice selectDeviceByMac(String mac) {
        Cursor cursor = db.query(DBConstants107dPro.TABLE_NAME_DEVICE, null, DBConstants107dPro.DEVICE_FIELD_MAC + " = ?", new String[]{mac}, null, null, null);
        MokoDevice mokoDevice = null;
        while (cursor.moveToFirst()) {
            mokoDevice = new MokoDevice();
            mokoDevice.id = cursor.getInt(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_ID));
            mokoDevice.name = cursor.getString(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_NAME));
            mokoDevice.mac = cursor.getString(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_MAC));
            mokoDevice.mqttInfo = cursor.getString(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_MQTT_INFO));
            mokoDevice.deviceType = cursor.getInt(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_DEVICE_TYPE));
            mokoDevice.lwtEnable = cursor.getInt(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_LWT_ENABLE));
            mokoDevice.lwtTopic = cursor.getString(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_LWT_TOPIC));
            mokoDevice.topicPublish = cursor.getString(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_TOPIC_PUBLISH));
            mokoDevice.topicSubscribe = cursor.getString(cursor
                    .getColumnIndex(DBConstants107dPro.DEVICE_FIELD_TOPIC_SUBSCRIBE));
            break;
        }
        return mokoDevice;
    }


    public void updateDevice(MokoDevice mokoDevice) {
        String where = DBConstants107dPro.DEVICE_FIELD_MAC + " = ?";
        String[] whereValue = {mokoDevice.mac};
        ContentValues cv = new ContentValues();
        cv.put(DBConstants107dPro.DEVICE_FIELD_NAME, mokoDevice.name);
        cv.put(DBConstants107dPro.DEVICE_FIELD_MAC, mokoDevice.mac);
        cv.put(DBConstants107dPro.DEVICE_FIELD_MQTT_INFO, mokoDevice.mqttInfo);
        cv.put(DBConstants107dPro.DEVICE_FIELD_LWT_ENABLE, mokoDevice.lwtEnable);
        cv.put(DBConstants107dPro.DEVICE_FIELD_LWT_TOPIC, mokoDevice.lwtTopic);
        cv.put(DBConstants107dPro.DEVICE_FIELD_TOPIC_PUBLISH, mokoDevice.topicPublish);
        cv.put(DBConstants107dPro.DEVICE_FIELD_TOPIC_SUBSCRIBE, mokoDevice.topicSubscribe);
        cv.put(DBConstants107dPro.DEVICE_FIELD_DEVICE_TYPE, mokoDevice.deviceType);
        db.update(DBConstants107dPro.TABLE_NAME_DEVICE, cv, where, whereValue);
    }

    public void deleteAllData() {
        db.delete(DBConstants107dPro.TABLE_NAME_DEVICE, null, null);
    }

    public void deleteDevice(MokoDevice device) {
        String where = DBConstants107dPro.DEVICE_FIELD_MAC + " = ?";
        String[] whereValue = {device.mac + ""};
        db.delete(DBConstants107dPro.TABLE_NAME_DEVICE, where, whereValue);
    }

    // drop table;
    public void dropTable(String tablename) {
        db.execSQL("DROP TABLE IF EXISTS " + tablename);
    }

    // close database;
    public void close(String databaseName) {
        db.close();
    }

}
