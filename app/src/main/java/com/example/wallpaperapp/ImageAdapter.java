package com.example.wallpaperapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private final int[] imageIds;
    private final Context context; // Add context to the adapter

    public ImageAdapter(int[] imageIds, Context context) {
        this.imageIds = imageIds;
        this.context = context; // Initialize context
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int imageResId = imageIds[position];

        // Use Glide to load the image efficiently
        Glide.with(context)+
                .load(imageResId)
                .placeholder(R.drawable.placeholder) // Placeholder image while loading
                .override(600, 600)
                .centerCrop()
                .into(holder.imageView);

        // Set an OnClickListener to handle image clicks
        holder.imageView.setOnClickListener(v -> {
            // Create an intent to start the FullScreenActivity
            Intent intent = new Intent(context, FullScreenActivity.class);
            // Pass the clicked image resource ID to FullScreenActivity
            intent.putExtra("imageResId", imageResId);
            context.startActivity(intent); // Start the FullScreenActivity
        });
    }

    @Override
    public int getItemCount() {
        return imageIds.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.grid_image);
        }
    }
}
