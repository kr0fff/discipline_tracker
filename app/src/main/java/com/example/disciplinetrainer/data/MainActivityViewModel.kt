package com.example.disciplinetrainer.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

data class QuotesUiState(
    val isLoading: Boolean = true,
    val quotes: List<Quote> = emptyList(),
    val error: String? = null
)

class MainActivityViewModel : ViewModel() {
    private var _quotesUiState = MutableStateFlow(QuotesUiState())
    val quotesUiState: StateFlow<QuotesUiState> = _quotesUiState.asStateFlow()

    init {
        getQuotesByQuery()
    }

    fun getQuotesByQuery() {
        viewModelScope.launch {
            _quotesUiState.update {
                try {
                    it.copy(quotes = AppContainer().getQuotes(), isLoading = false)
                } catch (e: IOException) {
                    it.copy(error = "Network error")
                } catch (e: HttpException) {
                    it.copy(error = "Server error")
                }
            }
        }
    }
}