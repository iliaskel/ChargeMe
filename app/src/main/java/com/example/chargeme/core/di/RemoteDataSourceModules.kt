package com.example.chargeme.core.di

import com.example.chargeme.data.remotedatasource.api.VehiclesApi
import com.example.chargeme.data.remotedatasource.datasource.VehiclesRemoteDataSource
import com.example.chargeme.data.remotedatasource.datasource.VehiclesRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val URL = "https://api.jsonbin.io"

@InstallIn(SingletonComponent::class)
@Module
class RetrofitApiModule {
    @Singleton
    @Provides
    fun provideRetrofitApiModule(): VehiclesApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URL)
            .client(getHttpClientInterceptor())
            .build()
            .create(VehiclesApi::class.java)
    }
}

@InstallIn(SingletonComponent::class)
@Module
abstract class VehiclesRemoteDataSourceModule {
    @Singleton
    @Binds
    abstract fun provideVehiclesRemoteDataSourceModule(vehiclesRemoteDataSourceImpl: VehiclesRemoteDataSourceImpl): VehiclesRemoteDataSource
}

private fun getHttpClientInterceptor(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    val httpClient = OkHttpClient.Builder()

    return httpClient.addInterceptor(logging).build()
}