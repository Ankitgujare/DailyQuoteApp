package gaur.himanshu.workmanageryt.data.remote

import gaur.himanshu.workmanageryt.data.model.QuotesDto
import retrofit2.http.GET

interface ApiService {
    //https://dummyjson.com/quotes/random
    @GET("/quotes/random")
    suspend fun getQuotes(): QuotesDto

    //todo what is the Apiservice Call and what it will Consist
    // What is the Diffrence Between Apiservices and Retrofit Instance

}