package com.joblogic.assessment.di

import com.joblogic.assessment.BuildConfig
import com.joblogic.assessment.api.endpoints.CallEndPoints
import com.joblogic.assessment.repository.CallRepository
import com.moczul.ok2curl.CurlInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

val networkModule = module {
    fun provideOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            chain.proceed(requestBuilder.build())
        }.addInterceptor(CurlInterceptor { message -> Timber.d(message) })

        return httpClient.build()
    }

    single { provideOkHttpClient() }



    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        provideRetrofit(get())
    }



    fun provideCallApi(retrofit: Retrofit): CallEndPoints {
        return retrofit.create(CallEndPoints::class.java)
    }

    single { provideCallApi(get()) }

    single { CallRepository( get() , get() ) }

}
