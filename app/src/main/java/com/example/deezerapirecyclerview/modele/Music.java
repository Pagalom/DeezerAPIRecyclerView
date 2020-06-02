package com.example.deezerapirecyclerview.modele;

public class Music {
    private int id,duration;
    private String title,type;
    private Artiste artist;
    private Album album;

    public int getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public Artiste getArtist() {
        return artist;
    }

    public String getArtistString() {
        return artist.getName();
    }

    public Album getAlbum() {
        return album;
    }
    public String getAlbumString() {
        return album.getTitle();
    }
}
