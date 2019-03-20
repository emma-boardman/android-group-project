package com.example.testandroidapplication.helper;

import android.util.Log;

import com.example.testandroidapplication.objects.Artist;
import com.example.testandroidapplication.objects.Venue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class Validator {

    public boolean isVenueObjectValid(Venue venue) {
           return true;
    }

    public JSONObject artistNullToString(JSONObject artist){
        Iterator<String> keys = artist.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            try {
                Object value = artist.get(key);
                Log.i("value: ", value.toString());
                if (value.toString() == "null"){
                    artist.put(key, "not populated");
                }

            } catch (JSONException e) {
                // Something went wrong!
            }
        }

      return artist;
    }

}
