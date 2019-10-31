package com.twisac.apps.amazin.networking;

import com.twisac.apps.amazin.models.request.LoginRequest;
import com.twisac.apps.amazin.models.request.RegisterRequest;
import com.twisac.apps.amazin.models.response.Login;
import com.twisac.apps.amazin.models.response.Reply;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiInterface {
    //url points

    //login
    @POST("/users/login/")
    Call<Login> postLogin(@Body LoginRequest loginRequest);

    //register user
    @POST("/users/create_user/")
    Call<Reply> postRegister(@Body RegisterRequest registerRequest);



    }

