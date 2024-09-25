package com.example.wallpaperapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

public class DashboardActivity extends AppCompatActivity {

    private static final int[] IMAGE_IDS = {
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image8,
            R.drawable.image9,
            R.drawable.image10,
            R.drawable.image11,
            R.drawable.image12,
            R.drawable.image13,
            R.drawable.image14,
            R.drawable.image15,
            R.drawable.image16,
            R.drawable.image17,
            R.drawable.image18,
            R.drawable.image19
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Remove the default app title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Add the hamburger icon and set up the drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, com.google.android.material.R.string.side_sheet_behavior, com.google.android.material.R.string.side_sheet_behavior);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Customize the toolbar title
        TextView titleTextView = new TextView(this);
        titleTextView.setText("Guru Ram Das Ji Wallpaper");
        titleTextView.setTextSize(20);
        titleTextView.setTextColor(Color.parseColor("#FFD700"));  // Set the text color to gold
        titleTextView.setTypeface(titleTextView.getTypeface());

        // Center the title in the toolbar
        Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(
                Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        titleTextView.setLayoutParams(layoutParams);
        toolbar.addView(titleTextView);

        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Pass the context to the adapter
        ImageAdapter adapter = new ImageAdapter(IMAGE_IDS, this);
        recyclerView.setAdapter(adapter);

        // Handle navigation item selection
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_share) {
                // Call the shareApp() method to trigger the share intent
                shareApp();
            } else if (id == R.id.nav_rate) {
                // Handle the rate app action
            } else if (id == R.id.nav_about) {
                // Handle the about us action
            } else if (id == R.id.nav_exit) {
                // Show exit confirmation dialog
                showExitConfirmationDialog();
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void shareApp() {
        String shareMessage = "Check out this amazing app: Guru Ram Das Ji Wallpaper App Download it here: [App Link from Play Store]";

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this app");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);

        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }


    private void showExitConfirmationDialog() {
        // Create an AlertDialog to ask for exit confirmation
        new AlertDialog.Builder(this)
                .setTitle("Exit App")
                .setMessage("Do you really want to exit?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    // Close the app
                    finish();
                })
                .setNegativeButton("No", (dialog, which) -> {
                    // Dismiss the dialog
                    dialog.dismiss();
                })
                .show();
    }
}
