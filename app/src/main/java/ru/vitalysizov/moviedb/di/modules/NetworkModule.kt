package ru.vitalysizov.moviedb.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.vitalysizov.moviedb.BuildConfig
import ru.vitalysizov.moviedb.data.remote.network.api.IMovieDbApiService
import ru.vitalysizov.moviedb.data.remote.network.interceptors.AddDefaultParametersInterceptor
import ru.vitalysizov.moviedb.data.remote.network.interceptors.IncludeImageLanguageInterceptor
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        const val BASE_URL = BuildConfig.BASE_API_URL
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        addDefaultParametersInterceptor: AddDefaultParametersInterceptor,
        includeImageLanguageInterceptor: IncludeImageLanguageInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(addDefaultParametersInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(includeImageLanguageInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }
    }

    @Provides
    @Singleton
    fun provideCallAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideConvertFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        callAdapter: CallAdapter.Factory,
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(callAdapter)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDbApiService(retrofit: Retrofit): IMovieDbApiService {
        return retrofit.create(IMovieDbApiService::class.java)
    }

}