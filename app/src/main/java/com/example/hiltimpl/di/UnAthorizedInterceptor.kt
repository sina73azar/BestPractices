package com.example.hiltimpl.di

import android.util.Log
import com.example.hiltimpl.di.events.SuccessEvent
import com.example.hiltimpl.di.events.UnauthorizedEvent
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class UnAthorizedInterceptor @Inject constructor(
    private val unauthorizedEvent: UnauthorizedEvent,
    private val successEvent: SuccessEvent
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
                EventBus.getDefault().post(unauthorizedEvent)
            }
            200->{msg="با موفقیت جواب دریافت شد"
                EventBus.getDefault().post(successEvent)
            }

        }
        Log.d(TAG, "intercept: $msg")
        return response
    }

}