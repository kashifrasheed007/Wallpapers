package com.kashif.wallpapers;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class WallpaperRVAdapter extends RecyclerView.Adapter<WallpaperRVAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> arrayListWallpaper;


    public WallpaperRVAdapter(Context context, ArrayList<String> arrayListWallpaper) {
        this.context = context;
        this.arrayListWallpaper = arrayListWallpaper;

    }

    @NonNull
    @Override
    public WallpaperRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wallpaper_items, parent, false);
        WallpaperRVAdapter.ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(arrayListWallpaper.get(position))
                .into(holder.wallpaperImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, setWallpaperActivity.class);
                intent.putExtra("imageUrl",arrayListWallpaper.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListWallpaper.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView wallpaperImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wallpaperImageView = (ImageView) itemView.findViewById(R.id.wallpaperImageView);
        }
    }
}
