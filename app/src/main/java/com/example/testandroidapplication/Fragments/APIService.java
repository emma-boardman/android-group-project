package com.example.testandroidapplication.Fragments;

import com.example.testandroidapplication.Notifications.MyResponse;
import com.example.testandroidapplication.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAZGfsOtQ:APA91bEAFCLcSbq-31gSQrx_M3nnJIHdATmEKEXJajzSZq3KHKr7swJQoXsLpylYYHGPp3UPoxMRs6H7QXslBk3PE0OXIC0yJgyrOF5jITgzTP6S3_QGZ2FKTt7PCvX4H-1Xb1wFUWgJ"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);

}
