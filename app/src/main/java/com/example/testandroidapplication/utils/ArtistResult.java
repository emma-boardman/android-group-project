package com.example.testandroidapplication.utils;

import com.example.testandroidapplication.objects.Artist;

public class ArtistResult {

    private final boolean success;
    private final Artist artist;

    public boolean isSuccess() {
        return success;
    }

    public Artist getArtist() {
        return artist;
    }

    private ArtistResult(Artist artist, boolean success) {
        this.artist = artist;
        this.success = success;
    }

    public static ArtistResult success(Artist artist){
        return new ArtistResult(artist, true);
    }

    public static ArtistResult failure(){
        return new ArtistResult(null, false);
    }
}
