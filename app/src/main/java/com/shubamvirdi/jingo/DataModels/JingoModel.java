package com.shubamvirdi.jingo.DataModels;

import com.google.gson.annotations.SerializedName;

// JAVA class to get the data from API

public class JingoModel {

    @SerializedName("song")
    private String song;

    @SerializedName("url")
    private String url;

    @SerializedName("artists")
    private String artists;

    @SerializedName("cover_image")
    private String cover_image;


    public JingoModel(String song, String url, String artists, String cover_image) {
        this.song = song;
        this.url = url;
        this.artists = artists;
        this.cover_image = cover_image;
    }


    public JingoModel() {
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }
}
