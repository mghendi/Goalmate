package com.suluhu.goalmate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final boolean firstRun = PreferenceManager
                .getDefaultSharedPreferences(this).getBoolean("firstRun", true);
        final Animation presto = new AlphaAnimation(0.0f, 1.0f);
        final TextView splashText = findViewById(R.id.splashText);
        presto.setDuration(1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splashText.setVisibility(View.VISIBLE);
                splashText.startAnimation(presto);
            }
        }, 1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, firstRun?SetupActivity.class:LoginActivity.class));
                finish();
            }
        }, 3000);
    }
}
