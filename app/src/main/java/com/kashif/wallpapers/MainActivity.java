package com.kashif.wallpapers;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMain;
    private WallpaperRVAdapter wallpaperRVAdapter;
    private ArrayList<String> arrayListWallpaper;

    // kbdkfVru4mFfzd18znFkGFqhLzunqI4o9Ma9u0mhrdkpyKAh5jlpEneI
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewMain = (RecyclerView) findViewById(R.id.recyclerViewMain);

        arrayListWallpaper = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerViewMain.setLayoutManager(gridLayoutManager);
        wallpaperRVAdapter = new WallpaperRVAdapter(this, arrayListWallpaper);
        recyclerViewMain.setAdapter(wallpaperRVAdapter);

        getWallpapers();

        /*searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchStr = searchView.getQuery().toString();
                if (searchStr.equals("")) {
                    Toast.makeText(MainActivity.this, "Enter Some Text...", Toast.LENGTH_SHORT).show();
                } else {
                    //getWallpapersBySearch(searchStr);
                }
            }
        });*/

    }

    /*private void getWallpapersBySearch(String searchStr) {
        arrayListWallpaper.clear();
        String url = "https://api.pexels.com/v1/search?query="+searchStr+"&per_page=80&page=1";
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray photoArray = null;
                try {
                    photoArray = response.getJSONArray("photos");
                    for (int i = 0; i<photoArray.length(); i++){
                        JSONObject photoObj = photoArray.getJSONObject(i);
                        String imageUrl = photoObj.getJSONObject("src").getString("portrait");
                        arrayListWallpaper.add(imageUrl);
                    }
                    wallpaperRVAdapter.notifyDataSetChanged();
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Failed To Load the Wallpaper", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "kbdkfVru4mFfzd18znFkGFqhLzunqI4o9Ma9u0mhrdkpyKAh5jlpEneI");
                return  headers;
            }
        };
        requestQueue.add(jsonObjectRequest);

    }*/

    private void getWallpapers(){
        arrayListWallpaper.clear();
        String url = "https://api.pexels.com/v1/search?query=random&per_page=10000&page=1";
        RequestQueue requestQueue  = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray photoArray = response.getJSONArray("photos");
                    for (int i = 0; i<photoArray.length(); i++){
                        JSONObject photoObj = photoArray.getJSONObject(i);
                        String imgUrl = photoObj.getJSONObject("src").getString("portrait");
                        arrayListWallpaper.add(imgUrl);
                    }
                    wallpaperRVAdapter.notifyDataSetChanged();
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Failed to Load the Error", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization","kbdkfVru4mFfzd18znFkGFqhLzunqI4o9Ma9u0mhrdkpyKAh5jlpEneI");
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }
}