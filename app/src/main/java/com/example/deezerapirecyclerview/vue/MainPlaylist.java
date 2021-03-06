package com.example.deezerapirecyclerview.vue;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deezerapirecyclerview.modele.DeezerAPI;
import com.example.deezerapirecyclerview.R;
import com.example.deezerapirecyclerview.controller.Constante;
import com.example.deezerapirecyclerview.modele.Music;
import com.example.deezerapirecyclerview.modele.PlaylistWMusic;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPlaylist extends AppCompatActivity {

    private static final String BASE_URL = "https://api.deezer.com/";
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private String id;
    private String titre;
    private TextView pTitre;
    private ImageButton retour;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ListAdapterPlaylist mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        Intent intent = getIntent();
        this.titre = intent.getStringExtra("Titre");
        this.id = intent.getStringExtra("Id");
        Log.i("MyLog","Id : "+id+"Title : "+ titre);
        pTitre = (TextView) findViewById(R.id.txtView);
        pTitre.setText(titre);
        retour = (ImageButton) findViewById((R.id.returnButton));

        sharedPreferences = getSharedPreferences("AppDeezer", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();

        retour.setOnClickListener(e->{
            finish();
            makeAPICall();
        });

        makeAPICall();
    }

    private void makeAPICall(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        DeezerAPI deezerAPI = retrofit.create(DeezerAPI.class);

        Call<PlaylistWMusic> call = deezerAPI.listRepos(this.id);
        call.enqueue(new Callback<PlaylistWMusic>() {
            @Override
            public void onResponse(Call<PlaylistWMusic> call, Response<PlaylistWMusic> response) {
                if(response.isSuccessful() && response.body()!=null){
                    Log.i("MyLog","myURL"+response.body());
                    List<Music> listMusic= response.body().getData();
                    saveList(listMusic);
                    showList(listMusic);
                }
                else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<PlaylistWMusic> call, Throwable t) {
                showError();
            }
        });
    }

    private void showList(List<Music> listMusic) {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);

        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapterPlaylist(listMusic, new ListAdapterPlaylist.OnItemClickListener() {
            @Override
            public void onItemClick(Music item) {
                navigateToMusic(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    public void navigateToMusic(Music item){
        Intent intent = new Intent(getApplicationContext(),MainMusic.class);
        intent.putExtra("Titre",item.getTitle());
        intent.putExtra("Album", item.getAlbumString());
        intent.putExtra("Artist", item.getArtistString());
        intent.putExtra("Duration", item.getDuration()+"");
        intent.putExtra("Image", item.getAlbum().getCover());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
    }

    private void saveList(List<Music> listMusic) {
        String jsonString = gson.toJson(listMusic);
        sharedPreferences
                .edit()
                .putString(Constante.KEY_DEEZER_LIST2,jsonString)
                .apply();
        //Toast.makeText(getApplicationContext(),"Saved list",Toast.LENGTH_LONG).show();
    }

    private void showError() {
        //Toast.makeText(getApplicationContext(),"download from cache",Toast.LENGTH_LONG).show();
        List<Music> listMusic = getDataFromCache();
        if (listMusic != null){
            showList(listMusic);
        } //else Toast.makeText(getApplicationContext(),"no cache to download, unable to reach API online",Toast.LENGTH_LONG).show();
    }

    private List<Music> getDataFromCache() {
        String listMusic = sharedPreferences.getString(Constante.KEY_DEEZER_LIST2,null);
        if (listMusic==null) return null;
        Type listType = new TypeToken<List<Music>>(){}.getType();
        return gson.fromJson(listMusic, listType);
    }



}
