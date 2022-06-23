package com.example.movietime.di

import android.content.Context
import com.example.movietime.data.Dispatchers
import com.example.movietime.data.glide.ImageLoader
import com.example.movietime.data.remote.MoviesReviewsApi
import com.example.movietime.data.repository.MoviesRepositoryImpl
import com.example.movietime.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun provideMoviesReviewsApi(): MoviesReviewsApi {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.nytimes.com/svc/movies/v2/reviews/").client(
                OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }).build()
            ).build().create(MoviesReviewsApi::class.java)
    }

    @Provides
    fun provideDispatchers(): Dispatchers = Dispatchers.Io()


    @Provides
    fun provideImageLoader(@ApplicationContext context: Context) : ImageLoader = ImageLoader.Loader(context)


    @Provides
    fun provideMoviesRepository(
        moviesReviewsApi: MoviesReviewsApi,
        dispatcher: Dispatchers
    ): MoviesRepository =
        MoviesRepositoryImpl(moviesReviewsApi = moviesReviewsApi, dispatcher = dispatcher)

}

