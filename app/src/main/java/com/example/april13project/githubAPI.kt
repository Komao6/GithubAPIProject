package com.example.april13project

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface githubAPI {


    @POST("/users/{username}")
    fun getUser(

        @Path("username") userName : String

    ): Call<UserModule>

}