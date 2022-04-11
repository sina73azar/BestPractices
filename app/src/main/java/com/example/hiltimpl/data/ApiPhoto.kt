package com.example.hiltimpl.data

import retrofit2.Response
import retrofit2.http.GET


interface ApiPhoto {


    @GET("/photos")
    suspend fun getAllPhotos():Response<List<Photo>>
}