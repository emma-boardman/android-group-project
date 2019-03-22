package com.example.testandroidapplication.helper;


/**
 * Created by Abhi on 19 Jun 2017 019.
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import android.util.JsonWriter;

import android.net.Uri;
import android.util.Log;

import com.example.testandroidapplication.objects.Venue;

public class HttpJsonParser {

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";
    HttpURLConnection urlConnection = null;
    JsonWriter writer;

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
                urlConnection.setRequestProperty("Content-Length", String.valueOf(encodedParams.getBytes().length));
                urlConnection.getOutputStream().write(encodedParams.getBytes());
            }


            urlConnection.connect();
            is = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
            Log.i("tagreceiveddata", "["+json+"]");
            Log.i("tagsentparams", "["+encodedParams+"]");

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

        // return JSON String
        return jObj;


    }

    public JSONObject makeHttpPost(String url, JSONObject jsonObject) throws IOException, JSONException {

        URL urlObj = new URL(url);

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
            sb.append(line + "\n");
        }
        is.close();
        json = sb.toString();
        Log.i("tagreceiveddata", "["+json+"]");
        Log.i("tagsentparams", "["+jsonObject.toString()+"]");

        jObj = new JSONObject(json);

        return jObj;

    }


    public JSONObject buildJsonObject(Venue venue) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("User_Id", venue.getUser().getUserID());
        jsonObject.accumulate("User_Name", venue.getUser().getName());
        jsonObject.accumulate("Email", venue.getUser().getEmail());
        jsonObject.accumulate("Password", venue.getUser().getPassword());
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