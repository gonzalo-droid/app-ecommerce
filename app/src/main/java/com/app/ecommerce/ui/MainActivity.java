package com.app.ecommerce.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.ecommerce.R;

public class MainActivity extends AppCompatActivity {

    Animation topSplash, bottomSplash;
    ImageView logApp;
    TextView txtAppName, txtWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start in FullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Hooks
        logApp = findViewById(R.id.logApp);
        txtAppName = findViewById(R.id.txtAppName);
        txtWelcome = findViewById(R.id.txtWelcome);

        // Animations
        topSplash = AnimationUtils.loadAnimation(this, R.anim.top_splash);
        bottomSplash = AnimationUtils.loadAnimation(this, R.anim.bottom_splash);

        logApp.setAnimation(topSplash);
        txtAppName.setAnimation(bottomSplash);
        txtWelcome.setAnimation(bottomSplash);

        // Delay for the Activity
        new android.os.Handler().postDelayed( new Runnable() {
                public void run() {
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish(); // Test this
                }
            }, 5000);

    }
}