package com.zxj.myandroidtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et;
    private RNActivity rnActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.et);
        ReactNativePreLoader.initLoad(this,"App");
        rnActivity = new RNActivity();
    }

    public void onSkipClick(View view) {
        startActivity(new Intent(this,ReactPageActivity.class));
        /*Intent intent = new Intent(this, RNPageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("pageIndex",et.getText().toString().trim());
        intent.putExtras(bundle);
        startActivity(intent);*/
    }

    public void onSkipRnClick(View view) {
        startActivity(new Intent(this,rnActivity.getClass()));
    }

    public void onNavigationClick(View view){
        Intent intent = new Intent(this, RNPageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("pageIndex",et.getText().toString().trim());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onSkipRNClick(View view){
        startActivity(new Intent(this,TwoActivity.class));
    }
}
