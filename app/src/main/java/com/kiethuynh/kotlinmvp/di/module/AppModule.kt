package com.kiethuynh.kotlinmvp.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import ch.smartlink.framework.data.retrofit.API
import com.kiethuynh.kotlinmvp.common.Constants
import com.kiethuynh.kotlinmvp.data.AppDataManager
import com.kiethuynh.kotlinmvp.data.DataManager
import com.kiethuynh.kotlinmvp.data.datasource.DataSource
import com.kiethuynh.kotlinmvp.data.datasource.RemoteDataSource
import com.kiethuynh.kotlinmvp.data.preferences.AppPreferencesHelper
import com.kiethuynh.kotlinmvp.data.preferences.PreferencesHelper
import com.kiethuynh.kotlinmvp.di.qualifier.ApplicationContext
import com.kiethuynh.kotlinmvp.di.qualifier.BodyLoggingInterceptor
import com.kiethuynh.kotlinmvp.di.qualifier.HeaderLoggingInterceptor
import com.kiethuynh.kotlinmvp.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by kiethuynh on 14/09/2017
 */

@Module
class AppModule(private val application: Application) {

    @Provides
    @ApplicationScope
    @ApplicationContext
    fun provideContext() = application.applicationContext!!

    @Provides
    @ApplicationScope
    fun provideDataManager(appDataManager: AppDataManager): DataManager = appDataManager

    @Provides
    @ApplicationScope
    fun providePreferencesHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper = appPreferencesHelper

    @Provides
    @ApplicationScope
    fun provideDataSource(remoteDataSource: RemoteDataSource): DataSource = remoteDataSource

    @Provides
    fun provideAPI(retrofit: Retrofit): API {
        return retrofit.create(API::class.java)
    }

    @Provides
    fun provideHttpClient(@HeaderLoggingInterceptor headerLogging: HttpLoggingInterceptor,
                          @BodyLoggingInterceptor bodyLogging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(30000, TimeUnit.MILLISECONDS)
                .readTimeout(30000, TimeUnit.MILLISECONDS)
                .writeTimeout(30000, TimeUnit.MILLISECONDS)
                .addInterceptor(headerLogging)
                .addInterceptor(bodyLogging)
                .build()
    }

    @Provides
    @HeaderLoggingInterceptor
    fun provideHeaderLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.HEADERS }
    }

    @Provides
    @BodyLoggingInterceptor
    fun provideBodyLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.PREFERENCES_NAME, Context.MODE_PRIVATE)
    }
}