package com.globallogic.pokemon.di.module
import com.globallogic.pokemon.data.network.ApiServices
import com.globallogic.pokemon.data.prefrences.AppPrefrencesImpl
import com.globallogic.pokemon.data.prefrences.AppPrefrencesHelper
import com.example.bilerplatemvvm.BuildConfig
import com.globallogic.pokemon.data.repository.Repository
import com.globallogic.pokemon.data.repository.RepositoryImpl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    fun provideBaseUrl() =BuildConfig.BASE_URL

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }
    @Provides
    @Singleton
    fun provideOkHttp(logging: HttpLoggingInterceptor):OkHttpClient{
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.retryOnConnectionFailure(true)
        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(logging)
        }
        return okHttpClient.build()
    }
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient,baseUrl:String):Retrofit=
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
//            .addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiServices =retrofit.create(ApiServices::class.java)


    @Provides
    @Singleton
    fun prividePrefrences(appPrefrencesImpl: AppPrefrencesImpl): AppPrefrencesHelper =appPrefrencesImpl


    @Provides
    @Singleton
    fun provideRepository(repositoryImpl: RepositoryImpl): Repository = repositoryImpl

    @Provides
    @Singleton
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO
}