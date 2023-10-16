package com.example.finalstudent;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("user/login")
    Call<ResponseDTO<UserDTO>> loginUser(@Body LoginRequest loginRequest);

    @POST("/attendance/enter")
    Call<ResponseBody> registerEnterTime(@Header("Authorization") String token);

    @POST("/attendance/exit")
    Call<ResponseBody> registerExitTime(@Header("Authorization") String token);


    @GET("igiveyougps")
    Call<ResponseBody> getGPS();


    @GET("vod/board-list")
    Call<ResponseBody> getVodBoard();

    @GET("trytogetphotofromserver")
    Call<ResponseBody> getPhotoKids(@Query("userBus") String userBus);
    @POST("sms/send")
    Call<ResponseBody> sendSMS(@Body MessageDTO messageDTO);

}
