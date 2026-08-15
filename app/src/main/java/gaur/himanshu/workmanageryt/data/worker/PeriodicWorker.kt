package gaur.himanshu.workmanageryt.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import gaur.himanshu.workmanageryt.data.local.QuoteDao
import gaur.himanshu.workmanageryt.data.mappers.toDomain
import gaur.himanshu.workmanageryt.data.model.QuotesDto
import gaur.himanshu.workmanageryt.data.remote.ApiService

const val PERIODIC_WORK="PERIODIC_WORK"
class PeriodicWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workpara: WorkerParameters,
    private val apiService: ApiService,
    private val quoteDao: QuoteDao
): CoroutineWorker(context,workpara) {
    override suspend fun doWork(): Result {
        /**
         * apiService.getQuotes()  this will give us QuoteDto
         *and we wanted to Convert this QuoteDto to Domain Level Model Quote
         * For that we will create a Mapper class
         */
        return try {
            val response=apiService.getQuotes().toDomain(PERIODIC_WORK)
            quoteDao.inserQuote(response)
            Result.success()
        }catch (e: Exception){
            Result.failure()
        }

    }
}