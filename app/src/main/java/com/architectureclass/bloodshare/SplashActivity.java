package com.architectureclass.bloodshare;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Handler handler = new Handler();
        final Context context = this;

        final ImageView logo = findViewById(R.id.logo);
        logo.startAnimation(AnimationUtils.loadAnimation(context, R.anim.splash_in));
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                logo.startAnimation(AnimationUtils.loadAnimation(context, R.anim.splash_out));
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 1500);
            }
        }, 3000);
    }
}
