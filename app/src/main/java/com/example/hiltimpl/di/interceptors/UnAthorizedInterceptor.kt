package com.example.hiltimpl.di.interceptors

import android.util.Log
import com.example.hiltimpl.di.events.NetworkEvents
import okhttp3.Interceptor
import okhttp3.Response
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class UnAthorizedInterceptor @Inject constructor(

)
    : Interceptor {

    companion object{
        private const val TAG = "UnAthorizedInterceptor"
    }
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val response = chain.proceed(request)
        var msg=""
        when (response.code) {
            401->{msg="شما اجازه ورود ندارید"
                EventBus.getDefault().post(NetworkEvents.UN_AUTHORIZED_EVENT)
            }
            200->{msg="با موفقیت جواب دریافت شد"
                EventBus.getDefault().post(NetworkEvents.SUCCESS_EVENT)
            }

        }
        Log.d(TAG, "intercept: $msg")
        return response
    }

}