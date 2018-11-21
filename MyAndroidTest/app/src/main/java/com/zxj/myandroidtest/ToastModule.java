package com.zxj.myandroidtest;

import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.IllegalViewOperationException;

import java.util.HashMap;
import java.util.Map;

public class ToastModule  extends ReactContextBaseJavaModule {
    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY = "LONG";

    public ToastModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "ToastExample";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
        constants.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);
        return constants;
    }

    @ReactMethod
    public void show(String message, int duration) {
        Toast.makeText(getReactApplicationContext(), message, duration).show();
    }

    /**
     * 这边只是演示相关回调方法的使用,所以这边的使用方法是非常简单的
     * @param errorCallback       数据错误回调函数
     * @param successCallback     数据成功回调函数
     */
    @ReactMethod
    public void measureLayout(Callback errorCallback,
                              Callback successCallback){
        try {
            successCallback.invoke(100, 100, 200, 200);
        } catch (IllegalViewOperationException e) {
            errorCallback.invoke(e.getMessage());
        }
    }
}
