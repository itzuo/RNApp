package com.zxj.myandroidtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;

public class RNActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler,PermissionAwareActivity {
    private PreLoadReactDelegate mPreLoadReactDelegate;

    public RNActivity(){
        mPreLoadReactDelegate = new PreLoadReactDelegate(this,getMainComponentName());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
//        bundle.putString("pageIndex","Page1");
        mPreLoadReactDelegate.onCreate(bundle);
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPreLoadReactDelegate.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPreLoadReactDelegate.onResume();
    }

    @Override
    public void onBackPressed() {
        mPreLoadReactDelegate.onBackPressed();
    }

    @Override
    public void onNewIntent(Intent intent) {
        if(!mPreLoadReactDelegate.onNewIntent(intent)) {
            super.onNewIntent(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPreLoadReactDelegate.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return mPreLoadReactDelegate.onRNKeyUp(keyCode) || super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPreLoadReactDelegate.onDestroy();
    }

    /**
     * 处理权限授权
     * @param permissions
     * @param requestCode
     * @param listener
     */
    @Override
    public void requestPermissions(String[] permissions, int requestCode, PermissionListener listener) {
        mPreLoadReactDelegate.requestPermissions(permissions,requestCode,listener);
    }


    /**
     * 授权结果
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(final int requestCode, final String[] permissions, final int[] grantResults) {
        mPreLoadReactDelegate.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    public String getMainComponentName() {
        return "App";
    }
}