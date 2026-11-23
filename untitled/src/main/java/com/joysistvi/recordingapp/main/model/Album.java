package com.joysistvi.recordingapp.main.model;

public class Album {
    private int id;
    private String name;
    private int year;
    private int artistId;
    private String artistName;
    private boolean archived;

    public Album() {
    }

    public Album(int id, String name, int year, int artistId, String artistName) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.artistId = artistId;
        this.artistName = artistName;

    }

    public Album(String name, int year, int artistId) {
        this.name = name;
        this.year = year;
        this.artistId = artistId;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}