package com.ibarra.location.di

import com.google.gson.GsonBuilder
import com.ibarra.location.BuildConfig
import com.ibarra.location.data.remote.IbarraLocationApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 15L
private const val WRITE_TIMEOUT = 15L
private const val READ_TIMEOUT = 15L

val NetworkModule = module {

    single<Retrofit>(named("mainService")) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
    }
    single { Cache(androidContext().cacheDir, 10L * 1024 * 1024) }

    single { GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create() }

    single{
        OkHttpClient.Builder().apply {
            cache(get())
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            })
        }.build()
    }
    single<IbarraLocationApi> { get<Retrofit>(named("mainService")).create(IbarraLocationApi::class.java) }
}