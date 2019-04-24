package com.example.testandroidapplication.objects;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.testandroidapplication.utils.JsonUtils.addStringToJson;

public class User implements Parcelable, JsonWritable {

    private String userID;
    private String name;
    private String email;


    public User(String userID, String name, String email) {
        this.userID = userID;
        this.name = name;
        this.email = email;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //write object values to parcel for storage
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userID);
        dest.writeString(name);
        dest.writeString(email);
    }

    //constructor used for parcel
    public User(Parcel parcel) {
        userID = parcel.readString();
        name = parcel.readString();
        email = parcel.readString();
    }

    //creator - used when un-parceling our parcle (creating the object)
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {

        @Override
        public User createFromParcel(Parcel parcel) {
            return new User(parcel);
        }

        @Override
        public User[] newArray(int size) {
            return new User[0];
        }
    };

    //return hashcode of object
    public int describeContents() {
        return hashCode();
    }

    @Override
    public JSONObject toJson() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        addStringToJson(jsonObject, "User_Id", getUserID());
        addStringToJson(jsonObject, "User_Name", getName());
        addStringToJson(jsonObject, "User_Email", getEmail());
        return jsonObject;
    }
}
