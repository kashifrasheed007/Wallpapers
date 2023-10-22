package com.kashif.wallpapers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.shashank.sony.fancytoastlib.FancyToast;

public class setWallpaperActivity extends AppCompatActivity {

    private ImageView imageViewWallpaper;
    private ImageButton btnWall, btnDownload;
    private String imgUrl;
    private WallpaperManager wallpaperManager;
    private DownloadManager downloadManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_wallpaper);

        imageViewWallpaper = (ImageView) findViewById(R.id.wallpaperImageView);
        btnWall = (ImageButton) findViewById(R.id.btnWall);
        btnDownload = (ImageButton) findViewById(R.id.btnDownload);
        imgUrl = getIntent().getStringExtra("imageUrl");
        Glide.with(this).load(imgUrl).into(imageViewWallpaper);

        wallpaperManager = wallpaperManager.getInstance(getApplicationContext());

        btnWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(setWallpaperActivity.this)
                        .asBitmap()
                        .load(imgUrl)
                        .listener(new RequestListener<Bitmap>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Bitmap> target, boolean isFirstResource) {
                                Toast.makeText(setWallpaperActivity.this, "Failed to Load image...", Toast.LENGTH_SHORT).show();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(@NonNull Bitmap resource, @NonNull Object model, Target<Bitmap> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                                try {
                                    wallpaperManager.setBitmap(resource);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                return false;
                            }
                        }).submit();
                FancyToast.makeText(setWallpaperActivity.this, "Wallpaper Set Successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
            }
        });

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a DownloadManager.Request with the image URL
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(imgUrl));

                // Set the title and description for the download notification (optional)
                request.setTitle("Wallpaper Download");
                request.setDescription("Downloading wallpaper...");

                // Set the destination directory for the downloaded file
                request.setDestinationInExternalPublicDir(
                        Environment.DIRECTORY_DOWNLOADS,
                        "wallpaper.jpg" // You can customize the filename here
                );

                // Get a reference to the DownloadManager
                DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

                // Enqueue the download request and get its ID
                long downloadId = downloadManager.enqueue(request);

                // Display a toast message to indicate that the download has started
                FancyToast.makeText(setWallpaperActivity.this, "Downloading wallpaper...", FancyToast.LENGTH_SHORT, FancyToast.INFO, false).show();
            }
        });

    }

}