package com.example.testandroidapplication.utils;

import android.util.Log;

import com.example.testandroidapplication.objects.Venue;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class Validator {

    public boolean isVenueObjectValid(Venue venue) {
           return true;
    }

    public JSONObject objectNullToString(JSONObject object){
        Iterator<String> keys = object.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            try {
                Object value = object.get(key);
                Log.i("value: ", value.toString());
                if (value.toString() == "null"){
                    object.put(key, "not populated");
                }

            } catch (JSONException e) {
                // Something went wrong!
            }
        }

      return object;
    }

}
