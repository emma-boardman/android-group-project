package com.example.testandroidapplication.utils;

import android.support.annotation.Nullable;

import com.google.android.gms.common.util.Strings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public final class JsonUtils {

    private JsonUtils() {}

    /**
     * Add a the value to the JSONObject under the key name, only if the value is not
     * null, empty or only whitespace.
     */
    public static void addStringToJson(JSONObject jsonObject, String name, @Nullable String value) throws JSONException {
        if (!Strings.isEmptyOrWhitespace(value)) {
            jsonObject.accumulate(name, value);
        }
    }

    /**
     * Return the string value with the given name from the JSONObject, or null
     * if the JSON object does not contain that name.
     */
    @Nullable
    public static String getStringOrNull(JSONObject jsonObject, String name) throws JSONException {
        if (jsonObject.has(name)) return jsonObject.getString(name);
        else return null;
    }

    /**
     * Return the JSONArray value with the given name from the JSONObject, or null
     * if the JSON object does not contain that name.
     */
    @Nullable
    public static JSONArray getJSONArrayOrNull(JSONObject jsonObject, String name) throws JSONException {
        if (jsonObject.has(name)) return jsonObject.getJSONArray(name);
        else return null;
    }

    /**
     * Return the JSONObject value with the given name from the JSONObject, or null
     * if the JSON object does not contain that name.
     */
    @Nullable
    public static JSONObject getJSONObjectOrNull(JSONObject jsonObject, String name) throws JSONException {
        if (jsonObject.has(name)) return jsonObject.getJSONObject(name);
        else return null;
    }

    /**
     * Merge the contents all given JSONObjects into a single JSONObject. The objects are merged in
     * order, and if any entries are duplicated, then the last one wins.
     */
    public static JSONObject merge(final JSONObject... objects) throws JSONException {
        final JSONObject result = new JSONObject();
        for (final JSONObject object : objects) {
            final Iterator<String> keys = object.keys();
            while (keys.hasNext()) {
                final String key = keys.next();
                result.put(key, object.get(key));
            }
        }
        return result;
    }
}
