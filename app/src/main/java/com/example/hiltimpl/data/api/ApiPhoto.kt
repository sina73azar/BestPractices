package com.example.hiltimpl.data.api

import com.example.hiltimpl.domain.Photo
import retrofit2.Response
import retrofit2.http.GET


interface ApiPhoto {


    @GET("/photos")
    suspend fun getAllPhotos():Response<List<Photo>>
}