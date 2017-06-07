package com.kekenet.vectordemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.anim:
                startActivity(this, MainActivity.class);
                break;
            case R.id.sign:
                startActivity(this, SignatureActivity.class);
                break;
        }
    }


    public static void startActivity(Context context, Class<? extends Activity> clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }
}
