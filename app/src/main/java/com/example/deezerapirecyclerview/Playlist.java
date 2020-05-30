package com.example.deezerapirecyclerview;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private int duration,nb_tracks;
    private String id,title,link,picture,picture_small,picture_medium,picture_big,picture_xl,tracklist,type;
    private boolean is_loved_track;
    private Utilisateur creator;
    //private boolean public on a un soucis avec le public


    public String getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public int getNb_tracks() {
        return nb_tracks;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getPicture() {
        return picture;
    }

    public String getPicture_small() {
        return picture_small;
    }

    public String getPicture_medium() {
        return picture_medium;
    }

    public String getPicture_big() {
        return picture_big;
    }

    public String getPicture_xl() {
        return picture_xl;
    }

    public String getTracklist() {
        return tracklist;
    }

    public String getType() {
        return type;
    }

    public boolean isIs_loved_track() {
        return is_loved_track;
    }

    public Utilisateur getCreator() {
        return creator;
    }
}
