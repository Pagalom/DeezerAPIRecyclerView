package com.example.deezerapirecyclerview;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DeezerAPI {
    @GET("/user/18732266/playlists")
    Call<ListPlaylist> getDeezerUserResponse();

    @GET("playlist/{value}")
    Call<PlaylistWMusic> listRepos(@Path("value") String user);
}
