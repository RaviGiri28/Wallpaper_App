package com.example.wallpaperapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Duration for splash screen (e.g., 3 seconds)
        int SPLASH_DISPLAY_LENGTH = 3000;

        new Handler().postDelayed(() -> {
            // Start MainActivity after splash screen
            Intent mainIntent = new Intent(MainActivity.this, DashboardActivity.class);
            startActivity(mainIntent);
            finish(); // Close SplashActivity
        }, SPLASH_DISPLAY_LENGTH);
    }
}
