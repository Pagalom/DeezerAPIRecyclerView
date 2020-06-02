package com.example.deezerapirecyclerview.modele;

import com.example.deezerapirecyclerview.controller.ListPlaylist;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DeezerAPI {
    @GET("/user/18732266/playlists")
    Call<ListPlaylist> getDeezerUserResponse();

    @GET("playlist/{value}")
    Call<PlaylistWMusic> listRepos(@Path("value") String user);
}
