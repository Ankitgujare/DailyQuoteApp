package gaur.himanshu.workmanageryt.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gaur.himanshu.workmanageryt.data.local.QuoteDao
import gaur.himanshu.workmanageryt.data.local.QuoteDatabase
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


    @Singleton
    @Provides
    fun provideQuoteDatabase(@ApplicationContext context: Context): QuoteDatabase{
        return QuoteDatabase.getInstance(context)
    }


    @Provides
    fun provideQuoteDao(quoteDatabase: QuoteDatabase): QuoteDao{
        return quoteDatabase.getQuoteDao()
    }
}