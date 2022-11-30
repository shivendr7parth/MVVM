package com.example.mvvm.data.api

import com.example.mvvm.data.models.UserModelItem
import com.google.gson.JsonObject
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface GithubService {

    @GET("users")
    suspend fun getUsers() : Response

    @GET("search/users")
    suspend fun searchUser(@Query("q") name:String) : Response

}