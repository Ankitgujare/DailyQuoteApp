package gaur.himanshu.workmanageryt.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import gaur.himanshu.workmanageryt.data.local.QuoteDao
import gaur.himanshu.workmanageryt.data.mappers.toDomain
import gaur.himanshu.workmanageryt.data.remote.ApiService



const val ONE_TIME_WORK="ONE_TIME_WORK"
@HiltWorker
class FetchWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerpara: WorkerParameters,
    private val apiService: ApiService,
    private val quoteDao: QuoteDao
):CoroutineWorker(context,workerpara) {

    override suspend fun doWork(): Result {
     return try {
          /**
           * apiService.getQuotes()  this will give us QuoteDto
           *and we wanted to Convert this QuoteDto to Domain Level Model Quote
           * For that we will create a Mapper class
           */
          val response=apiService.getQuotes().toDomain(ONE_TIME_WORK)
          quoteDao.inserQuote(response)
          Result.success()

      }catch (e: Exception){
          Result.failure()
      }
    }

}