package com.example.hiltimpl.data.repo

import com.example.hiltimpl.data.ApiPhoto
import com.example.hiltimpl.data.Photo
import retrofit2.Response
import javax.inject.Inject


class PhotoRepo @Inject constructor(
    private val apiPhoto: ApiPhoto
){

    suspend fun getAllPhotos():Response<List<Photo>> = apiPhoto.getAllPhotos()
}