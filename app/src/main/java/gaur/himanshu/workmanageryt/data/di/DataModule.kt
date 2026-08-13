package gaur.himanshu.workmanageryt.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gaur.himanshu.workmanageryt.data.remote.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create()) //this will convert Our json data into object
            .build()
    }

    //we will add implementation for our Apiservice
    @Provides
    fun provideApiservice(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }
}