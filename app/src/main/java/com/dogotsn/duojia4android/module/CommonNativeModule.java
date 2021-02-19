package com.dogotsn.duojia4android.module;/*
 * rn和native的桥接函数
 * @Author: ""
 * @Date: 2021-02-18 19:35:51
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2021-02-18 19:41:46
 * @Description: rn和native相互通信
 */


import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.dogotsn.duojia4android.MainApplication;
import com.dogotsn.duojia4android.util.RNEventEmitter;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class CommonNativeModule extends ReactContextBaseJavaModule {
    public static final String REACT_NATIVE_CLASSNAME = "CommonNativeModule";
    private ReactApplicationContext mContext;
    public static final String TAG = "TAG";

    public CommonNativeModule(ReactApplicationContext mContext) {
        super(mContext);
         if(mContext == null){
             this.mContext = getReactApplicationContext();
         } else {
             this.mContext = mContext;
         }
    }

    /**
     * 模块名
     * @return
     */
    @NonNull
    @Override
    public String getName() {
        return REACT_NATIVE_CLASSNAME;
    }

    @ReactMethod
    public void onToastNative(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * RN 打开Native页面
     * @param name
     * @param params
     */
    @ReactMethod
    public void startNativePage(String name, String params) {
        try {
            Activity activity = getCurrentActivity();
            if (activity != null) {
                Class toClass = Class.forName(name);
                Intent intent = new Intent(activity, toClass);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("params", params);
                activity.startActivity(intent);
            }
        } catch (Exception ex) {
            throw new JSApplicationIllegalArgumentException("不能打开原生页面: " + ex.getMessage());
        }
    }

    /**
     * Native 调用 RN 通过发送事件实现
     * @param eventName
     * @param msg
     */
    @ReactMethod
    public void emitNativeEvent(String eventName, String msg){
        RNEventEmitter.init(MainApplication.getInstance().getReactContext());
        RNEventEmitter.sendEventToRn(eventName, msg);
    }

}