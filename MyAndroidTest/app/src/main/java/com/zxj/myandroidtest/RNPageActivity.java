package com.zxj.myandroidtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;

public class RNPageActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {
    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReactInstanceManager = ((MainApplication)getApplication()).getReactNativeHost().getReactInstanceManager();

        mReactRootView = new ReactRootView(this);
        /*mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName("index.android.bundle")
                .setJSMainModulePath("index")
                .addPackage(new MainReactPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();*/
        // 这个"RNApp"名字一定要和我们在index.js中注册的名字保持一致AppRegistry.registerComponent()
        mReactRootView.startReactApplication(mReactInstanceManager, "RNApp", null);

        setContentView(mReactRootView);
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        if (getUseDeveloperSupport()) {
            if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {//Ctrl + M 打开RN开发者菜单
                mReactInstanceManager.showDevOptionsDialog();
                return true;
            }
           /* boolean didDoubleTapR = Assertions.assertNotNull(mDoubleTapReloadRecognizer).didDoubleTapR(keyCode, getCurrentFocus());
            if (didDoubleTapR) {//双击R 重新加载JS
                mReactInstanceManager.getDevSupportManager().handleReloadJS();
                return true;
            }*/
//        }
        return super.onKeyUp(keyCode, event);
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
}