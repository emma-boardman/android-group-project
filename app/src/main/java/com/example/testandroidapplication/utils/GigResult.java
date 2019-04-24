package com.example.testandroidapplication.utils;

import com.example.testandroidapplication.objects.Gig;

public class GigResult {

    private final boolean success;
    private final Gig gig;

    public boolean isSuccess() {
        return success;
    }

    public Gig getGig() {
        return gig;
    }

    private GigResult(Gig gig, boolean success) {
        this.gig = gig;
        this.success = success;
    }

    public static GigResult success(Gig gig){
        return new GigResult(gig, true);
    }

    public static GigResult failure(){
        return new GigResult(null, false);
    }
}
