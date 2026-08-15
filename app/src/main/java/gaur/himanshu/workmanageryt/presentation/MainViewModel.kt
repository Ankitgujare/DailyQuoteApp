package gaur.himanshu.workmanageryt.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gaur.himanshu.workmanageryt.domain.model.Quote
import gaur.himanshu.workmanageryt.domain.usecases.GetAllQuoteFromDbUseCase
import gaur.himanshu.workmanageryt.domain.usecases.GetQuoteUseCase
import gaur.himanshu.workmanageryt.domain.usecases.SetupPeriodicWokRequest
import kotlinx.coroutines.flow.SharingCommand
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllQuotefromDbUseCase: GetAllQuoteFromDbUseCase,
    private val getQuoteUsecase: GetQuoteUseCase,
    private val setPeriodicWorkUseCase: SetupPeriodicWokRequest
): ViewModel() {

    val uistate=getAllQuotefromDbUseCase.invoke()
        .map { Uistates(it) }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            Uistates(emptyList())
        )

    init {
        setPeriodicWorkUseCase.invoke()
    }

    fun getQuote()=getQuoteUsecase.invoke()
}


data class Uistates(
    val data:List<Quote>
)