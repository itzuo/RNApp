package com.zxj.myandroidtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.et);
    }

    public void onSkipClick(View view) {
        startActivity(new Intent(this,ReactPageActivity.class));
        /*Intent intent = new Intent(this, RNPageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("pageIndex",et.getText().toString().trim());
        intent.putExtras(bundle);
        startActivity(intent);*/
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
