package com.example.deezerapirecyclerview;


import java.util.List;

public class PlaylistWMusic {
    private int id,duration,nbTracks,fans;
    private String title,description,link,share,picture,picture_small,picture_medium,picture_big,picture_xl,tracklist,type;
    private Utilisateur creator;
    private boolean is_loved_track,collaborative;
    private Morceaux tracks;

    public List<Music> getData() {
        return tracks.getData();
    }

    public int getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public int getNbTracks() {
        return nbTracks;
    }

    public int getFans() {
        return fans;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getShare() {
        return share;
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

    public Utilisateur getCreator() {
        return creator;
    }

    public boolean isIs_loved_track() {
        return is_loved_track;
    }

    public boolean isCollaborative() {
        return collaborative;
    }

    public Morceaux getTracks() {
        return tracks;
    }
}
