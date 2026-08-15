package gaur.himanshu.workmanageryt.data.reposatory

import androidx.compose.ui.unit.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import gaur.himanshu.workmanageryt.data.local.QuoteDao
import gaur.himanshu.workmanageryt.data.worker.FetchWorker
import gaur.himanshu.workmanageryt.data.worker.PeriodicWorker
import gaur.himanshu.workmanageryt.domain.model.Quote
import gaur.himanshu.workmanageryt.domain.reposatory.QuotesReposatory
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.TimeUnit

class QuoteRepoImp(
    private val workManager: WorkManager,
    private val quoteDao: QuoteDao
): QuotesReposatory {
    override fun getQuotes() {

        val networkConstrains= androidx.work.Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workrequest= OneTimeWorkRequestBuilder<FetchWorker>()
            .setConstraints(networkConstrains)
            .build()
        workManager.enqueue(workrequest)
        TODO("Not yet implemented")
    }

    override fun getAllQuotes(): Flow<List<Quote>> =quoteDao.getAlllQuotes()

    override fun setPeriodicWorkRequest() {
        val networkConstrains= androidx.work.Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest= PeriodicWorkRequest.Builder(
            PeriodicWorker::class.java,
            15, TimeUnit.MINUTES
        )
            .setConstraints(networkConstrains)
            .build()

        workManager.enqueueUniquePeriodicWork(
            "unique_work",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
            )

    }
}