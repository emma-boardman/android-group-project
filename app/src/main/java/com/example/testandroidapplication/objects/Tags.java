package com.example.testandroidapplication.objects;

import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Tags implements JsonWritable {

        private final Map<String, List<String>> tags = new HashMap<>();

        public void addTag(String category, String value) {
            if (tags.keySet().contains(category)) {
                tags.get(category).add(value);
            } else {
                List<String> values = new ArrayList<>();
                values.add(value);
                tags.put(category, values);
            }
        }
        public List<String> getCategories() {
            return new ArrayList<>(tags.keySet());
        }

        public List<String> getTag(String category) {
            if (tags.containsKey(category)) return tags.get(category);
            else return Collections.emptyList();
        }

        public static Tags fromJson(@Nullable final JSONObject jsonObject) throws JSONException {
            final Tags tags = new Tags();
            if (jsonObject != null) {
                Iterator<String> keys = jsonObject.keys();
                while (keys.hasNext()) {
                    String category = keys.next();
                    JSONArray values = jsonObject.getJSONArray(category);
                    for (int i = 0; i < values.length(); i++ ) {
                        String value = values.getString(i);
                        tags.addTag(category, value);
                    }
                }
            }
            return tags;
        }

        public boolean hasTags() {
            return !tags.isEmpty();
        }

        @Override
        public JSONObject toJson() throws JSONException {
            JSONObject tags = new JSONObject();
            List<String> categories = this.getCategories();
            if (!categories.isEmpty()) {
                for (String category : categories) {
                    tags.accumulate(category, this.getTag(category));
                }
            }
            return tags;
        }
}

