package com.zxj.myandroidtest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.Callback;
import com.facebook.react.devsupport.DoubleTapReloadRecognizer;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.PermissionListener;

/**
 * 处理预加载代理类
 */
public class PreLoadReactDelegate {

    private final Activity mActivity;
    private ReactRootView mReactRootView;
    private Callback mPermissionsCallback;
    private final String mMainComponentName;
    private PermissionListener mPermissionListener;
    private final int REQUEST_OVERLAY_PERMISSION_CODE = 0x8000;
    private DoubleTapReloadRecognizer mDoubleTapReloadRecognizer;

    public PreLoadReactDelegate(Activity activity, @Nullable String mainComponentName) {
        this.mActivity = activity;
        this.mMainComponentName = mainComponentName;
    }

    public void onCreate(@Nullable Bundle initialProperties) {
        boolean needsOverlayPermission = false;
        if (getReactNativeHost().getUseDeveloperSupport() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Get permission to show redbox in dev builds.
            if (!Settings.canDrawOverlays(mActivity)) {
                needsOverlayPermission = true;
                Intent serviceIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + mActivity.getPackageName()));
                mActivity.startActivityForResult(serviceIntent, REQUEST_OVERLAY_PERMISSION_CODE);
            }
        }
        if (mMainComponentName != null && !needsOverlayPermission) {
            // 1.从缓存中获取RootView
            mReactRootView = ReactNativePreLoader.getReactRootView(mMainComponentName);

            if(mReactRootView == null) {
                // 2.缓存中不存在RootView,直接创建,并缓存
//                mReactRootView = ReactNativePreLoader.initLoad(mActivity,mMainComponentName,initialProperties);

                mReactRootView = new ReactRootView(mActivity);
                mReactRootView.startReactApplication(
                        getReactInstanceManager(),
                        mMainComponentName,
                        initialProperties);
            }else{
//                mReactRootView.startReactApplication(getReactInstanceManager(), mMainComponentName, initialProperties);
                mReactRootView.setAppProperties(initialProperties);
                int rootViewTag = mReactRootView.getRootViewTag();
                Log.e("PreLoadReactDelegate","rootViewTag="+rootViewTag);
            }

            //防止报错:The specified child already has a parent. You must call removeView() on the child's parent first.
            ViewGroup parent = (ViewGroup) mReactRootView.getParent();
            if (parent != null) {
                parent.removeView(mReactRootView);
            }
            // 3.将RootView设置到Activity布局
            mActivity.setContentView(mReactRootView);
        }

        mDoubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
    }

    public void onResume() {
        if (getReactNativeHost().hasInstance()) {
            getReactInstanceManager().onHostResume(mActivity, (DefaultHardwareBackBtnHandler)mActivity);
        }
        if (mPermissionsCallback != null) {
            mPermissionsCallback.invoke();
            mPermissionsCallback = null;
        }
    }

    public void onPause() {
        if (getReactNativeHost().hasInstance()) {
            getReactInstanceManager().onHostPause(mActivity);
        }
    }

    public void onDestroy() {

        if (mReactRootView != null) {
            mReactRootView.unmountReactApplication();
            mReactRootView = null;
        }
        if (getReactNativeHost().hasInstance()) {
            getReactInstanceManager().onHostDestroy(mActivity);
        }

        // 清除View
        ReactNativePreLoader.onDestroy(mMainComponentName);
    }

    public boolean onNewIntent(Intent intent) {
        if (getReactNativeHost().hasInstance()) {
            getReactInstanceManager().onNewIntent(intent);
            return true;
        }
        return false;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (getReactNativeHost().hasInstance()) {
            getReactInstanceManager().onActivityResult(mActivity, requestCode, resultCode, data);
        } else {
            // Did we request overlay permissions?
            if (requestCode == REQUEST_OVERLAY_PERMISSION_CODE && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Settings.canDrawOverlays(mActivity)) {
                    if (mMainComponentName != null) {
                        if (mReactRootView != null) {
                            throw new IllegalStateException("Cannot loadApp while app is already running.");
                        }
                        mReactRootView = new ReactRootView(mActivity);
                        mReactRootView.startReactApplication(
                                getReactInstanceManager(),
                                mMainComponentName,
                                null);
                        mActivity.setContentView(mReactRootView);
                    }
                }
            }
        }
    }

    public boolean onBackPressed() {
        if (getReactNativeHost().hasInstance()) {
            getReactInstanceManager().onBackPressed();
            return true;
        }
        return false;
    }

    public boolean onRNKeyUp(int keyCode) {
        if (getReactNativeHost().hasInstance() && getReactNativeHost().getUseDeveloperSupport()) {
            if (keyCode == KeyEvent.KEYCODE_MENU) {
                getReactInstanceManager().showDevOptionsDialog();
                return true;
            }
            boolean didDoubleTapR = Assertions.assertNotNull(mDoubleTapReloadRecognizer)
                    .didDoubleTapR(keyCode, mActivity.getCurrentFocus());
            if (didDoubleTapR) {
                getReactInstanceManager().getDevSupportManager().handleReloadJS();
                return true;
            }
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requestPermissions(String[] permissions, int requestCode, PermissionListener listener) {
        mPermissionListener = listener;
        mActivity.requestPermissions(permissions, requestCode);
    }

    public void onRequestPermissionsResult(final int requestCode, final String[] permissions, final int[] grantResults) {
        mPermissionsCallback = new Callback() {
            @Override
            public void invoke(Object... args) {
                if (mPermissionListener != null && mPermissionListener.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
                    mPermissionListener = null;
                }
            }
        };
    }

    /**
     * 获取 Application中 ReactNativeHost
     * @return
     */
    private ReactNativeHost getReactNativeHost() {
        return MainApplication.getInstance().getReactNativeHost();
    }

    /**
     * 获取 ReactInstanceManager
     * @return
     */
    private ReactInstanceManager getReactInstanceManager() {
        return getReactNativeHost().getReactInstanceManager();
    }
}

