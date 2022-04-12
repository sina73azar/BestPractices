package com.example.hiltimpl.di.modules

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.hiltimpl.data.api.ApiPhoto
import com.example.hiltimpl.di.interceptors.UnAthorizedInterceptor
import com.example.hiltimpl.repo.PhotoRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideUnathorizedInterceptor(): UnAthorizedInterceptor {
        return UnAthorizedInterceptor()
    }

    @Singleton
    @Provides
    fun provideOkHttp(unAthorizedInterceptor: UnAthorizedInterceptor):OkHttpClient{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        val builder = OkHttpClient.Builder()

        builder.addInterceptor(unAthorizedInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", UUID.randomUUID().toString())
                    .addHeader("SINA_SINA", "SINA_SINA")
                    .addHeader("SINA_SINA", "sSINA_SINA")
                    .addHeader("SINA_SINA", "sSINA_SINA")
                    .addHeader("SINA_SINA", "sSINA_SINA")
                    .build()
                return@addInterceptor chain.proceed(newRequest)
            }.addInterceptor(interceptor)
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client:OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiPhoto(retrofit: Retrofit): ApiPhoto {
       return retrofit.create(ApiPhoto::class.java)
    }


    @Singleton
    @Provides
    fun provideRequestQueue(@ApplicationContext appContext: Context):RequestQueue{
        return Volley.newRequestQueue(appContext)
    }



}