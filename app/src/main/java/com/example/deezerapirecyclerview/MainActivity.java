package com.example.deezerapirecyclerview;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    private static final String BASE_URL = "https://api.deezer.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

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

    private void showError() {
        Toast.makeText(getApplicationContext(),"API error",Toast.LENGTH_LONG).show();
    }
}