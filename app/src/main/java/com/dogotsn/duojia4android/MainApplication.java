/*
 * @Author: 
 * @Date: 2021-02-18 15:58:03
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2021-02-18 19:56:15
 * @Description: 
 */
package com.dogotsn.duojia4android;


import android.app.Application;
import android.content.Context;

import com.dogotsn.duojia4android.module_package.CommonPackage;
import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.soloader.SoLoader;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {
    // RN组件名
    public static final String RNComponentName = "duojia_app";

    private ReactApplicationContext reactContext;
    private static MainApplication mInstance = null;

    public static MainApplication getInstance() {
        return mInstance;
    }

    /**
     * 设置 ReactApplicationContext
     */
    public void setReactContext(ReactApplicationContext reactContext) {
        this.reactContext = reactContext;
    }

    /**
     * 获取 ReactApplicationContext
     */
    public ReactApplicationContext getReactContext() {
        return this.reactContext;
    }

    private final ReactNativeHost mReactNativeHost = 
        new ReactNativeHost(this) {
            @Override
            public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
            }

            @Override
            protected List<ReactPackage> getPackages() {
                @SuppressWarnings("UnnecessaryLocalVariable")
                List<ReactPackage> packages = new PackageList(this).getPackages();
                // Packages that cannot be autolinked yet can be added manually here, for example:
                // packages.add(new MyReactNativePackage());
                packages.add(new CommonPackage());
                return packages;
            }

            @Override
            protected String getJSMainModuleName() {
            return "index";
            }
        };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        SoLoader.init(this, /* native exopackage */ false);
        initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
    }

    /**
     * Loads Flipper in React Native templates. Call this in the onCreate method with something like
     * initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
     *
     * @param context
     * @param reactInstanceManager
     */
    private static void initializeFlipper(
        Context context, ReactInstanceManager reactInstanceManager) {
        if (BuildConfig.DEBUG) {
        try {
            /*
            We use reflection here to pick up the class that initializes Flipper,
            since Flipper library is not available in release mode
            */
            Class<?> aClass = Class.forName("com.duojiarnii.ReactNativeFlipper");
            aClass
                .getMethod("initializeFlipper", Context.class, ReactInstanceManager.class)
                .invoke(null, context, reactInstanceManager);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        }
    }
}