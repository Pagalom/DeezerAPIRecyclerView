package com.example.deezerapirecyclerview;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DeezerAPI {
    @GET("/user/18732266/playlists")
    Call<ListPlaylist> getDeezerUserResponse();

    /*@GET("/playlist")
    Call<ListPlaylist> getDeezerPlaylistResponse();*/
}
