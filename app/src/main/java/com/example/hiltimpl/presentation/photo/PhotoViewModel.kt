package com.example.hiltimpl.presentation.photo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.hiltimpl.domain.Photo
import com.example.hiltimpl.repo.PhotoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val photoRepo: PhotoRepo
) :ViewModel(){
    fun getAllPhotos() = liveData<Response<List<Photo>>> {
        emit(photoRepo.getAllPhotos())
    }


}