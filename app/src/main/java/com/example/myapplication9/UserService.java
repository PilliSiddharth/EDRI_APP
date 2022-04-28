package com.example.myapplication9;

import retrofit2.http.Body;
import retrofit2.Call;
import retrofit2.http.POST;

public interface UserService {
   @POST("/")
   Call<UserResponse> saveUsers(@Body UserRequest userRequest);
}
