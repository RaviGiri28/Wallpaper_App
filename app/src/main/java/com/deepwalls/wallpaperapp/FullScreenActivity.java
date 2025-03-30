package com.deepwalls.wallpaperapp;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FullScreenActivity extends AppCompatActivity {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private static final long REDIRECT_DELAY = 500; // 500 ms delay for smooth transition

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        // Get the image ID from the intent
        Intent intent = getIntent();
        int imageResId = intent.getIntExtra("imageResId", 0);

        // Use Glide to load the image into ImageView
        ImageView fullScreenImageView = findViewById(R.id.full_screen_image_view);
        Glide.with(this)
                .load(imageResId)
                .into(fullScreenImageView);

        // Back button functionality
        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> finish());

        // Apply Now button functionality
        findViewById(R.id.apply_button).setOnClickListener(v -> {
            // Show loading dialog
            AlertDialog loadingDialog = showLoadingDialog();

            // Set the wallpaper in the background
            executorService.execute(() -> setWallpaper(fullScreenImageView, loadingDialog));
        });
    }


    private AlertDialog showLoadingDialog() {
        // Create and show an AlertDialog with a ProgressBar (loading spinner)
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setView(R.layout.dialog_loading); // A custom layout for the loading dialog

        AlertDialog dialog = builder.create();
        dialog.show();
        return dialog;
    }


    private void setWallpaper(ImageView imageView, AlertDialog loadingDialog) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            wallpaperManager.setBitmap(bitmap);

            // Update UI in the main thread
            runOnUiThread(() -> {
                // Hide loading dialog
                loadingDialog.dismiss();

                // Show success message
                Toast.makeText(this, "Wallpaper set successfully!", Toast.LENGTH_SHORT).show();

                // Delay before going to the home screen for a smoother transition
                new Handler(Looper.getMainLooper()).postDelayed(this::goToHomeScreen, REDIRECT_DELAY);
            });
        } catch (IOException e) {
            // Update UI in the main thread
            runOnUiThread(() -> {
                // Hide loading dialog
                loadingDialog.dismiss();

                // Show failure message
                Toast.makeText(this, "Failed to set wallpaper", Toast.LENGTH_SHORT).show();
            });
            e.printStackTrace();
        }
    }

    private void goToHomeScreen() {
        // Intent to navigate to the home screen
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(homeIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}
