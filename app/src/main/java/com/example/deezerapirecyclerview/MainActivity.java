package com.example.deezerapirecyclerview;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    private static final String BASE_URL = "https://api.deezer.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("AppDeezer", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();

        makeAPICall();
    }

    private void showList(List<Playlist> listPlaylist) {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(listPlaylist);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeAPICall(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        DeezerAPI deezerAPI = retrofit.create(DeezerAPI.class);

        Call<ListPlaylist> call = deezerAPI.getDeezerUserResponse();
        call.enqueue(new Callback<ListPlaylist>() {
            @Override
            public void onResponse(Call<ListPlaylist> call, Response<ListPlaylist> response) {
                if(response.isSuccessful() && response.body()!=null){
                    List<Playlist> listPlaylist= response.body().getData();
                    saveList(listPlaylist);
                    showList(listPlaylist);
                }
                else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<ListPlaylist> call, Throwable t) {
                showError();
            }
        });
    }

    private void saveList(List<Playlist> listPlaylist) {
        String jsonString = gson.toJson(listPlaylist);
        sharedPreferences
                .edit()
                .putString(Constante.KEY_DEEZER_LIST,jsonString)
                .apply();
        Toast.makeText(getApplicationContext(),"Saved list",Toast.LENGTH_LONG).show();

    }

    private void showError() {
        Toast.makeText(getApplicationContext(),"download from cache",Toast.LENGTH_LONG).show();
        List<Playlist> listPlaylist = getDataFromCache();
        if (listPlaylist != null){
            showList(listPlaylist);
        } else Toast.makeText(getApplicationContext(),"no cache to download, unable to reach API online",Toast.LENGTH_LONG).show();
    }

    private List<Playlist> getDataFromCache() {
        String listPlaylist = sharedPreferences.getString(Constante.KEY_DEEZER_LIST,null);
        if (listPlaylist==null) return null;
        Type listType = new TypeToken<List<Playlist>>(){}.getType();
        return gson.fromJson(listPlaylist, listType);

    }
}