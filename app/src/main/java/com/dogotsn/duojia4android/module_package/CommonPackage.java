package com.dogotsn.duojia4android.module_package;/*
 * @Author: ""
 * @Date: 2021-02-18 19:42:26
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2021-02-18 19:43:35
 * @Description: RN包
 */

import androidx.annotation.NonNull;

import com.dogotsn.duojia4android.MainApplication;
import com.dogotsn.duojia4android.module.CommonNativeModule;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

public class CommonPackage implements ReactPackage {

    @NonNull
    @Override
    public List<NativeModule> createNativeModules(@Nonnull ReactApplicationContext reactContext) {
        MainApplication.getInstance().setReactContext(reactContext);
        List<NativeModule> modules = new ArrayList<>();
        // 将我们创建NativeModule添加进原生模块列表中
        modules.add(new CommonNativeModule(reactContext));
        return modules;
    }

    @NonNull
    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        // 该处后期RN调用原生控件或自定义组件时可用到
        return Collections.emptyList();
    }
}
