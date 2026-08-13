package gaur.himanshu.workmanageryt.domain.reposatory

import gaur.himanshu.workmanageryt.data.model.QuotesDto
import gaur.himanshu.workmanageryt.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuotesReposatory {



    fun getQuotes()

    fun getAllQuotes(): Flow<List<Quote>>

    fun setPeriodicWorkRequest()

}