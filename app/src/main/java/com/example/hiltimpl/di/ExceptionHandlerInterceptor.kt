package com.example.hiltimpl.di

import okhttp3.Interceptor
import okhttp3.Response

class ExceptionHandlerInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}