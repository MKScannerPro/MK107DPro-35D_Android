package com.moko.mk107dpro.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.elvishew.xlog.XLog;

public class DBOpenHelper107dPro extends SQLiteOpenHelper {
    private static final String DB_NAME = "MKRemoteGW107dPro";
    // 数据库版本号
    private static final int DB_VERSION = 1;

    private Context mContext;

    public DBOpenHelper107dPro(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DEVICE);
        Log.i("MKRemoteGW107dPro", "创建数据库");
    }

    /**
     * 升级时数据库时调用
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            XLog.i("数据库升级");
            XLog.i("旧版本:" + oldVersion + ";新版本:" + newVersion);
        }
    }

    /**
     * 删除数据库
     *
     * @param context
     * @return
     */
    public boolean deleteDatabase(Context context) {
        return context.deleteDatabase(DB_NAME);
    }

    // 设备表
    private static final String CREATE_TABLE_DEVICE = "CREATE TABLE "
            + DBConstants107dPro.TABLE_NAME_DEVICE
            // id
            + " (" + DBConstants107dPro.DEVICE_FIELD_ID
            + " INTEGER primary key autoincrement, "
            // 名字
            + DBConstants107dPro.DEVICE_FIELD_NAME + " TEXT,"
            // MAC
            + DBConstants107dPro.DEVICE_FIELD_MAC + " TEXT,"
            // mqtt信息
            + DBConstants107dPro.DEVICE_FIELD_MQTT_INFO + " TEXT,"
            // 遗愿开关
            + DBConstants107dPro.DEVICE_FIELD_LWT_ENABLE + " TEXT,"
            // 遗愿主题
            + DBConstants107dPro.DEVICE_FIELD_LWT_TOPIC + " TEXT,"
            // 发布主题
            + DBConstants107dPro.DEVICE_FIELD_TOPIC_PUBLISH + " TEXT,"
            // 订阅主题
            + DBConstants107dPro.DEVICE_FIELD_TOPIC_SUBSCRIBE + " TEXT,"
            // 设备类型
            + DBConstants107dPro.DEVICE_FIELD_DEVICE_TYPE + " INTEGER);";

}
