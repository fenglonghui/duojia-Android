/*
 * @Author: 
 * @Date: 2021-02-18 20:23:51
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2021-02-18 20:40:58
 * @Description: 
 */
package com.dogotsn.duojia4android.util;

import android.util.Log;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.modules.core.DeviceEventManagerModule;


public class RNEventEmitter {
    private static final String TAG = "RNEventEmitter";
    private static ReactApplicationContext mReactContext;

    public static void init(ReactApplicationContext mContext){
        mReactContext = mContext;
    }

    public static void sendEventToRn(String eventName, Object msg) {
        if (mReactContext == null) {
            Log.e(TAG, "ReactContext is null");
            return;
        }
        mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, msg);
    }

}