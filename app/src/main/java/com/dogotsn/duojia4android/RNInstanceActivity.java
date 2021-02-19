package com.dogotsn.duojia4android;
/*
 * @Author: ""
 * @Date: 2021-02-18 17:25:19
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2021-02-18 21:17:03
 * @Description: 
 */

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.devsupport.DoubleTapReloadRecognizer;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;


public class RNInstanceActivity extends AppCompatActivity  implements DefaultHardwareBackBtnHandler {
    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;
    private static String moduleName = MainApplication.RNComponentName;
    private DoubleTapReloadRecognizer mDoubleTapReloadRecognizer;
    private boolean mDeveloperSupport = true;


    /**
     * 打开RN容器页面
     * @param activity
     * @param modName
     * @param bundle
     */
    public static void start(Activity activity, String modName, Bundle bundle){
        if(!TextUtils.isEmpty(modName)){
            moduleName = modName;
        }
        Intent intent = new Intent(activity, RNInstanceActivity.class);
        intent.putExtra("bundle", bundle);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        mReactRootView = new ReactRootView(this);
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setCurrentActivity(this)
                .setBundleAssetName("index.android.bundle")
                .setJSMainModulePath("index")
                .addPackage(new MainReactPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
        mReactRootView.startReactApplication(mReactInstanceManager, moduleName, bundle);
        mDoubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
        setContentView(mReactRootView);
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(this, this);
        }
    }

    @Override
    public void onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostDestroy(this);
        }
        if (mReactRootView != null) {
            mReactRootView.unmountReactApplication();
        }
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (getUseDeveloperSupport()) {
            if (keyCode == KeyEvent.KEYCODE_MENU) {
                mReactInstanceManager.showDevOptionsDialog();
                return true;
            }
            boolean didDoubleTapR = Assertions.assertNotNull(mDoubleTapReloadRecognizer).didDoubleTapR(keyCode, getCurrentFocus());
            if (didDoubleTapR) {
                mReactInstanceManager.getDevSupportManager().handleReloadJS();
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    private boolean getUseDeveloperSupport() {
        return mReactInstanceManager!= null && mDeveloperSupport;
    }
}