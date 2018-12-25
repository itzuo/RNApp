package com.zxj.myandroidtest;

import android.graphics.Color;
import android.text.TextUtils;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.zxj.myandroidtest.view.FocusedTextView;

public class FocusedTextViewManager extends SimpleViewManager<FocusedTextView> {
    public static final String REACT_CLASS = "FocusedTextView";
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected FocusedTextView createViewInstance(ThemedReactContext reactContext) {
        FocusedTextView focusedTextView = new FocusedTextView(reactContext);
//        focusedTextView.setText("");
        focusedTextView.setTextColor(Color.BLUE);
        focusedTextView.setSingleLine(true);
        focusedTextView.setEllipsize(TextUtils.TruncateAt.MARQUEE);

        return focusedTextView;
    }

    @ReactProp(name="text")
    public void setText(FocusedTextView view,String text){
        view.setText(text);
    }

    @ReactProp(name="size")
    public void setTextSize(FocusedTextView view,float size){
        view.setTextSize(size);
    }
}
