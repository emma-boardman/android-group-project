package com.example.testandroidapplication.objects;

import org.json.JSONException;
import org.json.JSONObject;

public interface JsonWritable {
    JSONObject toJson() throws JSONException;
}
