package com.example.wallpaperapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

public class DashboardActivity extends AppCompatActivity {

    private static final int[] IMAGE_IDS = {
            R.drawable.placeholder,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4
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
            if (id == R.id.nav_home) {
                // Handle the home action
            } else if (id == R.id.nav_share) {
                // Handle the share action
            } else if (id == R.id.nav_about) {
                // Handle the about action
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }
}
