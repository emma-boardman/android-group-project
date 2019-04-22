package com.example.testandroidapplication.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.testandroidapplication.objects.Venue;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Objects;

public class Validator {

    public boolean isVenueObjectValid(Venue venue) {
           return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public JSONObject objectNullToString(JSONObject object) throws JSONException {
        Iterator<String> keys = object.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            Object value = object.get(key);
            Log.i("value: ", value.toString());
            if (Objects.equals(value.toString(), "null") || Objects.equals(value.toString(), "")){
                object.put(key, "not populated");
            }

        }
      return object;
    }


}
