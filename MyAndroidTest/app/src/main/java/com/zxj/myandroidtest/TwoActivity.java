package com.zxj.myandroidtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;


public class TwoActivity extends AppCompatActivity {

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
}
