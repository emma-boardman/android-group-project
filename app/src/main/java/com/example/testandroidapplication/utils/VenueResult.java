package com.example.testandroidapplication.utils;

import com.example.testandroidapplication.objects.Venue;

public class VenueResult {

    private final boolean success;
    private final Venue venue;

    public boolean isSuccess() {
        return success;
    }

    public Venue getVenue() {
        return venue;
    }

    private VenueResult(Venue venue, boolean success) {
        this.venue = venue;
        this.success = success;
    }

    public static VenueResult success(Venue venue){
        return new VenueResult(venue, true);
    }

    public static VenueResult failure(){
        return new VenueResult(null, false);
    }
}
