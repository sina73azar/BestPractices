package com.example.hiltimpl.repo

import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import javax.inject.Inject

class PhotoRepo @Inject constructor(
    private val requestQueue: RequestQueue,
) {

    fun getAllPhotos(request:StringRequest){
        requestQueue.add(request)
    }


}