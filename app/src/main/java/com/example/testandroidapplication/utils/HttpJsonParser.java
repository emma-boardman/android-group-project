package com.example.testandroidapplication.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.util.Log;

import com.example.testandroidapplication.objects.Venue;

public class HttpJsonParser {

    private static InputStream is = null;
    private static JSONObject jObj = null;
    private static String json = "";

    // function get json from url
    // by making HTTP POST or GET method
    public JSONObject makeHttpRequest(String url, String method,
                                      Map<String, String> params) {

        try {
            Uri.Builder builder = new Uri.Builder();
            URL urlObj;
            String encodedParams = "";
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    builder.appendQueryParameter(entry.getKey(), entry.getValue());
                }
            }
            if (builder.build().getEncodedQuery() != null) {
                encodedParams =  builder.build().getEncodedQuery();

            }
            HttpURLConnection urlConnection;
            if ("GET".equals(method)) {
                url = url + "?" + encodedParams;
                urlObj = new URL(url);
                urlConnection = (HttpURLConnection) urlObj.openConnection();
                urlConnection.setRequestMethod(method);


            } else {
                urlObj = new URL(url);
                urlConnection = (HttpURLConnection) urlObj.openConnection();
                urlConnection.setRequestMethod(method);
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                assert encodedParams != null;
                urlConnection.setRequestProperty("Content-Length", String.valueOf(encodedParams.getBytes().length));
                urlConnection.getOutputStream().write(encodedParams.getBytes());
            }


            urlConnection.connect();
            is = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            is.close();
            json = sb.toString();
            Log.i("tag received data", "["+json+"]");
            Log.i("tag sent params", "["+encodedParams+"]");

            jObj = new JSONObject(json);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        } catch (Exception e) {
            Log.e("Exception", "Error parsing data " + e.toString());
        }

        return jObj;

    }

    JSONObject makeHttpPost(JSONObject jsonObject) throws IOException, JSONException {

        URL urlObj = new URL("http://40414669.wdd.napier.ac.uk/inc/createVenueProfile.php");

        HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        conn.connect();

        OutputStream os = conn.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        osw.write(jsonObject.toString());
        osw.flush();
        osw.close();

        is = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        is.close();
        json = sb.toString();
        Log.i("tag received data", "["+json+"]");
        Log.i("tag sent params", "["+jsonObject.toString()+"]");

        jObj = new JSONObject(json);

        return jObj;

    }


    JSONObject buildJsonObject(Venue venue) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("User_Id", venue.getUser().getUserID());
        jsonObject.accumulate("User_Name", venue.getUser().getName());
        jsonObject.accumulate("Email", venue.getUser().getEmail());
        jsonObject.accumulate("Profile_Picture", venue.getUser().getProfileImage());
        jsonObject.accumulate("Facebook", venue.getUser().getFacebookLink());
        jsonObject.accumulate("Instagram", venue.getUser().getInstagramLink());
        jsonObject.accumulate("Twitter", venue.getUser().getTwitterLink());
        jsonObject.accumulate("Website", venue.getUser().getWebPageLink());
        jsonObject.accumulate("Tagline", venue.getUser().getTagLine());
        jsonObject.accumulate("Description", venue.getUser().getTagLine());
        jsonObject.accumulate("Location", venue.getUser().getLocation());
        jsonObject.accumulate("Address1", venue.getAddress1());
        jsonObject.accumulate("PostCode", venue.getPostcode());
        jsonObject.accumulate("Phone_Number", venue.getPhoneNumber());

        return jsonObject;

    }

}