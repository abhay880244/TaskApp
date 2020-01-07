package com.abhay.taskapp.service

import com.abhay.taskapp.models.User
import retrofit2.Call
import retrofit2.http.GET

interface Service {

    @GET("cardData")
    fun getUsers(): Call<List<User>>
}