package gaur.himanshu.workmanageryt.domain.usecases

import gaur.himanshu.workmanageryt.domain.reposatory.QuotesReposatory
import javax.inject.Inject

class SetupPeriodicWokRequest @Inject constructor(
    private val quotesReposatory: QuotesReposatory
) {

    operator fun invoke()=quotesReposatory.setPeriodicWorkRequest()
}