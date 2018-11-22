package com.zxj.myandroidtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.TextView;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;


public class TwoActivity extends AppCompatActivity  implements DefaultHardwareBackBtnHandler {

    private TextView mTv;
    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        mTv = findViewById(R.id.tv);

        String params = getIntent().getStringExtra("params");
        mTv.setText(TextUtils.isEmpty(params)?"这是原生文本控件":params);

        mReactInstanceManager = ((MainApplication)getApplication()).getReactNativeHost().getReactInstanceManager();

        mReactRootView = new ReactRootView(this);
        mReactRootView =findViewById(R.id.test_js);

//        这里的ReactNativeView对应index.android.js中AppRegistry.registerComponent('ReactNativeView', () => ReactNativeView)的ReactNativeView
        Bundle bundle = new Bundle();
        bundle.putString("flag","home");
        mReactRootView.startReactApplication(mReactInstanceManager, "MyTest", bundle);
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
