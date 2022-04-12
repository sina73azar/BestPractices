package com.example.hiltimpl.repo


import com.example.hiltimpl.data.api.ApiPhoto
import com.example.hiltimpl.domain.Photo
import retrofit2.Response
import javax.inject.Inject


class PhotoRepo @Inject constructor(
    private val apiPhoto: ApiPhoto
){

    suspend fun getAllPhotos(): Response<List<Photo>> = apiPhoto.getAllPhotos()
}